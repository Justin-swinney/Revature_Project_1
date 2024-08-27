package org.revature.ers.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.revature.ers.dto.auth.LoginResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute("USER") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to access this content!");
            return false;
        }

        LoginResponse loginResponse = (LoginResponse) httpSession.getAttribute("USER");
        if (loginResponse == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to access this content!");
            return false;
        }

        String requestURI = request.getRequestURI();
        String userRole = loginResponse.getRole();

        if (requestURI.startsWith("/manager") && !userRole.equals("MANAGER")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied!");
            return false;
        }
        if (requestURI.startsWith("/employee") && !(userRole.equals("EMPLOYEE") || userRole.equals("MANAGER"))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied!");
            return false;
        }

        return true;
    }
}
