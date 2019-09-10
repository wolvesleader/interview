package com.quincy.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


/**
 *http://andaily.com/spring-oauth-server/db_table_description.html
 * 授权连接
 * org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint
 *org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
 */
@Configuration
@EnableAuthorizationServer
class PasswordAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    //@Autowired
    //private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //配置OAuth2的客户端相关信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("XcWebApp")
                .secret(passwordEncoder.encode("XcWebApp"))
                //.redirectUris("http://localhost:40400/")
                // 授权码模式
                .authorizedGrantTypes("password")
                .scopes("read_userinfo");

    }

    /**
     * 密码模式需要在认证服务器中设置
     * 不然会报模式错误异常
     * @param endpoints
     * @throws Exception
     * 配置AuthorizationServerEndpointsConfigurer众多相关类，
     * 包括配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }


}

