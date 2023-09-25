package com.thuwsy.headlines.service;

import com.thuwsy.headlines.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.thuwsy.headlines.pojo.vo.PortalVo;
import com.thuwsy.headlines.utils.Result;

/**
* @author 吴晟宇
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-09-25 13:07:56
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadLine(Headline headline);
}
