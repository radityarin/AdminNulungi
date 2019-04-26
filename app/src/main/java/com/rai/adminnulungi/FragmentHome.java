package com.rai.adminnulungi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

public class FragmentHome extends Fragment {

    FirebaseRecyclerAdapter<Berita,FragmentHome.BeritaViewHolder> beritaadapter;
    DatabaseReference produkRef;
    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fragment_home, container, false);


        //======================

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        produkRef = FirebaseDatabase.getInstance().getReference().child("berita");

        Query query = produkRef.orderByChild("berita");
        FirebaseRecyclerOptions<Berita> options =
                new FirebaseRecyclerOptions.Builder<Berita>()
                        .setQuery(query, Berita.class)
                        .build();

        beritaadapter = new FirebaseRecyclerAdapter<Berita, FragmentHome.BeritaViewHolder>(options) {
            @Override
            public FragmentHome.BeritaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_news, parent, false);
                return new FragmentHome.BeritaViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(FragmentHome.BeritaViewHolder holder, int position, final Berita model) {
                holder.display(model.getJudul(), model.getUrlfoto(), model.getIsiberita());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),BeritaPage.class);
                        intent.putExtra("judul",model.getJudul());
                        intent.putExtra("url",model.getUrlfoto());
                        intent.putExtra("isi",model.getIsiberita());
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(beritaadapter);

        //=========================
        
        
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        beritaadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        beritaadapter.stopListening();
    }
    public class BeritaViewHolder extends RecyclerView.ViewHolder {

        View view;

        public BeritaViewHolder(View itemView) {
            super(itemView);

            view = itemView;

        }

        public void display(String judulberita ,String urlPhoto, String isiberita){
            TextView judulberitatv = (TextView) view.findViewById(R.id.judulberita);
            judulberitatv.setText(judulberita);
            ImageView fotoberita = (ImageView) view.findViewById(R.id.urlphoto);
            Picasso.get().load(urlPhoto).placeholder(R.drawable.searchbarbg).into(fotoberita);
            TextView isiberitatv = (TextView) view.findViewById(R.id.isiberita);
            isiberitatv.setText(isiberita);
        }

    }

}
