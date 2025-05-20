package com.example.quan_ly_phong_tro.servlet;

import com.example.quan_ly_phong_tro.service.PhongTroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "XoaPhongTroServlet", urlPatterns = {"/xoa"})
public class XoaPhongTroServlet extends HttpServlet {
    private PhongTroDAO phongTroDAO = new PhongTroDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("maPhong");
        if (ids != null) {
            List<Integer> listIds = new ArrayList<>();
            for (String id : ids) {
                listIds.add(Integer.parseInt(id));
            }
            phongTroDAO.deleteMany(listIds);
        }
        response.sendRedirect(request.getContextPath() + "/danhsach");
    }
}