package com.example.SCMProject.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.SCMProject.Services.SecurityCustomUserDetailImpl;


@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityCustomUserDetailImpl securityCustom;
     /* @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1=User.withUsername("Admin")
        .password("adMin123").roles("ADMIN").build();
        UserDetails user2=User.withUsername("user")
        .password("user123").roles("ADMIN","USER").build();

       var inMemoryUserDetailsManager =new InMemoryUserDetailsManager(user1,user2);
       return inMemoryUserDetailsManager;
    }*/ 
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityCustom);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorize->{
           // authorize.requestMatchers("/home","/register","/services").permitAll();
           authorize.requestMatchers("/user/**").authenticated();
           authorize.anyRequest().permitAll();
        });
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    

}
