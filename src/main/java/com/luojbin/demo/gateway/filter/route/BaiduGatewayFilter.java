package com.luojbin.demo.gateway.filter.route;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// public class BaiduGatewayFilter implements GatewayFilter, Ordered {
public class BaiduGatewayFilter {
    // @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // todo 实现方法
        return null;
    }

    // @Override
    public int getOrder() {
        // todo 实现方法
        return 0;
    }
}
