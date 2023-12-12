package com.bolun.hotel.servlet;

import com.bolun.hotel.helper.JspHelper;
import com.bolun.hotel.service.ApartmentService;
import com.bolun.hotel.service.ApartmentStatusService;
import com.bolun.hotel.service.ApartmentTypeService;
import com.bolun.hotel.service.impl.ApartmentServiceImpl;
import com.bolun.hotel.service.impl.ApartmentStatusServiceImpl;
import com.bolun.hotel.service.impl.ApartmentTypeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.bolun.hotel.helper.UrlPath.ADD_APARTMENT;

@WebServlet(ADD_APARTMENT)
public class AddApartmentServlet extends HttpServlet {

    private final ApartmentStatusService apartmentStatusService = ApartmentStatusServiceImpl.getInstance();
    private final ApartmentTypeService apartmentTypeService = ApartmentTypeServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("statuses", apartmentStatusService.findAll());
        req.setAttribute("types", apartmentTypeService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("/addApartment"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
