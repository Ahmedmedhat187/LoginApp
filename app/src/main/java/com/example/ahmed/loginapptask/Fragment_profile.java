package com.example.ahmed.loginapptask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_profile extends Fragment {

    static LinearLayout linear;
    View v;
    int index = 0;
    TextView tv_profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_fragment_profile, container, false);
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
        index = Fragment_Picture.index;
        linear = (LinearLayout)v.findViewById(R.id.linear_profile);
        linear.setBackgroundResource(Fragment_Picture.images[index]);
        tv_profile = (TextView) v.findViewById(R.id.tv_profile);
        tv_profile.setText("Welcome Mr "+Welcome.name);

    }
}
