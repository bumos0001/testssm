package org.example.ssmtest.filter;

import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // (需注入)
public class CaptchaFilter extends OncePerRequestFilter { // 在spring框架中，實現Filter，直接繼承OncePerRequestFilter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(request, response);

        // 判斷是不是登入請求
//        String requestURI = request.getRequestURI();
//        logger.info("requestURI:" + requestURI);
//        if (!requestURI.equals("/login")){
//            logger.info("通過");
//            filterChain.doFilter(request, response);
//        }else{
//            String code = request.getParameter("captcha");
//            String sessionCode =  request.getSession().getAttribute("captcha").toString();
//            if (!StringUtils.hasText(code)) {
//                throw new RuntimeException("沒有輸入驗證碼，請重試");
//            }
//            if (!StringUtils.hasText(sessionCode)) {
//                throw new RuntimeException("暫存的驗證碼已過期，請重試");
//            }
//            if (!code.equalsIgnoreCase(sessionCode)) {
//                throw new RuntimeException("驗證碼輸入錯誤");
//            }
//            doFilter(request, response, filterChain);
//        }
    }
}
