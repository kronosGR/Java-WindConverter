package me.kandz.WindConverter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    public static final String PREF_CITY = "city";
    public static final String PREF_TEMP = "temp";
    public static final String PREF_WIND = "wind";
    public static final String PREF_CITY_ID = "cityID";

    SharedPreferences mSharedPreferences;

    public Preferences(Activity activity){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    // City Preferences
    void setCity(String city){
        mSharedPreferences.edit().putString(PREF_CITY, city).commit();
    }

    String getCity(){
        String city = mSharedPreferences.getString(PREF_CITY,"Athens");
        return city;
    }

    // temp preferences
    void setTemp(int selected){
        mSharedPreferences.edit().putInt(PREF_TEMP,selected).commit();
    }

    int getTemp(){
        return mSharedPreferences.getInt(PREF_TEMP,1);
    }

    //wind preferences
    void setWind(int selected){
        mSharedPreferences.edit().putInt(PREF_WIND, selected).commit();
    }

    int getWind(){
        return mSharedPreferences.getInt(PREF_WIND,1);
    }

    //city ID preferences
    void setCityID(int cityID){
        mSharedPreferences.edit().putInt(PREF_CITY_ID,cityID).commit();
    }

    int getCityID(){
        return mSharedPreferences.getInt(PREF_CITY_ID,1);
    }
}
