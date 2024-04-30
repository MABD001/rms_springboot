package com.dasa.rms.configurations.security;
import com.dasa.rms.configurations.logs.SL4J;
import com.dasa.rms.configurations.logs.LogLevel4j;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtService;
    private final UserDetailsService userDetailsService;
    @Value("${jwt.secret}")
    private String secret;
    private final SL4J sl4J;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String userEmail;
        final String jwt;

        try {
            for (String str : SecurityConfiguration.urlPermitAll) {
                if (str.contains(request.getServletPath())){
                    filterChain.doFilter(request, response);
                    return;

                }
            }
            if (request.getServletPath().contains("/api/v1/auth")) {
                filterChain.doFilter(request, response);
                return;
            }
            if (request.getServletPath().contains("/v3/api-docs") || request.getServletPath().contains("/swagger-ui")) {
                filterChain.doFilter(request, response);
                return;
            }
            final String authHeader = request.getHeader("Authorization");
            String endpoint = request.getRequestURI();
            if(authHeader==null){
                Enumeration<String> x = request.getHeaderNames();
                while(x.hasMoreElements()) {
                    System.out.println(x.nextElement());
                }
            }
            else{
                System.out.println("Start Header \n"+authHeader+" \n End Header");

            }
            System.out.println(endpoint);
            if (SecurityConfiguration.urlPermitAll.contains(endpoint)) {
                filterChain.doFilter(request, response);
                return;
            }
            if(request.getMethod().equalsIgnoreCase("OPTIONS")){
                filterChain.doFilter(request, response);
                return;

            }
            else{
                if(authHeader==null){
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                StringBuilder logData = new StringBuilder();
                logData.append("API-> " + endpoint);
                logData.append("Response-> Token is required ");
                sl4J.writeLog(LogLevel4j.WARN, JwtFilter.class
                        , "doFilterInternal"
                        , String.valueOf(logData));
                sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Token is required");
                return;
            }
            try {
                jwt = authHeader.substring(7);

                userEmail = jwtService.extractUsername(jwt);
                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        filterChain.doFilter(request, response);
                    }
                } else {
                    StringBuilder logData = new StringBuilder();
                    logData.append("API-> " + endpoint);
                    logData.append("Response-> Invalid token");
                    sl4J.writeLog(LogLevel4j.WARN, JwtFilter.class
                            , "doFilterInternal"
                            , String.valueOf(logData));
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> errorDetails = new HashMap<>();
                    errorDetails.put("message", "Invalid token");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    mapper.writeValue(response.getWriter(), errorDetails);

                }
            } catch (Exception exception) {
                sl4J.writeLog(LogLevel4j.ERROR, JwtFilter.class
                        , "doFilterInternal"
                        , "JWTFILET ISSUE\n" + exception);
                ExceptionResponse(response, "" + exception);
            }
        } catch (JwtException e) {
            sl4J.writeLog(LogLevel4j.ERROR, JwtFilter.class, "doFilterInternal", String.valueOf(e));
            ExceptionResponse(response, "" + e);
        }
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public Map<String, String> getPayload(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        String sessionId = claims.get("sessionId", String.class);

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("username", username);
        valuesMap.put("sessionId", sessionId);
        return valuesMap;
    }

    private boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", message);
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), errorDetails);
    }

    private void ExceptionResponse(HttpServletResponse response, String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", message);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), errorDetails);
    }

}
