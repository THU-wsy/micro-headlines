package com.thuwsy.headlines.pojo.vo;

import lombok.Data;

/**
 * ClassName: PortalVo
 * Package: com.thuwsy.headlines.pojo.vo
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/9/25 19:28
 * @Version 1.0
 */
@Data
public class PortalVo {
    private String keyWords;
    private Integer type = 0;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
