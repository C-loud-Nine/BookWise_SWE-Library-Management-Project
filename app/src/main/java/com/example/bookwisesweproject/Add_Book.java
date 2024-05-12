package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Book extends AppCompatActivity {
    TextInputEditText genredt, autedt, bookedt, isbnedt;
    String genre, author, book, isbn;
    MaterialButton bookbtn;
    DatabaseReference gref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_book);

        genredt = findViewById(R.id.genre);
        autedt = findViewById(R.id.author);
        bookedt = findViewById(R.id.book_name);
        isbnedt = findViewById(R.id.isbn);
        bookbtn = findViewById(R.id.btnaddbook);

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] checker = {0};

                genre = genredt.getText().toString().trim();
                author = autedt.getText().toString().trim();
                book = bookedt.getText().toString().trim();
                isbn = isbnedt.getText().toString().trim();

                if(genre.equals("") || author.equals("") || book.equals("") || isbn.equals(""))
                {
                    Toast.makeText(Add_Book.this, "Wrong Input", Toast.LENGTH_SHORT).show();
                    return;
                }


                gref = FirebaseDatabase.getInstance().getReference().child("books");

                gref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Check if the genre already exists in the dataBase
                        if(snapshot.hasChild(genre))
                        {
                            //Check if the isbn exists. If Yes, then only update barcode part.
                            if(snapshot.child(genre).hasChild(isbn))
                            {
                               /* gref.child(genre).child(isbn).child("author").setValue(author);
                                gref.child(genre).child(isbn).child("author").setValue(author);*/
                                gref.child(genre).child(isbn).child("barcode").push().setValue("available").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Add_Book.this, "New Copy Added", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            //If No, add author, book-name as well along with barcode.
                            else
                            {
                                gref.child(genre).child(isbn).child("author").setValue(author);
                                gref.child(genre).child(isbn).child("name").setValue(book);

                                gref.child(genre).child(isbn).child("barcode").push().setValue("available").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Add_Book.this, "New Book Added", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        else
                        {
                            //If genre doesn't exist, check if isbn exists in existing genres because it shouldn't happen.
                            for(DataSnapshot genresnap: snapshot.getChildren())
                            {
                                if(genresnap.hasChild(isbn))
                                {
                                    checker[0] = 1;
                                    Toast.makeText(Add_Book.this, "ISBN unavailable", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            //If the isbn provided is unique, then this book will be added
                            if(checker[0]==0)
                            {
                                gref.child(genre).child(isbn).child("author").setValue(author);
                                gref.child(genre).child(isbn).child("name").setValue(book);

                                gref.child(genre).child(isbn).child("barcode").push().setValue("available").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Add_Book.this, "New Copy Added", Toast.LENGTH_SHORT).show();
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
        });

    }
}