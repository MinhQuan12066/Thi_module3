package com.example.quan_ly_phong_tro.service;

import com.example.quan_ly_phong_tro.model.HinhThucThanhToan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HinhThucThanhToanDAO {
    private static final String SQL_SELECT_ALL = "SELECT id, ten_hinh_thuc FROM hinh_thuc_thanh_toan";
    private static final String SQL_SELECT_BY_ID = "SELECT id, ten_hinh_thuc FROM hinh_thuc_thanh_toan WHERE id = ?";

    public List<HinhThucThanhToan> getAll() {
        List<HinhThucThanhToan> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HinhThucThanhToan httt = new HinhThucThanhToan(rs.getInt("id"), rs.getString("ten_hinh_thuc"));
                list.add(httt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public HinhThucThanhToan findById(int id) {
        HinhThucThanhToan httt = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    httt = new HinhThucThanhToan(rs.getInt("id"), rs.getString("ten_hinh_thuc"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return httt;
    }
}