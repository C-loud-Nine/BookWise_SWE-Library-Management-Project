package com.example.bookwisesweproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class User_Dash extends AppCompatActivity implements View.OnClickListener {


    //Variables

    DrawerLayout drawerLayout;
    //    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;

    TextView textView;
    CardView c1,c2,c3,c4,c5,c6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dash);

        /*---------------------Hooks------------------------*/

        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView=findViewById(R.id.navigationView);
        textView=findViewById(R.id.textView);
        toolbar=findViewById(R.id.toolbar);
        c1=findViewById(R.id.rentbook);
        c2=findViewById(R.id.inventory);
        c3=findViewById(R.id.rentalhistory);
        c4=findViewById(R.id.wishabook);
        c5=findViewById(R.id.profile);
        c6=findViewById(R.id.returnbook);


        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);



    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        if(v.getId()==R.id.rentbook){
            Intent intent = new Intent(User_Dash.this, Rent_Book.class);
            startActivity(intent);
        } else if (v.getId() == R.id.inventory) {
            Intent intent = new Intent(User_Dash.this, Inventory.class);
            startActivity(intent);
        } else if (v.getId() == R.id.rentalhistory) {
            Intent intent = new Intent(User_Dash.this, Rental_History.class);
            startActivity(intent);
        } else if (v.getId() == R.id.wishabook) {
            Intent intent = new Intent(User_Dash.this, User_Wish_Book.class);
            startActivity(intent);
        } else if (v.getId() == R.id.profile) {
            Intent intent = new Intent(User_Dash.this, Profile.class);
            startActivity(intent);
        } else if (v.getId() == R.id.returnbook) {
            Intent intent = new Intent(User_Dash.this, Return_Book.class);
            startActivity(intent);

        }

    }
}