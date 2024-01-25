package com.bolun.hotel.servlet;

import com.bolun.hotel.dto.UserFilter;
import com.bolun.hotel.entity.enums.Gender;
import com.bolun.hotel.entity.enums.Role;
import com.bolun.hotel.helper.JspHelper;
import com.bolun.hotel.service.UserService;
import com.bolun.hotel.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.bolun.hotel.helper.UrlPath.ALL_USERS;

@WebServlet(ALL_USERS)
public class AllUsersServlet extends HttpServlet {
    private int pageNumber;
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("user-search");
        req.setAttribute("firstName", parameter);

        try {
            req.setAttribute("users", userService.findAll(new UserFilter(
                    parameter,
                    req.getParameter("lastName"),
                    req.getParameter("email"),
                    Role.find(req.getParameter("role")).orElse(null),
                    Gender.find(req.getParameter("gender")).orElse(null),
                    Integer.parseInt(req.getParameter("pageSize") == null ? "1" : req.getParameter("pageSize")),
                    ++pageNumber
            )));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher(JspHelper.getPath(ALL_USERS))
                .forward(req, resp);
    }
}
