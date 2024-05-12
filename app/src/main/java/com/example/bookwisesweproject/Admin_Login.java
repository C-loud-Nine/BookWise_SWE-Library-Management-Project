package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.bookwisesweproject.patterns.singleton.LoggedSingleton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_Login extends AppCompatActivity {

    String emailfirebase,passwordfirebase, email, pass;
    TextInputEditText emailedt, passedt;
    //DatabaseReference lref = FirebaseDatabase.getInstance().getReference().child("Admin").child("Logged");;
    DatabaseReference lref = LoggedSingleton.getInstance();

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        final String[] checker = new String[1];
        lref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                checker[0] = snapshot.getValue(String.class);
                if(checker[0].equals("true")){
                    Toast.makeText(Admin_Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Admin_Dash.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_login);

        MaterialButton loginadmin = findViewById(R.id.btnloginadmin);
        emailedt = findViewById(R.id.email_admin);
        passedt = findViewById(R.id.password_admin);
        //lref = FirebaseDatabase.getInstance().getReference().child("Admin").child("Logged");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin").child("Login Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                emailfirebase = String.valueOf(snapshot.child("Email").getValue());
                passwordfirebase = String.valueOf(snapshot.child("Password").getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        loginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity when loginButton is clicked
                email = emailedt.getText().toString().trim();
                pass = passedt.getText().toString().trim();

                if(email.equals("")){
                    emailedt.setError("Required");
                    emailedt.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailedt.setError("Enter a valid email");
                    emailedt.requestFocus();
                    return;
                }
                if (pass.equals("")){
                    passedt.setError("Required");
                    return;
                }

                if(email.equals(emailfirebase) && pass.equals(passwordfirebase))
                {
                    lref.setValue("true").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Admin_Login.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Admin_Login.this, Admin_Dash.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
                else {
                    Toast.makeText(Admin_Login.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}