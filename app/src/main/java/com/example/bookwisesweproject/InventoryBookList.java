package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InventoryBookList extends AppCompatActivity {

    ListView bookListview;
    ArrayAdapter<String> bookadapter;
    ArrayList<String> books = new ArrayList<>();
    ArrayList<String> isbns = new ArrayList<>();
    DatabaseReference gref;
    String genre_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_book_list);

        bookListview = findViewById(R.id.inventorybooksListView);
        bookadapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, books);
        bookListview.setAdapter(bookadapter);

        Intent intent = getIntent();
        genre_name = intent.getStringExtra("genre_name");

        gref = FirebaseDatabase.getInstance().getReference().child("books").child(genre_name);

        // Resume from here
        gref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isbns.clear(); books.clear();
                for(DataSnapshot snapshot2: snapshot.getChildren()){
                    String isbn_value = snapshot2.getKey();
                    String book_name = snapshot2.child("author").getValue(String.class);
                    isbns.add(isbn_value);
                    books.add(book_name);
                }
                bookadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });//End of gref

        //boi er details onno page e dekhabe
        bookListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(InventoryBookList.this, InventoryBookDetails.class);
                Intent intent = new Intent(InventoryBookList.this, Rent_Book.class);
                //String test = cars.get(position);
                intent.putExtra("isbn_name",isbns.get(position));
                intent.putExtra("book_name",books.get(position));
                startActivity(intent);
            }
        });
    }
}