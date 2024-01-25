package com.bolun.hotel.servlet;

import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.helper.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import com.bolun.hotel.service.OrderService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.bolun.hotel.service.impl.OrderServiceImpl;

import java.io.IOException;

import static com.bolun.hotel.helper.UrlPath.USER_ORDERS;
@WebServlet(USER_ORDERS)
public class UserOrdersServlet extends HttpServlet {

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        Long id = userId != null
                ? Long.parseLong(userId)
                : ((ReadUserDto) req.getSession().getAttribute("user")).getId();

        req.setAttribute("userId", userId);
        req.setAttribute("orders", orderService.findAllByUserId(id));
        req.getRequestDispatcher(JspHelper.getPath(USER_ORDERS))
                .forward(req, resp);
    }
}
