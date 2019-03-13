package com.example.lamp.Adapters;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lamp.R;

import java.util.ArrayList;

/**
 * Created by Администратор on 27.01.2019.
 */

public class MyDeviceListAdapter extends ArrayAdapter<BluetoothDevice> {


    private LayoutInflater mLayoutInflater;
    private int mResourceView;
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<>();

    public MyDeviceListAdapter(@NonNull Context context,int resource, ArrayList<BluetoothDevice> devices){
        super(context,resource);

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResourceView = resource;
        mDevices = devices;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = mLayoutInflater.inflate(mResourceView, null);

        BluetoothDevice device = mDevices.get(position);

        TextView tvName = convertView.findViewById(R.id.tvNameDevice);
        TextView tvAddress = convertView.findViewById(R.id.tvAddressDevice);

        tvName.setText(device.getName());
        tvAddress.setText(device.getAddress());

        return convertView;

    }
}
