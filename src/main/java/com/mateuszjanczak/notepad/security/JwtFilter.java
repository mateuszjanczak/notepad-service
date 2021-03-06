package com.mateuszjanczak.notepad.security;

import com.mateuszjanczak.notepad.dto.ErrorResponse;
import com.mateuszjanczak.notepad.users.entity.User;
import com.mateuszjanczak.notepad.users.service.UserService;
import com.mateuszjanczak.notepad.users.web.AuthErrorController;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final AuthErrorController authErrorController;

    @Autowired
    public JwtFilter(UserService userService, JwtProvider jwtProvider, AuthErrorController authErrorController) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.authErrorController = authErrorController;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(checkHeader(header)){
            try {
                String username = jwtProvider.parseHeader(header);
                User user = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                response.addHeader(JwtProvider.AUTHORIZATION_HEADER, jwtProvider.createToken(user.getUsername()));
            } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException ex) {
                ErrorResponse errorResponse = authErrorController.handleJwtException(ex);
                setErrorResponse(response, errorResponse);
                return;
            } catch (UsernameNotFoundException ex) {
                ErrorResponse errorResponse = authErrorController.handleUsernameNotFoundException(ex);
                setErrorResponse(response, errorResponse);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setErrorResponse(HttpServletResponse response, ErrorResponse errorResponse) throws IOException {
        response.setStatus(errorResponse.getErrorCode());
        response.setContentType("application/json");
        response.getOutputStream().write(errorResponse.toJson().getBytes());
    }

    private boolean checkHeader(String header) {
        return !Objects.isNull(header) && header.startsWith(JwtProvider.TOKEN_PREFIX);
    }

}