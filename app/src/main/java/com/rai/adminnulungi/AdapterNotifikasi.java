package com.rai.adminnulungi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNotifikasi extends RecyclerView.Adapter<AdapterNotifikasi.ViewHolder> {

    private ArrayList<Donasi> listnotifikasi;
    private Context context;

    public AdapterNotifikasi(ArrayList<Donasi> listnotifikasi, Context context) {
        this.listnotifikasi = listnotifikasi;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_notification, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(listnotifikasi.get(position).getUrlbarang()).into(holder.iv_url);
        holder.tv_namaorang.setText(listnotifikasi.get(position).getNamadonatur());
        holder.tv_namabarang.setText(listnotifikasi.get(position).getNama());
        holder.tv_tanggaldonasi.setText(listnotifikasi.get(position).getTgldonasi());
        holder.tv_statusdonasi.setText(listnotifikasi.get(position).getStatusdonasi());
        holder.btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PageSumbangan.class);
                intent.putExtra("idbarang",listnotifikasi.get(position).getIdbarang());
                intent.putExtra("iddonatur",listnotifikasi.get(position).getIddonatur());
                intent.putExtra("idpenerima",listnotifikasi.get(position).getIdpenerima());
                intent.putExtra("kategori",listnotifikasi.get(position).getKategori());
                intent.putExtra("metode",listnotifikasi.get(position).getMetode());
                intent.putExtra("nama",listnotifikasi.get(position).getNama());
                intent.putExtra("namadonatur",listnotifikasi.get(position).getNamadonatur());
                intent.putExtra("statusdonasi",listnotifikasi.get(position).getStatusdonasi());
                intent.putExtra("tanggaldonasi",listnotifikasi.get(position).getTgldonasi());
                intent.putExtra("tujuandonasi",listnotifikasi.get(position).getTujuan());
                intent.putExtra("urlbarang",listnotifikasi.get(position).getUrlbarang());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listnotifikasi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_url;
        private TextView tv_namaorang, tv_namabarang,tv_tanggaldonasi,tv_statusdonasi;
        private Button btnKonfirmasi;
        private LinearLayout llnotif;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_url = (ImageView) itemView.findViewById(R.id.logokategori);
            tv_namaorang = itemView.findViewById(R.id.namaorang);
            tv_namabarang = itemView.findViewById(R.id.namabarang);
            tv_tanggaldonasi = itemView.findViewById(R.id.tanggaldonasi);
            tv_statusdonasi = itemView.findViewById(R.id.statusdonasi);
            btnKonfirmasi = itemView.findViewById(R.id.buttonkonfirmasi);
            llnotif=itemView.findViewById(R.id.llnotif);
        }
    }
}