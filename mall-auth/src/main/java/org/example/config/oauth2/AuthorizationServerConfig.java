package org.example.config.oauth2;

import lombok.AllArgsConstructor;
import org.example.component.JwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 认证服务端配置
 * @Author silentiger@yyh
 * @Date 2023-12-17 14:10:01
 */

@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 注入权限验证控制器 来支持 password grant type
     */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


//    @Autowired
//    private CustomWebResponseExceptionTranslator webResponseExceptionTranslator;

    /**
     * 注入userDetailsService，开启refresh_token需要用到
     */
    @Autowired
    UserDetailsService userDetailsService;
    private final JwtTokenEnhancer jwtTokenEnhancer;
    /**
     * 设置保存token的方式，一共有五种，这里采用数据库的方式
     */
    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(dataSource);
        clients.inMemory()
                .withClient("silentiger-mall-portal-client")
                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "client_credentials", "implicit")
                .scopes("all")
                .secret("{noop}secret")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600*24)
                .refreshTokenValiditySeconds(3600*24*7)
//                .redirectUris("https://baidu.com", "https://baidu.com2")
                .and()
                .withClient("silentiger-mall-admin-client")
                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "client_credentials", "implicit")
                .scopes("all")
                .secret("{noop}secret")
//                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600*24)
                .refreshTokenValiditySeconds(3600*24*7)
//                .redirectUris("https://baidu.com", "https://baidu.com2")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates); //配置JWT的内容增强器
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService) //配置加载用户信息的服务
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
//        endpoints
////              //配置token存储方式
//                .tokenStore(tokenStore)
//                .approvalStoreDisabled()
////                .userApprovalHandler(userApprovalHandler)
//                //开启密码授权类型
//                .authenticationManager(authenticationManager)
//                //要使用refresh_token的话，需要额外配置userDetailsService
//                .userDetailsService(userDetailsService);
    }


//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients();
//    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }



    //clients.withClient配置以及加密算法
    private static final String CLIENT = "silentiger-mall-portal-client:secret";

    public static void main(String[] args) {
        byte[] encode = Base64.getEncoder().encode(CLIENT.getBytes(StandardCharsets.UTF_8));
        System.out.println("Basic " + new String(encode));
    }

}
