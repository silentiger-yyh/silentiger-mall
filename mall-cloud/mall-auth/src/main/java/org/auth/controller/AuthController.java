package org.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.silentiger.api.CommonResult;
import org.silentiger.constant.AuthConstant;
import org.silentiger.dto.oauth2.Oauth2TokenDto;
import org.silentiger.enumeration.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@RequestMapping("/oauth")
@Api(tags = "AuthController", value = "认证中心登录认证")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;


    @PostMapping("/token")
    @ApiOperation("OAuth2获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "grant_type", value = "授权类型"),
            @ApiImplicitParam(name = "scopes", value = "授权范围"),
    })
    public CommonResult<Oauth2TokenDto> postAccessToken(HttpServletRequest request,
                                                @RequestBody HashMap<String, String> paramsMap) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(request.getUserPrincipal(), paramsMap).getBody();
        String tokenValue = oAuth2AccessToken.getValue();
        String username = (String) oAuth2AccessToken.getAdditionalInformation().get("username");
        if (StringUtils.isBlank(tokenValue) || StringUtils.isBlank(username)) {
            return CommonResult.failed(ResultCodeEnum.FAILED.getMessage());
        }
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(tokenValue)
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX)
                .username(username)
                .build();
        return CommonResult.success(oauth2TokenDto);
    }

}

