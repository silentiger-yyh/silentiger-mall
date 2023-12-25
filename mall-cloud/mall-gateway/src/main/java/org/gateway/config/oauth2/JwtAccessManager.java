package org.gateway.config.oauth2;

import org.apache.commons.lang3.StringUtils;
import org.silentiger.constant.AuthConstant;
import org.silentiger.service.RedisService;
import org.silentiger.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 鉴权管理器自定义
 *
 * @Author silentiger@yyh
 * @Date 2023-12-24 13:19:41
 */

@Component
public class JwtAccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedisService redisService;


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        try {
            ServerHttpRequest request = authorizationContext.getExchange().getRequest();
            String token = request.getHeaders().getFirst(AuthConstant.AUTHORIZATION);
            if (token != null && token.startsWith(AuthConstant.JWT_TOKEN_PREFIX)) {
                token = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            } else {
                throw new Exception("无效的TOKEN");
            }
            // 从redis获取token,如果没有获取到则表明该token无效
            DefaultOAuth2AccessToken redisAccessToken = (DefaultOAuth2AccessToken) redisService.get(AuthConstant.REDIS_KEY_ACCESS_TOKEN + token);
            if (redisAccessToken == null) {
                throw new Exception("无效的TOKEN");
            }
            boolean verified = JWTUtil.verify(token);
            if (!verified) {
                throw new Exception("无效的TOKEN");
            }
            String username = JWTUtil.getUsername(token);
            if (StringUtils.isNotBlank(username)) {
                // 获取用户所有有权限的url，过滤
            }else {
                throw new Exception("用户信息缺失");
            }
        } catch (Exception e) {
            return Mono.error(e);
        }
//        return Mono.just(new AuthorizationDecision(false));
        return Mono.just(new AuthorizationDecision(true));
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return ReactiveAuthorizationManager.super.verify(authentication, object);
    }
}
