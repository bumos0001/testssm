package org.example.ssmtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ssmtest.mapper.PermissionMapper;
import org.example.ssmtest.mapper.UserMapper;
import org.example.ssmtest.model.entity.Permission;
import org.example.ssmtest.model.entity.User;
import org.example.ssmtest.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_act", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        //  將權限轉成 GrantedAuthority
        List<Permission> permissions = permissionMapper.findByRoleIds(Collections.singletonList(user.getRoleId()));

        List<GrantedAuthority> authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList());

        // 5. 把權限放進 User 物件（需在 User 加一個欄位 List<GrantedAuthority>）
        user.setAuthorities(authorities);

        return user;
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
