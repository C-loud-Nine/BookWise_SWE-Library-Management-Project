package com.example.bookwisesweproject.patterns.singleton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoggedSingleton {
    private static DatabaseReference loginInfoReferenceInstance;

    private LoggedSingleton() {
        // private constructor to prevent instantiation
    }

    public static DatabaseReference getInstance() {
        if (loginInfoReferenceInstance == null) {
            loginInfoReferenceInstance = FirebaseDatabase.getInstance().getReference().child("Admin").child("Logged");
        }
        return loginInfoReferenceInstance;
    }
}
