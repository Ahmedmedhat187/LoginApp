package com.example.ahmed.loginapptask;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Logo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Logo.this , MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }},1000);


    }
}
