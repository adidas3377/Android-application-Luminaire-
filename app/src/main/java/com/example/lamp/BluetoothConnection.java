package com.example.lamp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * Created by Администратор on 27.01.2019.
 */

public class BluetoothConnection {

    BluetoothDevice device;
    BluetoothSocket mBluetoothSocket;
    OutputStream mOutputStream;
    Context context;

    public BluetoothConnection(Context context, BluetoothDevice device, CallBackService callBackService) {
        this.device = device;
        this.context = context;

        Method method = null;
        try {
            method = device.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
            mBluetoothSocket = (BluetoothSocket) method.invoke(device, 1);
            mBluetoothSocket.connect();
            callBackService.onSuccess(mBluetoothSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BluetoothConnection(Context context, BluetoothDevice device) {
        this.device = device;
        this.context = context;


    }

    public void connection() {
        if (device != null) {
            try {
                Method method = null;
                method = device.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                mBluetoothSocket = (BluetoothSocket) method.invoke(device, 1);
                mBluetoothSocket.connect();
            } catch (Exception e) {
                Toast.makeText(context, "Ошибка подключения!", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, ConnectToBluetoothActivity.class));
                e.printStackTrace();
            }

        }
    }

    public void setMessage(String command) {
        byte[] buffer = command.getBytes();
        try {
            mOutputStream = mBluetoothSocket.getOutputStream();
            mOutputStream.write(buffer);
            mOutputStream.flush();
        } catch (IOException e) {
            Toast.makeText(context, "Ошибка запроса :(", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public boolean isBluetoothConnected() {
        return mBluetoothSocket.isConnected();

    }

    public void closeBluetoothConnection() {
        if (mBluetoothSocket.isConnected()) {
            try {
                mBluetoothSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

