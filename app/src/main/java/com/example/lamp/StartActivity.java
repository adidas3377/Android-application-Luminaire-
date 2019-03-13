package com.example.lamp;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.example.lamp.AppDataBase.UserSettings;

public class StartActivity extends AppCompatActivity {


    private static final int REQ_ENABLE_BLUETOOTH = 1001;
    public final String TAG = "LAMPA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 22) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                    enableBluetooth();
                } else {
                    checkAddress();
                }


            }
        }, 5 * 1000);
    }

    private void goToActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    private void enableBluetooth() {
        Log.d(TAG, "enableBluetooth: Bluetooth выключен, пытаемся включить");
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, REQ_ENABLE_BLUETOOTH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG,"onActivityResult()");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ENABLE_BLUETOOTH) {
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                Log.d(TAG, "onActivityResult: Повторно пытаемся отправить запрос на включение bluetooth");
                enableBluetooth();
            }else{
                checkAddress();
            }
        }
    }

    private void checkAddress() {
        Log.d(TAG,"checkAddress()");
        UserSettings checkUser = new UserSettings(StartActivity.this);
        if (!checkUser.getBluetoothAddress().equals("none")) {
            goToActivity(new Intent(StartActivity.this, MenuActivity.class));
        } else {
            goToActivity(new Intent(StartActivity.this, ConnectToBluetoothActivity.class));
        }
    }
}
