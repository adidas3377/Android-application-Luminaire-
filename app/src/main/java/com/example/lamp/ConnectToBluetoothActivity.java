package com.example.lamp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lamp.Adapters.MyDeviceListAdapter;
import com.example.lamp.AppDataBase.UserSettings;
import com.roger.catloadinglibrary.CatLoadingView;

import java.io.IOException;
import java.util.ArrayList;

public class ConnectToBluetoothActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQ_ENABLE_BLUETOOTH = 1001;
    public final String TAG = "LAMPA";
    private Button btnFindDevice;

    private BluetoothAdapter mBlueToothAdapter;

    private CatLoadingView catLoadingView;
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<>();

    private MyDeviceListAdapter mDeviceListAdapter;
    private ListView listDevices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 22) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_bluetooth);
        btnFindDevice = findViewById(R.id.btnFindDevice);
        mBlueToothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBlueToothAdapter == null) {
            Log.d(TAG, "onCreate: Ваше устройство не поддерживает bluetooth");
            finish();
        }
        btnFindDevice = findViewById(R.id.btnFindDevice);
        btnFindDevice.setOnClickListener(this);
        catLoadingView = new CatLoadingView();
        catLoadingView.setText(" ");
    }

    private void enableBluetooth() {
        Log.d(TAG, "enableBluetooth()");
        if (!mBlueToothAdapter.isEnabled()) {
            Log.d(TAG, "enableBluetooth: Bluetooth выключен, пытаемся включить");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQ_ENABLE_BLUETOOTH);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ENABLE_BLUETOOTH) {
            if (!mBlueToothAdapter.isEnabled()) {
                Log.d(TAG, "onActivityResult: Повторно пытаемся отправить запрос на включение bluetooth");
                enableBluetooth();
            }
        }
    }

    private void searchDevices() {
        enableBluetooth();

        checkPermissionLocation();
        if (!mBlueToothAdapter.isDiscovering()) {
            Log.d(TAG, "searchDevices: начинаем поиск устройств.");
            mBlueToothAdapter.startDiscovery();
        }
        if (mBlueToothAdapter.isDiscovering()) {
            Log.d(TAG, "searchDevices: поиск уже был запущен... перезапускаем еще раз.");
            mBlueToothAdapter.cancelDiscovery();
            mBlueToothAdapter.startDiscovery();
        }

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

    }

    private void showListDevices() {
        Log.d(TAG, "showListDevices()");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Найденные устройства");
        mDeviceListAdapter = new MyDeviceListAdapter(ConnectToBluetoothActivity.this, R.layout.device_item, mDevices);
        for (BluetoothDevice d : mDevices) {
            mDeviceListAdapter.add(d);
        }
        View view = getLayoutInflater().inflate(R.layout.list_devices_view, null);
        listDevices = (ListView) view.findViewById(R.id.list_devices);
        listDevices.setAdapter(mDeviceListAdapter);
        listDevices.setOnItemClickListener(itemOnClickListener);
        builder.setView(view);
        builder.setNegativeButton("OK", null);
        builder.create();
        builder.show();
    }

    private AdapterView.OnItemClickListener itemOnClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            BluetoothDevice device = mDevices.get(position);
            if (device.getName().equals("luminaireann") || device.getName().equals("luminaireira")) {
                startConnection(device);
            } else {
                showToast("Неправильный выбор bluetooth устройства.");
            }
        }
    };

    private void startConnection(BluetoothDevice device) {
        Log.d(TAG, "startConnection()");
        if (device != null) {
            UserSettings checkUser = new UserSettings(this);
            checkUser.setBluetoothAddress(device.getAddress());
            BluetoothConnection bluetoothConnection = new BluetoothConnection(this, device, new CallBackService() {
                @Override
                public void onSuccess(BluetoothSocket mBluetoothSocket) {
                    try {
                        mBluetoothSocket.close();
                        startNewActivity();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private void checkPermissionLocation() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int check = checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            check += checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if (check != 0) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1002);
            }
        }
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                Log.d(TAG, "onReceive: ACTION_DISCOVERY_STARTED");

                showToast("Начат поиск устройств.");

                catLoadingView.show(getSupportFragmentManager(), TAG);
            }
            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                Log.d(TAG, "onReceive: ACTION_DISCOVERY_FINISHED");

                showToast("Поиск устройств завершен");

                catLoadingView.dismiss();
                showListDevices();
            }
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                Log.d(TAG, "onReceive: ACTION_FOUND");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device != null) {
                    if (!mDevices.contains(device)) {
                        mDevices.add(device);
                    }
                }
            }
        }
    };


    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        searchDevices();
    }

    private void startNewActivity() {
        Intent intent = new Intent(ConnectToBluetoothActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}
