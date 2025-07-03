package org.example.ssmtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.ssmtest.model.entity.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT p.* FROM permissions p " +
            "JOIN role_permission rp ON p.id = rp.permission_id " +
            "WHERE rp.role_id = #{roleId}")
    List<Permission> findByRoleId(@Param("roleId") Integer roleId);
}
