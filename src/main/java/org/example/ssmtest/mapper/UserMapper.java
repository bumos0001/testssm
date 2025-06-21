package org.example.ssmtest.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.ssmtest.po.User;

@Mapper // 告訴Spring 這是MyBatis操作資料庫的Interface
public interface UserMapper extends BaseMapper<User> {
    void updateBalanceByIds(@Param("ew") LambdaQueryWrapper<User> wrapper, @Param("amount") Integer amount);

}
