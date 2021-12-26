package com.flybird.cms.gateway.filter;

import com.flybird.cms.common.core.constant.CacheConstants;
import com.flybird.cms.common.core.constant.HttpStatus;
import com.flybird.cms.common.core.constant.SecurityConstants;
import com.flybird.cms.common.core.constant.TokenConstants;
import com.flybird.cms.common.core.utils.JwtUtils;
import com.flybird.cms.common.core.utils.ServletUtils;
import com.flybird.cms.common.core.utils.StringUtils;
import com.flybird.cms.common.redis.service.RedisService;
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

    @Autowired
    RedisService redisService;

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
        Boolean isLogin = redisService.hasKey(getTokenKey(userKey));
        if (!isLogin) {
            return unauthorizedResponse(exchange, "登录状态已过期");
        }
        String userId = JwtUtils.getUserId(claims);
        String userName = JwtUtils.getUserName(claims);
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userName)) {
            return unauthorizedResponse(exchange, "令牌验证失败");
        }
        addHeader(mutate, SecurityConstants.USER_KEY, userKey);
        addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userId);
        addHeader(mutate, SecurityConstants.DETAILS_USERNAME, userName);

        removeHeader(mutate, SecurityConstants.FROM_SOURCE);

        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        mutate.header(name, ServletUtils.urlEncode(value.toString()));
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

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
