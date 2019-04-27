package com.rai.adminnulungi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class FragmentNotification extends Fragment {

//    FirebaseRecyclerAdapter<Donasi, FragmentNotification.DonasiViewHolder> donasiadapter;
//    DatabaseReference donasiRef;
    FirebaseAuth auth;


    public FragmentNotification() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_notification, container, false);auth = FirebaseAuth.getInstance();

        auth = FirebaseAuth.getInstance();

        final ArrayList<Donasi> listnotifikasi =new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("List Barang Donasi");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Donasi mDonasi = dt.getValue(Donasi.class);
                     if(!mDonasi.getStatusdonasi().equals("Selesai")) {
                        listnotifikasi.add(mDonasi);
                    }
                }

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new AdapterNotifikasi(listnotifikasi, getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        donasiRef = FirebaseDatabase.getInstance().getReference().child("List Barang Donasi");
//
//        Query query = donasiRef.orderByChild("idpenerima").equalTo(auth.getUid());
//        FirebaseRecyclerOptions<Donasi> options =
//                new FirebaseRecyclerOptions.Builder<Donasi>()
//                        .setQuery(query, Donasi.class)
//                        .build();
//
//        donasiadapter = new FirebaseRecyclerAdapter<Donasi, FragmentNotification.DonasiViewHolder>(options) {
//            @Override
//            public FragmentNotification.DonasiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                // Create a new instance of the ViewHolder, in this case we are using a custom
//                // layout called R.layout.message for each item
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.card_notification, parent, false);
//
//                return new FragmentNotification.DonasiViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(FragmentNotification.DonasiViewHolder holder, int position, final Donasi model) {
//                holder.display(model.getKategori(), model.getNamadonatur(), model.getTgldonasi(),model.getStatusdonasi(),model.getNama());
////                holder.view.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////                        Intent intent = new Intent(getContext(),produkPage.class);
////                        intent.putExtra("judulproduk",model.getNamaProduk());
////                        intent.putExtra("urlproduk",model.getUrlProduk());
////                        intent.putExtra("deskripsiproduk",model.getDeskripsiProduk());
////                        intent.putExtra("hargaproduk",model.getHargaProduk());
////                        intent.putExtra("kategoriproduk",model.getKategoriProduk());
////                        startActivity(intent);
////                    }
////                });
//            }
//        };
//        recyclerView.setAdapter(donasiadapter);


        return view;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        donasiadapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        donasiadapter.stopListening();
//    }
//
//    public class DonasiViewHolder extends RecyclerView.ViewHolder {
//
//        View view;
//
//        public DonasiViewHolder(View itemView) {
//            super(itemView);
//            view = itemView;
//        }
//
//        public void display(String kategoribarang,String namaorang, String tanggaldonasi, String statusdonasi, String namabarang){
//            ImageView ivlogokategori = view.findViewById(R.id.logokategori);
//            if(kategoribarang.equals("Pakaian")){
//                ivlogokategori.setImageResource(R.drawable.baju);
//            } else if(kategoribarang.equals("Elektronik")){
//                ivlogokategori.setImageResource(R.drawable.elektronik);
//            } else if(kategoribarang.equals("Furnitur")){
//                ivlogokategori.setImageResource(R.drawable.furniture);
//            } else if(kategoribarang.equals("Buku")){
//                ivlogokategori.setImageResource(R.drawable.book);
//            }
//            TextView tvnamabarang = view.findViewById(R.id.namabarang);
//            tvnamabarang.setText(namabarang);
//            TextView tvnamaorang = view.findViewById(R.id.namaorang);
//            tvnamaorang.setText(namaorang);
//            TextView tvtanggaldonasi = view.findViewById(R.id.tanggaldonasi);
//            tvtanggaldonasi.setText(tanggaldonasi);
//            TextView tvstatusdonasi = view.findViewById(R.id.statusdonasi);
//            tvstatusdonasi.setText(statusdonasi);
//        }
//
//    }
}