package org.example.ssmtest.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("roles")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;             // 角色ID

    private String name;            // 角色名稱（如 ADMIN、EDITOR）

    private String description;     // 描述
}
