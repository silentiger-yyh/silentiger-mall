package org.example.config.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * 注入Bean
 *
 * @Author silentiger@yyh
 * @Date 2023-12-17 15:44:26
 */

@Configuration
public class Beans {
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;
//
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
//        return new JdbcTokenStore( dataSource );
    }

    @Bean
    public UserDetailsService users() throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("silentiger").password("{noop}password").roles("USER").build());
        manager.createUser(users.username("admin").password("123456").roles("USER", "ADMIN").build());
        return manager;
    }
}
