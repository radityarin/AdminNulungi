package com.rai.adminnulungi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BeritaPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_page);

        String judul = getIntent().getStringExtra("judul");
        String isi = getIntent().getStringExtra("isi");
        String urlberita = getIntent().getStringExtra("url");


        ImageView ivberita = findViewById(R.id.urlberita);
        Picasso.get().load(urlberita).into(ivberita);
        TextView tvjudul = findViewById(R.id.judulberita);
        TextView tvisi = findViewById(R.id.isiberita);
        tvjudul.setText(judul);
        tvisi.setText(isi);

    }
}
