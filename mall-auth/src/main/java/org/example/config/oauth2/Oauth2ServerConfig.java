//package org.example.config.oauth2;
//
//import lombok.AllArgsConstructor;
//import org.example.component.JwtTokenEnhancer;
//import org.example.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
//
//import java.security.KeyPair;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 认证服务器配置
// * Created by macro on 2020/6/19.
// */
//@AllArgsConstructor
//@Configuration
//@EnableAuthorizationServer
//public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//    //    /**
//    //     * 注入userDetailsService，开启refresh_token需要用到
//    //     */
//    @Autowired
//    UserDetailsService userDetailsService;
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenEnhancer jwtTokenEnhancer;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("silentiger-mall-portal-client")
//                .secret(passwordEncoder.encode("123456"))
//                .scopes("all")
//                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "client_credentials", "implicit")
//                .accessTokenValiditySeconds(3600*24)
//                .refreshTokenValiditySeconds(3600*24*7)
//                .and()
//                .withClient("silentiger-mall-admin-client")
//                .secret(passwordEncoder.encode("123456"))
//                .scopes("all")
//                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "client_credentials", "implicit")
//                .accessTokenValiditySeconds(3600*24)
//                .refreshTokenValiditySeconds(3600*24*7);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> delegates = new ArrayList<>();
//        delegates.add(jwtTokenEnhancer);
//        delegates.add(accessTokenConverter());
//        enhancerChain.setTokenEnhancers(delegates); //配置JWT的内容增强器
//        endpoints.authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService) //配置加载用户信息的服务
//                .accessTokenConverter(accessTokenConverter())
//                .tokenEnhancer(enhancerChain);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients();
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setKeyPair(keyPair());
//        return jwtAccessTokenConverter;
//    }
//
//    @Bean
//    public KeyPair keyPair() {
//        //从classpath下的证书中获取秘钥对
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
//        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
//    }
//
//}
