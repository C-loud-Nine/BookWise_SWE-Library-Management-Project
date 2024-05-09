package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookwisesweproject.patterns.Book;
import com.example.bookwisesweproject.patterns.BookGenre;
import com.example.bookwisesweproject.patterns.LibraryItem;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Dash extends AppCompatActivity implements View.OnClickListener {


    //Variables

    private FirebaseAuth firebaseAuth;

    DrawerLayout drawerLayout;
    //    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;

    TextView textView;
    CardView c1,c2,c3,c4,c5,c6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dash);

        /*---------------------Hooks------------------------*/

        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView=findViewById(R.id.navigationView);
        textView=findViewById(R.id.textView);
        toolbar=findViewById(R.id.toolbar);
        c1=findViewById(R.id.rentbook);
        c2=findViewById(R.id.inventory);
        c3=findViewById(R.id.rentalhistory);
        c4=findViewById(R.id.wishabook);
        c5=findViewById(R.id.profile);
        c6=findViewById(R.id.returnbook);


        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);


        firebaseAuth = FirebaseAuth.getInstance();

    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        if(v.getId()==R.id.rentbook){
            Intent intent = new Intent(User_Dash.this, Rent_Book.class);
            startActivity(intent);
        } else if (v.getId() == R.id.inventory) {
            Intent intent = new Intent(User_Dash.this, Inventory.class);
            startActivity(intent);
        } else if (v.getId() == R.id.rentalhistory) {
            Intent intent = new Intent(User_Dash.this, Rental_History.class);
            startActivity(intent);
        } else if (v.getId() == R.id.wishabook)
        {
//            Intent intent = new Intent(User_Dash.this, User_Wish_Book.class);
//            startActivity(intent);


            //Apadoto Logout er kaj ekhane rakhlam
            logoutAlertDialog();
        }
        else if (v.getId() == R.id.profile) {
            Intent intent = new Intent(User_Dash.this, Profile.class);
            startActivity(intent);
        } else if (v.getId() == R.id.returnbook) {
            Intent intent = new Intent(User_Dash.this, Return_Book.class);
            startActivity(intent);

            //Testing
            /*BookGenre bookGenre = new BookGenre("Horror");
            Book book = new Book("Martin","12345",bookGenre);*/
            //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("books");
            //databaseReference.push().setValue(book);
            /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild("horror"))
                    {
                        Book book = new Book("modasser","lalshalu","2","horror");
                        LibraryItem libraryItem = new LibraryItem(); //etare kaje lagau
                        libraryItem.book = book;
                        databaseReference.child("horror").child(book.isbn).child("author").setValue(book.author);
                        databaseReference.child("horror").child(book.isbn).child("name").setValue(book.name);

                        //databaseReference.child("horror").child(book.isbn).child("barcode").

                        databaseReference.child("horror").child(book.isbn).child("barcode").push().setValue("available").addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(User_Dash.this, "Book Added", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

            
        }
    }

    private void logoutAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        alert.setTitle("ALERT");
        alert.setMessage("Do you want to Log Out?");
        alert.setIcon(R.drawable.baseline_logout_24);
        alert.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                firebaseAuth.signOut();
                Toast.makeText(User_Dash.this, "Signed Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(User_Dash.this, Admin_User_Switch.class));
                finish();
            }
        });
        alert.show();
    }
}