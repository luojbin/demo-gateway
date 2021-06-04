package com.luojbin.demo.gateway.filter.route.factory;

import com.luojbin.demo.gateway.filter.route.MyGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

// @Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new MyGatewayFilter();
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}
