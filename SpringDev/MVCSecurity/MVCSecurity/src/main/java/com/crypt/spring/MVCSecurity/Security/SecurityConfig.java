package com.crypt.spring.MVCSecurity.Security;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails John = User.builder().username("John").password("{noop}test123").roles("Employee").build();
//        UserDetails Julie = User.builder().username("Julie").password("{noop}test123").roles("Employee", "Manager").build();
//        UserDetails Susan = User.builder().username("Susan").password("{noop}test123").roles("Employee", "Manager", "Admin").build();
//        return new InMemoryUserDetailsManager(John, Julie, Susan);
//    }
//
//
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer.requestMatchers("/").hasRole("Employee")
                .requestMatchers("/leaders/**").hasRole("Manager")
                .anyRequest().authenticated()).formLogin(form -> form.loginPage("/showMyLoginPage").
                loginProcessingUrl("/authenticateTheUser").permitAll()).logout(logout -> logout.permitAll())
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
        //method chaining -> previous return type -> new param
        return http.build();
    }

}
