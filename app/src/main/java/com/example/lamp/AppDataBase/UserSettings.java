package com.example.lamp.AppDataBase;

import android.content.Context;
import android.content.SharedPreferences;


public class UserSettings {

    private SharedPreferences sharedPreferences;
    public static final String SP_NAME = "userDetails";

    public UserSettings(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, 0);
    }

    public void setBluetoothAddress(String address) {
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString("address", address);
        spEditor.apply();
    }

    public void setLoggedIn(boolean s) {
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putBoolean("check", s);
        spEditor.apply();
    }

    public void setLightness(int p) {
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putInt("lightness", p);
        spEditor.apply();
    }

    public void setMode(int p) {
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putInt("mode", p);
        spEditor.apply();
    }

    public boolean getLoggedIn() {
        return sharedPreferences.getBoolean("check", false);
    }

    public String getBluetoothAddress() {
        return sharedPreferences.getString("address", "none");
    }

    public int getLightness() {
        return sharedPreferences.getInt("lightness", 100);
    }

    public int getMode() {
        return sharedPreferences.getInt("mode", 0);
    }
}
