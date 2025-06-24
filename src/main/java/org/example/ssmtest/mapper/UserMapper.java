package org.example.ssmtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.ssmtest.model.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
