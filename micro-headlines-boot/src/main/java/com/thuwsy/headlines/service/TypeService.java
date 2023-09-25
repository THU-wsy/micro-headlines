package com.thuwsy.headlines.service;

import com.thuwsy.headlines.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.thuwsy.headlines.utils.Result;

/**
* @author 吴晟宇
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-09-25 13:07:56
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
