package org.example.controller;

import org.silentiger.utils.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/time")
    public String getTime() {
        return String.valueOf(DateUtil.getTimeNow());
    }
}
