package org.example.config.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 *
 * @Author silentiger@yyh
 * @Date 2023-12-17 14:14:58
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 这里设置需要token验证的url
     * 可以在WebSecurityConfigurerAdapter中排除掉，
     * 对于相同的url，如果二者都配置了验证
     * 则优先进入ResourceServerConfigurerAdapter,进行token验证。而不会进行
     * WebSecurityConfigurerAdapter 的 basic auth或表单认证。
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.requestMatchers()
                .antMatchers("/no")
//                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/no")
//                .antMatchers("/**")
                .authenticated();
    }
}