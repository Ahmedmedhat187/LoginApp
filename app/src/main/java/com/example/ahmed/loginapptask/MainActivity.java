package com.example.ahmed.loginapptask;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_msg;
    EditText et_username , et_password;
    Button btn_login , btn_sign;
    static int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComp();

    }


    public void initComp()
    {
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_sign = (Button) findViewById(R.id.btn_sign);
        tv_msg = (TextView)findViewById(R.id.tv_msg);
        tv_msg.setVisibility(View.INVISIBLE);
        btn_sign.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        tv_msg.setVisibility(View.INVISIBLE);
        if (view.getId() == R.id.btn_sign)
        {
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.btn_login)
        {
            String user = et_username.getText().toString().toLowerCase();
            String pass = et_password.getText().toString();
            String n1 ="-"; String n2 = "\'";
            if (user.trim().length() == 0 || pass.trim().length()==0)
            {
                maketoast(" enter username and password to login  ");
                return;
            }
            else
            {
                if (user.contains(n1)|| pass.contains(n1)|| user.contains(n2)|| pass.contains(n2) )
                {
                    tv_msg.setVisibility(View.VISIBLE);
                    tv_msg.setText("user and password  must not contain - or '");
                    maketoast(" user and password  must not contain - or ' ");
                    Intent i = new Intent(MainActivity.this, Locked.class);
                    i.putExtra("key","hack");
                    startActivity(i);
                    return;
                }
                else
                {
                    MyDatabase db = new MyDatabase(this);
                    boolean result = db.getUserandPassword(user , pass);
                    if (result)
                    {
                        Intent i = new Intent(MainActivity.this, Welcome.class);
                        String name = et_username.getText().toString();
                        char c = name.charAt(0);
                        String s = Character.toString(c).toUpperCase();
                        s = s.concat((name.substring(1, name.length())));
                        i.putExtra("username", s);
                        startActivity(i);
                    }
                    else
                    {
                        if (count == 3)
                        {
                            count = 0;
                            Intent i = new Intent(MainActivity.this, Locked.class);
                            i.putExtra("key","not");
                            startActivity(i);
                        }
                        else
                        {
                            tv_msg.setVisibility(View.VISIBLE);
                            tv_msg.setText("Invalid Credintials you only " + (3 - count) + " times to login");
                            maketoast("Invalid Credintials, you have only " + (3 - count) + " times to login");
                            count++;
                        }
                    }
                }
            }
        }
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

    public void getall(View view) {
        MyDatabase db = new MyDatabase(this);
        int s = db.getAllUser();
        Toast.makeText(MainActivity.this, s+"" , Toast.LENGTH_SHORT).show();
    }

    public void wel(View view) {
        Intent i = new Intent(MainActivity.this, Welcome.class);
        startActivity(i);
    }

}
