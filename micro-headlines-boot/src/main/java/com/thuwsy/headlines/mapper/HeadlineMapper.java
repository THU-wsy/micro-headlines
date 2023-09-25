package com.thuwsy.headlines.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.thuwsy.headlines.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thuwsy.headlines.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 吴晟宇
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-09-25 13:07:56
* @Entity com.thuwsy.headlines.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map<String, Object>> selectMyPage(IPage<Map<String, Object>> page, @Param("portalVo") PortalVo portalVo);

    Map<String, Object> selectDetailMap(Integer hid);
}




