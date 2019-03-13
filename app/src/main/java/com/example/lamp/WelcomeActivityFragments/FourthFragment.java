package com.example.lamp.WelcomeActivityFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lamp.R;
import com.example.lamp.WelcomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment implements View.OnClickListener {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment_fourth, container, false);
        Button button = view.findViewById(R.id.btnFourthFragment);
        button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        WelcomeActivity.viewPager.setCurrentItem(4);
    }
}
