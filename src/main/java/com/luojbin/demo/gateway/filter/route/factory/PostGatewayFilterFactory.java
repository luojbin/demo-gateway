package com.luojbin.demo.gateway.filter.route.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PostGatewayFilterFactory extends AbstractGatewayFilterFactory<PostGatewayFilterFactory.Config> {

    public PostGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new PostGatewayFilter(config);
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }

    public static class PostGatewayFilter implements GatewayFilter{
        public PostGatewayFilter(Config config) {}

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            // 先完成 pre filter 链, 发送到目标服务器并拿到响应
            Mono<Void> result = chain.filter(exchange)
                    // 拿到响应之后, 对响应进行处理
                    .then(Mono.fromRunnable(() -> {
                        // 从 exchange 中拿到 response 对象
                        ServerHttpResponse response = exchange.getResponse();


                        // todo 操作 response 对象
                        System.out.println("post filter");



                    }));
            return result;
        }
    }
}