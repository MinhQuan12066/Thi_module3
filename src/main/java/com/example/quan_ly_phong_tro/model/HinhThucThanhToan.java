package com.example.quan_ly_phong_tro.model;

public class HinhThucThanhToan {
    private int id;
    private String ten;

    public HinhThucThanhToan() {}

    public HinhThucThanhToan(int id, String ten) {
        this.id = id;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}