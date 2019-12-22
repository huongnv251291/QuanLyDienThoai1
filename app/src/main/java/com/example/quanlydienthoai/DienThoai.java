package com.example.quanlydienthoai;

public class DienThoai {
    private int id;
    private String ma, ten, hangsanxuat, danhgia;
    private String kichthuoc;
    private String hangsanxuat_id;

    public DienThoai() {
    }

    public DienThoai(String ma, String ten, String hangsanxuat_id, String danhgia,
        String kichthuoc) {
        this.ma = ma;
        this.ten = ten;
        this.hangsanxuat_id = hangsanxuat_id;
        this.danhgia = danhgia;
        this.kichthuoc = kichthuoc;
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

    public String getHangsanxuat() {
        return hangsanxuat;
    }

    public void setHangsanxuat(String hangsanxuat) {
        this.hangsanxuat = hangsanxuat;
    }

    public String getHangsanxuat_id() {
        return hangsanxuat_id;
    }

    public void setHangsanxuat_id(String hangsanxuat_id) {
        this.hangsanxuat_id = hangsanxuat_id;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }
}
