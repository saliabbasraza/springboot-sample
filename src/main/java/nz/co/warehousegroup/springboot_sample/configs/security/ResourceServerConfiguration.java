package nz.co.warehousegroup.springboot_sample.configs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public ResourceServerConfiguration(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("api");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/signup**", "/actuator/**", "/api-docs/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .antMatchers("/api/user/**").hasAuthority("ADMIN")
                .antMatchers("/api/blog/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}