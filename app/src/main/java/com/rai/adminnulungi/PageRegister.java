package com.rai.adminnulungi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class PageRegister extends AppCompatActivity {

    private String nama, alamat, urlfoto, kordinat, notelepon, kebutuhan, kategori, email, password;
    private EditText edtnama, edtalamat, edtnotelepon, edtemail, edtpassword;
    private ImageView uploadfotoproduk;
    private Button daftar;
    private FirebaseAuth auth;
    private StorageReference imageStorage;
    private final int PICK_IMAGE_REQUEST = 1;
    private ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_register);

        edtnama = findViewById(R.id.nama);
        edtalamat = findViewById(R.id.inputalamat);
        edtnotelepon = findViewById(R.id.notelepon);
        edtemail = findViewById(R.id.email);
        edtpassword = findViewById(R.id.password);
        Spinner inputkategori = findViewById(R.id.spnkategori);
        String[] tipetempat = new String[]{"Pilih kategori", "Panti Jompo", "Panti Asuhan", "Komunitas", "Masjid"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, tipetempat
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        inputkategori.setAdapter(spinnerArrayAdapter);
        inputkategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kategori = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        daftar = findViewById(R.id.tomboldaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = edtnama.getText().toString();
                final String alamat = edtalamat.getText().toString();
                final String notelepon = edtnotelepon.getText().toString();
                final String email = edtemail.getText().toString();
                final String password = edtpassword.getText().toString();

                try {
                    if (password.length() > 0 && email.length() > 0) {
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(PageRegister.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(
                                                    PageRegister.this,
                                                    "Authentication Failed",
                                                    Toast.LENGTH_LONG).show();
                                            Log.v("error", task.getResult().toString());
                                        } else {
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference myRef = database.getReference("Detail Tempat").child(auth.getUid());
                                            Tempat tempat = new Tempat(auth.getUid(),email,alamat,kategori,nama,notelepon,urlfoto,kebutuhan,kordinat);
                                            myRef.setValue(tempat);
                                            Intent intent = new Intent(PageRegister.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(
                                PageRegister.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
