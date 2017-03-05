package com.example.ahmed.loginapptask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity  implements View.OnClickListener{

    EditText user , pass , confirmpass;
    Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initComp();


    }

    public void onClick(View view) {
        String u = user.getText().toString().toLowerCase();
        String p = pass.getText().toString();
        String cp = confirmpass.getText().toString();
        if (u.contains("'") || u.contains("-") || p.contains("'") || p.contains("-") || cp.contains("'") || cp.contains("-"))
        {
            Toast.makeText(this, "invalid character entered do not use - or ' ", Toast.LENGTH_SHORT).show();
            user.setText(""); pass.setText(""); confirmpass.setText("");
        }
        else
        {
            if (p.equals(cp))
            {
                MyDatabase db = new MyDatabase(this);
                boolean found = db.getUser(u);
                if (found)
                {
                    user.setText(""); pass.setText(""); confirmpass.setText("");
                    Toast.makeText(this, "This user is already register", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   // result = db.insertUser(user.getText().toString().toLowerCase(), pass.getText().toString());
                    if (true)
                    {
                        Long id = db.insertUser(u, p);
                        if (id==-1)
                        {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            char c = u.charAt(0);
                            String s = Character.toString(c).toUpperCase();
                            s = s.concat((u.substring(1, u.length())));
                            Intent i = new Intent(SignUp.this, Welcome.class);
                            i.putExtra("username", s);
                            startActivity(i);
                            finish();
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "not sign up", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
            {
                user.setText(""); pass.setText(""); confirmpass.setText("");
                Toast.makeText(SignUp.this, "Password not identical", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void initComp ()
    {
        user = (EditText) findViewById(R.id.et_u); user.setText("");
        pass = (EditText) findViewById(R.id.et_p); pass.setText("");
        confirmpass = (EditText) findViewById(R.id.et_p2); confirmpass.setText("");
        sign = (Button) findViewById(R.id.btn_s);
        sign.setOnClickListener(this);
    }


}
