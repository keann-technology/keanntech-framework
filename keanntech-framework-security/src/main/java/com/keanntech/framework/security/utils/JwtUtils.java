package com.keanntech.framework.security.utils;

import com.keanntech.framework.security.domain.UserDetail;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private Long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenExpiration;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public UserDetail getUserDetailFromToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        String username = claims.getSubject();
        String userId = claims.get(CLAIM_KEY_USER_ID).toString();
        String authorities = claims.get(CLAIM_KEY_AUTHORITIES).toString();
        Set<GrantedAuthority> grantedAuthorities = Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return UserDetail.builder()
                .id(Long.parseLong(userId))
                .userName(username)
                .authorities(grantedAuthorities)
                .build();
    }

    public UserDetail getUserDetailFromAuthContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetail) authentication.getPrincipal();
    }

    public String generateAccessToken(UserDetail userDetail) {

        String token = generateToken(userDetail, accessTokenExpiration);
        tokenMap.put(userDetail.getId(), token);
        return token;
    }

    public String generateRefreshToken(UserDetail userDetail) {
        String refreshToken = generateToken(userDetail, refreshTokenExpiration);
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
                    .setSigningKey(secret)
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
            expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(refreshToken).getBody().getExpiration();
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
        final long userId = getUserDetailFromAuthContext().getId();
        final String username = getUserDetailFromAuthContext().getUsername();
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
        return claims;
    }

    private String generateToken(UserDetail userDetail, long expiration) {
        Map<String, Object> claims = generateClaims(userDetail);
        String username = userDetail.getUsername();
        String userId = String.valueOf(userDetail.getId());
        claims.put(CLAIM_KEY_TENANT_CODE, userDetail.getTenantCode());
        claims.put(CLAIM_KEY_ADMIN_TYPE, userDetail.getAdminType());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setId(userId)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

//    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";
//
//    private static final String CLAIM_KEY_USER_ID = "user_id";
//    private static final String CLAIM_KEY_AUTHORITIES = "scope";
//    private static final String CLAIM_KEY_TENANT_CODE = "tenant_code";
//    private static final String CLAIM_KEY_ADMIN_TYPE = "admin_type";
//
//    private Map<Long, String> tokenMap = new ConcurrentHashMap<>(32);
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long access_token_expiration;
//
//    @Value("${jwt.expiration}")
//    private Long refresh_token_expiration;
//
//    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
//
//    public UserDetails getUserFromToken(String token) {
//        UserDetails userDetail;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            long userId = getUserIdFromToken(token);
//            String username = claims.getSubject();
//            List<String> roleCodeList = (List) claims.get(CLAIM_KEY_AUTHORITIES);
//            String tenantCode = (String) claims.get(CLAIM_KEY_TENANT_CODE);
//            Integer adminType = Integer.valueOf(String.valueOf(claims.get(CLAIM_KEY_ADMIN_TYPE)));
//            List<Roles> roleList = new ArrayList<>();
//            if (Objects.nonNull(roleCodeList)) {
//                roleCodeList.forEach(role -> {
//                    Roles roles = new Roles();
//                    roles.setRoleCode(role);
//                    roleList.add(roles);
//                });
//            }
//            userDetail = new UserDetails(userId, username, "", roleList, tenantCode, adminType);
//        } catch (Exception e) {
//            userDetail = null;
//        }
//        return userDetail;
//    }
//
//    public long getUserIdFromToken(String token) {
//        long userId;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            userId = Long.parseLong(String.valueOf(claims.get(CLAIM_KEY_USER_ID)));
//        } catch (Exception e) {
//            userId = 0;
//        }
//        return userId;
//    }
//
//    public String getUsernameFromToken(String token) {
//        String username;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            username = claims.getSubject();
//        } catch (Exception e) {
//            username = null;
//        }
//        return username;
//    }
//
//    public Date getCreatedDateFromToken(String token) {
//        Date created;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            created = claims.getIssuedAt();
//        } catch (Exception e) {
//            created = null;
//        }
//        return created;
//    }
//
//    public String generateAccessToken(UserDetails userDetail) {
//        Map<String, Object> claims = generateClaims(userDetail);
//        claims.put(CLAIM_KEY_AUTHORITIES, authoritiesToArray(userDetail.getAuthorities()));
//        claims.put(CLAIM_KEY_TENANT_CODE, userDetail.getTenantCode());
//        claims.put(CLAIM_KEY_ADMIN_TYPE, userDetail.getAdminType());
//        return generateAccessToken(userDetail.getUsername(), claims);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        Date expiration;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            expiration = claims.getExpiration();
//        } catch (Exception e) {
//            expiration = null;
//        }
//        return expiration;
//    }
//
//    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
//        final Date created = getCreatedDateFromToken(token);
//        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
//                && (!isTokenExpired(token));
//    }
//
//    public String refreshToken(String token) {
//        String refreshedToken;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            refreshedToken = generateAccessToken(claims.getSubject(), claims);
//        } catch (Exception e) {
//            refreshedToken = null;
//        }
//        return refreshedToken;
//    }
//
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        UserDetails userDetail = (UserDetails) userDetails;
//        final long userId = getUserIdFromToken(token);
//        final String username = getUsernameFromToken(token);
////        final Date created = getCreatedDateFromToken(token);
//        return (userId == userDetail.getId()
//                && username.equals(userDetail.getUsername())
//                && !isTokenExpired(token)
////                && !isCreatedBeforeLastPasswordReset(created, userDetail.getLastPasswordResetDate())
//        );
//    }
//
//    public String generateRefreshToken(UserDetails userDetail) {
//        Map<String, Object> claims = generateClaims(userDetail);
//        // 只授于更新 token 的权限
//        String roles[] = new String[]{JwtUtils.ROLE_REFRESH_TOKEN};
//        claims.put(CLAIM_KEY_AUTHORITIES, JSONUtil.toJSON(roles));
//        return generateRefreshToken(userDetail.getUsername(), claims);
//    }
//
//    public void putToken(Long userId, String token) {
//        tokenMap.put(userId, token);
//    }
//
//    public void deleteToken(Long userId) {
//        tokenMap.remove(userId);
//    }
//
//    public boolean containToken(Long userId, String token) {
//        if (userId != null && tokenMap.containsKey(userId) && tokenMap.get(userId).equals(token)) {
//            return true;
//        }
//        return false;
//    }
//    private Claims getClaimsFromToken(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(secret)
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            claims = null;
//        }
//        return claims;
//    }
//
//    private Date generateExpirationDate(long expiration) {
//        return new Date(System.currentTimeMillis() + expiration * 1000);
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
//        return (lastPasswordReset != null && created.before(lastPasswordReset));
//    }
//
//    private Map<String, Object> generateClaims(UserDetails userDetail) {
//        Map<String, Object> claims = new HashMap<>(16);
//        claims.put(CLAIM_KEY_USER_ID, userDetail.getId());
//        return claims;
//    }
//
//    private String generateAccessToken(String subject, Map<String, Object> claims) {
//        return generateToken(subject, claims, access_token_expiration);
//    }
//
//    private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
//        List<String> list = new ArrayList<>();
//        for (GrantedAuthority ga : authorities) {
//            list.add(ga.getAuthority());
//        }
//        return list;
//    }
//
//
//    private String generateRefreshToken(String subject, Map<String, Object> claims) {
//        return generateToken(subject, claims, refresh_token_expiration);
//    }
//
//
//
//    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(generateExpirationDate(expiration))
//                .compressWith(CompressionCodecs.DEFLATE)
//                .signWith(SIGNATURE_ALGORITHM, secret)
//                .compact();
//    }

}
