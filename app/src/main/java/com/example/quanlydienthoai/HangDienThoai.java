package com.example.quanlydienthoai;

public class HangDienThoai {
    private int id;
    private String ma, ten, mota;

    public HangDienThoai(String ma, String ten, String mota) {
        this.ma = ma;
        this.ten = ten;
        this.mota = mota;
    }

    public HangDienThoai() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
