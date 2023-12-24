package org.gateway.config.filter;

import org.apache.commons.lang3.StringUtils;
import org.silentiger.constant.AuthConstant;
import org.silentiger.service.RedisService;
import org.silentiger.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 定义一个全局过滤器，用于将用户的信息从token中解析出来放到header中，方便后续的服务直接使用
 *
 * 这个过滤器在JwtAccessManager之后执行
 *
 * @Author silentiger@yyh
 * @Date 2023-12-20 21:24:51
 */

@Component
public class AuthGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 从请求头中获取jwt
        String jwt = request.getHeaders().getFirst(AuthConstant.AUTHORIZATION);
        if (StringUtils.isBlank(jwt)) {  // null || "" || " "
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            System.out.println("无权访问");
            return response.setComplete();
        }
        // 解析token，将用户信息放到header中
        return chain.filter(exchange); // 放行，流向下一个过滤器
    }
}