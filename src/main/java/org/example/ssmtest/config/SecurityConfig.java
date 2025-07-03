package org.example.ssmtest.config;

import org.example.ssmtest.filter.CaptchaFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Resource
    CaptchaFilter captchaFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 預設使用 bcrypt，加上多種演算法支援
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() // ✅ Spring Security 5.x 用這個
                    .antMatchers("/", "/user","/login.html","/login", "/common/**").permitAll() // ✅ 這樣 permitAll 才會有效
                    .anyRequest().authenticated()
                    .and()
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/login.html") // 自訂登入頁
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/failure.html")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();


        return http.build();
    }
}
