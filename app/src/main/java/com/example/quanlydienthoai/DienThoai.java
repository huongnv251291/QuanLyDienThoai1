package com.example.quanlydienthoai;

import android.os.Parcel;
import android.os.Parcelable;

public class DienThoai implements Parcelable {
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

    protected DienThoai(Parcel in) {
        id = in.readInt();
        ma = in.readString();
        ten = in.readString();
        hangsanxuat = in.readString();
        danhgia = in.readString();
        kichthuoc = in.readString();
        hangsanxuat_id = in.readString();
    }

    public static final Creator<DienThoai> CREATOR = new Creator<DienThoai>() {
        @Override
        public DienThoai createFromParcel(Parcel in) {
            return new DienThoai(in);
        }

        @Override
        public DienThoai[] newArray(int size) {
            return new DienThoai[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(ma);
        dest.writeString(ten);
        dest.writeString(hangsanxuat);
        dest.writeString(danhgia);
        dest.writeString(kichthuoc);
        dest.writeString(hangsanxuat_id);
    }
}
