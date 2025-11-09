// java
package com.curso.bruno.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.curso.bruno.security.JWTAuthenticationFilter;
import com.curso.bruno.security.JWTAuthorizationFilter;
import com.curso.bruno.security.JWTUtil;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private Environment env;

    private UserDetailsService userDetailsService;

    private JWTUtil jwtUtil;

    public SecurityConfig(Environment env, UserDetailsService userDetailsService, JWTUtil jwtUtil) {
		this.env = env;
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}

    private static final String[] PUBLIC_MATCHER = { "/h2-console/**" };
    private static final String[] PUBLIC_MATCHER_GET = { "/produtos/**", "/categorias/**", "/estados/**" };
    private static final String[] PUBLIC_MATCHER_POST = { "/clientes", "/auth/forgot/**" };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        }

        // deepcode ignore DisablesCSRFProtection: This is a REST API and CSRF protection is not needed.
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, PUBLIC_MATCHER_GET).permitAll()
                .requestMatchers(HttpMethod.POST, PUBLIC_MATCHER_POST).permitAll()
                .requestMatchers(PUBLIC_MATCHER).permitAll()
                .anyRequest().authenticated()
            );

        http.addFilter(new JWTAuthenticationFilter(authManager, jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authManager, jwtUtil, userDetailsService));

        return http.build();
    }

   
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth, BCryptPasswordEncoder bCryptPwdEncoder) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPwdEncoder);
    }

  
}
