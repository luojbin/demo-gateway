package com.luojbin.demo.gateway.filter.global;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// public class MyGlobalFilter implements GlobalFilter, Ordered {
public class MyGlobalFilter  {
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
