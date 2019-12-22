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
import com.example.quanlydienthoai.DienThoai;
import com.example.quanlydienthoai.R;
import com.example.quanlydienthoai.TuongTacDatabase;

public class ThemDienThoaiActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String DATA = "data";
    private static final String DATA_FAIL = "DATA_FAIL";
    EditText edtMa, edtTen, edtKichThuoc, edtDanhGia, edtHangSanXuat;
    Button btnThem;
    TuongTacDatabase mTuongTacDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dien_thoai);
        getSupportActionBar().setTitle("Thêm điện thoại");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtMa = findViewById(R.id.edtMa);
        edtTen = findViewById(R.id.edtTen);
        edtKichThuoc = findViewById(R.id.edtKichThuoc);
        edtDanhGia = findViewById(R.id.edtDanhGia);
        edtHangSanXuat = findViewById(R.id.edtHangSanXuat);
        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        errorAddData(true);
        return super.onSupportNavigateUp();
    }

    private void errorAddData(boolean isUserCANCELED) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(DATA_FAIL,isUserCANCELED);
        setResult(Activity.RESULT_CANCELED,returnIntent);
        finish();
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnThem: {
                themDienThoai();
                break;
            }
        }
    }

    private void themDienThoai() {
        if (mTuongTacDatabase == null) {
            mTuongTacDatabase = new TuongTacDatabase(this);
        }

        DienThoai dienThoai = getDataForm();
        if (dataIsValid(dienThoai)) {
            long idDienThoai = mTuongTacDatabase.themDT(dienThoai);
            if (idDienThoai == -1) {
                // tra lai ketqua neu them that bai
                Toast.makeText(this, "Thêm thất bại !", Toast.LENGTH_SHORT).show();
                errorAddData(false);
            } else {
                // tra lai ketqua neu them thanh cong
                Toast.makeText(this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra(DATA,dienThoai.getId());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }
    }

    private DienThoai getDataForm() {
        String ma = edtMa.getText().toString();
        String ten = edtTen.getText().toString();
        String kichthuoc = edtKichThuoc.getText().toString();
        String danhgia = edtDanhGia.getText().toString();
        String idHangSx = edtHangSanXuat.getText().toString();

        return new DienThoai(ma, ten, kichthuoc, danhgia, idHangSx);
    }

    private boolean dataIsValid(DienThoai dienThoai) {

        boolean isValid = true;
        if (TextUtils.isEmpty(dienThoai.getMa().trim())) {
            edtMa.setError("Bạn chưa nhập mã");
            isValid = false;
        }
        if (TextUtils.isEmpty(dienThoai.getTen().trim())) {
            edtTen.setError("Bạn chưa nhập tên");
            isValid = false;
        }
        if (TextUtils.isEmpty(dienThoai.getKichthuoc().trim())) {
            edtKichThuoc.setError("Bạn chưa nhập kích thước");
            isValid = false;
        }
        if (TextUtils.isEmpty(dienThoai.getDanhgia().trim())) {
            edtDanhGia.setError("Bạn chưa nhập đánh giá");
            isValid = false;
        }
        if (TextUtils.isEmpty(dienThoai.getHangsanxuat_id().trim())) {
            edtHangSanXuat.setError("Bạn chưa nhập id hãng sản xuất");
            isValid = false;
        }

        return isValid;
    }
}
