package com.flybird.cms.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flybird.cms.common.core.utils.ServletUtils;
import com.flybird.cms.common.core.utils.StringUtils;
import com.flybird.cms.gateway.config.properties.CaptchaProperties;
import com.flybird.cms.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * description: 验证码过滤器
 *
 * @author: flybird
 * @date: 2021-12-26 5:58 下午
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

    private final static String[] VALIDATE_URL = new String[]{"/auth/login", "/auth/register"};
    private static final String CODE = "code";
    private static final String UUID = "uuid";

    @Autowired
    CaptchaProperties captchaProperties;

    @Autowired
    private ValidateCodeService validateCodeService;


    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {

            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();

                // 非登录/注册请求或验证码关闭，不处理
                if (!StringUtils.containsAnyIgnoreCase(request.getURI().getPath(), VALIDATE_URL) || !captchaProperties.getEnabled()) {
                    return chain.filter(exchange);
                }

                try {
                    String respStr = resolveBodyFromRequest(request);
                    JSONObject obj = JSON.parseObject(respStr);
                    validateCodeService.checkCapcha(obj.getString(CODE), obj.getString(UUID));
                } catch (Exception e) {
                    return ServletUtils.webFluxResponseWriter(exchange.getResponse(), e.getMessage());
                }
                return chain.filter(exchange);
            }
        };
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
