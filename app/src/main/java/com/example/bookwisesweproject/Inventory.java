package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity {

    ListView genreListview;
    ArrayAdapter<String> genreadapter;
    ArrayList<String> genres = new ArrayList<>();
    DatabaseReference gref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inventory);

        genreListview = findViewById(R.id.inventorygenreListView);
        genreadapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,genres);
        genreListview.setAdapter(genreadapter);

        gref = FirebaseDatabase.getInstance().getReference().child("books");

        gref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                genres.clear();
                for(DataSnapshot snapshot2: snapshot.getChildren()){
                    String genre_value = snapshot2.getKey();
                    genres.add(genre_value);
                }
                genreadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });//End of gref


        //click korle oi genre er nam onno activity te pathay de jekhan theke shob boi er list pawa jabe.
        genreListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Inventory.this, InventoryBookList.class);
                //String test = cars.get(position);
                intent.putExtra("genre_name",genres.get(position));
                startActivity(intent);
            }
        });

        //end of On-Create
    }
}