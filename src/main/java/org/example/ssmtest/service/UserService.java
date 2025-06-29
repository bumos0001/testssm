package org.example.ssmtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ssmtest.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService, IService<User> {
    boolean insertUser(User user);
}
