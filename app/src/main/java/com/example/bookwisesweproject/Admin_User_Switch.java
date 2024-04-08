package com.example.bookwisesweproject;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;


public class Admin_User_Switch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_user_switch);

        MaterialButton adminButton = findViewById(R.id.adminButton);
        MaterialButton userButton = findViewById(R.id.userButton);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AdminLoginActivity when adminButton is clicked
                Intent intent = new Intent(Admin_User_Switch.this, Admin_Login.class);
                startActivity(intent);
            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to UserLoginActivity when userButton is clicked
                Intent intent = new Intent(Admin_User_Switch.this, User_Login.class);
                startActivity(intent);
            }
        });
    }
}

