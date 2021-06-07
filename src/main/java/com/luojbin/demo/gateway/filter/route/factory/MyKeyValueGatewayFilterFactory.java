package com.luojbin.demo.gateway.filter.route.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
// 继承 AbstractNameValueGatewayFilterFactory, 非泛型类, 不需要指定配置类
public class MyKeyValueGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory{

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new MyKeyValueGatewayFilter(config);
    }

    public static class MyKeyValueGatewayFilter implements GatewayFilter, Ordered {
        private String key;
        private String value;

        // 配置类固定使用 NameValueConfig, 包含两个参数 name 和 value
        public MyKeyValueGatewayFilter(NameValueConfig config) {
            this.key = config.getName();
            this.value = config.getValue();
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();

            System.out.println("pre-key-value filter: key=" + this.key + ", value=" + this.value);

            return chain.filter(exchange.mutate().request(builder.build()).build());
        }

        @Override
        public int getOrder() {
            return 100;
        }
    }

}