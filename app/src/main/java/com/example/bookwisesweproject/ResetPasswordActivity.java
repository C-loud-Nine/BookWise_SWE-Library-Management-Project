package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    TextInputEditText emailedt;
    TextInputLayout emaillayout;
    AppCompatButton resetbtn;
    String email;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reset_password);

        emailedt = findViewById(R.id.email_forgetPass);
        emaillayout = findViewById(R.id.emaillayout_forgetPass);
        resetbtn = findViewById(R.id.resetButton);
        firebaseAuth = FirebaseAuth.getInstance();

        //Reset Password over here
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailedt.getText().toString().trim();
                if(email.isEmpty()){
                    //emailedt.setError("Enter Email");
                    emaillayout.setErrorEnabled(true);
                    emaillayout.setError("Required");
                    emailedt.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //emailedt.setError("Enter a valid email");
                    emaillayout.setErrorEnabled(true);
                    emaillayout.setError("Enter a valid email");
                    emailedt.requestFocus();
                    return;
                }
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ResetPasswordActivity.this, "Password Reset Link has been sent to this email", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(ResetPasswordActivity.this, LogIn.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ResetPasswordActivity.this, "Error "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}