package org.gateway.config.oauth2;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * 网关白名单配置，后期需要转移到数据库
 * Created by macro on 2020/3/3.
 */
@Data
//@EqualsAndHashCode(callSuper = false)
//@Component
//@ConfigurationProperties(prefix="secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls = Arrays.asList(
            "/doc.html",
            "/swagger-resources/**",
            "/swagger/**",
            "/*/v2/api-docs",
            "/*/*.js",
            "/*/*.css",
            "/*/*.png",
            "/*/*.ico",
            "/mall-auth/oauth/token"
    );
}
