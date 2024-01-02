package com.bolun.hotel.servlet;

import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.dto.UserDetailDto;
import com.bolun.hotel.entity.UserDetail;
import com.bolun.hotel.exception.UserDetailValidationException;
import com.bolun.hotel.helper.JspHelper;
import com.bolun.hotel.service.UserDetailService;
import com.bolun.hotel.service.impl.UserDetailServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static com.bolun.hotel.helper.UrlPath.APARTMENT;
import static com.bolun.hotel.helper.UrlPath.PERSONAL_ACCOUNT;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(PERSONAL_ACCOUNT)
public class PersonalAccount extends HttpServlet {

    private final UserDetailService userDetailService = UserDetailServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto readUserDto = (ReadUserDto) req.getSession().getAttribute("user");
        Optional<UserDetail> maybeUserDetail = userDetailService.findById(readUserDto.getId());

        req.setAttribute("userDetail", maybeUserDetail.orElse(null));
        req.getRequestDispatcher(JspHelper.getPath(PERSONAL_ACCOUNT))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto readUserDto = (ReadUserDto) req.getSession().getAttribute("user");
        Part part = req.getPart("photo");

        UserDetailDto userDetailDto = new UserDetailDto(
                readUserDto.getId(),
                req.getParameter("contact-number"),
                part,
                req.getParameter("birthdate"),
                req.getParameter("money")
        );

        try {
            userDetailService.update(userDetailDto);
            resp.sendRedirect(APARTMENT);
        } catch (UserDetailValidationException ex) {
            req.setAttribute("errors", ex.getErrors());
            doGet(req, resp);
        }
    }
}
