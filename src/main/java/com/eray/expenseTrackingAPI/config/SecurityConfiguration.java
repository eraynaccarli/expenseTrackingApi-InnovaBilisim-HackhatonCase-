package com.eray.expenseTrackingAPI.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.eray.expenseTrackingAPI.enums.Role.ADMIN;
import static com.eray.expenseTrackingAPI.enums.Role.USER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                )
                .permitAll()

                .requestMatchers("/v1/expenseTracking/user/admin/**").hasAnyRole(ADMIN.name())
                .requestMatchers("/v1/expenseTracking/user/user/**").hasAnyRole(ADMIN.name(), USER.name())

                .requestMatchers("/v1/expenseTracking/expenses/**").hasAnyRole(ADMIN.name())
                .requestMatchers("/v1/expenseTracking/expenses/user/**").hasAnyRole(ADMIN.name(), USER.name())

                .requestMatchers(GET,"/v1/expenseTracking/user/**").hasAnyAuthority(ADMIN.name(), USER.name())
                .requestMatchers(POST,"/v1/expenseTracking/user/**").hasAnyAuthority(ADMIN.name())
                .requestMatchers(PUT,"/v1/expenseTracking/user/**").hasAnyAuthority(ADMIN.name())
                .requestMatchers(DELETE,"/v1/expenseTracking/user/**").hasAnyAuthority(ADMIN.name())

                .requestMatchers(GET,"/v1/expenseTracking/expenses/user/**").hasAnyAuthority(ADMIN.name(), USER.name())
                .requestMatchers(GET,"/v1/expenseTracking/expenses/**").hasAnyAuthority(ADMIN.name())

                .requestMatchers(POST,"/v1/expenseTracking/expenses/user/**").hasAnyAuthority(ADMIN.name(), USER.name())
                .requestMatchers(POST,"/v1/expenseTracking/expenses/**").hasAnyAuthority(ADMIN.name())

                .requestMatchers(DELETE,"/v1/expenseTracking/expenses/**").hasAnyAuthority(ADMIN.name())
                .requestMatchers(PUT,"/v1/expenseTracking/expenses/**").hasAnyAuthority(ADMIN.name())


                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }

}
