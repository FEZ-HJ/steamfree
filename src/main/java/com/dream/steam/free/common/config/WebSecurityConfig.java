package com.dream.steam.free.common.config;

import com.dream.steam.free.common.filter.AuthenticationFailureHandler;
import com.dream.steam.free.common.filter.ValidateCodeFilter;
import com.dream.steam.free.common.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.client.HttpClientErrorException;

import javax.sql.DataSource;

/**
 * Created by H.J
 * 2019/11/8
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminUserService userService;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer(){
//        return container -> {
//            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error_403"));
//            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error_404"));
//            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error"));
//        };
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        http
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .headers()
            .frameOptions()
            .sameOrigin()
            .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/form")
                .failureHandler(authenticationFailureHandler)
                .permitAll()
            .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(7*24*60*60)
                .userDetailsService(userService)
            .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web){
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/style/**","/layui/**","/lib/**","/modules/**","/config.js");
        web.ignoring().antMatchers("/login","/code/image","/freeGame/findAll","/freeGame/findById","/lottery/**","/signIn/**","/user/**");
    }
}
