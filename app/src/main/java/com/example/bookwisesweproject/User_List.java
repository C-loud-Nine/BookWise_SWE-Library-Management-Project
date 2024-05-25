package com.example.bookwisesweproject;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_List extends AppCompatActivity {

    private ListView userListView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_list);

        userListView = findViewById(R.id.userListView);
        userList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        userListView.setAdapter(adapter);

        fetchUsers();
    }

    private void fetchUsers() {
        DatabaseReference uref = FirebaseDatabase.getInstance().getReference().child("user");

        uref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot usersnap : snapshot.getChildren()) {
                    String email = usersnap.child("email").getValue(String.class);
                    String name = usersnap.child("name").getValue(String.class);
                    String phone = usersnap.child("phone").getValue(String.class);

                    if (email != null && name != null && phone != null) {
                        userList.add("Name: " + name + "\nEmail: " + email + "\nPhone: " + phone);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(User_List.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
