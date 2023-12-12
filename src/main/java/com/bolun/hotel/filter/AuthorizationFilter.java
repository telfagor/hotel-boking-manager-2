package com.bolun.hotel.filter;

import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.helper.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.bolun.hotel.entity.enums.Role.ADMIN;
import static com.bolun.hotel.entity.enums.Role.USER;
import static com.bolun.hotel.helper.UrlPath.*;

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
            
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");

        if (isPublicPath(uri) || (isLoginPath(uri) && isLoginIn(user) && user.getRole() == USER)
                || (isLoginPath(uri) && isLoginIn(user) && user.getRole() == ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).setStatus(403);
            ((HttpServletResponse) servletResponse).sendRedirect(LOGIN);
        }
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATHS.stream().anyMatch(uri::startsWith);
    }

    private boolean isLoginPath(String uri) {
        return LOGIN_PATHS.stream().anyMatch(uri::startsWith);
    }


    private boolean isLoginIn(ReadUserDto user) {
        return user != null;
    }
}
