package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView emailtv, nametv, numbertv, booknotv;
    MaterialButton backbtn;
    DatabaseReference uref, bref;
    String uid = FirebaseAuth.getInstance().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        emailtv = findViewById(R.id.titlemail);
        nametv = findViewById(R.id.profileName);
        numbertv = findViewById(R.id.profileBranch);
        booknotv = findViewById(R.id.profiletotalbooks);
        backbtn = findViewById(R.id.backButton);
        uref = FirebaseDatabase.getInstance().getReference().child("user").child(uid);
        bref = FirebaseDatabase.getInstance().getReference().child("rents");

        uref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                emailtv.setText(snapshot.child("email").getValue(String.class));
                nametv.setText(snapshot.child("name").getValue(String.class));
                numbertv.setText(snapshot.child("phone").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(uid))
                {
                    booknotv.setText(String.valueOf(snapshot.child(uid).getChildrenCount()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}