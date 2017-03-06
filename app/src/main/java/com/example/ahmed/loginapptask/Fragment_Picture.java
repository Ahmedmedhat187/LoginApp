package com.example.ahmed.loginapptask;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragment_Picture extends Fragment implements View.OnClickListener{

    ImageView image;
    TextView t;
    Button pre , next , ok;
    View v ;
    static int index = 1;
    static int[] images = {R.drawable.bg ,R.drawable.bg2 ,R.drawable.bg3 ,R.drawable.bglock };
    RelativeLayout relative;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment__picture, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        image = (ImageView)v.findViewById(R.id.image_gallary);
        t = (TextView) v.findViewById(R.id.t);
        pre = (Button)v.findViewById(R.id.btn_pre);
        next = (Button)v.findViewById(R.id.btn_next);
        ok = (Button)v.findViewById(R.id.btn_ok);
        relative = (RelativeLayout)v.findViewById(R.id.relative);
        pre.setOnClickListener(this);
        next.setOnClickListener(this);
        ok.setOnClickListener(this);

    }


    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("index",index);
        super.onSaveInstanceState(outState);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null)
        {
        }
        else {
            relative.setBackgroundResource(images[index]);
        }
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_pre)
        {
            index --;
            if (index ==-1){
                index = 3;
            }
            image.setImageResource(images[index]);
            t.setText(index+"");
        }
        else if (v.getId() == R.id.btn_next)
        {
            index ++;
            if (index == 4) {
                index = 0;
            }
            image.setImageResource(images[index]);
            t.setText(index+"");
        }
        else
        {
            relative.setBackgroundResource(images[index]);
        }


    }
}
