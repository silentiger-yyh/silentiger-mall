package org.search.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.silentiger.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Api(tags = "测试controller")
public class DemoController {

    @GetMapping("/time")
    @ApiOperation("测试接口")
    public String getTime() {
        return String.valueOf(DateUtil.getTimeNow());
    }
}
