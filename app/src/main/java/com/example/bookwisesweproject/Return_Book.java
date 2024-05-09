package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
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

public class Return_Book extends AppCompatActivity {

    ListView bookListview;
    String isbn;
    ArrayList<String> barcodes = new ArrayList<>();
    ArrayList<String> books = new ArrayList<>();
    ArrayList<String> isbns = new ArrayList<>();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();
    DatabaseReference gref, uref, cref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_return_book);

        bookListview = findViewById(R.id.rentalhistory);
        //For showing both barCode and isbn
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"main", "sub"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});
        bookListview.setAdapter(adapter);

        uref = FirebaseDatabase.getInstance().getReference().child("rents")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
        gref = FirebaseDatabase.getInstance().getReference().child("books");

        //Find the list of isbn of the books that have been rented
        uref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                barcodes.clear();
                //books.clear();
                isbns.clear();
                data.clear();
                for(DataSnapshot booksnap: snapshot.getChildren())
                {
                    barcodes.add(booksnap.getKey());
                    isbns.add(booksnap.getValue(String.class));
                }
                /*for (int i = 0; i < barcodes.size(); i++) {
                    Map<String, String> datum = new HashMap<String, String>(2);
                    datum.put("main", books.get(i));
                    datum.put("sub", isbns.get(i)); // replace 'codes' with your sub-items list
                    data.add(datum);
                }
                adapter.notifyDataSetChanged();*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Get the names of the books from the isbn list
        gref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot napshot) {
                data.clear();
                books.clear();
                for(DataSnapshot genresnap: napshot.getChildren())
                {
                    for(int i=0; i<isbns.size(); i++){
                        if(genresnap.hasChild(isbns.get(i))){
                            books.add(i,genresnap.child(isbns.get(i)).child("name").getValue(String.class));
                        }
                    }
                }
                for (int i = 0; i < barcodes.size(); i++) {
                    Map<String, String> datum = new HashMap<String, String>(2);
                    datum.put("main", books.get(i));
                    datum.put("sub", isbns.get(i)); // replace 'codes' with your sub-items list
                    data.add(datum);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //By clicking, a book can be returned and the listview is updated immediately
        bookListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(new ContextThemeWrapper(Return_Book.this, R.style.AlertDialogCustom));
                alert.setTitle("Rent");
                alert.setMessage("Return this book?");
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
                        DatabaseReference tref = FirebaseDatabase.getInstance().getReference()
                                .child("books");
                        //uref = FirebaseDatabase.getInstance().getReference().child("rents");
                        tref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot genresnap: snapshot.getChildren())
                                {
                                    if(genresnap.hasChild(isbns.get(position)))
                                    {

                                        uref.child(barcodes.get(position)).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(Return_Book.this, "Book Returned", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        tref.child(genresnap.getKey()).child(isbns.get(position)).child("barcode")
                                                .child(barcodes.get(position)).setValue("available");

                                        data.clear();
                                        isbns.remove(position);
                                        barcodes.remove(position);
                                        books.remove(position);

                                        for (int i = 0; i < barcodes.size(); i++) {
                                            Map<String, String> datum = new HashMap<String, String>(2);
                                            datum.put("main", books.get(i));
                                            datum.put("sub", isbns.get(i)); // replace 'codes' with your sub-items list
                                            data.add(datum);
                                        }
                                        adapter.notifyDataSetChanged();

                                        break;
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