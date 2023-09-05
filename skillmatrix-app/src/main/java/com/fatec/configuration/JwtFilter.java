package com.fatec.configuration;

import com.fatec.exceptions.UnauthorizedException;
import com.fatec.model.User;
import com.fatec.service.AuthService;
import com.fatec.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AuthService authService;
    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractTokenFromRequest(request);
        User user = tokenService.validateToken(token);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(ROLE_PREFIX.concat(user.role().name())));
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (isNull(authorization) || authorization.isBlank() || !authorization.startsWith("Bearer ")) {
            throw new UnauthorizedException("Bearer Token not found.");
        }

        return authorization.replace("Bearer ", "");
    }
}
