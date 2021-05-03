package com.dongverine.wol.config;

import com.dongverine.wol.common.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${config.id}")
    private String id;
    @Value("${config.pass}")
    private String password;

    //@Override
    public void configure(WebSecurity web){
        //static 소스들
        web.ignoring().antMatchers("/common/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl wolJsp =
                http.authorizeRequests().antMatchers("/wol/**");
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl wolAjax =
                http.authorizeRequests().antMatchers("/wol/ajax/**");
        if(StringUtils.hasLength(id.trim()) && StringUtils.hasLength(password.trim())){
            /*
            http.authorizeRequests()
                    .antMatchers("/wol/**")
                    .hasRole(LoginType.ADMIN.name());
            http.authorizeRequests()
                    .antMatchers("/wol/ajax/**")
                    .hasRole(LoginType.ADMIN.name())
                    .and()
                    .csrf().disable();
            */
            log.info("SecurityConfig configure : {}",LoginType.ADMIN.name());
            wolJsp.hasRole(LoginType.ADMIN.name());
            wolAjax.hasRole(LoginType.ADMIN.name())
                    .and().csrf().disable();
            http.formLogin()
                    .defaultSuccessUrl("/wol")
                    //.loginProcessingUrl("/loginProc")
                    //.passwordParameter("pass")
                    //.usernameParameter("name")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true);
        }else{
            log.info("SecurityConfig configure permitAll");
            wolJsp.permitAll();
            wolAjax.permitAll().and().csrf().disable();
        }
    }

    @Autowired
    public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
        if(StringUtils.hasLength(id.trim()) && StringUtils.hasLength(password.trim())) {
            auth.inMemoryAuthentication()
                    .withUser(id)
                    .password("{noop}" + password)
                    .roles(LoginType.ADMIN.name());
        }
    }
}
