//package org.example.config.oauth2;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
///**
// * 资源服务器配置
// *
// * @Author silentiger@yyh
// * @Date 2023-12-17 14:14:58
// */
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    /**
//     * 这里是资源服务端配置
//     * 在分布式环境下，资源服务器和认证服务器是分开的
//     * 每一个微服务都是一个资源服务，每次访问资源的接口都需要被拦截
//     * 通过认证服务器统一认证之后，各服务接口可正常调用
//     *
//     * 最合适的方式是：在网关服务中定义资源服务器所有接口统一走网关
//     */
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.requestMatchers()
//                .anyRequest()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
//    }
//}