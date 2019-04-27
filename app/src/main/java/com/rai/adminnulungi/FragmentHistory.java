package com.rai.adminnulungi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FragmentHistory extends Fragment {
    FirebaseAuth auth;

    public FragmentHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fragment_history, parent, false);
        auth = FirebaseAuth.getInstance();

        final ArrayList<Donasi> listnotifikasi =new ArrayList<>();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("List Barang Donasi");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dt : dataSnapshot.getChildren()) {
                    Donasi mDonasi = dt.getValue(Donasi.class);
                    if(mDonasi.getStatusdonasi().equals("Selesai")) {
                        listnotifikasi.add(mDonasi);
                    }
                }

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new AdapterHistory(listnotifikasi, getContext()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return view ;
    }

}
