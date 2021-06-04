package com.luojbin.demo.gateway.filter.route;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// public class MyGatewayFilter implements GatewayFilter, Ordered {
public class MyGatewayFilter  {
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
