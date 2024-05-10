package com.example.bookwisesweproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Notification extends AppCompatActivity {
    MaterialButton msgbtn;
    EditText msgedt;
    String msg;
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_notification);

        msgbtn = findViewById(R.id.msgbutton);
        msgedt = findViewById(R.id.msgedt);
        mref = FirebaseDatabase.getInstance().getReference().child("observer");
        msgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgbtn.setClickable(false);
                msg = msgedt.getText().toString().trim();
                if(msg.equals("")){
                    msgedt.setError("Message can't be empty");
                    msgedt.requestFocus();
                    msgbtn.setClickable(true);
                    return;
                }
                else {
                    mref.child("message").setValue(msg).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Admin_Notification.this, "Success", Toast.LENGTH_SHORT).show();
                            msgbtn.setClickable(true);
                        }
                    });
                }
            }
        });
    }
}