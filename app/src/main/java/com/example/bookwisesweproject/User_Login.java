package com.example.bookwisesweproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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

public class User_Login extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextInputEditText Email, password;
    MaterialButton loginButton;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
            Toast.makeText(User_Login.this, "Log In Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(User_Login.this, User_Dash.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_login);

        firebaseAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.btnlogin);
        MaterialButton registerButton = findViewById(R.id.callreg);

        Email = findViewById(R.id.email);
        password = findViewById(R.id.pin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login functionality here
                // For demonstration purposes, let's just navigate to another activity
                /*Intent intent = new Intent(User_Login.this, User_Dash.class);
                startActivity(intent);*/
                SignIN();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the registration activity when registerButton is clicked
                Intent intent = new Intent(User_Login.this, User_Reg.class);
                startActivity(intent);
            }
        });

    }

    private void SignIN() {

        String email = Email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Enter Email");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Enter a valid email");
            Email.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Enter password");
            password.requestFocus();
            return;
        }
        loginButton.setClickable(false); //Onek bar click korle onek bar sign in hoye jawa bondho kore.

        //  User Login
            firebaseAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loginButton.setClickable(true);
                        Toast.makeText(User_Login.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(User_Login.this, User_Dash.class));
                        finish();
                    } else {
                        loginButton.setClickable(true);
                        Toast.makeText(User_Login.this, "Log In Failure", Toast.LENGTH_SHORT).show();

                    }
                }
            });


    }
}
