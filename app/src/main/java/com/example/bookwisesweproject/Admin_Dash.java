package com.example.bookwisesweproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookwisesweproject.patterns.observer.Admin;
import com.example.bookwisesweproject.patterns.singleton.LoggedSingleton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Dash extends AppCompatActivity implements View.OnClickListener {


    //Variables

    DrawerLayout drawerLayout;
//    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;

    TextView textView;
    CardView c1,c2,c3,c4,c5,c6;
    DatabaseReference lref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_dash);

        //lref = FirebaseDatabase.getInstance().getReference().child("Admin").child("Logged");
        lref = LoggedSingleton.getInstance();


        /*---------------------Hooks------------------------*/

        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView=findViewById(R.id.navigationView);
        textView=findViewById(R.id.textView);
        toolbar=findViewById(R.id.toolbar);
        c1=findViewById(R.id.userlist);
        c2=findViewById(R.id.inventory);
        c3=findViewById(R.id.addbook);
        c4=findViewById(R.id.updatebook);
        c5=findViewById(R.id.wishlist);
        c6=findViewById(R.id.defaulter);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.userlist) {
            Intent intent = new Intent(getApplicationContext(), User_List.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.inventory) {
            Intent intent = new Intent(getApplicationContext(), Inventory.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.addbook) {
            Intent intent = new Intent(getApplicationContext(), Add_Book.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.updatebook) {
            Intent intent = new Intent(getApplicationContext(), Update_Book.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.wishlist) {
            //Intent intent = new Intent(getApplicationContext(), Wish_List.class);
            Intent intent = new Intent(getApplicationContext(), Admin_Notification.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.defaulter) {
            /*Intent intent = new Intent(getApplicationContext(), Defaulter_List.class);
            startActivity(intent);*/
            logoutAlertDialog();
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
                //firebaseAuth.signOut();
                lref.setValue("false").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Admin_Dash.this, "Signed Out", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(Admin_Dash.this, Admin_User_Switch.class));
                        finish();
                    }
                });

            }
        });
        alert.show();
    }
}