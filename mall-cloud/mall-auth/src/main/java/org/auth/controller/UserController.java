package org.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

//    @ResponseBody
//    @RequestMapping("/user")
//    public Object user(Principal principal) {
//        return principal;
//    }
//    @GetMapping("/info")
//    public void info(HttpServletRequest req) {
//        String remoteUser = req.getRemoteUser();
//        Authentication auth = ((Authentication) req.getUserPrincipal());
//        boolean admin = req.isUserInRole("admin");
//        System.out.println("remoteUser = " + remoteUser);
//        System.out.println("auth.getName() = " + auth.getName());
//        System.out.println("admin = " + admin);
//    }
}

