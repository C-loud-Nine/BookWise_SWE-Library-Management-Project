package com.example.bookwisesweproject;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookwisesweproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {


    //Variables
    private static int SPLASH_SCREEN = 5000;
    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo,slogan;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Animations

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        //Set animation to elements

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        /*new Handler().postDelayed(new Runnable(){

            @Override

            public void run(){

                Intent intent=new Intent(MainActivity.this, Admin_User_Switch.class);
                // Attach all the elements those you want to animate in design

                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View, String>(image,"logo_image");
                pairs[1]=new Pair<View, String>(logo,"logo_text");

                //wrap the call in API level 21 or higher

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent,options.toBundle());



            }

        },SPLASH_SCREEN);*/

        startActivity(new Intent(MainActivity.this, Admin_User_Switch.class));
        /*databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.setValue("Hello This is a test from the northern part of England");*/
    }
}