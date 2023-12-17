package com.bolun.hotel.servlet;

import com.bolun.hotel.dao.OrderDao;
import com.bolun.hotel.dao.impl.OrderDaoImpl;
import com.bolun.hotel.dto.ReadOrderDto;
import com.bolun.hotel.dto.ReadUserDto;
import com.bolun.hotel.helper.UrlPath;
import com.bolun.hotel.service.OrderService;
import com.bolun.hotel.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.bolun.hotel.helper.UrlPath.DOWNLOAD;

@WebServlet(DOWNLOAD)
public class DownloadServlet extends HttpServlet {

    private final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"orders.txt\"");
        resp.setContentType("text/plain");

        ReadUserDto readUserDto = (ReadUserDto) req.getSession().getAttribute("user");
        List<ReadOrderDto> readUserDtoList = orderService.findAllByUserId(readUserDto.getId());

        try (PrintWriter printWriter = resp.getWriter()) {
            for (ReadOrderDto readOrderDto : readUserDtoList) {
                printWriter.write(readOrderDto.toString().concat(System.lineSeparator()));
            }
        }
    }
}
