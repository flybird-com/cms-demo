package com.flybird.cms.gateway.filter;

import com.flybird.cms.common.core.constant.HttpStatus;
import com.flybird.cms.common.core.constant.TokenConstants;
import com.flybird.cms.common.core.utils.JwtUtils;
import com.flybird.cms.common.core.utils.ServletUtils;
import com.flybird.cms.common.core.utils.StringUtils;
import com.flybird.cms.gateway.config.properties.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description: 网关鉴权
 *
 * @author: flybird
 * @date: 2021-12-26 2:18 下午
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private IgnoreWhiteProperties ignoreWhiteProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 忽略白名单
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        if (StringUtils.matches(url, ignoreWhiteProperties.getWhites())) {
            chain.filter(exchange);
        }
        // 获取token
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return unauthorizedResponse(exchange, "令牌不能为空");
        }
        Claims claims = JwtUtils.parseToken(token);
        if (null == claims) {
            return unauthorizedResponse(exchange, "令牌已过期或验证不正确！");
        }
        String userKey = JwtUtils.getUserKey(claims);
        // 处理异常
        // redis是否存在user
        // 从token获取user_key user_id user_name放入headers并移除from-source
        return null;
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, HttpStatus.UNAUTHORIZED);
    }

    private String getToken(ServerHttpRequest request) {
        String tokenStr = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        if (StringUtils.isNotEmpty(tokenStr)) {
            assert tokenStr != null;
            if (tokenStr.startsWith(TokenConstants.PREFIX)) {
                tokenStr = tokenStr.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
            }
        }
        return tokenStr;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
