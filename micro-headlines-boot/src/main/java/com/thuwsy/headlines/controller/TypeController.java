package com.thuwsy.headlines.controller;

import com.thuwsy.headlines.pojo.vo.PortalVo;
import com.thuwsy.headlines.service.HeadlineService;
import com.thuwsy.headlines.service.TypeService;
import com.thuwsy.headlines.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: TypeController
 * Package: com.thuwsy.headlines.controller
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/9/25 18:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/portal")
@CrossOrigin
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("/findAllTypes")
    public Result findAllTypes() {
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("/showHeadlineDetail")
    public Result showHeadlineDetail(@RequestParam("hid") Integer hid) {
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }


}
