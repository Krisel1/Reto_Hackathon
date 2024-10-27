package com.hackathon.bankingapp.config;

import com.hackathon.bankingapp.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebConfigSecurity {

    private final AuthenticationProvider authenticationProvider;

    private final AuthTokenFilter authTokenFilter;

    public WebConfigSecurity(AuthenticationProvider authenticationProvider, AuthTokenFilter authTokenFilter) {
        this.authenticationProvider = authenticationProvider;
        this.authTokenFilter = authTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/users/**").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/transactions").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(HttpMethod.GET,"/api/transactions").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/transactions/{id}").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT,"/api/transactions/{id}").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/transactions/{id}").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
