package com.kostakuu.moviestar.security.config;

import com.kostakuu.moviestar.contract.service.UserServiceInterface;
import com.kostakuu.moviestar.security.rest.RestAuthenticationEntryPoint;
import com.kostakuu.moviestar.security.token.TokenAuthenticationFilter;
import com.kostakuu.moviestar.security.token.TokenHelper;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserServiceInterface userService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final TokenHelper tokenHelper;

    public WebSecurityConfig(UserServiceInterface userService,
                             RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                             TokenHelper tokenHelper) {
        this.userService = userService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.tokenHelper = tokenHelper;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        try {
            auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        http.cors().configurationSource(request -> configuration);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers("/api/auth/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/genre/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/movie/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/projection/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }
}
