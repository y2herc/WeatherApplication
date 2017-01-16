package com.example.fouzia.weatherapplication;

import android.app.Activity;
import android.content.SharedPreferences;

public class CityPref {

    SharedPreferences prefs;

    public CityPref(Activity activity){
        prefs=activity.getPreferences(Activity.MODE_PRIVATE);
    }

    String getCityPref(){
        return prefs.getString("city","Toronto, CA");
    }

    void setCityPref(String city){
        prefs.edit().putString("city",city).commit();
    }
}
