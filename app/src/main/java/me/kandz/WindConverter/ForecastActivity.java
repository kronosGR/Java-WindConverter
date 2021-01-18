package me.kandz.WindConverter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ForecastActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private int cityID = 0;
    private int temp = 0;
    private Handler mHandler;
    private TextView mLocation;
    private List<Forecast> mForecasts;
    private int wind;
    private ForecastRecAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        cityID = new Preferences(this).getCityID();
        temp = new Preferences(this).getTemp();
        wind = new Preferences(this).getWind();
        mHandler = new Handler();

        mRecyclerView = findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mLocation = findViewById(R.id.locationTxt);

        getForecasts();
    }

    private void getForecasts() {
        new Thread() {
            @Override
            public void run() {
                final JSONObject json = FetchForecast.getJSON(getBaseContext(),
                        cityID, returnTemperatureName());

                if (json == null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Place not fount!", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            showForecast(json);
                        }
                    });
                }
            }

        }.start();
    }

    private void showForecast(JSONObject json) {
        mForecasts = new ArrayList<>();

        try {
            JSONObject city = json.getJSONObject("city");
            JSONArray frcA = json.getJSONArray("list");

            String tempChar = "";
            if (temp == 1)
                tempChar =" ℃";
            else
                tempChar= " °F";


            mLocation.setText(city.getString("name").toUpperCase(Locale.US));

            //get each jsonObject from the JSONArray
            for (int i=0; i<frcA.length(); i++){
                JSONObject jso = frcA.getJSONObject(i);

                String dateTime = jso.getString("dt_txt");
                String tempNow = jso.getJSONObject("main").getString("temp");
                String tempMin = jso.getJSONObject("main").getString("temp_min");
                String tempMax = jso.getJSONObject("main").getString("temp_max");
                String pressure = jso.getJSONObject("main").getString("pressure");
                String humidity = jso.getJSONObject("main").getString("humidity");

                JSONObject weather = jso.getJSONArray("weather").getJSONObject(0);

                String weatherDescription = weather.getString("description");
                String weatherID = weather.getString("id");
                String weatherIconID = weather.getString("icon");

                String windSpeed = jso.getJSONObject("wind").getString("speed");
                int windDegrees = jso.getJSONObject("wind").getInt("deg");

                String rain = "0.00";
                if (jso.has("rain"))
                    rain = jso.getJSONObject("rain").getString("3h");

                String snow = "0.00";
                if (jso.has("snow"))
                    snow = jso.getJSONObject("snow").getString("3h");

                //process and populate Forecast object
                Forecast tmpForecast = new Forecast();
                tmpForecast.setDateTime(dateTime);
                tmpForecast.setTemp(tempNow + tempChar);
                tmpForecast.setTempMax("Max: "+ tempMax + tempChar);
                tmpForecast.setTempMin("Min: " + tempMin+ tempChar);
                tmpForecast.setPressure("Pressure: " + pressure + " hPa");
                tmpForecast.setHumidity("Humidity: " + humidity + "%");
                tmpForecast.setWeatherDescription(weatherDescription);
                tmpForecast.setWindSpeed(
                        WeatherTools.convertWindForRecycler(Double.parseDouble(windSpeed),
                                temp,wind));
                tmpForecast.setWindIcon(
                        WeatherTools.setWindyIconForRecycler(
                                this, windDegrees));
                tmpForecast.setRain("Rain: " + rain+ " mm");
                tmpForecast.setSnow("Snow: " + snow + " mm");
                tmpForecast.setWeatherIcon(WeatherTools.setWeatherIconForRecycler(
                    this, Integer.parseInt(weatherID), weatherIconID));

                tmpForecast.setBfIcon(
                        WeatherTools.chooseBfForRecycler(
                                this, Double.parseDouble(windSpeed), temp));

                mForecasts.add(tmpForecast);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        mAdapter = new ForecastRecAdapter(this, mForecasts);
        mRecyclerView.setAdapter(mAdapter);


    }

    private String returnTemperatureName() {
        if (temp == 1)
            return FetchForecast.WEATHER_CELSIUS;
        else
            return FetchForecast.WEATHER_FAHRENHEIT;
    }
}
