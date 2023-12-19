package org.example.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@RestController
//@RequestMapping("/user")
public class UserController {

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("confirmAccess")
    public ModelAndView confirmAccess() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("approval");
        return modelAndView;
    }
    @PostMapping("test")
    public String test() {
        return "test";
    }

    @ResponseBody
    @RequestMapping("/user")
    public Object user(Principal principal) {
        return principal;
    }
}

