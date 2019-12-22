package com.example.quanlydienthoai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;
import static com.example.quanlydienthoai.Database.COLUMN_HANGSANXUAT;

public class TuongTacDatabase {
   private Database database;
   private SQLiteDatabase sqLiteDatabase;

    public TuongTacDatabase(Context context) {
        database = new Database(context);
    }

    //// ham mo database
    public void openConnection() {
        sqLiteDatabase = database.getWritableDatabase();
    }

    //// ham dong database
    public void closeConnection() {
        database.close();
    }

    //// ham them dien thoai vao bang
    public long themDT(DienThoai dt) {
        openConnection();
        ContentValues cv = new ContentValues();

        cv.put(Database.COLUMN_MA, dt.getMa());
        cv.put(Database.COLUMN_NAME, dt.getTen());
        cv.put(Database.COLUMN_HANGSANXUAT, dt.getHangsanxuat());
        cv.put(Database.COLUMN_DANHGIA, dt.getDanhgia());
        cv.put(Database.COLUMN_KICHTHUOC, dt.getKichthuoc());

        long id =
            sqLiteDatabase.insertWithOnConflict(Database.TABLE_DIEN_THOAI, null, cv,
                    SQLiteDatabase.CONFLICT_REPLACE);

        closeConnection();

        return id;
    }

    //// ham sua dien thoai trong bang
    public long suaDT(DienThoai dt) {
        ContentValues cv = new ContentValues();

        cv.put(Database.COLUMN_MA, dt.getMa());
        cv.put(Database.COLUMN_NAME, dt.getTen());
        cv.put(Database.COLUMN_HANGSANXUAT, dt.getHangsanxuat());
        cv.put(Database.COLUMN_DANHGIA, dt.getDanhgia());
        cv.put(Database.COLUMN_KICHTHUOC, dt.getKichthuoc());

        return sqLiteDatabase.update(Database.TABLE_DIEN_THOAI, cv,
            Database.COLUMN_ID + "=" + dt.getId(),
            null);
    }

    //// ham xoa dien thoai trong bang
    public int xoaDT(int id) {
        openConnection();
        int banGhi = sqLiteDatabase.delete(Database.TABLE_DIEN_THOAI, Database.COLUMN_ID + "=" + id,
            null);
        closeConnection();
        return banGhi;
    }

    //// ham lay ra all trong bang
    public ArrayList<DienThoai> getAllPhone() {
        openConnection();
        Cursor cursor =
            sqLiteDatabase.rawQuery("SELECT * FROM " + Database.TABLE_DIEN_THOAI + "", null);
        ArrayList<DienThoai> arr = new ArrayList<>();
        while (cursor.moveToNext()) {
            DienThoai dt = new DienThoai();

            dt.setId(cursor.getInt(cursor.getColumnIndex(Database.COLUMN_ID)));
            dt.setMa(cursor.getString(cursor.getColumnIndex(Database.COLUMN_MA)));
            dt.setTen(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME)));
            dt.setHangsanxuat(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME_HANG)));
            dt.setHangsanxuat_id(
                cursor.getString(cursor.getColumnIndex(Database.COLUMN_HANGSANXUAT)));
            dt.setDanhgia(cursor.getString(cursor.getColumnIndex(Database.COLUMN_DANHGIA)));
            dt.setKichthuoc(cursor.getString(cursor.getColumnIndex(Database.COLUMN_KICHTHUOC)));
            arr.add(dt);
        }
        cursor.close();
        closeConnection();
        return arr;
    }

    public ArrayList<DienThoai> getAllPhoneAndManufactory() {
        openConnection();
        Cursor cursor =
            sqLiteDatabase.rawQuery("SELECT * FROM "
                + Database.TABLE_DIEN_THOAI + " a "
                + " INNER JOIN "
                + Database.TABLE_HANG_DIEN_THOAI + " b "
                + " ON a.hangsanxuat_id=b.hangsanxuat_id", null);
        ArrayList<DienThoai> arr = new ArrayList<>();
       try {
           while (cursor.moveToNext()) {
               DienThoai dt = new DienThoai();

               dt.setId(cursor.getInt(cursor.getColumnIndex(Database.COLUMN_ID)));
               dt.setMa(cursor.getString(cursor.getColumnIndex(Database.COLUMN_MA)));
               dt.setTen(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME)));
               dt.setHangsanxuat(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME_HANG)));
               dt.setHangsanxuat_id(
                       cursor.getString(cursor.getColumnIndex(Database.COLUMN_HANGSANXUAT)));
               dt.setDanhgia(cursor.getString(cursor.getColumnIndex(Database.COLUMN_DANHGIA)));
               dt.setKichthuoc(cursor.getString(cursor.getColumnIndex(Database.COLUMN_KICHTHUOC)));

               arr.add(dt);
           }
       }finally {
           cursor.close();
           closeConnection();
       }

        return arr;
    }

    //// ham them hang dien thoai vao bang
    public long themHDT(HangDienThoai hdt) {
        openConnection();
        ContentValues cv = new ContentValues();

        cv.put(Database.COLUMN_MA_HANG, hdt.getMa());
        cv.put(Database.COLUMN_NAME_HANG, hdt.getTen());
        cv.put(Database.COLUMN_MOTA, hdt.getMota());

        long id =
                sqLiteDatabase.insertWithOnConflict(Database.TABLE_HANG_DIEN_THOAI, null, cv,
                        CONFLICT_REPLACE);
        closeConnection();
        return id;
    }

    //// ham sua hang dien thoai trong bang
    public long suaHDT(HangDienThoai hdt) {
        ContentValues cv = new ContentValues();

        cv.put(Database.COLUMN_MA_HANG, hdt.getMa());
        cv.put(Database.COLUMN_NAME_HANG, hdt.getTen());
        cv.put(Database.COLUMN_MOTA, hdt.getMota());

        return sqLiteDatabase.update(Database.TABLE_HANG_DIEN_THOAI, cv,
                Database.COLUMN_ID + "=" + hdt.getId(),
                null);
    }

    //// ham xoa hang dien thoai trong bang
    public int xoaHDT(int id) {
        openConnection();
        int banGhi = sqLiteDatabase.delete(Database.TABLE_HANG_DIEN_THOAI, Database.COLUMN_ID_HANG + "=" + id,
                null);
        closeConnection();
        return banGhi;
    }

    //// ham lay ra all hang dien thoai trong bang
    public ArrayList<HangDienThoai> getAllManufactory() {
        openConnection();
        Cursor cursor =
                sqLiteDatabase.rawQuery("SELECT * FROM " + Database.TABLE_HANG_DIEN_THOAI + "", null);
        ArrayList<HangDienThoai> arr = new ArrayList<>();
        while (cursor.moveToNext()) {
            HangDienThoai hdt = new HangDienThoai();

            hdt.setId(cursor.getInt(cursor.getColumnIndex(Database.COLUMN_ID_HANG)));
            hdt.setMa(cursor.getString(cursor.getColumnIndex(Database.COLUMN_MA_HANG)));
            hdt.setTen(cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME_HANG)));
            hdt.setMota(cursor.getString(cursor.getColumnIndex(Database.COLUMN_MOTA)));
            arr.add(hdt);
        }
        cursor.close();
        closeConnection();
        return arr;
    }
}
