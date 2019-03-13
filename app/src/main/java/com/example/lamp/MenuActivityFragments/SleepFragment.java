package com.example.lamp.MenuActivityFragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lamp.AppDataBase.UserSettings;
import com.example.lamp.BluetoothConnection;
import com.example.lamp.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class SleepFragment extends Fragment implements View.OnClickListener {


    private BluetoothConnection bluetoothConnection;
    private Button btnChangeSleep;
    private boolean checkBtnSleep;
    private TextView text1;
    private UserSettings userSettings;

    @SuppressLint("ValidFragment")
    public SleepFragment(BluetoothConnection bluetoothConnection) {
        this.bluetoothConnection = bluetoothConnection;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);
         userSettings = new UserSettings(getContext());
        btnChangeSleep = view.findViewById(R.id.btnChangeSleep);
        btnChangeSleep.setOnClickListener(this);
        text1 = view.findViewById(R.id.textView21);
        if(userSettings.getMode() == 2) {
            onClick(btnChangeSleep);
        }

        return view;
    }


    @Override
    public void onClick(View view) {
        if (checkBtnSleep) {
            checkBtnSleep = false;
            btnChangeSleep.setBackgroundResource(R.drawable.style_effects_background_on);
            btnChangeSleep.setTextColor(ContextCompat.getColor(getContext(), R.color.mainBlue));
            btnChangeSleep.setText("Включить режим");
            text1.setText("Режим отключен");
            userSettings.setMode(0);
            sendCommand("0:255:255:255:" + new UserSettings(getContext()).getLightness() + ";");
        } else {
            checkBtnSleep = true;
            btnChangeSleep.setBackgroundResource(R.drawable.style_effects_background_off);
            btnChangeSleep.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            btnChangeSleep.setText("Выключить режим");
            text1.setText("Режим включен");
            userSettings.setMode(2);

            sendCommand("2:1:1:1:1;");
        }
    }

    private void sendCommand(String command) {
        if (!bluetoothConnection.isBluetoothConnected()) {
            bluetoothConnection.connection();
        }
        bluetoothConnection.setMessage(command);
    }
}
