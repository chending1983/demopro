package cn.isc.iscResourceServer.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 资源服务器配置文件，按照顺序，
 * 分别配置资源服务器的tokenservices
 * 当前资源的ID
 * 对受保护的资源的访问规则，URL访问限制以及规则
 *
 * @author cd
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 将配置好的jwtToken转换器以及store装配rousources TokenServices当中
     */
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenStore jwtTokenStore;

    /**
     * 组装当前资源服务器的token服务，token转换器，这里我们要与授权服务器一致
     *
     * @return
     */
    @Bean
    public ResourceServerTokenServices resourceJwtTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter);
        defaultTokenServices.setTokenStore(jwtTokenStore);
        return defaultTokenServices;
    }

    /**
     * 制定当前资源服务器对应的资源ID，需要配置到配置文件当中
     */
    private String RESOURCE_ID = "test";

    /**
     * 配置用于保护的资源ID和资源token解析方法
     * 这里因为资源服务器和验证授权服务器是一台机器，所以不用设置signkey，复用jwt配置文件内的signkey
     * 资源ID对应当前资源服务器所在的资源标示，单个用户可能会有多个的资源权限，在不同的资源服务器下
     *
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // resource资源只允许基于令牌的身份验证
        resources.resourceId(RESOURCE_ID).stateless(true);
        resources.tokenServices(resourceJwtTokenServices());
    }

    /**
     * 用于配置对受保护的资源的访问规则
     * 默认情况下所有不在/oauth/**下的资源都是受保护的资源 {@link OAuth2WebSecurityExpressionHandler}
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 由于我们希望在用户界面中访问受保护的资源，因此我们需要允许创建会话
        // 这部分内容可以注释掉，打开后用户可以通过页面登陆建立session访问资源
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers()
                .anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/hello/**").permitAll()
                .antMatchers("/api/**").access("#oauth2.hasScope('basic')").anyRequest().authenticated();
    }

}
