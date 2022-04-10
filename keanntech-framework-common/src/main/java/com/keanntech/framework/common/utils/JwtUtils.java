package com.keanntech.framework.common.utils;

import com.keanntech.framework.common.config.JwtConfig;
import com.keanntech.framework.common.domain.UserDetail;
import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 8:52 上午
 */
@Component
public class JwtUtils {

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_AUTHORITIES = "authorities";
    private static final String CLAIM_KEY_USERNAME = "userName";
    private static final String CLAIM_KEY_TENANT_CODE = "tenant_code";
    private static final String CLAIM_KEY_ADMIN_TYPE = "admin_type";
    private final Map<Long, String> tokenMap = new HashMap<>();
    private final Map<Long, String> refreshTokenMap = new HashMap<>();

    private JwtConfig jwtConfig;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /**
     * 通过TOKEN获取用户信息
     * @param token
     * @return
     */
    public UserDetail getUserDetailFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        String username = claims.getSubject();
        String userId = claims.get(CLAIM_KEY_USER_ID).toString();
        String authorities = claims.get(CLAIM_KEY_AUTHORITIES).toString();
        String tenantCode = claims.get(CLAIM_KEY_TENANT_CODE).toString();
        Integer adminType = Integer.valueOf(String.valueOf(claims.get(CLAIM_KEY_ADMIN_TYPE)));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (!StringUtils.isEmpty(authorities)) {
            grantedAuthorities = Arrays.stream(authorities.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
        return UserDetail.builder()
                .id(Long.parseLong(userId))
                .userName(username)
                .adminType(adminType)
                .tenantCode(tenantCode)
                .authorities(grantedAuthorities)
                .build();
    }

    public UserDetail getUserDetailFromAuthContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetail) authentication.getPrincipal();
    }

    public String generateAccessToken(UserDetail userDetail) {

        String token = generateToken(userDetail, jwtConfig.getAccessTokenExpiration());
        tokenMap.put(userDetail.getId(), token);
        return token;
    }

    public String generateRefreshToken(UserDetail userDetail) {
        String refreshToken = generateToken(userDetail, jwtConfig.getRefreshTokenExpiration());
        refreshTokenMap.put(userDetail.getId(), refreshToken);
        return refreshToken;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public Boolean isAccessTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        if (expiration == null) {
            return true;
        }
        return expiration.before(new Date());
    }

    public Boolean isRefreshTokenExpired(String refreshToken) {
        Date expiration;
        try {
            expiration = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(refreshToken).getBody().getExpiration();
        } catch (ExpiredJwtException e) {
            return true;
        }
        return expiration.before(new Date());
    }

    public String refreshToken(String refreshToken) {
        if (isRefreshTokenExpired(refreshToken)) {
            return null;
        }
        String token;
        synchronized (this) {
            final UserDetail userDetail = getUserDetailFromToken(refreshToken);
            if (userDetail == null) {
                return null;
            }
            Long userId = userDetail.getId();
            token = tokenMap.get(userId);
            if (isAccessTokenExpired(token)) {
                token = generateAccessToken(userDetail);
                tokenMap.put(userId, token);
            }
        }
        return token;
    }

    public boolean checkValidToken(String token, Long userId) {
        return tokenMap.containsKey(userId) && tokenMap.get(userId).equals(token);
    }

    public Boolean validateToken(String token, UserDetail userDetail) {
        final long userId = getUserDetailFromToken(token).getId();
        final String username = getUserDetailFromToken(token).getUsername();
        return (userId == userDetail.getId()
                && username.equals(userDetail.getUsername())
                && !isTokenExpired(token)
//                && !isCreatedBeforeLastPasswordReset(created, userDetail.getLastPasswordResetDate())
        );
    }

    public boolean removeToken(Long id) {
        return tokenMap.remove(id) != null && refreshTokenMap.remove(id) != null;
    }

    private Map<String, Object> generateClaims(UserDetail userDetail) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put(CLAIM_KEY_USER_ID, String.valueOf(userDetail.getId()));
        claims.put(CLAIM_KEY_AUTHORITIES, userDetail.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")));
        claims.put(CLAIM_KEY_USERNAME, userDetail.getUsername());
        claims.put(CLAIM_KEY_ADMIN_TYPE, userDetail.getAdminType());
        claims.put(CLAIM_KEY_TENANT_CODE, userDetail.getTenantCode());
        return claims;
    }

    private String generateToken(UserDetail userDetail, long expiration) {
        Map<String, Object> claims = generateClaims(userDetail);
        String username = userDetail.getUsername();
        String userId = String.valueOf(userDetail.getId());
        claims.put(CLAIM_KEY_TENANT_CODE, userDetail.getTenantCode());
        claims.put(CLAIM_KEY_ADMIN_TYPE, userDetail.getAdminType());
        return Jwts.builder()
                .setIssuer(jwtConfig.getIssuer())
                .setClaims(claims)
                .setSubject(username)
                .setId(userId)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, jwtConfig.getSecret())
                .compact();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

}
