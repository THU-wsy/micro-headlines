package com.thuwsy.headlines.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thuwsy.headlines.pojo.Headline;
import com.thuwsy.headlines.pojo.vo.PortalVo;
import com.thuwsy.headlines.service.HeadlineService;
import com.thuwsy.headlines.mapper.HeadlineMapper;
import com.thuwsy.headlines.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 吴晟宇
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-09-25 13:07:56
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map<String, Object>> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(page, portalVo);

        Map<String, Object> pageInfo = new HashMap<>();
        List<Map<String, Object>> records = page.getRecords();
        pageInfo.put("pageData", records);
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());

        Map<String, Object> data = new HashMap<>();
        data.put("pageInfo", pageInfo);
        return Result.ok(data);
    }


    @Override
    public Result showHeadlineDetail(Integer hid) {
        // 1. 查询对应的数据
        Map<String, Object> headlineInfo = headlineMapper.selectDetailMap(hid);
        Map<String, Object> data = new HashMap<>();
        data.put("headline", headlineInfo);
        // 2. 修改阅读量+1
        Headline headline = new Headline();
        headline.setHid((Integer) headlineInfo.get("hid"));
        headline.setVersion((Integer) headlineInfo.get("version"));
        headline.setPageViews((Integer) headlineInfo.get("pageViews") + 1);
        headlineMapper.updateById(headline);

        return Result.ok(data);
    }

    @Override
    public Result publish(Headline headline) {
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map<String, Object> data = new HashMap<>();
        data.put("headline", headline);
        return Result.ok(data);
    }

    @Override
    public Result updateHeadLine(Headline headline) {
        // 先查询最新的版本
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);
        headline.setUpdateTime(new Date()); // 最新的更新时间

        // 更新
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}




