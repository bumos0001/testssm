package org.example.ssmtest.config;

import cn.hutool.captcha.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("common")
public class CaptchaController {
    @GetMapping("captcha")
    public void generateCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 建立寬 120px，高 40px，4 個字元，干擾線數量為 10 的驗證碼
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(120, 40, 4, 50);

        // 將驗證碼內容存到 session 中（可改為 Redis）
        session.setAttribute("captcha", captcha.getCode());

        // 設定 response 輸出為圖片格式
        response.setContentType("image/png");
        ServletOutputStream out = response.getOutputStream();
        captcha.write(out);
        out.flush();
    }
}
