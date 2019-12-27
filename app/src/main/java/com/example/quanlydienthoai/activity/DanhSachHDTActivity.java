package com.example.quanlydienthoai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlydienthoai.HangDienThoai;
import com.example.quanlydienthoai.R;
import com.example.quanlydienthoai.TuongTacDatabase;
import com.example.quanlydienthoai.adapter.ListHangAdapter;

import java.util.List;

import static com.example.quanlydienthoai.activity.SuaDienThoaiActivity.DATA;

public class DanhSachHDTActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_FOR_RESULT = 1;
    SwipeRefreshLayout swLamMoi;
    ListView lvHangDienThoai;
    Button btThem;
    TuongTacDatabase mTuongTacDatabase;
    List<HangDienThoai> mHangDienThoais;
    ListHangAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hdt);
        getSupportActionBar().setTitle("Danh sách điện thoại");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvHangDienThoai = findViewById(R.id.lvHangDienThoai);
        swLamMoi = findViewById(R.id.swLamMoi);
        btThem = findViewById(R.id.btnThem);
        btThem.setOnClickListener(this);
        swLamMoi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layDanhSachHangDienThoai();
            }
        });

        mTuongTacDatabase = new TuongTacDatabase(this);

        layDanhSachHangDienThoai();
    }

    private void layDanhSachHangDienThoai() {
        mHangDienThoais = mTuongTacDatabase.getAllManufactory();

        if (adapter == null) {
            adapter = new ListHangAdapter(this, R.layout.hangdienthoai, mHangDienThoais);
            adapter.setOnClickListener(this);
            lvHangDienThoai.setAdapter(adapter);
        }else{
            adapter.updateHangDienThoaiList(mHangDienThoais);
        }

        swLamMoi.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThem:
                Intent intent = new Intent(this, ThemHangActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_RESULT);
                break;
            case R.id.ivxoa:
                HangDienThoai hangDienThoaiDelete = (HangDienThoai) v.getTag();
                adapter.xoaHangDienThoai(hangDienThoaiDelete);
                break;
            case R.id.ivsua:
                HangDienThoai hangDienThoaiSua = (HangDienThoai) v.getTag();
                Intent suaIntent = new Intent(this, SuaHangActivity.class);
                suaIntent.putExtra(DATA,hangDienThoaiSua);
                startActivityForResult(suaIntent, REQUEST_CODE_FOR_RESULT);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_RESULT) {
            if (resultCode == RESULT_OK) {
                layDanhSachHangDienThoai();
            }
        }
    }

}
