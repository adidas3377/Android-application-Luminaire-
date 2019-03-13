package com.example.lamp.MenuActivityFragments;


import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lamp.AppDataBase.UserSettings;
import com.example.lamp.BluetoothConnection;
import com.example.lamp.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class EffectsFragment extends Fragment implements View.OnClickListener {


    private LinearLayout Layout_Fire_Effect;
    private TextView textViewFireEffect1;
    private TextView textViewFireEffect2;
    private TextView btnSetFireEffect;

    private LinearLayout Layout_Second_Effect;
    private TextView textViewSecondEffect1;
    private TextView textViewSecondEffect2;
    private TextView btnSetSecondEffect;

    private LinearLayout Layout_Third_Effect;
    private TextView textViewThirdEffect1;
    private TextView textViewThirdEffect2;
    private TextView btnSetThirdEffect;

    private LinearLayout Layout_Fourth_Effect;
    private TextView textViewFourthEffect1;
    private TextView textViewFourthEffect2;
    private TextView btnSetFourthEffect;

    private LinearLayout Layout_Fifth_Effect;
    private TextView textViewFifthEffect1;
    private TextView textViewFifthEffect2;
    private TextView btnSetFifthEffect;

    private UserSettings userSettings;

    private BluetoothConnection bluetoothConnection;

    @SuppressLint("ValidFragment")
    public EffectsFragment(BluetoothConnection bluetoothConnection) {
        this.bluetoothConnection = bluetoothConnection;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_effects, container, false);
        Layout_Fire_Effect = view.findViewById(R.id.Layout_Fire_Effect);
        textViewFireEffect1 = view.findViewById(R.id.textViewFireEffect1);
        textViewFireEffect2 = view.findViewById(R.id.textViewFireEffect2);
        btnSetFireEffect = view.findViewById(R.id.btnSetFireEffect);
        btnSetFireEffect.setOnClickListener(this);

        Layout_Second_Effect = view.findViewById(R.id.Layout_Second_Effect);
        textViewSecondEffect1 = view.findViewById(R.id.textViewSecondEffect1);
        textViewSecondEffect2 = view.findViewById(R.id.textViewSecondEffect2);
        btnSetSecondEffect = view.findViewById(R.id.btnSetSecondEffect);
        btnSetSecondEffect.setOnClickListener(this);

        Layout_Third_Effect = view.findViewById(R.id.Layout_Third_Effect);
        textViewThirdEffect1 = view.findViewById(R.id.textViewThirdEffect1);
        textViewThirdEffect2 = view.findViewById(R.id.textViewThirdEffect2);
        btnSetThirdEffect = view.findViewById(R.id.btnSetThirdEffect);
        btnSetThirdEffect.setOnClickListener(this);

        Layout_Fourth_Effect = view.findViewById(R.id.Layout_Fourth_Effect);
        textViewFourthEffect1 = view.findViewById(R.id.textViewFourthEffect1);
        textViewFourthEffect2 = view.findViewById(R.id.textViewFourthEffect2);
        btnSetFourthEffect = view.findViewById(R.id.btnSetFourthEffect);
        btnSetFourthEffect.setOnClickListener(this);

        Layout_Fifth_Effect = view.findViewById(R.id.Layout_Fifth_Effect);
        textViewFifthEffect1 = view.findViewById(R.id.textViewFifthEffect1);
        textViewFifthEffect2 = view.findViewById(R.id.textViewFifthEffect2);
        btnSetFifthEffect = view.findViewById(R.id.btnSetFifthEffect);
        btnSetFifthEffect.setOnClickListener(this);
         userSettings = new UserSettings(getContext());
        checkEffect(userSettings.getMode(), view);
        return view;
    }

    private void checkEffect(int mode, View view) {
        switch (mode) {
            case 7:
                textViewFireEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewFireEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                Layout_Fire_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                btnSetFireEffect.setText("Установлен");
                btnSetFireEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
            case 6:
                Layout_Second_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewSecondEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewSecondEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetSecondEffect.setText("Установлен");
                btnSetSecondEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
            case 5:
                Layout_Third_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewThirdEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewThirdEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetThirdEffect.setText("Установлен");
                btnSetThirdEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
            case 4:
                Layout_Fourth_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewFourthEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewFourthEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetFourthEffect.setText("Установлен");
                btnSetFourthEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
            case 3:
                Layout_Fifth_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewFifthEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewFifthEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetFifthEffect.setText("Установлен");
                btnSetFifthEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;

        }
    }

    @Override

    public void onClick(View view) {
        textViewFireEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewFireEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        Layout_Fire_Effect.setBackgroundResource(R.drawable.style_effects_background_off);
        btnSetFireEffect.setText("Установить");
        btnSetFireEffect.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_light.ttf"));

        Layout_Second_Effect.setBackgroundResource(R.drawable.style_effects_background_off);
        textViewSecondEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewSecondEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        btnSetSecondEffect.setText("Установить");
        btnSetSecondEffect.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_light.ttf"));


        Layout_Third_Effect.setBackgroundResource(R.drawable.style_effects_background_off);
        textViewThirdEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewThirdEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        btnSetThirdEffect.setText("Установить");
        btnSetThirdEffect.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_light.ttf"));


        Layout_Fourth_Effect.setBackgroundResource(R.drawable.style_effects_background_off);
        textViewFourthEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewFourthEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        btnSetFourthEffect.setText("Установить");
        btnSetFourthEffect.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_light.ttf"));


        Layout_Fifth_Effect.setBackgroundResource(R.drawable.style_effects_background_off);
        textViewFifthEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        textViewFifthEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        btnSetFifthEffect.setText("Установить");
        btnSetFifthEffect.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/roboto_light.ttf"));
        String command = "";
        UserSettings userSettings = new UserSettings(getContext());
        switch (view.getId()) {
            case R.id.btnSetFireEffect:
                textViewFireEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewFireEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                Layout_Fire_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                btnSetFireEffect.setText("Установлен");
                btnSetFireEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                userSettings.setMode(7);
                command = "7:1:1:1:1;";
                break;
            case R.id.btnSetSecondEffect:
                Layout_Second_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewSecondEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewSecondEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetSecondEffect.setText("Установлен");
                btnSetSecondEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                userSettings.setMode(6);
                command = "6:1:1:1:1;";
                break;
            case R.id.btnSetThirdEffect:
                Layout_Third_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewThirdEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewThirdEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetThirdEffect.setText("Установлен");
                btnSetThirdEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                userSettings.setMode(5);
                command = "5:1:1:1:1;";
                break;
            case R.id.btnSetFourthEffect:
                Layout_Fourth_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewFourthEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewFourthEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetFourthEffect.setText("Установлен");
                btnSetFourthEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                userSettings.setMode(4);
                command = "4:1:1:1:1;";
                break;
            case R.id.btnSetFifthEffect:
                Layout_Fifth_Effect.setBackgroundResource(R.drawable.style_effects_background_on);
                textViewFifthEffect1.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                textViewFifthEffect2.setTextColor(ContextCompat.getColor(getContext(), R.color.orange));
                btnSetFifthEffect.setText("Установлен");
                btnSetFifthEffect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                userSettings.setMode(3);
                command = "3:1:1:1:1;";
                break;
        }
        sendCommand(command);
    }

    private void sendCommand(String command) {
        if (!bluetoothConnection.isBluetoothConnected()) {
            bluetoothConnection.connection();
        }
        bluetoothConnection.setMessage(command);
    }
}
