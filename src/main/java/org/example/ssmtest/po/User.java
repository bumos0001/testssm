package org.example.ssmtest.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String info; // 儲存 JSON 字串，可轉成物件
    private Integer status;
    private Integer balance;
    private Date createTime;
    private Date updateTime;
}
