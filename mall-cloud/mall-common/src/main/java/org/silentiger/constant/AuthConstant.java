package org.silentiger.constant;

/**
 * 用于授权的常量
 *
 * @Author silentiger@yyh
 * @Date 2023-12-20 21:33:06
 */

public class AuthConstant {
    /**
     * JWT认证信息请求头字段名称（预留）
     */
    public static final String JWT_HEADER = "ACCESS-TOKEN";
    /**
     * header令牌字段
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * access_token存储在redis中的key前缀，key为access:jwt
     */
    public static final String REDIS_KEY_ACCESS_TOKEN = "access:";
    /**
     * JWT令牌前缀(TOKEN类型)
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
}
