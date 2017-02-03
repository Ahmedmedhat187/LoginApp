package com.example.ahmed.loginapptask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView tv , tv_name, tv_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        MainActivity.count =0;
        initComp();
        Intent i = getIntent();
        String name = i.getStringExtra("username");
        tv.setText("Welcome Mr "+name);
        tv_name.setText("Your Name :"+name);
        tv_num.setText("Name Length :"+name.length());

    }

    public void initComp()
    {
        tv = (TextView) findViewById(R.id.tv_welcome);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_num = (TextView) findViewById(R.id.tv_namenum);

    }
}
