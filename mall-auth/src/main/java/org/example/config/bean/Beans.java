package org.example.config.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

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

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore( dataSource );
    }
}
