package ru.yandex.yamblz.ui.other;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by platon on 23.07.2016.
 */
public class PreferencesManager
{
    private static final String SHARED_PREF_NAME = "ru.yandex.yamblz.pref";

    private static SharedPreferences sSharedPreferences;
    private static PreferencesManager sPreferencesManager;

    public static void init(Context context, String name)
    {
        if (sPreferencesManager == null)
        {
            sPreferencesManager = new PreferencesManager(context, name);
        }
    }

    public  static void init(Context context)
    {
        init(context, SHARED_PREF_NAME);
    }

    private PreferencesManager(Context context, String name)
    {
        if (sSharedPreferences == null)
        {
            sSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    public static SharedPreferences getSharedPref()
    {
        return sSharedPreferences;
    }

    public static PreferencesManager getPreferencesMngr()
    {
        return sPreferencesManager;
    }

    public Boolean getBoolean(String key)
    {
        return sSharedPreferences.getBoolean(key, false);
    }

    public void setBoolean(String key, boolean value)
    {
        sSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public int getInt(String key, int defaultValue)
    {
        return sSharedPreferences.getInt(key, defaultValue);
    }

    public void setInt(String key, int value)
    {
        sSharedPreferences.edit().putInt(key, value).apply();
    }
}
