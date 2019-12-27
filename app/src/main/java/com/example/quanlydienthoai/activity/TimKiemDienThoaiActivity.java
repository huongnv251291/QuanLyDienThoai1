package com.example.quanlydienthoai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlydienthoai.DienThoai;
import com.example.quanlydienthoai.R;
import com.example.quanlydienthoai.TuongTacDatabase;
import com.example.quanlydienthoai.adapter.MyListAdapter;

import java.util.List;

import static com.example.quanlydienthoai.activity.SuaDienThoaiActivity.DATA;

/**
 * Created by Haku on 12/28/2019.
 */
public class TimKiemDienThoaiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_RESULT = 101;
    SwipeRefreshLayout swLamMoi;
    ListView lvDienThoai;
    Button ntTimKiem;
    TuongTacDatabase mTuongTacDatabase;
    EditText paramTimKiem;
    List<DienThoai> mDienThoais;
    MyListAdapter adapter;
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dt_tim_kiem);
        getSupportActionBar().setTitle("Tìm kiếm điện thoại");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lvDienThoai = findViewById(R.id.lvDienThoai);
        swLamMoi = findViewById(R.id.swLamMoi);
        paramTimKiem = findViewById(R.id.edt_dk_tim_kiem);
        ntTimKiem = findViewById(R.id.bt_tim_kiem);
        ntTimKiem.setOnClickListener(this);
        swLamMoi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layDanhSachDienThoai();
            }
        });

        mTuongTacDatabase = new TuongTacDatabase(this);

    }

    private void layDanhSachDienThoai() {
        if (TextUtils.isEmpty(paramTimKiem.getText())) {
            Toast.makeText(this, "Nhập điều kiện tìm kiếm", Toast.LENGTH_SHORT).show();
            return;
        }
        mDienThoais = mTuongTacDatabase.getAllPhoneBySize(paramTimKiem.getText() + "");
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
            case R.id.bt_tim_kiem:
                layDanhSachDienThoai();
                break;

            case R.id.ivxoa:
                DienThoai itemXoa = (DienThoai) v.getTag();
                adapter.xoaDienThoai(itemXoa);
                break;
            case R.id.ivsua:
                DienThoai itemSua = (DienThoai) v.getTag();
                Intent suaIntent = new Intent(this, SuaDienThoaiActivity.class);
                suaIntent.putExtra(DATA, itemSua);
                startActivityForResult(suaIntent, REQUEST_CODE_FOR_RESULT);
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
