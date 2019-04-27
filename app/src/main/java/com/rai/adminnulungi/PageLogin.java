package com.rai.adminnulungi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PageLogin extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnMasuk, btnBack;
    private ProgressDialog PD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_login);

        inputEmail = findViewById(R.id.emaillogin);
        inputPassword = findViewById(R.id.passwordlogin);
        btnMasuk = findViewById(R.id.buttonlogin);
        auth = FirebaseAuth.getInstance();

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                PD = new ProgressDialog(PageLogin.this);
                PD.setMessage("Loading...");
                PD.setCancelable(true);
                PD.setCanceledOnTouchOutside(false);
                PD.show();
                try {

                    if (password.length() > 0 && email.length() > 0) {
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(PageLogin.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            PD.dismiss();
                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = auth.getCurrentUser();
                                            Toast.makeText(PageLogin.this, "BERHASIL", Toast.LENGTH_SHORT).show();
                                            final FirebaseAuth auth = FirebaseAuth.getInstance();
                                            Intent i = new Intent(PageLogin.this, MainActivity.class);
                                            startActivity(i);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(PageLogin.this, "GAGAL", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(
                                PageLogin.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    protected void onResume() {
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(PageLogin.this, MainActivity.class));
            finish();
        }
        super.onResume();
    }

}
