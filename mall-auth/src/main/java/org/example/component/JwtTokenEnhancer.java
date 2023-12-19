package org.example.component;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT内容增强器
 * Created by macro on 2020/6/19.
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        //把用户ID设置到JWT中
        info.put("username", user.getUsername());
        info.put("client_id", "silentiger-mall-portal-client");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
//        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
//        Map<String, Object> info = new HashMap<>();
//        //把用户ID设置到JWT中
//        info.put("id", securityUser.getId());
//        info.put("client_id",securityUser.getClientId());
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
//        return accessToken;
    }
}
