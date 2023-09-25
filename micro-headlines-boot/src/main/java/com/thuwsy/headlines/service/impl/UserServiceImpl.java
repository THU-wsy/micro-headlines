package com.thuwsy.headlines.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thuwsy.headlines.pojo.User;
import com.thuwsy.headlines.service.UserService;
import com.thuwsy.headlines.mapper.UserMapper;
import com.thuwsy.headlines.utils.JwtHelper;
import com.thuwsy.headlines.utils.MD5Util;
import com.thuwsy.headlines.utils.Result;
import com.thuwsy.headlines.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 吴晟宇
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-09-25 13:07:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    // 登录业务
    @Override
    public Result login(User user) {
        // 1. 根据账号查询数据
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);

        // 2. 若账号错误，返回501
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        // 3. 若密码正确，根据用户id生成一个token并返回
        if (!StringUtils.isEmpty(user.getUserPwd()) &&
                MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())) {
            // 登录成功
            // 根据用户id生成token，然后封装到Result中返回
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map<String, String> data = new HashMap<>();
            data.put("token", token);
            return Result.ok(data);
        }

        // 4. 否则，密码错误，返回503
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }


    // 根据token获取用户数据
    @Override
    public Result getUserInfo(String token) {
        // 1. 判断token是否过期
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        // 2. 根据token解析用户id
        Integer userId = jwtHelper.getUserId(token).intValue();

        // 3. 根据用户id查询用户数据
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        // 4. 去掉密码，封装User对象并返回
        user.setUserPwd("");
        Map<String, User> data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    // 检查账号是否可用
    @Override
    public Result checkUserName(String username) {
        // 1. 根据账号查询count
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, username);
        Long num = userMapper.selectCount(lambdaQueryWrapper);

        // 2. count == 0 说明账号可用
        if (num == 0) {
            return Result.ok(null);
        }

        // 3. 否则，账号不可用
        return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        // 1. 检查账号是否被注册
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        Long num = userMapper.selectCount(lambdaQueryWrapper);
        if (num != 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        // 2. 密码加密
        String password = MD5Util.encrypt(user.getUserPwd());
        user.setUserPwd(password);

        // 3. 保存账号
        userMapper.insert(user);
        return Result.ok(null);
    }
}




