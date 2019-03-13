package com.example.lamp.WelcomeActivityFragments;


import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lamp.AppDataBase.AppDataBase;
import com.example.lamp.AppDataBase.UserSettings;
import com.example.lamp.AppDataBase.User;
import com.example.lamp.ConnectToBluetoothActivity;
import com.example.lamp.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class FifthFragment extends Fragment implements View.OnClickListener {


    private EditText name;
    private Context context;


    @SuppressLint("ValidFragment")
    public FifthFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment_fifth, container, false);
        Button button = view.findViewById(R.id.btnFifthFragment);
        button.setOnClickListener(this);
        name = view.findViewById(R.id.editTextName);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (name.getText().toString().length() < 3) {
            Toast.makeText(context, "Слишком короткое имя D:", Toast.LENGTH_LONG).show();
            return;
        } else if (name.getText().toString().equals("Ира")) {
            Toast.makeText(context, "Пливет класотка Ируся :**", Toast.LENGTH_LONG).show();
        }
        UserSettings checkUser = new UserSettings(context);
        User user = new User();
        user.setId(0);
        user.setName(name.getText().toString());
        AppDataBase appDataBase = Room.databaseBuilder(context,
                AppDataBase.class, "userDB").allowMainThreadQueries().build();
        appDataBase.myDao().addUser(user);
        checkUser.setLoggedIn(true);
        Intent intent = new Intent(context, ConnectToBluetoothActivity.class);
        startActivity(intent);
    }
}
