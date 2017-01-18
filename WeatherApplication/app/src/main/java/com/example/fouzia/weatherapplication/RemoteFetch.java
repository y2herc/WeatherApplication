package com.example.fouzia.weatherapplication;


import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RemoteFetch {


private static final String OPEN_WEATHER_MAP_API=
        "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";


    public static JSONObject getJSON(Context context, String city){
    try {

        String tempurl=String.format(OPEN_WEATHER_MAP_API,city);
        tempurl+="&appid="+context.getString(R.string.open_weather_maps_app_id);
        URL url=new URL(tempurl);

        HttpURLConnection connection=(HttpURLConnection)url.openConnection();

//        connection.addRequestProperty("x-api-key",context.getString(R.string.open_weather_maps_app_id));
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuffer json=new StringBuffer(1024);
        String tmp="";

        while((tmp=bufferedReader.readLine())!=null)
            json.append(tmp).append("\n");

        JSONObject data=new JSONObject(json.toString());

        if(data.getInt("cod")!=200){

            return null;
        }
        bufferedReader.close();
        connection.disconnect();

        return data;
    }
    catch (Exception e){

        Log.d("RemoteFetch","Exception",e);
        return null;
    }

}

}
