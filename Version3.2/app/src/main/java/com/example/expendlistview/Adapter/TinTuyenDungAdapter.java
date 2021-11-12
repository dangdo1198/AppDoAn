package com.example.expendlistview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expendlistview.API.ChuongTrinhLienKetApi;
import com.example.expendlistview.API.DoanhNghiepHopTacApi;
import com.example.expendlistview.API.TinTuyenDungApi;
import com.example.expendlistview.Activity.DangTinTuyenDung;
import com.example.expendlistview.Activity.DanhSachDoanhNghiep;
import com.example.expendlistview.Activity.FormCapNhatDoanhNghiep;
import com.example.expendlistview.Activity.FormCapNhatTinTuyenDung;
import com.example.expendlistview.Activity.XemChiTietChuongTrinhLienKet;
import com.example.expendlistview.Activity.XemChiTietTinTuyenDung;
import com.example.expendlistview.Model.CTLKDoanhNghiep;
import com.example.expendlistview.Model.DoanhNghiepHopTac;
import com.example.expendlistview.Model.TinTuyenDung;
import com.example.expendlistview.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TinTuyenDungAdapter extends RecyclerView.Adapter<TinTuyenDungAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private List<TinTuyenDung> listTinTuyenDung;
    int idTinTuyenDung;
    TinTuyenDung tinTuyenDung;



    public TinTuyenDungAdapter(Context context, Activity activity, List<TinTuyenDung> listTinTuyenDung ) {
        this.context = context;
        this.activity = activity;
        this.listTinTuyenDung = listTinTuyenDung;
    }

    @NonNull
    @Override
    public TinTuyenDungAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_tin_tuyen_dung,parent,false);
        return new TinTuyenDungAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinTuyenDungAdapter.MyViewHolder holder, int position) {

        TinTuyenDung tinTuyenDung = listTinTuyenDung.get(position);
        holder.idTxt.setText(String.valueOf(tinTuyenDung.getId()));
        holder.tieuDeTxt.setText(String.valueOf(tinTuyenDung.getTieuDe()));

        holder.chiTietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idTinTuyenDung = Integer.parseInt((String)holder.idTxt.getText());
                getTinTuyenDungByIdXemChiTiet(idTinTuyenDung);
            }
        });
        holder.capNhatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idTinTuyenDung = Integer.parseInt((String)holder.idTxt.getText());
                getTinTuyenDungById(idTinTuyenDung);
            }
        });
        holder.xoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idTinTuyenDung= Integer.parseInt((String)holder.idTxt.getText());
                confirmDialog();
            }
        });




    }

    @Override
    public int getItemCount() {
        return listTinTuyenDung.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idTxt, tieuDeTxt;
        Button chiTietBtn, capNhatBtn, xoaBtn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxt = itemView.findViewById(R.id.id_txt);
           tieuDeTxt = itemView.findViewById(R.id.tieude_txt);
           chiTietBtn = itemView.findViewById(R.id.chitiet_btn);
           capNhatBtn = itemView.findViewById(R.id.capnhat_btn);
           xoaBtn = itemView.findViewById(R.id.xoa_btn);






        }
    }


    public void getTinTuyenDungById(int id){
        TinTuyenDungApi.tinTuyenDungApi.getTinTuyenDungById(id).enqueue(new Callback<TinTuyenDung>() {
            @Override
            public void onResponse(Call<TinTuyenDung> call, Response<TinTuyenDung> response) {
                TinTuyenDung tinTuyenDung = response.body();
                Intent intent = new Intent(context, FormCapNhatTinTuyenDung.class);
                intent.putExtra("tintuyendung",(Serializable) tinTuyenDung);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<TinTuyenDung> call, Throwable t) {
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());
            }
        });




    }

    public void confirmDialog(){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("XÓA TIN TUYỂN DỤNG");
        builder.setMessage("Bạn muốn xóa tin tuyển dụng này?");

        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTinTuyenDung(idTinTuyenDung);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((DangTinTuyenDung)context).clickCallApi();
                    }
                },1000);
                Toast.makeText(context, "Xóa doanh nghiệp thành công", Toast.LENGTH_LONG).show();




            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void deleteTinTuyenDung(int id){
        TinTuyenDungApi.tinTuyenDungApi.deleteTinTuyenDung(id).enqueue(new Callback<TinTuyenDung>() {
            @Override
            public void onResponse(Call<TinTuyenDung> call, Response<TinTuyenDung> response) {

            }

            @Override
            public void onFailure(Call<TinTuyenDung> call, Throwable t) {

            }
        });




    }
    public void getTinTuyenDungByIdXemChiTiet(int id){
        TinTuyenDungApi.tinTuyenDungApi.getTinTuyenDungById(id).enqueue(new Callback<TinTuyenDung>() {
            @Override
            public void onResponse(Call<TinTuyenDung> call, Response<TinTuyenDung> response) {
                TinTuyenDung tinTuyenDung = response.body();
                Intent intent = new Intent(context, XemChiTietTinTuyenDung.class);
                intent.putExtra("xemchitiettintuyendung",(Serializable)tinTuyenDung);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<TinTuyenDung> call, Throwable t) {
                Toast.makeText(context, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
                Log.d("ddgfffffffffffff", t.getMessage());
            }
        });



    }
}