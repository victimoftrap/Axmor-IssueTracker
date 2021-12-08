package ru.axmor.trial.tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous();
        http
                .authorizeRequests()
                .antMatchers("/issues/**").authenticated()
                .antMatchers("/static/**").permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .loginProcessingUrl("/login")
                .permitAll();
    }
}
