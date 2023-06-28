package com.Soni5.vibehub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    Button btn;
    FirebaseAuth firebaseAuth;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btn  =  findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        String user = firebaseAuth.getUid();


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Start the main activity or any other desired activity
//                Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 2000);

        if(user != null)
        {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent i = new Intent(SplashActivity.this,SignUpActivity.class);
                startActivity(i);
        }


//        btn.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent i = new Intent(.this,MainActivity.class);
//                startActivity(i);
//            }
//        });
    }
}