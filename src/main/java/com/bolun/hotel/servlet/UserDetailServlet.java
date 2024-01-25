package com.bolun.hotel.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import com.bolun.hotel.dto.UserDetailDto;
import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.helper.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bolun.hotel.service.UserDetailService;
import com.bolun.hotel.service.impl.UserDetailServiceImpl;
import com.bolun.hotel.exception.UserDetailValidationException;

import java.io.IOException;

import static com.bolun.hotel.helper.UrlPath.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(USER_DETAIL)
public class UserDetailServlet extends HttpServlet {
    private final UserDetailService userDetailService = UserDetailServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(USER_DETAIL))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto user = (ReadUserDto) req.getSession().getAttribute("user");

        UserDetailDto userDetailDto = new UserDetailDto(
                user.getId(),
                req.getParameter("telephone"),
                req.getPart("photo"),
                req.getParameter("birthdate"),
                req.getParameter("amount")
        );

        try {
            userDetailService.create(userDetailDto);
            resp.sendRedirect(ORDER);
        } catch (UserDetailValidationException ex) {
            req.setAttribute("errors", ex.getErrors());
            doGet(req, resp);
        }
    }
}
