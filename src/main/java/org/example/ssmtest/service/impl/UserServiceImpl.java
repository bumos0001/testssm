package org.example.ssmtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ssmtest.mapper.UserMapper;
import org.example.ssmtest.model.entity.User;
import org.example.ssmtest.service.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_act", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // 構建SpringSecurity管理的User返回
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(user.getLoginPwd())
                .authorities(AuthorityUtils.NO_AUTHORITIES) // 先不給權限
                .build();
        return userDetails;
    }

    @Override
    public boolean insertUser(User user) {
        String loginPwd = user.getLoginPwd();
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = delegatingPasswordEncoder.encode(loginPwd);
        user.setLoginPwd(encode);
        return save(user);
    }
}
