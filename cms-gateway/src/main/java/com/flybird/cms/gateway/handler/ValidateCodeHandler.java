package com.flybird.cms.gateway.handler;

import com.flybird.cms.common.core.exception.CaptchaException;
import com.flybird.cms.common.core.web.domain.AjaxResult;
import com.flybird.cms.gateway.service.ValidateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * description: 验证码获取
 *
 * @author: flybird
 * @date: 2021-12-26 8:14 下午
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {

    @Autowired
    ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        AjaxResult ajax;
        try {
            ajax = validateCodeService.createCapcha();
        } catch (CaptchaException e) {
            return Mono.error(e);
        }
        return ServerResponse.ok().body(BodyInserters.fromValue(ajax));
    }
}
