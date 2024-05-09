package com.example.bookwisesweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InventoryBookDetails extends AppCompatActivity {
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_book_details);

        t1 = findViewById(R.id.textView8);
        t2 = findViewById(R.id.textView9);

        Intent intent = getIntent();
        String isbn_name = intent.getStringExtra("isbn_name");
        String book_name = intent.getStringExtra("book_name");

        t1.setText(book_name);
        t2.setText(isbn_name);
    }
}