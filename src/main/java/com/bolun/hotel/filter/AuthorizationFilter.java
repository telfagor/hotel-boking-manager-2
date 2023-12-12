package com.bolun.hotel.filter;

import jakarta.servlet.*;
import com.bolun.hotel.dto.ReadUserDto;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Set;
import java.io.IOException;

import static com.bolun.hotel.helper.UrlPath.*;
import static com.bolun.hotel.entity.enums.Role.USER;
import static com.bolun.hotel.entity.enums.Role.ADMIN;

@WebFilter(ALL)
public class AuthorizationFilter implements Filter {

    private final Set<String> PUBLIC_PATHS = Set.of(
            REGISTRATION,
            LOGIN,
            APARTMENT
    );

    private final Set<String> LOGIN_PATHS = Set.of(
            ORDER,
            USER_DETAIL
    );

    private final Set<String> ADMIN_PATHS = Set.of(
            ADD_APARTMENT
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");

        if (isPublicPath(uri) || (isLoginPath(uri) && isLoginIn(user) && user.getRole() == USER)
                || (isAdminPath(uri) && isLoginIn(user) && user.getRole() == ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            resp.setStatus(403);
            resp.sendRedirect(LOGIN);
        }
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATHS.stream().anyMatch(uri::startsWith);
    }

    private boolean isLoginPath(String uri) {
        return LOGIN_PATHS.stream().anyMatch(uri::startsWith);
    }

    private boolean isAdminPath(String uri) {
        return ADMIN_PATHS.stream().anyMatch(uri::startsWith);
    }

    private boolean isLoginIn(ReadUserDto user) {
        return user != null;
    }
}
