package org.example.ssmtest.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;                     // 主鍵，自動增長
    private String loginAct;               // 登錄帳號
    private String loginPwd;               // 登錄密碼
    private String name;                   // 用戶姓名
    private String phone;                  // 用戶手機
    private String email;                  // 用戶郵箱
    private Integer accountNoExpired;      // 帳號是否未過期（1正常，0過期）
    private Integer credentialsNoExpired;  // 憑證是否未過期（1正常，0過期）
    private Integer accountNoLocked;       // 帳號是否未鎖定（1正常，0鎖定）
    private Integer accountEnabled;        // 是否啟用（1啟用，0禁用）
    private LocalDateTime createTime;               // 建立時間
    private Integer createBy;              // 建立人
    private LocalDateTime editTime;                 // 編輯時間
    private Integer editBy;                // 編輯人
    private LocalDateTime lastLoginTime;            // 最後登入時間
}
