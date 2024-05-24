package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class User_Reg extends AppCompatActivity {

    // Singleton Instance of FirebaseAuth
    private static FirebaseAuth instance, firebaseauth;

    public static FirebaseAuth getInstance() {
        if (instance == null) {
            instance = FirebaseAuth.getInstance();
        }
        return instance;
    }
    private TextInputEditText name_edt, phone_edt, email_edt, pin_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_reg);

        firebaseauth = FirebaseAuth.getInstance();
        name_edt = findViewById(R.id.reg_name);
        phone_edt = findViewById(R.id.reg_phone);
        email_edt = findViewById(R.id.reg_email);
        pin_edt = findViewById(R.id.reg_pin);
        MaterialButton registerButton = findViewById(R.id.btnreg);
        @SuppressLint("WrongViewCast") MaterialButton loginButton = findViewById(R.id.call_login);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity when loginButton is clicked
                /*Intent intent = new Intent(User_Reg.this, User_Login.class);
                startActivity(intent);*/
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.println(Log.ASSERT, String.valueOf(22),"Eta porjonto aise thikthak");


                String names = name_edt.getText().toString().trim();
                String emails = email_edt.getText().toString().trim();
                String passwords = pin_edt.getText().toString().trim();
                String phones = phone_edt.getText().toString().trim();

                if(names.isEmpty()){
                    name_edt.setError("Enter Name");
                    name_edt.requestFocus();
                    return;
                }
                if(emails.isEmpty()){
                    email_edt.setError("Enter Email");
                    email_edt.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
                    email_edt.setError("Enter a valid email");
                    email_edt.requestFocus();
                    return;
                }
                if(passwords.isEmpty() || passwords.length()<6){
                    pin_edt.setError("Enter password of minimum length 6");
                    pin_edt.requestFocus();
                    return;
                }
                if(phones.length() != 11){
                    phone_edt.setError("Enter valid phone number");
                    phone_edt.requestFocus();
                    return;
                }

                Signin(emails,passwords);
            }
        });

    //end of on-create method
    }

    private void Signin(String Email, String Password){
        firebaseauth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseauth.getCurrentUser();
                    String uid = user.getUid();
                    Toast.makeText(User_Reg.this, "Ekhane aise 1", Toast.LENGTH_SHORT).show();
                    updateUi(uid, Email);
                } else{
                    // Log the error details
                    Exception e = task.getException();
                    if (e != null) {
                        Log.e("FirebaseAuth", "Error: " + e.getMessage());
                    }
                    Toast.makeText(User_Reg.this, "Sign In Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUi(String uid, String email){
        HashMap<String, Object> map = new HashMap<>();

        map.put("name",name_edt.getText().toString().trim());
        map.put("phone",phone_edt.getText().toString().trim());
        map.put("email",email_edt.getText().toString().trim());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user");
        reference.child(uid).setValue(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(User_Reg.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(User_Reg.this, User_Dash.class));
                            finish();
                        }
                        else{

                            Exception e = task.getException();
                            if(e==null){
                                Toast.makeText(User_Reg.this, "Sign Up Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(User_Reg.this, "Sign Up Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
