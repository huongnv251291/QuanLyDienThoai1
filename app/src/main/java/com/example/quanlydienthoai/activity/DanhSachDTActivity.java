package com.example.quanlydienthoai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanlydienthoai.DienThoai;
import com.example.quanlydienthoai.R;
import com.example.quanlydienthoai.TuongTacDatabase;
import com.example.quanlydienthoai.adapter.MyListAdapter;

import java.util.List;

public class DanhSachDTActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_RESULT = 1;
    SwipeRefreshLayout swLamMoi;
    ListView lvDienThoai;
    Button btThem;
    TuongTacDatabase mTuongTacDatabase;
    List<DienThoai> mDienThoais;
    MyListAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dt);
        getSupportActionBar().setTitle("Danh sách điện thoại");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvDienThoai = findViewById(R.id.lvDienThoai);
        swLamMoi = findViewById(R.id.swLamMoi);
        btThem = findViewById(R.id.btThem);
        btThem.setOnClickListener(this);
        swLamMoi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layDanhSachDienThoai();
            }
        });

        mTuongTacDatabase = new TuongTacDatabase(this);

        layDanhSachDienThoai();
    }

    private void layDanhSachDienThoai() {
        mDienThoais = mTuongTacDatabase.getAllPhone();

        if (adapter == null) {
            adapter = new MyListAdapter(this, R.layout.dienthoai, mDienThoais);
            lvDienThoai.setAdapter(adapter);
        }else{
            adapter.updateDienThoaiList(mDienThoais);
        }

        swLamMoi.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btThem) {
            Intent intent = new Intent(this, ThemDienThoaiActivity.class);
            startActivityForResult(intent, REQUEST_CODE_FOR_RESULT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_RESULT) {
            if (resultCode == RESULT_OK) {
                layDanhSachDienThoai();
            }
        }
    }
}
