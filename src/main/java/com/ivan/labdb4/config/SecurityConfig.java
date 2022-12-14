package com.ivan.labdb4.config;

import com.ivan.labdb4.jwt.JwtConfigurer;
import com.ivan.labdb4.model.security.CustomerPermission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()

                .antMatchers("/main").hasAuthority(CustomerPermission.HIGHLIGHT_READ.getPermission())
                .antMatchers("/api/v1/video/{id:[\\d+]}").hasAuthority(CustomerPermission.HIGHLIGHT_READ.getPermission())
                .antMatchers("/api/v1/video/all").hasAuthority(CustomerPermission.HIGHLIGHT_READ.getPermission())

                .antMatchers("/api/v1/video").hasAuthority(CustomerPermission.HIGHLIGHT_WRITE.getPermission())
                .antMatchers("/manage/highlights").hasAuthority(CustomerPermission.HIGHLIGHT_WRITE.getPermission())
                .antMatchers("/api/v1/video/manage/all").hasAuthority(CustomerPermission.HIGHLIGHT_WRITE.getPermission())

                .antMatchers("/movie-info").permitAll()
                .antMatchers("/like-video/{video-info-id:[\\d+]}").permitAll()
                .antMatchers("/subscribe").permitAll()
//                .antMatchers("/api/v1/video/**").permitAll()
//                .antMatchers("/auth/logout").permitAll()
//                .antMatchers("/auth/login-request").permitAll()
//                .antMatchers()
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
