package com.flybird.cms.common.security.aspect;

import com.flybird.cms.common.core.constant.SecurityConstants;
import com.flybird.cms.common.core.exception.InnerAuthException;
import com.flybird.cms.common.core.utils.ServletUtils;
import com.flybird.cms.common.core.utils.StringUtils;
import com.flybird.cms.common.security.annotation.InnerAuth;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * description: 内部服务调用验证处理
 *
 * @author: flybird
 * @date: 2022-01-03 11:31 上午
 */
@Aspect
@Component
public class InnerAuthAspect implements Ordered {

    @Around("@annotation(innerAuth)")
    public Object innerAround(ProceedingJoinPoint point, InnerAuth innerAuth) throws Throwable {
        String source = ServletUtils.getRequest().getHeader(SecurityConstants.FROM_SOURCE);

        if (!StringUtils.equals(SecurityConstants.INNER, source)) {
            throw new InnerAuthException("没有内部访问权限，不允许访问");
        }

        String username = ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_USERNAME);
        String userid = ServletUtils.getRequest().getHeader(SecurityConstants.DETAILS_USER_ID);

        if (innerAuth.isUser() && (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username))) {
            throw new InnerAuthException("没有设置用户信息，不允许访问 ");
        }
        return point.proceed();
    }

    /**
     * 确保在权限认证aop执行前执行
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
