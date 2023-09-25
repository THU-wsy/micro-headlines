package com.thuwsy.headlines.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thuwsy.headlines.pojo.Type;
import com.thuwsy.headlines.service.TypeService;
import com.thuwsy.headlines.mapper.TypeMapper;
import com.thuwsy.headlines.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 吴晟宇
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-09-25 13:07:56
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        List<Type> list = typeMapper.selectList(null);
        return Result.ok(list);
    }
}




