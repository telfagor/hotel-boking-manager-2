package com.bolun.hotel.servlet;

import com.bolun.hotel.entity.enums.OrderStatus;
import com.bolun.hotel.helper.JspHelper;
import com.bolun.hotel.service.OrderService;
import com.bolun.hotel.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/setOrderStatus")
public class SetOrderStatusServlet extends HttpServlet {

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        req.getRequestDispatcher(JspHelper.getPath("/setOrderStatus"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String status = req.getParameter("status");
       String orderId = req.getParameter("orderId");

       orderService.updateStatusByOrderId(Long.parseLong(orderId), OrderStatus.valueOf(status.toUpperCase()));
    }
}
