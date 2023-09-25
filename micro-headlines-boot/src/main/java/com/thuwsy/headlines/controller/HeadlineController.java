package com.thuwsy.headlines.controller;

import com.thuwsy.headlines.pojo.Headline;
import com.thuwsy.headlines.service.HeadlineService;
import com.thuwsy.headlines.utils.JwtHelper;
import com.thuwsy.headlines.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: HeadlineController
 * Package: com.thuwsy.headlines.controller
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/9/25 21:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/headline")
@CrossOrigin
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/publish")
    public Result publish(@RequestBody Headline headline,
                          @RequestHeader("token") String token) {
        // token查询用户id
        Integer userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }

    @PostMapping("/findHeadlineByHid")
    public Result findHeadlineByHid(@RequestParam("hid") Integer hid) {
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody Headline headline) {
        Result result = headlineService.updateHeadLine(headline);
        return result;
    }

    @PostMapping("/removeByHid")
    public Result removeByHid(@RequestParam("hid") Integer hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
