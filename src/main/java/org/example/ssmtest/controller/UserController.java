package org.example.ssmtest.controller;

import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import org.example.ssmtest.model.request.UserBalanceUpdateRequest;
import org.example.ssmtest.po.User;
import org.example.ssmtest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @PutMapping("balance")
    public ResponseEntity<Object> updateByCondition(UserBalanceUpdateRequest userBalanceUpdateRequest) {
        Integer amount = userBalanceUpdateRequest.getAmount();
        Integer id = userBalanceUpdateRequest.getId();
        User user = userService.getById(id);
        if (user == null){
            return ResponseEntity.status(400).body("沒有此用戶");
        }
        if (user.getBalance() - amount < 0){
            return ResponseEntity.status(400).body("餘額不足");
        }
        boolean update = userService.update().setSql("balance = balance - " + amount).eq("id", id).update();
        if (!update){
            return ResponseEntity.status(400).body("扣款失敗");
        }
        return ResponseEntity.ok().build();
    }
}
