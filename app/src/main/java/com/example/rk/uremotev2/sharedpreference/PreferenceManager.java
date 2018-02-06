package com.example.rk.uremotev2.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nilesh Deokar on 03/09/15.
 */
public class PreferenceManager {

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private String SHARED_PREFERENCES_FILE = "uremote_preference";


    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveIntForKey(String key, int data) {

        editor.putInt(key, data);
        editor.commit();
    }

    public int getIntForKey(String key, int defaultData) {
        return sharedPreferences.getInt(key, defaultData);
    }

    public void removeIntForKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    //Float operations
    public void saveFloatForKey(String key, int data) {
        editor.putInt(key, data);
        editor.commit();
    }

    public int getFloatForKey(Context context, String key, int defaultData) {
        SharedPreferences
                sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultData);
    }


    public void removeFloatForKey(String key) {
        editor.remove(key);
        editor.commit();
    }


    //String operations
    public void saveStringForKey(String key, String data) {
        editor.putString(key, data);
        editor.commit();
    }

    public String getStringForKey(String key, String defaultData) {

        return sharedPreferences.getString(key, defaultData);
    }


    public void saveDoubleForKey(String key, double data) {
        editor.putLong(key, Double.doubleToRawLongBits(data));
        editor.commit();
    }

    public double getDoubleForKey(String key, double defaultData) {

        return Double.longBitsToDouble(sharedPreferences.getLong(key, Double.doubleToLongBits(defaultData)));
    }

    public void removeStringForKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void SaveSelecteditemlist(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getSelectedItemlist(String key, String defaultData) {

        return sharedPreferences.getString(key, defaultData);
    }

    //boolean operations
    public void saveBooleanForKey(String key, boolean data) {
        editor.putBoolean(key, data);
        editor.commit();
    }

    public boolean getBooleanForKey(String key, boolean defaultData) {
        return sharedPreferences.getBoolean(key, defaultData);
    }

    public void removeBooleanForKey(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }


    public void clearPreference() {
        editor.clear();
        editor.commit();
    }
}