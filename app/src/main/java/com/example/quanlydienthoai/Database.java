package com.example.quanlydienthoai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "qldt.sqlite";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_DIEN_THOAI = "dienthoai";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MA = "ma";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_HANGSANXUAT = "hangsanxuat_id";
    public static final String COLUMN_DANHGIA = "danhgia";
    public static final String COLUMN_KICHTHUOC = "kichthuoc";

    public static final String TABLE_HANG_DIEN_THOAI = "hangdienthoai";
    public static final String COLUMN_ID_HANG = "hangsanxuat_id";
    public static final String COLUMN_MA_HANG = "ma";
    public static final String COLUMN_NAME_HANG = "name";
    public static final String COLUMN_MOTA = "mota";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format(
            "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT ,%s TEXT ,%s TEXT,%s TEXT)"
            , TABLE_HANG_DIEN_THOAI
            , COLUMN_ID_HANG
            , COLUMN_MA_HANG
            , COLUMN_NAME_HANG
            , COLUMN_MOTA));
        db.execSQL(String.format(
            "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT ,%s TEXT ,%s TEXT,%s TEXT ,%s TEXT ,%s TEXT, CONSTRAINT fk_dienthoai"
                + " FOREIGN KEY (hangsanxuat_id)"
                + " REFERENCES hangdienthoai(hangsanxuat_id))"
            , TABLE_DIEN_THOAI
            , COLUMN_ID
            , COLUMN_MA
            , COLUMN_NAME
            , COLUMN_HANGSANXUAT
            , COLUMN_DANHGIA
            , COLUMN_KICHTHUOC));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
