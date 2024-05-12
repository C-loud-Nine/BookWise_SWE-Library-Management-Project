package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.Objects;

public class Update_Book extends AppCompatActivity {
    TextInputEditText genredt, autedt, bookedt, isbnedt;
    MaterialButton updatebtn, delbtn;
    String genre_name, author_name, isbn_name, book_name;
    String genre, author, isbn, book;
    DatabaseReference gref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_book);

        genredt = findViewById(R.id.genre);
        autedt = findViewById(R.id.author);
        bookedt = findViewById(R.id.book_name);
        isbnedt = findViewById(R.id.isbn);
        updatebtn = findViewById(R.id.btnupdatebook);
        delbtn = findViewById(R.id.deletebtn);
        gref = FirebaseDatabase.getInstance().getReference().child("books");

        Intent intent = getIntent();
        genre_name = intent.getStringExtra("genre_name");
        isbn_name = intent.getStringExtra("isbn_name");
        book_name = intent.getStringExtra("book_name");
        author_name = intent.getStringExtra("author_name");

        if(genre_name!=null)
        {
            genredt.setText(genre_name);
            autedt.setText(author_name);
            bookedt.setText(book_name);
            isbnedt.setText(isbn_name);
        }

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genre = genredt.getText().toString().trim();
                author = autedt.getText().toString().trim();
                book = bookedt.getText().toString().trim();
                isbn = isbnedt.getText().toString().trim();

                if(genre.equals(""))
                {
                    genredt.setError("Required");
                    genredt.requestFocus();
                    return;
                }
                else if(author.equals(""))
                {
                    autedt.setError("Required");
                    autedt.requestFocus();
                    return;
                }
                else if(book.equals(""))
                {
                    bookedt.setError("Required");
                    bookedt.requestFocus();
                    return;
                }
                else if(isbn.equals(""))
                {
                    isbnedt.setError("Required");
                    isbnedt.requestFocus();
                    return;
                }
                gref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int checker = 0;
                        if(snapshot.hasChild(genre))
                        {
                            if(snapshot.child(genre).hasChild(isbn))
                            {
                                //Toast.makeText(Update_Book.this, "Book no "+isbn+" is available", Toast.LENGTH_SHORT).show();
                                for(DataSnapshot booksnap: snapshot.child(genre).child(isbn).child("barcode").getChildren())
                                {
                                    if(Objects.equals(booksnap.getValue(String.class), "booked"))
                                    {
                                        checker = 1;
                                        break;
                                    }
                                }
                                //If no one has rented the book
                                if(checker==0)
                                {
                                    gref.child(genre).child(isbn).child("author").setValue(author);
                                    gref.child(genre).child(isbn).child("name").setValue(book)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(Update_Book.this, "Book Updated", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                                //If anyone rented any copy of this book
                                else if(checker==1)
                                {
                                    Toast.makeText(Update_Book.this, "Book rented by User", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                //gref.child(genre).child(isbn).child("author").setValue(author);
                                //gref.child(genre).child(isbn).child("name").setValue(book);
                            }
                            else
                            {
                                Toast.makeText(Update_Book.this, "Isbn "+isbn+" is invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                        //genre doesn't exist
                        else
                        {
                            Toast.makeText(Update_Book.this,genre+" genre doesn't exist", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genre = genredt.getText().toString().trim();
                author = autedt.getText().toString().trim();
                book = bookedt.getText().toString().trim();
                isbn = isbnedt.getText().toString().trim();

                if(genre.equals(""))
                {
                    genredt.setError("Required");
                    genredt.requestFocus();
                    return;
                }
                else if(author.equals(""))
                {
                    autedt.setError("Required");
                    autedt.requestFocus();
                    return;
                }
                else if(book.equals(""))
                {
                    bookedt.setError("Required");
                    bookedt.requestFocus();
                    return;
                }
                else if(isbn.equals(""))
                {
                    isbnedt.setError("Required");
                    isbnedt.requestFocus();
                    return;
                }
                Intent intent1 = new Intent(Update_Book.this,Delete_Book.class);
                intent1.putExtra("isbn_name",isbn);
                intent1.putExtra("genre_name",genre);
                startActivity(intent1);
            }
        });
    }
}