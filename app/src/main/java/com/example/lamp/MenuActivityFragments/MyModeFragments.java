package com.example.lamp.MenuActivityFragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.lamp.AppDataBase.UserSettings;
import com.example.lamp.BluetoothConnection;
import com.example.lamp.R;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MyModeFragments extends Fragment {

    private BluetoothConnection bluetoothConnection;
    private Handler mHandler = new Handler();
    private String command = "";
    private SeekBar seekBarMyMode;
    private String RGB;
    private String Lightness;
    private UserSettings userSettings;

    @SuppressLint("ValidFragment")
    public MyModeFragments(BluetoothConnection bluetoothConnection) {
        this.bluetoothConnection = bluetoothConnection;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_mode, container, false);
        final ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);
        seekBarMyMode = view.findViewById(R.id.seekBarMyMode);
        userSettings = new UserSettings(getContext());
        Lightness = userSettings.getLightness() + "";
        userSettings.setMode(0);
        seekBarMyMode.setProgress(userSettings.getLightness());
        seekBarMyMode.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Lightness = progress + "";
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                int a[] = colorEnvelope.getColorRGB();
                RGB = "0:" + a[0] + ":" + a[1] + ":" + a[2] + ":";

            }
        });
        colorPickerView.saveData();
        mHandler.postDelayed(send, 100);
        return view;
    }

    private Runnable send = new Runnable() {
        @Override
        public void run() {
            if (!bluetoothConnection.isBluetoothConnected()) {
                bluetoothConnection.connection();
            }
            userSettings.setMode(0);
            command = RGB + Lightness + ";";
            bluetoothConnection.setMessage(command);
            mHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(send);
        userSettings.setLightness(seekBarMyMode.getProgress());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(send);
        userSettings.setLightness(seekBarMyMode.getProgress());
    }
}
