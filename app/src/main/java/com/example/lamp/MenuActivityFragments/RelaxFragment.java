package com.example.lamp.MenuActivityFragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lamp.AppDataBase.UserSettings;
import com.example.lamp.BluetoothConnection;
import com.example.lamp.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class RelaxFragment extends Fragment implements View.OnClickListener {


    private Button btnChangeRelax;
    private boolean checkBtnRelax;
    private BluetoothConnection bluetoothConnection;
    private TextView text1;
    private UserSettings userSettings;

    @SuppressLint("ValidFragment")
    public RelaxFragment(BluetoothConnection bluetoothConnection) {
        this.bluetoothConnection = bluetoothConnection;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relax, container, false);
        userSettings = new UserSettings(getContext());
        btnChangeRelax = view.findViewById(R.id.btnChangeRelax);
        btnChangeRelax.setOnClickListener(this);
        text1 = view.findViewById(R.id.textView20);

        if (userSettings.getMode() == 1) {
            onClick(btnChangeRelax);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if (checkBtnRelax) {
            checkBtnRelax = false;
            btnChangeRelax.setBackgroundResource(R.drawable.style_effects_background_on);
            btnChangeRelax.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
            btnChangeRelax.setText("Включить режим");
            text1.setText("Режим отключен");
            userSettings.setMode(0);
            sendCommand("0:255:255:255:" + new UserSettings(getContext()).getLightness() + ";");
        } else {
            checkBtnRelax = true;
            btnChangeRelax.setBackgroundResource(R.drawable.style_effects_background_off);
            btnChangeRelax.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            btnChangeRelax.setText("Выключить режим");
            text1.setText("Режим включен");
            userSettings.setMode(1);
            sendCommand("1:1:1:1:1;");
        }
    }

    private void sendCommand(String command) {
        if (!bluetoothConnection.isBluetoothConnected()) {
            bluetoothConnection.connection();
        }
        bluetoothConnection.setMessage(command);
    }

}
