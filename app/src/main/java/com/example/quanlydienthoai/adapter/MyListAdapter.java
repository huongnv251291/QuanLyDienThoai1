package com.example.quanlydienthoai.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.quanlydienthoai.DienThoai;
import com.example.quanlydienthoai.R;
import com.example.quanlydienthoai.TuongTacDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends ArrayAdapter<DienThoai> {
    private View.OnClickListener onClickListener;

    public void updateDienThoaiList(List<DienThoai> dienThoaiList) {
        if(mDienThoaiList==null)
        {
            mDienThoaiList = new ArrayList<>();
        }
        mDienThoaiList.clear();
        this.mDienThoaiList .addAll(dienThoaiList);
        notifyDataSetChanged();
    }

    //the list values in the List of type hero
    List<DienThoai> mDienThoaiList;
    TuongTacDatabase mTuongTacDatabase;

    //activity context 
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values 
    public MyListAdapter(Context context, int resource, List<DienThoai> mDienThoai) {
        super(context, resource, mDienThoai);
        this.context = context;
        this.resource = resource;
        this.mDienThoaiList = mDienThoai;
        mTuongTacDatabase = new TuongTacDatabase(context);
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView tvTen = view.findViewById(R.id.tvten);
        TextView tvMa = view.findViewById(R.id.tvma);
        TextView tvDanhGia = view.findViewById(R.id.tvdanhgia);
        TextView tvKichThuoc = view.findViewById(R.id.tvkichthuoc);
        TextView tvHangSx = view.findViewById(R.id.tvhsx);
        ImageView ivXoa = view.findViewById(R.id.ivxoa);
        ImageView ivSua = view.findViewById(R.id.ivsua);
        //getting the hero of the specified position
        final DienThoai dienThoai = mDienThoaiList.get(position);

        //adding values to the list item 
        tvTen.setText("Tên điện thoại : " + dienThoai.getTen());
        tvMa.setText("Mã : " + dienThoai.getMa());
        tvDanhGia.setText("Đánh giá : " + dienThoai.getDanhgia());
        tvKichThuoc.setText("Kích thước : " + dienThoai.getKichthuoc());
        tvHangSx.setText("Hãng sản xuất : " + dienThoai.getHangsanxuat());

        //adding a click listener to the button to remove item from the list
        ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
                view.setTag(dienThoai);
                if(onClickListener!=null){
                    onClickListener.onClick(view);
                }
//
            }
        });
        ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setTag(dienThoai);
                if(onClickListener!=null){
                    onClickListener.onClick(view);
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        //finally returning the view
        return view;
    }

    //this method will remove the item from the list 
    public void xoaDienThoai( final DienThoai dienThoai) {
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert 
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item 
                int soBanGhi = xoaDienThoaiDB(dienThoai);
                if (soBanGhi > 0) {
                    mDienThoaiList.remove(dienThoai);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                dialogInterface.cancel();
            }
        });

        //if response is negative nothing is being done 
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //creating and displaying the alert dialog 
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public int xoaDienThoaiDB(DienThoai dienThoai) {
        int soBanGhi = mTuongTacDatabase.xoaDT(dienThoai.getId());

        return soBanGhi;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void suaDT(DienThoai itemSua) {

    }
}