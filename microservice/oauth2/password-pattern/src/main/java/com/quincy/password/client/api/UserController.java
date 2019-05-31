package com.quincy.password.client.api;

import com.quincy.password.resourcesowner.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:quincy
 * Date:2019-05-29
 */
@RestController
public class UserController {


    //1，用户点击第三方登陆,跳转到授权页面
    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = user.getUsername() + "@spring2go.com";

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user.getUsername());
        userInfo.setPassword(email);

        return ResponseEntity.ok(userInfo);
    }

}
