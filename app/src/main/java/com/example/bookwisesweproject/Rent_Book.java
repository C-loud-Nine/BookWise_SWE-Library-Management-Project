package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookwisesweproject.patterns.Book;
import com.example.bookwisesweproject.patterns.LibraryItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Rent_Book extends AppCompatActivity {

    TextView bookt, authort, genret;
    TextInputEditText isbnedt;
    String isbn_num, book_name;
    String isbn; //Be careful with this one
    String uid = FirebaseAuth.getInstance().getUid();
    ListView bookListview;
    ArrayAdapter<String> bookadapter;
    ArrayList<String> barcodes = new ArrayList<>();
    ArrayList<String> avails = new ArrayList<>();
    DatabaseReference gref, uref;
    Button rentbtn;

    List<Map<String, String>> data = new ArrayList<Map<String, String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rent_book);

        bookt = findViewById(R.id.book_name);
        authort = findViewById(R.id.book_author);
        genret = findViewById(R.id.book_genre);
        rentbtn = findViewById(R.id.searchButton);
        isbnedt = findViewById(R.id.bookNumberEditText);
        bookListview = findViewById(R.id.booklistview);

        /*bookadapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, barcodes);
        bookListview.setAdapter(bookadapter);*/

        //For showing both barCode and availability
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"main", "sub"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});
        bookListview.setAdapter(adapter);


        Intent intent = getIntent();
        isbn_num = intent.getStringExtra("isbn_name");
        book_name = intent.getStringExtra("book_name");

        if(isbn_num!=null){
            isbnedt.setText(isbn_num);
            // Button ta ekbar click hobe, eita korte chaisilam
            //rentbtn.performClick();
        }

        //This button shows the example of Abstraction-Occurence Pattern
        rentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.ASSERT, String.valueOf(22),"Eta porjonto aise thikthak");
                isbn = isbnedt.getText().toString().trim();
                if(!isbn.equals("")){
                    gref = FirebaseDatabase.getInstance().getReference().child("books");
                    gref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot booksnap: snapshot.getChildren())
                            {
                                if(booksnap.hasChild(isbn))
                                {
                                    //Here book is the abstraction
                                    Book book = new Book(booksnap.child(isbn).child("author").getValue(String.class),booksnap.child(isbn).child("name").getValue(String.class),isbn,booksnap.getKey());
                                    bookt.setText(book.name);
                                    authort.setText(book.author);
                                    genret.setText(book.genre);
                                    barcodes.clear();
                                    avails.clear();
                                    data.clear();

                                    for(DataSnapshot bcodesnap: booksnap.child(isbn).child("barcode").getChildren())
                                    {
                                        //Here LibraryItem is the occurrence
                                        LibraryItem libraryItem = new LibraryItem(bcodesnap.getKey(), book,bcodesnap.getValue(String.class));
                                        barcodes.add(libraryItem.barcode);
                                        avails.add(libraryItem.avail);
                                    }
                                    for (int i = 0; i < barcodes.size(); i++) {
                                        Map<String, String> datum = new HashMap<String, String>(2);
                                        datum.put("main", barcodes.get(i));
                                        datum.put("sub", avails.get(i)); // replace 'codes' with your sub-items list
                                        data.add(datum);
                                    }
                                    //bookadapter.notifyDataSetChanged();
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        bookListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(new ContextThemeWrapper(Rent_Book.this, R.style.AlertDialogCustom));
                alert.setTitle("Rent");
                alert.setMessage("Rent this book?");
                alert.setIcon(R.drawable.rent);
                alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(Rent_Book.this, "Works", Toast.LENGTH_SHORT).show();
                        gref = FirebaseDatabase.getInstance().getReference().child("books");
                        uref = FirebaseDatabase.getInstance().getReference().child("rents");
                        gref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot genresnap: snapshot.getChildren())
                                {
                                    if(genresnap.hasChild(isbn))
                                    {
                                        if(Objects.equals(genresnap.child(isbn).child("barcode").child(barcodes.get(position)).getValue(String.class), "booked"))
                                        {
                                            Toast.makeText(Rent_Book.this, "Already Booked", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        gref.child(Objects.requireNonNull(genresnap.getKey())).child(isbn)
                                                .child("barcode").child(barcodes.get(position)).setValue("booked");
                                        avails.set(position, "booked");
                                        data.clear();
                                        for (int i = 0; i < barcodes.size(); i++) {
                                            Map<String, String> datum = new HashMap<String, String>(2);
                                            datum.put("main", barcodes.get(i));
                                            datum.put("sub", avails.get(i)); // replace 'codes' with your sub-items list
                                            data.add(datum);
                                        }
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(Rent_Book.this, "Book Rent Successful", Toast.LENGTH_SHORT).show();

                                        uref.child(uid).child(barcodes.get(position)).setValue(isbn);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        //finish();
                    }
                });
                alert.show();
            }
        });
    }
}