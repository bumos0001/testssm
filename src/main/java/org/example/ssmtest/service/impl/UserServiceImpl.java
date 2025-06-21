package org.example.ssmtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ssmtest.mapper.UserMapper;
import org.example.ssmtest.po.User;
import org.example.ssmtest.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
