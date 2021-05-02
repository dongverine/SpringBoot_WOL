package com.dongverine.wol.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //@Override
    public void configure(WebSecurity web){
        //static 소스들
        web.ignoring().antMatchers("/common/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/wol/**").hasRole("ADMIN")
                //.antMatchers("/wol/**").authenticated()
                .antMatchers("/wol/**").permitAll();
        http.authorizeRequests()
                .antMatchers("/wol/ajax/**").permitAll()
                //.antMatchers("/wol/**").authenticated()
                .and()
                .csrf().disable();

//        http.formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/wol")
//                .permitAll();
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

    }
}
