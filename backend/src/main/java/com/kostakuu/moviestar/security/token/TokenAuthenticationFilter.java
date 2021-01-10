package com.kostakuu.moviestar.security.token;

import com.kostakuu.moviestar.contract.service.UserServiceInterface;
import com.kostakuu.moviestar.entity.User;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenHelper tokenHelper;
    private final UserServiceInterface userService;

    public TokenAuthenticationFilter(TokenHelper tokenHelper, UserServiceInterface userService) {
        this.tokenHelper = tokenHelper;
        this.userService = userService;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username;
        String authToken = tokenHelper.getToken(request);

        if (authToken != null) {
            username = tokenHelper.getUsernameFromToken(authToken);
            if (username != null) {
                User user = (User) userService.loadUserByUsername(username);
                if (tokenHelper.validateToken(authToken, user)) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(user);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

}