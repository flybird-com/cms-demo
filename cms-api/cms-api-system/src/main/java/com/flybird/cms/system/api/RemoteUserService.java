package com.flybird.cms.system.api;

import com.flybird.cms.common.core.constant.SecurityConstants;
import com.flybird.cms.common.core.constant.ServiceNameConstants;
import com.flybird.cms.common.core.domain.R;
import com.flybird.cms.system.api.domain.SysUser;
import com.flybird.cms.system.api.factory.RemoteUserFallbackFactory;
import com.flybird.cms.system.api.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * description: 用户服务
 *
 * @author: flybird
 * @date: 2022-01-09 18:13:12
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param source   请求来源
     * @return 结果
     */
    @GetMapping("/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source  请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
