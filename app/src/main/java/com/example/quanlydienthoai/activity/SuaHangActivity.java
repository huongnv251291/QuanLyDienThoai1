package com.example.quanlydienthoai.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlydienthoai.HangDienThoai;
import com.example.quanlydienthoai.R;
import com.example.quanlydienthoai.TuongTacDatabase;

public class SuaHangActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String DATA = "data";
    private static final String DATA_FAIL = "DATA_FAIL";
    EditText edtMa, edtTen, edtMota;
    Button btnThem;
    TuongTacDatabase mTuongTacDatabase;
    HangDienThoai hang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hang_dien_thoai);
        getSupportActionBar().setTitle("Sửa hãng điện thoại");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtMa = findViewById(R.id.edtMa);
        edtTen = findViewById(R.id.edtTen);
        edtMota = findViewById(R.id.edtMota);
        btnThem = findViewById(R.id.btnThem);
        btnThem.setText("Sửa");
        btnThem.setOnClickListener(this);
        hang = getIntent().getParcelableExtra(DATA);
        edtMa.setText(hang.getMa());
        edtTen.setText(hang.getTen());
        edtMota.setText(hang.getMota());
    }

    @Override
    public boolean onSupportNavigateUp() {
        errorAddData(true);
        return super.onSupportNavigateUp();
    }

    private void errorAddData(boolean isUserCANCELED) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(DATA_FAIL, isUserCANCELED);
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThem: {
                themHangDienThoai();
                break;
            }
        }
    }

    private void themHangDienThoai() {
        if (mTuongTacDatabase == null) {
            mTuongTacDatabase = new TuongTacDatabase(this);
        }

        HangDienThoai hangDienThoai = getDataForm();
        if (dataIsValid(hangDienThoai)) {
            long idHangDienThoai = mTuongTacDatabase.suaHDT(hangDienThoai);
            if (idHangDienThoai == -1) {
                // tra lai ketqua neu them that bai
                Toast.makeText(this, "Thêm thất bại !", Toast.LENGTH_SHORT).show();
                errorAddData(false);
            } else {
                // tra lai ketqua neu them thanh cong
                Toast.makeText(this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(DATA, idHangDienThoai);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }

    private HangDienThoai getDataForm() {
        String ma = edtMa.getText().toString();
        String ten = edtTen.getText().toString();
        String mota = edtMota.getText().toString();
        HangDienThoai hangDienThoai = new HangDienThoai(ma, ten, mota);
        hangDienThoai.setId(hang.getId());
        return hangDienThoai;
    }

    private boolean dataIsValid(HangDienThoai hangDienThoai) {

        boolean isValid = true;
        if (TextUtils.isEmpty(hangDienThoai.getMa().trim())) {
            edtMa.setError("Bạn chưa nhập mã");
            isValid = false;
        }
        if (TextUtils.isEmpty(hangDienThoai.getTen().trim())) {
            edtTen.setError("Bạn chưa nhập tên");
            isValid = false;
        }
        if (TextUtils.isEmpty(hangDienThoai.getMota().trim())) {
            edtMota.setError("Bạn chưa nhập mô tả");
            isValid = false;
        }

        return isValid;
    }
}
