package com.bolun.hotel.servlet;

import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.helper.JspHelper;
import com.bolun.hotel.service.OrderService;
import com.bolun.hotel.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.bolun.hotel.entity.enums.Role.USER;
import static com.bolun.hotel.helper.UrlPath.USER_ORDERS;

@WebServlet(USER_ORDERS)
public class UserOrdersServlet extends HttpServlet {

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReadUserDto readUserDto = (ReadUserDto) req.getSession().getAttribute("user");

        if (readUserDto.getRole() == USER) {
            req.setAttribute("orders", orderService.findAllByUserId(readUserDto.getId()));
        } else {
            req.setAttribute("orders", orderService.findAll());
        }

        req.getRequestDispatcher(JspHelper.getPath(USER_ORDERS))
                .forward(req, resp);
    }
}
