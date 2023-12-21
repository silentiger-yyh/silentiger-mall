package org.auth.config.bean;

import org.silentiger.constant.SecretKeyConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 注入Bean
 *
 * @Author silentiger@yyh
 * @Date 2023-12-17 15:44:26
 */

@Configuration
public class Beans {

    /**
     * 生成TokenStore来保存token  此处为JwtTokenStore实现
     * @return TokenStore
     */
    @Bean
    public JwtTokenStore jwtTokenStore() {
//        return new InMemoryTokenStore();
//        return new JdbcTokenStore( dataSource );
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    /**
     *  生成JwtAccessTokenConverter转换器，并设置密钥
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 设置jwt密钥
        jwtAccessTokenConverter.setSigningKey(SecretKeyConstant.JWT_KEY);
        return jwtAccessTokenConverter;
    }

    /**
     * JwtTokenEnhancer的注入
     *
     * @return JwtTokenEnhancer
     */
    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }

//    @Bean
//    public UserDetailsService users() {
////        User.UserBuilder users = User.withDefaultPasswordEncoder();
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(users.username("silentiger").password("{noop}password").roles("USER").build());
////        manager.createUser(users.username("admin").password("123456").roles("USER", "ADMIN").build());
////        return manager;
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Role("SUPER_ADMIN"));
//        roles.add(new Role("USER"));
//        User user = new User("admin", "123456", roles);
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(user);
//        System.out.println(user.getPassword());
//        return manager;
//    }
}
