package com.example.bookwisesweproject.patterns.observer;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bookwisesweproject.User_Notification;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin implements Observable{
    //public List<Observer> observers = new ArrayList<>();
    public String message;
    DatabaseReference uref = FirebaseDatabase.getInstance().getReference()
            .child("observer").child("users");
    DatabaseReference mref = FirebaseDatabase.getInstance().getReference()
            .child("observer");
    private final User_Notification userActivity;

    public Admin(User_Notification userActivity)
    {
        this.userActivity = userActivity;
    }
    @Override
    public void registerObserver(Observer observer) {
        //observers.add(observer);
        uref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.hasChild(observer.getVal()))
                {
                    uref.child(observer.getVal()).setValue("true").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            userActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //userActivity
                                    observer.update();
                                    Toast.makeText(userActivity, "Subscribed", Toast.LENGTH_SHORT).show();
                                    //userActivity.msgtv.setText(observer.getVal());
                                }
                            });
                        }
                    });

                }
                else
                {
                    userActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //userActivity
                            Toast.makeText(userActivity, "Already Subscribed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void removeObserver(Observer observer) {
        //observers.remove(observer);
        uref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(observer.getVal()))
                {
                    uref.child(observer.getVal()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            userActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(userActivity, "Unsubscribed Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void notifyObservers() {
        /*for (Observer observer : observers) {
            observer.update(message);
        }*/
        mref.child("message").setValue(this.message);
    }

    public void postMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}
