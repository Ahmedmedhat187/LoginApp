package com.example.ahmed.loginapptask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText user , pass , confirmpass;
    Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initComp();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = user.getText().toString();
                String p = pass.getText().toString();
                String cp = confirmpass.getText().toString();

                if (p.equals(cp)) {
                    char c = u.charAt(0);
                    String s = Character.toString(c).toUpperCase();
                    s = s.concat((u.substring(1, u.length())));
                    MainActivity.data.put(u, p);
                    Toast.makeText(SignUp.this, "Thank you for Registration Mr " + s, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this, Welcome.class);
                    i.putExtra("username",s);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(SignUp.this, "Password not identical", Toast.LENGTH_SHORT).show();
                }
            }});

    }

    public void initComp ()
    {
        user = (EditText) findViewById(R.id.et_u);
        pass = (EditText) findViewById(R.id.et_p);
        confirmpass = (EditText) findViewById(R.id.et_p2);
        sign = (Button) findViewById(R.id.btn_s);
    }
}
