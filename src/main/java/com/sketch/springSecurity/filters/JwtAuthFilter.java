package com.sketch.springSecurity.filters;

import com.sketch.springSecurity.entities.UserEntity;
import com.sketch.springSecurity.services.AuthService;
import com.sketch.springSecurity.services.JwtService;
import com.sketch.springSecurity.services.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserServiceImpl userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestTokenHeader.split("Bearer ")[1];
        Long userId = jwtService.getUserIdFromToken(token);
        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserEntity user = userService.getUserByUserId(userId);
            UsernamePasswordAuthenticationToken autheticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, null);
            autheticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(autheticationToken);

        }
        filterChain.doFilter(request, response);
        //with the response

    }
}
