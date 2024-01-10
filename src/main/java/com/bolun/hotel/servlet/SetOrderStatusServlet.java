package com.bolun.hotel.servlet;

import com.bolun.hotel.dto.ReadOrderDto;
import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.entity.enums.OrderStatus;
import com.bolun.hotel.entity.enums.Role;
import com.bolun.hotel.helper.JspHelper;
import com.bolun.hotel.service.OrderService;
import com.bolun.hotel.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.bolun.hotel.entity.enums.Role.ADMIN;
import static com.bolun.hotel.helper.UrlPath.*;

@WebServlet(SET_ORDER_STATUS)
public class SetOrderStatusServlet extends HttpServlet {

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath(SET_ORDER_STATUS))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String status = req.getParameter("status");
       String orderId = req.getParameter("orderId");
       String userId = req.getParameter("userId");

       orderService.updateStatusByOrderId(Long.parseLong(orderId), OrderStatus.valueOf(status.toUpperCase()));
       ReadUserDto readUserDto = (ReadUserDto) req.getSession().getAttribute("user");

       if (readUserDto.getRole() == ADMIN) {
           resp.sendRedirect(USER_ORDERS.concat("?userId=" + userId));
       } else {
           resp.sendRedirect(APARTMENT);
       }
    }
}
