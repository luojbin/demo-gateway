package com.luojbin.demo.gateway.filter.route.factory;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
// 继承 AbstractNameValueGatewayFilterFactory, 非泛型类, 不需要指定配置类
public class MyArgsGatewayFilterFactory extends AbstractGatewayFilterFactory<MyArgsGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new MyArgsGatewayFilter(config);
    }

    // region 自定义配置类
    @Override
    // 使用自定义配置类时, 需要覆盖 shortcutFieldOrder 方法, 指定在配置文件中各参数的配置顺序
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name", "age", "address");
    }
    // 使用自定义配置类时, 需要在工厂类的无参构造中指定配置类的类型
    public MyArgsGatewayFilterFactory() {
        super(Config.class);
    }
    @Data
    public static class Config{
        private String name;
        private String age;
        private String address;
    }
    // endregion 自定义配置类

    public static class MyArgsGatewayFilter implements GatewayFilter {
        private String desc;
        private String number;
        private String location;


        public MyArgsGatewayFilter(Config config) {
            this.desc = config.name;
            this.number = config.age;
            this.location = config.address;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();

            System.out.println("pre-args filter: desc=" + this.desc + ", number=" + this.number + ", location=" + this.location);

            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
    }

}