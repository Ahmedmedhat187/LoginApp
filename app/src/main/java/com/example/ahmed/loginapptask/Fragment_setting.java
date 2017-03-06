package com.example.ahmed.loginapptask;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_setting extends Fragment implements View.OnClickListener {

    TextView tv_username , oldpass , newpass , confirm_newpass;
    Button confirm;
    View v ;
    int index = 0;
    LinearLayout linear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment_setting, container, false);
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("index",index);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null)
        {
        }
        else {
            index = savedInstanceState.getInt("index");
            linear.setBackgroundResource(Fragment_Picture.images[index]);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        tv_username = (TextView) v.findViewById(R.id.editText_username);
        oldpass = (TextView) v.findViewById(R.id.editText_oldpass);
        newpass = (TextView) v.findViewById(R.id.editText_newpass);
        confirm_newpass = (TextView) v.findViewById(R.id.editText_confirmpass);
        confirm = (Button) v.findViewById(R.id.button2);
        confirm.setOnClickListener(this);

        index = Fragment_Picture.index;
        linear = (LinearLayout)v.findViewById(R.id.linear_setting);
        linear.setBackgroundResource(Fragment_Picture.images[index]);

    }

    @Override
    public void onClick(View v) {
        String user = tv_username.getText().toString();
        String old = oldpass.getText().toString();
        String newp = newpass.getText().toString();
        String confirmp = confirm_newpass.getText().toString();

        if (user.trim().length()==0 || old.trim().length()==0 || newp.trim().length()==0 || confirmp.trim().length()==0)
        {
            Toast.makeText(getContext(), "please enter data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (user.contains("'") || user.contains("-") || old.contains("'") || old.contains("-") || newp.contains("'") || newp.contains("-"))
            {
                Toast.makeText(getContext(), "invalid character entered do not use - or ' ", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (newp.equals(confirmp))
                {
                    MyDatabase db = new MyDatabase(getContext());
                    boolean result = db.getUserandPassword(user , old);
                    if (result)
                    {
                        db.updateUser(user , old , newp);
                        Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "invalid user name or password", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "pass not confirmed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
