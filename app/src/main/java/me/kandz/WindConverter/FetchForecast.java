package me.kandz.WindConverter;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchForecast {

    private static String OPEN_WEATHER_FORECAST_API =
            "http://api.openweathermap.org/data/2.5/forecast?id=%s&units=%s";

    public static final String WEATHER_CELSIUS= "metric";
    public static final String WEATHER_FAHRENHEIT= "imperial";

    public static JSONObject getJSON(Context context, int cityID, String temp){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_FORECAST_API, cityID, temp));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.addRequestProperty("x-api-key",
                    context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = reader.readLine())!= null){
                json.append(tmp).append("\n");
            }
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if (data.getInt("cod") != 200){
                return null;
            }
            return data;

        } catch (Exception e) {
            return null;
        }
    }
}
