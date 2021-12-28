package com.flybird.cms.gateway.service;


import com.flybird.cms.common.core.exception.CaptchaException;
import com.flybird.cms.common.core.web.domain.AjaxResult;

/**
 * description: 验证码处理
 *
 * @author: flybird
 * @date: 2021-12-26 18:12:53
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    AjaxResult createCapcha() throws CaptchaException;

    /**
     * 校验验证码
     */
    void checkCapcha(String code, String uuid) throws CaptchaException;
}
