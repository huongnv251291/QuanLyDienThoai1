package com.example.quanlydienthoai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.quanlydienthoai.Database;
import com.example.quanlydienthoai.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Trang chủ");
        findViewById(R.id.btnDienThoai).setOnClickListener(this);
        findViewById(R.id.btnHangDienThoai).setOnClickListener(this);
        findViewById(R.id.btnLietKe).setOnClickListener(this);
        Database db = new Database(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDienThoai: {
                startActivity(new Intent(this, DanhSachDTActivity.class));
                break;
            }
            case R.id.btnHangDienThoai: {
                startActivity(new Intent(this, DanhSachHDTActivity.class));
                break;
            }
            case R.id.btnLietKe: {
                break;
            }
        }
    }
}
