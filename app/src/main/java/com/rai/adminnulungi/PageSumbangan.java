package com.rai.adminnulungi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PageSumbangan extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sumbangan);

        auth=FirebaseAuth.getInstance();

        final String idbarang = getIntent().getStringExtra("idbarang");
        String idonatur = getIntent().getStringExtra("iddonatur");
        String idpenerima = getIntent().getStringExtra("idpenerima");
        String kategori = getIntent().getStringExtra("kategori");
        String metode = getIntent().getStringExtra("metode");
        String nama = getIntent().getStringExtra("nama");
        String namadonatur = getIntent().getStringExtra("namadonatur");
        final String statusdonasi = getIntent().getStringExtra("statusdonasi");
        String tanggaldonasi = getIntent().getStringExtra("tanggaldonasi");
        String tujuandonasi = getIntent().getStringExtra("tujuandonasi");
        String urlbarang = getIntent().getStringExtra("urlbarang");

        ImageView iv_urlbarang = findViewById(R.id.urlbarang);
        TextView tv_namabarang = findViewById(R.id.namabarang);
        TextView tv_namadonatur = findViewById(R.id.namaorang);
        TextView tv_tgldonasi = findViewById(R.id.tanggaldonasi);
        TextView tv_metode = findViewById(R.id.metode);
        TextView tv_status = findViewById(R.id.statusdonasi);
        Picasso.get().load(urlbarang).into(iv_urlbarang);
        tv_namabarang.setText(nama);
        tv_namadonatur.setText(namadonatur);
        tv_tgldonasi.setText(tanggaldonasi);
        tv_metode.setText(metode);
        tv_status.setText(statusdonasi);

        Button konfirmasi = findViewById(R.id.konfirmasibutton);
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String status = "Selesai";
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mDatabaseRef = database.getReference();
                mDatabaseRef.child("List Barang Donasi").child(idbarang).child("statusdonasi").setValue(status);
                Intent intent = new Intent(PageSumbangan.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
