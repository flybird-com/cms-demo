//package com.flybird.cms.gateway.config;
//
//import com.ruoyi.gateway.handler.SentinelFallbackHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//
///**
// * @description: 网关限流配置
// * @author: flybird
// * @date: 2021/12/23 11:28 下午
// */
//@Configuration
//public class GatewayConfig
//{
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
//    {
//        return new SentinelFallbackHandler();
//    }
//}