package com.example.ahmed.loginapptask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView tv_msg;
    EditText et_username , et_password;
    Button btn_login , btn_sign;
    static HashMap<String , String> data;
    static int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComp();
        data = new HashMap<>();
        data.put("ah","q");
        data.put("omar","123");
        data.put("hamdy","123");

    }


    public void initComp()
    {
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_sign = (Button) findViewById(R.id.btn_sign);
        tv_msg = (TextView)findViewById(R.id.tv_msg);
        tv_msg.setVisibility(View.INVISIBLE);
        init();
    }
    public void init()
    {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.containsKey(et_username.getText().toString().toLowerCase()))
                {
                    tv_msg.setVisibility(View.INVISIBLE);
                    if (data.get((et_username.getText().toString().toLowerCase())).equals(et_password.getText().toString()))
                    {
                        Intent i = new Intent(MainActivity.this, Welcome.class);
                        String name = et_username.getText().toString();
                        char c = name.charAt(0);
                        String s = Character.toString(c).toUpperCase();
                        s = s.concat((name.substring(1, name.length())));
                        i.putExtra("username", s);
                        startActivity(i);
                    }
                    else {
                        if (count == 3)
                        {
                            count = 0;
                            Intent i = new Intent(MainActivity.this, Locked.class);
                            startActivity(i);
                        }
                        else {
                            maketoast("Invalid Credintials, you have only " + (3 - count) + " times to login");
                            count++;
                        }
                    }
                }
                else { tv_msg.setVisibility(View.VISIBLE); tv_msg.setText("Sign up first");}
            }});
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });

    }
    /*
     public void waittime(int x)
       {
           long future =( x*1000 ) + System.currentTimeMillis();
           while (System.currentTimeMillis() < future)
           {
               synchronized (this)
               {
                   try { wait (future - System.currentTimeMillis());}
                   catch (InterruptedException e) { e.printStackTrace();}
               }
           }

       }
    */
    public void maketoast(String s)
    {
        Toast.makeText(getBaseContext(), s ,Toast.LENGTH_SHORT).show();
    }




}
