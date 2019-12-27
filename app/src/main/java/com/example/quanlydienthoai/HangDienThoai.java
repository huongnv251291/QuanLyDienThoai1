package com.example.quanlydienthoai;

import android.os.Parcel;
import android.os.Parcelable;

public class HangDienThoai implements Parcelable {
    private int id;
    private String ma, ten, mota;

    public HangDienThoai(String ma, String ten, String mota) {
        this.ma = ma;
        this.ten = ten;
        this.mota = mota;
    }

    public HangDienThoai() {
    }

    protected HangDienThoai(Parcel in) {
        id = in.readInt();
        ma = in.readString();
        ten = in.readString();
        mota = in.readString();
    }

    public static final Creator<HangDienThoai> CREATOR = new Creator<HangDienThoai>() {
        @Override
        public HangDienThoai createFromParcel(Parcel in) {
            return new HangDienThoai(in);
        }

        @Override
        public HangDienThoai[] newArray(int size) {
            return new HangDienThoai[size];
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

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
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
        dest.writeString(mota);
    }
}
