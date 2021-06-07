package com.luojbin.demo.gateway.filter.route.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class PreGatewayFilterFactory extends AbstractGatewayFilterFactory<PreGatewayFilterFactory.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        return new PreGatewayFilter(config);
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }

    public static class PreGatewayFilter implements GatewayFilter {
        public PreGatewayFilter(Config config) {}

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            // 先从 exchage 中获取到 request builder
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();


            // todo 通过 request builder 来修改 request 对象


            // 修改完成后, 用 builder 构造一个新的 request 对象, 传递到下游继续处理
            ServerHttpRequest newRequest = builder.build();
            // 用 newRequest 构造一个 newExchange
            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
            // 将 newExchange 传递到下游继续处理
            Mono<Void> result = chain.filter(newExchange);
            return result;
        }
    }

}