package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    TokenEndpoint tokenEndPoint;

    @ApiOperation("OAuth2认证生成token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "grant_type", value = "Oauth2模式类型", required = true),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码"),
    })
//    @PostMapping("/login")
//    public String token(@ApiIgnore Principal principal,
//                         @ApiIgnore @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("status", 200);
//        map.put("data", tokenEndPoint.postAccessToken(principal, parameters).getBody());
//        return map.toString();
//    }

    @PostMapping("/login")
    public String token(@RequestBody Map<String, String> parameters) {
        System.out.println(parameters);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 200);
        return map.toString();
    }
}

