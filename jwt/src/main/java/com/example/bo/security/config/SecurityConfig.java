package com.example.bo.security.config;

import com.example.bo.security.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //todo
    @Value("${cors.allowed-origins}")
    private List<String> corsAllowedOrigins;

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    //private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors();

        http.authorizeRequests(authorizeRequestsConfig -> {
            authorizeRequestsConfig.antMatchers(HttpMethod.POST, "/api/token").permitAll();
            authorizeRequestsConfig.anyRequest().authenticated();
        });

        //http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(exceptionHandlingConfig -> exceptionHandlingConfig.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

        return http.build();
    }

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors();

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.authorizeRequests(authorizeRequestsConfig -> {
            authorizeRequestsConfig.antMatchers(HttpMethod.POST, "/api/token").permitAll();
            authorizeRequestsConfig.anyRequest().authenticated();
        });

        http.exceptionHandling(exceptionHandlingConfig -> exceptionHandlingConfig.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

        //http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        //http.logout(AbstractHttpConfigurer::disable);

        return http.build();
    }*/

    /*@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setMaxAge(Duration.ofSeconds(60));

        corsAllowedOrigins.forEach(corsAllowedOrigin -> {
            config.addAllowedOrigin(corsAllowedOrigin);
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            config.addExposedHeader("Content-Disposition");

            source.registerCorsConfiguration("/api/**", config);
        });

        return source;
    }*/
}
