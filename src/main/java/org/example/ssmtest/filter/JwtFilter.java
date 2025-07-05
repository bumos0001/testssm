package org.example.ssmtest.filter;

import org.example.ssmtest.mapper.PermissionMapper;
import org.example.ssmtest.mapper.UserMapper;
import org.example.ssmtest.model.entity.Permission;
import org.example.ssmtest.model.entity.User;
import org.example.ssmtest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;  // 自訂的 JWT 工具類，負責 Token 產生與解析

    @Resource
    private PermissionMapper permissionMapper;  // 資料庫權限查詢接口

    @Resource
    private UserMapper userMapper;  // 資料庫使用者查詢接口

    /**
     * 核心過濾方法，每次 HTTP 請求都會被調用
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 如果是 /login 或 /register，直接放行
        if ("/login".equals(requestURI) || "/register".equals(requestURI)) {
            chain.doFilter(request, response);
            return;  // 放行後直接 return，不執行後續 JWT 驗證邏輯
        }


        // 以下是原本 JWT 驗證邏輯
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                Integer uid = jwtUtil.getUid(token);
                User user = userMapper.selectById(uid);
                List<Integer> roles = jwtUtil.getRoles(token);
                List<Permission> byRoleIds = permissionMapper.findByRoleIds(roles);
                List<GrantedAuthority> authorities = byRoleIds.stream()
                        .map(a -> new SimpleGrantedAuthority(a.getName()))
                        .collect(Collectors.toList());
                user.setAuthorities(authorities);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }


}


