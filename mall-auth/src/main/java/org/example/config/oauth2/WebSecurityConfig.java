package org.example.config.oauth2;

import org.example.config.exception.CustomWebResponseExceptionTranslator;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * SpringSecurity配置文件,支持password模式要配置AuthenticationManager
 * @Author silentiger@yyh
 * @Date 2023-12-17 13:52:58
 */

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userDetailService;

    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //校验用户
        auth.userDetailsService(userDetailService)
            .passwordEncoder( new PasswordEncoder() {
                //对密码进行加密
                @Override
                public String encode(CharSequence charSequence) {
//                    System.out.println(charSequence.toString());
                    return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                }
                //对密码进行判断匹配
                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                    return s.equals(encode);
                }
            }
        );
    }
    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // CRSF禁用，因为不使用session
            .csrf().disable()
            // 认证失败处理类
//            .exceptionHandling().authenticationEntryPoint(customWebResponseExceptionTranslator).and()
            // 基于token，所以不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // 过滤请求
            .authorizeRequests()
            // 对于登录login 验证码captchaImage 允许匿名访问
            .antMatchers("/**/login", "/captchaImage", "/oauth/**").permitAll()
            .antMatchers(
                    HttpMethod.GET,
                    "/*.html",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js"
            ).permitAll()
//            .antMatchers("/profile/**").anonymous()
//            .antMatchers("/common/download**").anonymous()
//            .antMatchers("/common/download/resource**").anonymous()
//            .antMatchers("/swagger-ui.html").anonymous()
            .antMatchers("/swagger-resources/**").anonymous()
//            .antMatchers("/webjars/**").anonymous()
            .antMatchers("/*/api-docs").anonymous()
//            .antMatchers("/druid/**").anonymous()
            //Demo目录下的请求不需要鉴权认证
            .antMatchers("/demo/**").anonymous()
            // 除上面外的所有请求全部需要鉴权认证
            .anyRequest().authenticated()
            .and()
            .headers().frameOptions().disable()
            .and()
            .formLogin();   // 使用默认的登录页人，如果要使用自定义的登录页在后面添加.loginPage( "/login" )
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return Objects.equals(charSequence.toString(),s);
            }
        };
    }
}