package com.example.bookwisesweproject.patterns.observer;

import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bookwisesweproject.User_Dash;
import com.example.bookwisesweproject.User_Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User implements Observer{
    private final String uid; // Unique identifier for the user
    private String msg;
    private User_Notification userActivity;
    private User_Dash userDash;
    DatabaseReference mref = FirebaseDatabase.getInstance().getReference()
            .child("observer");

    public User(String uid, User_Notification userActivity) {
        this.uid = uid;
        this.userActivity = userActivity;
    }

    public User(String uid, User_Dash userDash) {
        this.uid = uid;
        this.userDash = userDash;
    }

    @Override
    public void update() {
        // Update the user's UI with the new message
        // This can be done by updating a TextView or sending a notification
        //getMsgFirebase();
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("users").hasChild(uid))
                {
                    msg = String.valueOf(snapshot.child("message").getValue(String.class));

                    //Below part is to change the TextView of the activity file.
                    if(userActivity!=null)
                    {
                        userActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userActivity.msgtv.setText(msg);
                            }
                        });
                    }
                    else if(userDash!=null)
                    {
                        userDash.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userDash.notificationText.setText(msg);
                                userDash.notificationIcon.performClick();
                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public String getVal() {
        return uid;
    }

    /*public void getMsgFirebase()
    {
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String test = String.valueOf(snapshot.child("message").getValue(String.class));
                msg = test;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
