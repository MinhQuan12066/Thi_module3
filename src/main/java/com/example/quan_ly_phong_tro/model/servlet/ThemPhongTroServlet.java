package com.example.quan_ly_phong_tro.servlet;

import com.example.quan_ly_phong_tro.model.HinhThucThanhToan;
import com.example.quan_ly_phong_tro.model.PhongTro;
import com.example.quan_ly_phong_tro.service.PhongTroDAO;
import com.example.quan_ly_phong_tro.service.HinhThucThanhToanDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ThemPhongTroServlet", urlPatterns = {"/them"})
public class ThemPhongTroServlet extends HttpServlet {
    private PhongTroDAO phongTroDAO = new PhongTroDAO();
    private HinhThucThanhToanDAO htttDAO = new HinhThucThanhToanDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String ten = request.getParameter("tenNguoiThue");
        String sdt = request.getParameter("soDienThoai");
        String ngay = request.getParameter("ngayBatDau");
        String htttId = request.getParameter("hinhThucThanhToanId");
        String ghiChu = request.getParameter("ghiChu");

        // Validate cơ bản tại server
        String error = null;
        if (ten == null || !ten.matches("^[^\\d\\W]{5,50}$")) {
            error = "Tên người thuê không hợp lệ (5-50 ký tự, không chứa số hoặc ký tự đặc biệt).";
        } else if (sdt == null || !sdt.matches("^\\d{10}$")) {
            error = "Số điện thoại phải gồm 10 chữ số.";
        } else if (ngay == null) {
            error = "Chưa chọn ngày bắt đầu.";
        } else {
            try {
                Date ngayBD = new SimpleDateFormat("dd-MM-yyyy").parse(ngay);
                if (ngayBD.before(new Date())) {
                    error = "Ngày bắt đầu không được là quá khứ.";
                }
            } catch (ParseException e) {
                error = "Định dạng ngày không hợp lệ. (dd-MM-yyyy)";
            }
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/danhsach").forward(request, response);
            return;
        }

        try {
            Date ngayBD = new SimpleDateFormat("dd-MM-yyyy").parse(ngay);
            HinhThucThanhToan httt = htttDAO.findById(Integer.parseInt(htttId));
            PhongTro pt = new PhongTro();
            pt.setTenNguoiThue(ten);
            pt.setSoDienThoai(sdt);
            pt.setNgayBatDau(ngayBD);
            pt.setHinhThucThanhToan(httt);
            pt.setGhiChu(ghiChu);

            phongTroDAO.insert(pt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/danhsach");
    }
}