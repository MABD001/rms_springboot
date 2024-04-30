package com.dasa.rms.configurations.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {




        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/account/user/login",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/v2/api-docs/**",
                                "/swagger-resources/**"

                        ).permitAll()
                        .requestMatchers(
                                "/account/registerNew/**",
                                "/user/account/register/**",
                                "/user/account/**",
                                "/account/user/**",
                                "/Menu/**",
                                "/MenuItem/**",
                                "/Customer/**",
                                "/Tables/**",
                                "/Reservation/**",
                                "/Feedback/**"
                        ).permitAll()
//                        .hasAnyAuthority(RoleEnum.USER.name(),RoleEnum.SUPER_ADMIN.name())

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            System.out.println(accessDeniedException.getMessage());
                            response.setContentType("application/json");
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.writeValue(response.getWriter(), "Not authorized");
                        })
                );


        return http.build();
    }

    public static List<String> urlPermitAll = new ArrayList<>();

    static {
        urlPermitAll.add("/v3/api-docs/**");
        urlPermitAll.add("/swagger-ui/**");
        urlPermitAll.add("/v2/api-docs/**");
        urlPermitAll.add("/swagger-resources/**");
        urlPermitAll.add("/account/user/login");
    }
}
