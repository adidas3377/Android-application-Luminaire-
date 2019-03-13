package com.example.lamp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.lamp.AppDataBase.UserSettings;
import com.example.lamp.MenuActivityFragments.EffectsFragment;
import com.example.lamp.MenuActivityFragments.MyModeFragments;
import com.example.lamp.MenuActivityFragments.RelaxFragment;
import com.example.lamp.MenuActivityFragments.SleepFragment;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = "LAMPA";

    public static FragmentManager fragmentManager;
    private Button btnMyMode;
    private Button btnEffects;
    private Button btnRelax;
    private Button btnSleep;
    private BluetoothConnection bluetoothConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 22) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fragmentManager = getSupportFragmentManager();
        btnMyMode = findViewById(R.id.btnMyMode);
        btnEffects = findViewById(R.id.btnEffects);
        btnRelax = findViewById(R.id.btnRelax);
        btnSleep = findViewById(R.id.btnSleep);

        btnMyMode = findViewById(R.id.btnMyMode);
        btnEffects = findViewById(R.id.btnEffects);
        btnRelax = findViewById(R.id.btnRelax);
        btnSleep = findViewById(R.id.btnSleep);

        btnMyMode.setOnClickListener(this);
        btnEffects.setOnClickListener(this);
        btnRelax.setOnClickListener(this);
        btnSleep.setOnClickListener(this);
        UserSettings userSettings = new UserSettings(getApplicationContext());
        connectToBluetooth(true);
        bluetoothConnection.setMessage(userSettings.getMode() + ":1:1:1:" + userSettings.getLightness() + ";");
        onClick(btnMyMode);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (!bluetoothConnection.isBluetoothConnected()) {
            bluetoothConnection.connection();
        }
        switch (view.getId()) {
            case R.id.btnMyMode:
                MyModeFragments myModeFragments = new MyModeFragments(bluetoothConnection);
                fragmentTransaction.replace(R.id.Fragment_Manager, myModeFragments);
                break;
            case R.id.btnEffects:
                EffectsFragment effectsFragment = new EffectsFragment(bluetoothConnection);
                fragmentTransaction.replace(R.id.Fragment_Manager, effectsFragment);
                break;
            case R.id.btnRelax:
                RelaxFragment relaxFragment = new RelaxFragment(bluetoothConnection);
                fragmentTransaction.replace(R.id.Fragment_Manager, relaxFragment);
                break;
            case R.id.btnSleep:
                SleepFragment sleepFragment = new SleepFragment(bluetoothConnection);
                fragmentTransaction.replace(R.id.Fragment_Manager, sleepFragment);
                break;

        }
        fragmentTransaction.commit();
        Button[] buttons = new Button[]{btnMyMode, btnEffects, btnSleep, btnRelax};

        if (view.getId() == R.id.btnSleep) {
            for (Button button : buttons) {
                button.setBackgroundResource(R.drawable.style_menu_activity_background_off);
                button.setTextColor(ContextCompat.getColor(this, R.color.mainBlue));
                button.setEnabled(true);
            }
            Button b = findViewById(view.getId());
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.mainBlue));
            b.setTextColor(ContextCompat.getColor(this, R.color.white));
            b.setEnabled(false);
        } else if (view.getId() == R.id.btnMyMode) {
            for (Button button : buttons) {
                button.setBackgroundResource(R.drawable.style_menu_activity_background_off);
                button.setTextColor(ContextCompat.getColor(this, R.color.btnMyMode));
                button.setEnabled(true);
            }
            Button b = findViewById(view.getId());
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.btnMyMode));
            b.setTextColor(ContextCompat.getColor(this, R.color.white));
            b.setEnabled(false);
        } else {
            for (Button button : buttons) {
                button.setBackgroundResource(R.drawable.style_menu_activity_background_off);
                button.setTextColor(ContextCompat.getColor(this, R.color.orange));
                button.setEnabled(true);
            }
            Button b = findViewById(view.getId());
            b.setBackgroundResource(R.drawable.background_effects);
            b.setTextColor(ContextCompat.getColor(this, R.color.white));
            b.setEnabled(false);
        }


    }

    private void connectToBluetooth(boolean b) {
        Log.d(TAG, "connectToBluetooth()");

        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();

        UserSettings checkUser = new UserSettings(this);
        if (!BluetoothAdapter.checkBluetoothAddress(checkUser.getBluetoothAddress())) {
            toConnectBluetoothActivity();
        }
        BluetoothDevice device = bluetooth.getRemoteDevice(checkUser.getBluetoothAddress());
        if (device == null) {
            toConnectBluetoothActivity();
        }
        bluetoothConnection = new BluetoothConnection(this, device);
        bluetoothConnection.connection();
        if (b) {
            Toast.makeText(getApplicationContext(), "Подключено успешно!", Toast.LENGTH_SHORT).show();
        }

    }


    private void toConnectBluetoothActivity() {
        Intent i = new Intent(MenuActivity.this, ConnectToBluetoothActivity.class);
        startActivity(i);
    }


}
