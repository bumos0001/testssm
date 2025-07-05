package org.example.ssmtest.controller;

import cn.hutool.captcha.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("common")
public class CaptchaController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("captcha")
    public Map<String, String> generateCaptcha() {
        // 建立寬 120px，高 40px，4 個字元，干擾線數量為 10 的驗證碼
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(120, 40, 4, 50);

        String code = captcha.getCode();
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        String imageBase64 = captcha.getImageBase64();

        stringRedisTemplate.opsForValue().set("captcha:"+key,code,5, TimeUnit.MINUTES);

        Map map = new HashMap();
        map.put("key", key);
        map.put("captchaImage", "data:image/png;base64," + imageBase64);
        return map;
    }
}
