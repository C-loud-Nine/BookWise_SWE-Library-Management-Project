package com.example.bookwisesweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Reg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_reg);

        MaterialButton registerButton = findViewById(R.id.btnreg);
        @SuppressLint("WrongViewCast") MaterialButton loginButton = findViewById(R.id.call_login);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.setValue("Hello This is a test!");

//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Perform registration functionality here
//                // For demonstration purposes, let's just navigate to another activity
//                Intent intent = new Intent(User_Reg.this, DashboardActivity.class);
//                startActivity(intent);
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity when loginButton is clicked
                Intent intent = new Intent(User_Reg.this, User_Login.class);
                startActivity(intent);
            }
        });
    }
}
