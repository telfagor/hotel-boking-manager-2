package com.bolun.hotel.servlet;

import com.bolun.hotel.dto.CreateApartmentDto;
import com.bolun.hotel.exception.ApartmentValidationException;
import com.bolun.hotel.helper.JspHelper;
import com.bolun.hotel.service.ApartmentService;
import com.bolun.hotel.service.ApartmentStatusService;
import com.bolun.hotel.service.ApartmentTypeService;
import com.bolun.hotel.service.impl.ApartmentServiceImpl;
import com.bolun.hotel.service.impl.ApartmentStatusServiceImpl;
import com.bolun.hotel.service.impl.ApartmentTypeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.bolun.hotel.helper.UrlPath.ADD_APARTMENT;
import static com.bolun.hotel.helper.UrlPath.ORDER;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(ADD_APARTMENT)
public class AddApartmentServlet extends HttpServlet {

    private final ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
    private final ApartmentStatusService apartmentStatusService = ApartmentStatusServiceImpl.getInstance();
    private final ApartmentTypeService apartmentTypeService = ApartmentTypeServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("statuses", apartmentStatusService.findAll());
        req.setAttribute("types", apartmentTypeService.findAll());
        req.getRequestDispatcher(JspHelper.getPath(ADD_APARTMENT))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateApartmentDto createApartmentDto = new CreateApartmentDto(
                req.getParameter("number_of_rooms"),
                req.getParameter("number_of_seats"),
                req.getParameter("price_per_hour"),
                req.getPart("photo"),
                req.getParameter("type")
        );

        try {
            apartmentService.save(createApartmentDto);
            resp.sendRedirect(ORDER);
        } catch (ApartmentValidationException ex) {
            req.setAttribute("errors", ex.getErrors());
            doGet(req, resp);
        }
    }
}
