package com.example.quan_ly_phong_tro.service;

import com.example.quan_ly_phong_tro.model.PhongTro;
import com.example.quan_ly_phong_tro.model.HinhThucThanhToan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongTroDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM phong_tro";
    private static final String SQL_SEARCH_BY_KEYWORD =
            "SELECT * FROM phong_tro WHERE ma_phong LIKE ? OR ten_nguoi_thue LIKE ? OR so_dien_thoai LIKE ?";
    private static final String SQL_INSERT =
            "INSERT INTO phong_tro (ten_nguoi_thue, so_dien_thoai, ngay_bat_dau, hinh_thuc_thanh_toan_id, ghi_chu) VALUES (?, ?, ?, ?, ?)";
    // Xóa nhiều
    private static final String SQL_DELETE_MANY_PREFIX = "DELETE FROM phong_tro WHERE ma_phong IN (";

    public List<PhongTro> getAll() {
        List<PhongTro> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            HinhThucThanhToanDAO htttDAO = new HinhThucThanhToanDAO();
            while (rs.next()) {
                HinhThucThanhToan httt = htttDAO.findById(rs.getInt("hinh_thuc_thanh_toan_id"));
                PhongTro pt = new PhongTro(
                        rs.getInt("ma_phong"),
                        rs.getString("ten_nguoi_thue"),
                        rs.getString("so_dien_thoai"),
                        rs.getDate("ngay_bat_dau"),
                        httt,
                        rs.getString("ghi_chu")
                );
                list.add(pt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PhongTro> search(String keyword) {
        List<PhongTro> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SEARCH_BY_KEYWORD)) {

            String kw = "%" + keyword + "%";
            ps.setString(1, kw);
            ps.setString(2, kw);
            ps.setString(3, kw);

            try (ResultSet rs = ps.executeQuery()) {
                HinhThucThanhToanDAO htttDAO = new HinhThucThanhToanDAO();
                while (rs.next()) {
                    HinhThucThanhToan httt = htttDAO.findById(rs.getInt("hinh_thuc_thanh_toan_id"));
                    PhongTro pt = new PhongTro(
                            rs.getInt("ma_phong"),
                            rs.getString("ten_nguoi_thue"),
                            rs.getString("so_dien_thoai"),
                            rs.getDate("ngay_bat_dau"),
                            httt,
                            rs.getString("ghi_chu")
                    );
                    list.add(pt);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(PhongTro phongTro) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, phongTro.getTenNguoiThue());
            ps.setString(2, phongTro.getSoDienThoai());
            ps.setDate(3, new Date(phongTro.getNgayBatDau().getTime()));
            ps.setInt(4, phongTro.getHinhThucThanhToan().getId());
            ps.setString(5, phongTro.getGhiChu());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMany(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) return;
        StringBuilder sql = new StringBuilder(SQL_DELETE_MANY_PREFIX);
        for (int i = 0; i < ids.size(); i++) {
            sql.append("?");
            if (i < ids.size() - 1) sql.append(",");
        }
        sql.append(")");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < ids.size(); i++) {
                ps.setInt(i + 1, ids.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}