package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.bookwisesweproject.patterns.observer.Admin;
import com.example.bookwisesweproject.patterns.observer.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Notification extends AppCompatActivity {

    public TextView msgtv;
    String uid = FirebaseAuth.getInstance().getUid();
    DatabaseReference messageRef = FirebaseDatabase.getInstance().getReference().child("observer");
    MaterialButton subtn, unsubtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_notification);

        msgtv = findViewById(R.id.msgtv);
        subtn = findViewById(R.id.subscribebtn);
        unsubtn = findViewById(R.id.unsubscribebtn);

        User user = new User(uid,this);

        //msgtv.setText(user.getVal());

        /*messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.update();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        user.update();

        Admin admin = new Admin(this);

        subtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin.registerObserver(user);
                //user.update();
            }
        });
        unsubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin.removeObserver(user);
                msgtv.setText("");
            }
        });
    }
}