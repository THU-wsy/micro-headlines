package com.thuwsy.headlines.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.thuwsy.headlines.pojo.User;
import com.thuwsy.headlines.service.UserService;
import com.thuwsy.headlines.utils.JwtHelper;
import com.thuwsy.headlines.utils.Result;
import com.thuwsy.headlines.utils.ResultCodeEnum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.thuwsy.headlines.controller
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/9/25 13:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader("token") String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("/checkUserName")
    public Result checkUserName(@RequestParam("username") String username) {
        Result result = userService.checkUserName(username);
        return result;
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        return result;
    }

    @GetMapping("/checkLogin")
    public Result checkLogin(@RequestHeader("token") String token) {
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}
