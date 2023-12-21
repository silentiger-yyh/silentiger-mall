package org.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
//@RequestMapping("/user")
public class UserController {

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

//    @ResponseBody
//    @RequestMapping("/user")
//    public Object user(Principal principal) {
//        return principal;
//    }
}

