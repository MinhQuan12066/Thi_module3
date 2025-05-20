package com.example.quan_ly_phong_tro.servlet;

import com.example.quan_ly_phong_tro.model.PhongTro;
import com.example.quan_ly_phong_tro.service.PhongTroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DanhSachPhongTroServlet", urlPatterns = {"/danhsach"})
public class DanhSachPhongTroServlet extends HttpServlet {
    private PhongTroDAO phongTroDAO = new PhongTroDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<PhongTro> list;
        if (query != null && !query.trim().isEmpty()) {
            list = phongTroDAO.search(query.trim());
        } else {
            list = phongTroDAO.getAll();
        }
        request.setAttribute("listPhongTro", list);

        // Lấy danh sách hình thức thanh toán để hiển thị trong form tạo mới
        request.setAttribute("listHTTT", new com.example.quan_ly_phong_tro.service.HinhThucThanhToanDAO().getAll());

        request.getRequestDispatcher("/danhsach.jsp").forward(request, response);
    }
}