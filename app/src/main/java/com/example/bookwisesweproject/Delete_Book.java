package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

public class Delete_Book extends AppCompatActivity {
    ListView bookListview;
    ArrayAdapter<String> bookadapter;
    //ArrayList<String> books = new ArrayList<>();
    ArrayList<String> barcodes = new ArrayList<>();
    ArrayList<String> avails = new ArrayList<>();
    List<Map<String, String>> data = new ArrayList<Map<String, String>>();
    String genre_name, isbn_name;
    DatabaseReference gref, bref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_delete_book);

        bookListview = findViewById(R.id.deletebookListView);
        //bookadapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, barcodes);
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"main", "sub"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});
        bookListview.setAdapter(adapter);
        //bookListview.setAdapter(bookadapter);

        Intent intent = getIntent();
        genre_name = intent.getStringExtra("genre_name");
        isbn_name = intent.getStringExtra("isbn_name");

        gref = FirebaseDatabase.getInstance().getReference().child("books");

        gref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                barcodes.clear(); avails.clear(); data.clear();
                if(snapshot.hasChild(genre_name))
                {
                    if(snapshot.child(genre_name).hasChild(isbn_name))
                    {
                        for (DataSnapshot booksnap: snapshot.child(genre_name).child(isbn_name).child("barcode").getChildren())
                        {
                            barcodes.add(booksnap.getKey());
                            avails.add(booksnap.getValue(String.class));
                        }
                        for (int i = 0; i < barcodes.size(); i++) {
                            Map<String, String> datum = new HashMap<String, String>(2);
                            datum.put("main", barcodes.get(i));
                            datum.put("sub", avails.get(i)); // replace 'codes' with your sub-items list
                            data.add(datum);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bookListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(new ContextThemeWrapper(Delete_Book.this, R.style.AlertDialogCustom));
                alert.setTitle("Delete");
                alert.setMessage("Delete this book?");
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
                        bref = FirebaseDatabase.getInstance().getReference().child("books").child(genre_name).child(isbn_name).child("barcode");

                        bref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(barcodes.get(position)))
                                {
                                    if(Objects.equals(snapshot.child(barcodes.get(position))
                                            .getValue(String.class), "booked"))
                                    {
                                        Toast.makeText(Delete_Book.this, "This is booked", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    bref.child(barcodes.get(position)).removeValue(new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            /*barcodes.remove(position);
                                            avails.remove(position);
                                            data.clear();

                                            for (int i = 0; i < barcodes.size(); i++) {
                                                Map<String, String> datum = new HashMap<String, String>(2);
                                                datum.put("main", barcodes.get(i));
                                                datum.put("sub", avails.get(i)); // replace 'codes' with your sub-items list
                                                data.add(datum);
                                            }
                                            //bookadapter.notifyDataSetChanged();
                                            adapter.notifyDataSetChanged();*/
                                            Toast.makeText(Delete_Book.this, "Book Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
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