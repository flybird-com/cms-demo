package com.flybird.cms.gateway.service.impl;

import com.flybird.cms.common.core.constant.Constants;
import com.flybird.cms.common.core.exception.CaptchaException;
import com.flybird.cms.common.core.utils.Base64;
import com.flybird.cms.common.core.utils.IdUtils;
import com.flybird.cms.common.core.utils.StringUtils;
import com.flybird.cms.common.core.web.domain.AjaxResult;
import com.flybird.cms.common.redis.service.RedisService;
import com.flybird.cms.gateway.config.properties.CaptchaProperties;
import com.flybird.cms.gateway.service.ValidateCodeService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * description: 验证码实现处理
 *
 * @author: flybird
 * @date: 2021-12-26 6:21 下午
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private CaptchaProperties captchaProperties;
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Override
    public AjaxResult createCapcha() throws CaptchaException {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaOnOff = captchaProperties.getEnabled();
        ajax.put("captchaOnOff", captchaOnOff);
        if (!captchaOnOff) {
            return ajax;
        }
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        String captchaType = captchaProperties.getType();
        // 生成验证码
        if ("math".equals(captchaType)) {
            // 1+2=?@3
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisService.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            assert image != null;
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }

    @Override
    public void checkCapcha(String code, String uuid) throws CaptchaException {
        if (StringUtils.isEmpty(code)) {
            throw new CaptchaException("验证码不能为空");
        }

        if (StringUtils.isEmpty(uuid)) {
            throw new CaptchaException("验证码已失效");
        }

        String codeKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(codeKey);
        redisService.deleteObject(codeKey);
        if (code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException("验证码不对，请重新输入");
        }
    }
}
