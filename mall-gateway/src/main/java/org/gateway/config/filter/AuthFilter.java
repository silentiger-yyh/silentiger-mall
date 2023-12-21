package org.gateway.config.filter;

import org.apache.commons.lang3.StringUtils;
import org.silentiger.constant.AuthConstant;
import org.silentiger.util.JWTUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权拦截器
 *
 * @Author silentiger@yyh
 * @Date 2023-12-20 21:24:51
 */

@Component
public class AuthFilter implements GlobalFilter {
    /**
     * 进行鉴权操作
     * 如果鉴权通过，则调用 chain.filter(exchange) 方法继续执行下一个过滤器
     * 如果鉴权不通过，则返回一个错误响应
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 从请求头中获取jwt
        String jwt = request.getHeaders().getFirst(AuthConstant.JWT_HEADER);
        if (StringUtils.isBlank(jwt)) {  // null || "" || " "
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            System.out.println("无权访问");
            return response.setComplete();
        }
        boolean verify = JWTUtil.verify(jwt);
        if (!verify) {
            return response.setComplete();
        }
        return chain.filter(exchange); // 放行，流向下一个过滤器
    }
}
