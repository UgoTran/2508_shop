package com.t3h.eshop.config;

import com.t3h.eshop.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] PREMIT_URLS = {
            "/", "/api/public/**", "/index",
            "/index2", "/login/**",
            "/css/**", "/js/**", "/images/**"
    };

    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Các trang public (không cần đăng nhập)
                        .requestMatchers(PREMIT_URLS).permitAll()

                        // Phân quyền login theo folder
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/client/**").hasAnyRole("ADMIN", "USER")

                        // Mọi request khác đều cần login
                        .anyRequest().authenticated()
                )
                .formLogin(f -> f
                        .loginPage("/login/client")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            String loginType = request.getParameter("loginType");

                            boolean isAdmin = authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

                            // User cố tình đăng nhập vào form admin
                            if ("ADMIN".equalsIgnoreCase(loginType) && !isAdmin) {
                                request.getSession().invalidate(); // hủy sesion
                                response.sendRedirect("/login/admin?roleError=true");
                                return;
                            }

                            response.sendRedirect(isAdmin ? "/admin" : "/"); // nếu là admin cho qua trang admin
                        })
                        .failureHandler(customFailureHandler())
                        .permitAll()
                )
                .logout(l -> l
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login/client?logout=true")
                        .permitAll()
                );

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }

    @Bean
    public AuthenticationFailureHandler customFailureHandler() {
        return (request, response, exception) -> {
            String username = request.getParameter("username");
            String loginType = request.getParameter("loginType");

            String baseUrl;
            if ("ADMIN".equalsIgnoreCase(loginType)) {
                baseUrl = "/login/admin";
            } else {
                baseUrl = "/login/client";
            }

            String encodedUsername = username != null ? URLEncoder.encode(username, StandardCharsets.UTF_8) : "";

            response.sendRedirect(baseUrl + "?error=true&username=" + encodedUsername);
        };
    }

}