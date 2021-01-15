package cn.isc.iscAuthServer.Configuration;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Authorization Server Configuration
 * 
 * ClientDetailsServiceConfigurer: a configurer that defines the client details service. 
 * Client details can be initialized, or you can just refer to an existing store. 
 * AuthorizationServerSecurityConfigurer: defines the security constraints on the token endpoint. 
 * AuthorizationServerEndpointsConfigurer: defines the authorization and token endpoints and the token services.
 * 
 * 
 * @author isc
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    
    /**
     * 将ClientDetailsServiceConfigurer（从您的回调AuthorizationServerConfigurer） 
     * 可以用来在内存或JDBC实现客户的细节服务来定义的。线上采用的Jdbc方式存储。
     * 客户端的重要属性是
     * clientId：（必填）客户端ID。 
     * secret:(可信客户端需要）客户机密码
     * resource_id: 客户端能访问的资源id集合，注册客户端时，根据实际需要可选择资源id，也可以根据不同的额注册流程，赋予对应的额资源id
     * scope：客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
     * authorizedGrantTypes：授予客户端使用授权的类型。默认值为空。 
     * authorities授予客户的授权机构（普通的Spring Security权威机构）。 客户端的详细信息可以通过直接访问底层商店（
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 采用的JDBC方式存储客户端信息，用于访问token的发放验证和授权
        clients.withClientDetails(clientDetails());
    }
    
    /**
     * 配置验证服务器的数据源
     */
    @Autowired
    private DataSource datasource;

    /**
     * 验证服务器存储的客户端详情对应的service，
     * 框架层面已经封装好了，只需要在配置阶段将数据源配置好就可以。
     * @return
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(datasource);
    }

    /**
     * 此处用来定义获取令牌令牌端点上的安全约束。
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }
    
    /**
     * 此处用来做/oauth/token的token类型，分为InMemoryTokenStore，JdbcTokenStore，JwtTokenStore三种
     * 此处使用的jwttoken,无需物理存储，集群状态下，配置相同的signkey即可
     * 
     * @param endpoints
     * @throws Exception
     * 
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);
        endpoints.authenticationManager(authenticationManager)
            .tokenStore(jwtTokenStore)
            .accessTokenConverter(jwtAccessTokenConverter)
            .tokenEnhancer(enhancerChain);
        // 配置token获取端点，改为自定义地址
//        endpoints.pathMapping("/oauth/token", "/api/auth/authToken");
    }
   
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("jwtTokenEnhancer")
    private TokenEnhancer jwtTokenEnhancer;

    public static void main(String args[]) {
        System.out.println(new BCryptPasswordEncoder().encode("gtmc_secret"));

    }
}
