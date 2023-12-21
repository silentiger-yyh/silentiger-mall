package org.silentiger.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.silentiger.constant.SecretKeyConstant;

import java.util.Map;

/**
 * JWT工具类
 *
 * @Author silentiger@yyh
 * @Date 2023-12-20 21:59:09
 */

public class JWTUtil {


    /**
     * 解码jwt
     * @param jwt jwt字符串
     * @return payloadMap
     */
    public static Map<String, Claim> decode(String jwt) {
        return JWT.decode(jwt).getClaims();
    }

    /**
     * 验证JWT的有效性
     * @param jwt jwt字符串
     * @return 有效返回true
     */
    public static boolean verify(String jwt) {

        /**
         * 这里会验证token是否过期，但是不会验证pay中的自定义的一些字段
         */
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SecretKeyConstant.JWT_KEY)).build().verify(jwt);
        // 验证payload中的字段
        String username = decodedJWT.getClaim("username").asString();
        String user_name = decodedJWT.getClaim("user_name").asString();
        if (StringUtils.isBlank(user_name) || StringUtils.isBlank(username) || !user_name.equals(username)) {
            throw new RuntimeException("TOKEN验证失败，用户名不匹配");
//            return false;
        }
        return true;
    }

    public static Claim getClaim(String jwt, String key) {
        return JWT.decode(jwt).getClaims().get(key);
    }

    public static String getUsername(String jwt) {
        Claim user_nameClaim = JWT.decode(jwt).getClaim("user_name");
        Claim usernameClaim = JWT.decode(jwt).getClaim("username");

        return (user_nameClaim != null)?user_nameClaim.asString():usernameClaim.asString();
    }


    public static void main(String[] args) {
        String jwt2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE3MDMyMjE0MDYsImF1dGhvcml0aWVzIjpbIlNVUEVSX0FETUlOIiwiVVNFUiJdLCJqdGkiOiJyZGczZXRsalk0cG0xYm9pMWJramduZmxjSnMiLCJjbGllbnRfaWQiOiJzaWxlbnRpZ2VyLW1hbGwtcG9ydGFsLWNsaWVudCIsImVuaGFuY2UiOiLlop7lvLrnmoTkv6Hmga8iLCJ1c2VybmFtZSI6ImFkbWluMSJ9.yjZThSsRyPxThuppbDocs287cIZwFOla7rb-c7a94xo";
//        String jwt2 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE3MDMxNzE0MTUsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwianRpIjoiTjJKNkVIWG9ueEZuX0NySHZ0ZTdJN2d2RHJnIiwiY2xpZW50X2lkIjoic2lsZW50aWdlci1tYWxsLXBvcnRhbC1jbGllbnQiLCJlbmhhbmNlIjoi5aKe5by655qE5L-h5oGvIn0.cdIJUwt-Wc9VGW_yvyDs46V6LH0zsjjSeMO-g6B6YG4";
        boolean verify = verify(jwt2);
        Map<String, Claim> decode = decode(jwt2);
        Claim claim = decode.get("username");
        String username = claim.asString();
        System.out.println(username);
    }
}
