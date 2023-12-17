package com.bolun.hotel.filter;

import jakarta.servlet.*;
import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.service.ApartmentService;
import com.bolun.hotel.service.impl.ApartmentServiceImpl;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Set;
import java.util.HashSet;
import java.io.IOException;

import static com.bolun.hotel.helper.UrlPath.*;
import static com.bolun.hotel.entity.enums.Role.ADMIN;

@WebFilter(ALL)
public class AuthorizationFilter implements Filter {

    private final ApartmentService apartmentService = ApartmentServiceImpl.getInstance();

    private final Set<String> PUBLIC_PATHS = new HashSet<>();

    {
        PUBLIC_PATHS.add(REGISTRATION);
        PUBLIC_PATHS.add(LOGIN);
        PUBLIC_PATHS.add(APARTMENT);

        PUBLIC_PATHS.addAll(apartmentService.findAllImagesPaths());
    }

    private final Set<String> LOGIN_PATHS = Set.of(
            ORDER,
            USER_DETAIL,
            LOGOUT,
            DOWNLOAD,
            USER_ORDERS
    );

    private final Set<String> ADMIN_PATHS = Set.of(
            ADD_APARTMENT,
            "/setOrderStatus"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");

        if (isPublicPath(uri) || (isLoginPath(uri) && isLoginIn(user))
                || (isAdminPath(uri) && isLoginIn(user) && user.getRole() == ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
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
