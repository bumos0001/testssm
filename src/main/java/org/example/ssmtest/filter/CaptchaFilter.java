package org.example.ssmtest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ssmtest.model.request.LoginRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component // (需注入)
public class CaptchaFilter extends OncePerRequestFilter { // 在spring框架中，實現Filter，直接繼承OncePerRequestFilter
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 判斷是不是登入請求
        String requestURI = request.getRequestURI();
        logger.info("requestURI:" + requestURI);
        if (!requestURI.equals("/login")){
            logger.info("通過");
            filterChain.doFilter(request, response);
        }else{

            // 包裝 request，讓 body 可重複讀
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);

            // 讀取 JSON 並轉成物件
            String body = StreamUtils.copyToString(requestWrapper.getInputStream(), StandardCharsets.UTF_8);
            LoginRequest loginRequest = objectMapper.readValue(body, LoginRequest.class);

            String code = loginRequest.getCode();
            String sessionCode =  stringRedisTemplate.opsForValue().get("captcha:"+loginRequest.getKey());
            System.out.println("code:"+code);
            System.out.println("sessionCode:"+sessionCode);
            if (!StringUtils.hasText(code)) {
                throw new RuntimeException("沒有輸入驗證碼，請重試");
            }
            if (!StringUtils.hasText(sessionCode)) {
                throw new RuntimeException("暫存的驗證碼已過期，請重試");
            }
            if (!code.equalsIgnoreCase(sessionCode)) {
                throw new RuntimeException("驗證碼輸入錯誤");
            }
            doFilter(request, response, filterChain);
        }
    }
}
