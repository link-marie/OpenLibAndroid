package com.linknext.libopen;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class MyPref {

    static public String readDefaultString( Context context, String key, String defValue ) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences( context );
        if( pref == null ) {
            return defValue;
        }
        String lVal = pref.getString( key, defValue );
        return lVal;
    }

    static public boolean readDefaultBoolean( Context context, String key, boolean defValue ) {
        boolean lVal = PreferenceManager.getDefaultSharedPreferences( context ).getBoolean( key, defValue );
        return lVal;
    }

    static public int readDefaultInt( Context context, String key, int defValue ) {
        int lVal = PreferenceManager.getDefaultSharedPreferences( context ).getInt( key, defValue );
        return lVal;
    }

    static public float readDefaultFloat( Context context, String key, float defValue ) {
        float lVal = PreferenceManager.getDefaultSharedPreferences( context ).getFloat( key, defValue );
        return lVal;
    }

    static public void saveDefaultString( Context context, String key, String value ) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences( context );
        Editor editor = pref.edit();
        editor.putString( key, value );
        editor.commit();
    }

    static public void saveDefaultInt( Context context, String key, int value ) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences( context );
        Editor editor = pref.edit();
        editor.putInt( key, value );
        editor.commit();
    }

    static public void saveDefaultBoolean( Context context, String key, boolean b ) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences( context );
        Editor editor = pref.edit();
        editor.putBoolean( key, b );
        editor.commit();
    }

    static public void saveDefaultFloat( Context context, String key, float v ) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences( context );
        Editor editor = pref.edit();
        editor.putFloat( key, v );
        editor.commit();
    }

    /**
     * Check existance of preference
     *
     * @param context
     * @param key
     * @return
     */
    static public boolean isContain( Context context, String key ) {
        return PreferenceManager.getDefaultSharedPreferences( context ).contains( key );
    }
}
