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

import static com.example.quanlydienthoai.activity.SuaDienThoaiActivity.DATA;

public class DanhSachDTActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_RESULT = 1;
    SwipeRefreshLayout swLamMoi;
    ListView lvDienThoai;
    Button btThem,ntTimKiem;
    TuongTacDatabase mTuongTacDatabase;
    List<DienThoai> mDienThoais;
    MyListAdapter adapter;
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dt);
        getSupportActionBar().setTitle("Danh sách điện thoại");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvDienThoai = findViewById(R.id.lvDienThoai);
        swLamMoi = findViewById(R.id.swLamMoi);
        btThem = findViewById(R.id.btnThem);
        btThem.setOnClickListener(this);
        ntTimKiem = findViewById(R.id.btTimKiem);
        ntTimKiem.setOnClickListener(this);
        ntTimKiem.setVisibility(View.VISIBLE);
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
            adapter.setOnClickListener(this);
            lvDienThoai.setAdapter(adapter);
        } else {
            adapter.updateDienThoaiList(mDienThoais);
        }

        swLamMoi.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThem:
                Intent intent = new Intent(this, ThemDienThoaiActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_RESULT);
                break;

            case R.id.ivxoa:
                DienThoai itemXoa = (DienThoai) v.getTag();
                adapter.xoaDienThoai(itemXoa);
                break;
            case R.id.ivsua:
                DienThoai itemSua = (DienThoai) v.getTag();
                Intent suaIntent = new Intent(this, SuaDienThoaiActivity.class);
                suaIntent.putExtra(DATA,itemSua);
                startActivityForResult(suaIntent, REQUEST_CODE_FOR_RESULT);
                break;
            case R.id.btTimKiem:
                Intent intentTK = new Intent(this, TimKiemDienThoaiActivity.class);
                startActivity(intentTK);
                break;
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
