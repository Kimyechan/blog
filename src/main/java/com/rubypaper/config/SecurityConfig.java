package com.rubypaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.util.Locale;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BlogUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.csrf().disable();

        security.authorizeRequests().antMatchers("/login", "/join").permitAll();
//        security.authorizeRequests().antMatchers("/user/**").hasRole("MEMBER");
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        security.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess",true);
        security.logout().invalidateHttpSession(true).logoutSuccessUrl("/blog/view/list");
        security.exceptionHandling().accessDeniedPage("/accessDenied");

        security.userDetailsService(userDetailsService);
    }

}
