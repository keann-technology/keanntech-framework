package com.keanntech.framework.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.core.Ordered;
import reactor.core.publisher.Mono;

/**
 * @author miaoqingfu
 * @date 2022年03月17日 9:32 AM
 */
@Component
@RequiredArgsConstructor
public class SecurityGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println(exchange.getRequest().getPath().toString());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
