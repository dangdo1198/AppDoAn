package com.example.expendlistview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expendlistview.Model.CTLKDoanhNghiep;
import com.example.expendlistview.R;

import java.util.List;

public class DeXuatTuChoiAdapter extends RecyclerView.Adapter<DeXuatTuChoiAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private List<CTLKDoanhNghiep> listCtklDoanhNghiep;

    CTLKDoanhNghiep ctlkDoanhNghiep;


    public DeXuatTuChoiAdapter(Context context, Activity activity, List<CTLKDoanhNghiep> listCtklDoanhNghiep ) {
        this.context = context;
        this.activity = activity;
        this.listCtklDoanhNghiep = listCtklDoanhNghiep;
    }

    @NonNull
    @Override
    public DeXuatTuChoiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_de_xuat_tu_choi,parent,false);
        return new DeXuatTuChoiAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeXuatTuChoiAdapter.MyViewHolder holder, int position) {
        CTLKDoanhNghiep ctlkDoanhNghiep = listCtklDoanhNghiep.get(position);

        holder.idTxt.setText(String.valueOf(ctlkDoanhNghiep.getId()));
        holder.tenChuongTrinhTxt.setText(String.valueOf(ctlkDoanhNghiep.getTenChuongTrinh()));
        holder.thoiGianDuKienTxt.setText(String.valueOf(ctlkDoanhNghiep.getThoiGianDuKien()));
        holder.phanHoiTxt.setText(String.valueOf(ctlkDoanhNghiep.getLyDo()));



    }

    @Override
    public int getItemCount() {
        return listCtklDoanhNghiep.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idTxt,tenChuongTrinhTxt, thoiGianDuKienTxt, phanHoiTxt;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxt = itemView.findViewById(R.id.id_txt);
            tenChuongTrinhTxt = itemView.findViewById(R.id.tenchuongtrinh_txt);
            thoiGianDuKienTxt = itemView.findViewById(R.id.thoigiandukien_txt);
            phanHoiTxt = itemView.findViewById(R.id.phanhoi_txt);



        }
    }


}
