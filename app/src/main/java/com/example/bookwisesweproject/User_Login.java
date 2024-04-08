package com.example.bookwisesweproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;

public class User_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_login);

        MaterialButton loginButton = findViewById(R.id.btnlogin);
        MaterialButton registerButton = findViewById(R.id.callreg);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login functionality here
                // For demonstration purposes, let's just navigate to another activity
                Intent intent = new Intent(User_Login.this, User_Dash.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the registration activity when registerButton is clicked
                Intent intent = new Intent(User_Login.this, User_Reg.class);
                startActivity(intent);
            }
        });
    }
}
