package me.kandz.WindConverter;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class WeatherFragment extends Fragment {


    public static final int REQUEST_PERMISSIONS = 1;
    private Typeface weatherFont;
    private Button mCityBtn;
    private Button mTempBtn;
    private Button mWindBtn;
    private TextView mCityTxt;
    private TextView mUpdateTxt;
    private TextView mDetailsTxt;
    private TextView mCurrentTempTxt;
    private TextView mWeatherIcon;
    private Handler mHandler;

    private String city = null;
    private int selectedTemp = -1;
    private int selectedWind = -1;
    private TextView mWindIcon;
    private TextView mWindText;
    private TextView mGustText;
    private Typeface mWindFont;
    private Typeface mBfFont;
    private TextView mBfIcon;
    private int mCityID;
    private Button mForecastButton;


    public WeatherFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        checkForInternetPermission();

    }

    /**
     * request permission for the internet
     */
    private void checkForInternetPermission() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{ Manifest.permission.INTERNET} , REQUEST_PERMISSIONS);
        } else {
            initializeFragment();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSIONS:
                if (grantResults.length>0){
                    initializeFragment();
                }
                else {
                    Toast.makeText(getActivity(), "The app needs the Internet permission to download the weather information",
                            Toast.LENGTH_LONG).show();
                }
        }
    }

    /**
     * initializes the fragment
     */
    private void initializeFragment() {
        mHandler = new Handler();

        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons.ttf");
        mWindFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/weathericons.ttf");
        mBfFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/weathericons.ttf");

        mCityTxt = getActivity().findViewById(R.id.cityTxt);
        mUpdateTxt = getActivity().findViewById(R.id.updatedTxt);
        mDetailsTxt = getActivity().findViewById(R.id.detailsTxt);
        mCurrentTempTxt = getActivity().findViewById(R.id.currentTemperatureTxt);
        mWeatherIcon = getActivity().findViewById(R.id.weatherTxt);
        mWeatherIcon.setTypeface(weatherFont);

        mWindIcon = getActivity().findViewById(R.id.windTxt);
        mWindText = getActivity().findViewById(R.id.windLabelTxt);
        mWindIcon.setTypeface(mWindFont);
        mBfIcon = getActivity().findViewById(R.id.bfTxt);
        mBfIcon.setTypeface(mBfFont);

        mCityBtn = getActivity().findViewById(R.id.changeCityBtn);
        mCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Set City");

                //EditText for the city
                final EditText cityText = new EditText(getActivity());
                cityText.setInputType(InputType.TYPE_CLASS_TEXT);
                cityText.setText(city);

                builder.setView(cityText);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        changeCity(cityText.getText().toString());
                    }
                });
                builder.show();
            }
        });

        mTempBtn = getActivity().findViewById(R.id.tempBtn);
        mTempBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Set ℃ / °F");

                final RadioGroup group = new RadioGroup(getActivity());

                final RadioButton cel = new RadioButton(getActivity());
                cel.setText("℃");
                cel.setId(1);
                if (selectedTemp == 1)
                    cel.setChecked(true);
                group.addView(cel);

                final RadioButton far = new RadioButton(getActivity());
                far.setText("°F");
                far.setId(2);
                if (selectedTemp == 2)
                    far.setChecked(true);
                group.addView(far);
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch  (checkedId){
                            case 1:
                                far.setChecked(false);
                                break;
                            case 2:
                                cel.setChecked(false);
                                break;
                        }
                    }
                });

                builder.setView(group);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int setID = group.getCheckedRadioButtonId();
                        if (setID == -1){
                            //if none selected then select C
                            new Preferences(getActivity()).setTemp(1);
                            selectedTemp = 1;
                            updateWeather();
                        } else {
                            new Preferences(getActivity()).setTemp(setID);
                            selectedTemp = setID;
                            updateWeather();
                        }
                    }
                });
                builder.show();
            }
        });
        mWindBtn = getActivity().findViewById(R.id.windBtn);
        mWindBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Set Wind Units");

                final RadioGroup group = new RadioGroup(getActivity());
                final RadioButton ms = new RadioButton(getActivity());
                ms.setText("m/s");
                ms.setId(1);
                if (selectedWind ==1)
                    ms.setChecked(true);
                group.addView(ms);

                final RadioButton mph = new RadioButton(getActivity());
                mph.setText("mph");
                mph.setId(2);
                if (selectedWind == 2)
                    mph.setChecked(true);
                group.addView(mph);

                final RadioButton knots = new RadioButton(getActivity());
                knots.setText("knots");
                knots.setId(3);
                if (selectedWind == 3)
                    knots.setChecked(true);
                group.addView(knots);

                final RadioButton kmh = new RadioButton(getActivity());
                kmh.setText("km/h");
                kmh.setId(4);
                if (selectedWind == 4)
                    kmh.setChecked(true);
                group.addView(kmh);

                builder.setView(group);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int setID = group.getCheckedRadioButtonId();
                        if (setID == 1){
                            new Preferences(getActivity()).setWind(1);
                            selectedWind = 1;
                            updateWeather();
                        } else if (setID == 2){
                            new Preferences(getActivity()).setWind(2);
                            selectedWind = 2;
                            updateWeather();

                        } else if (setID == 3){
                            new Preferences(getActivity()).setWind(3);
                            selectedWind = 3;
                            updateWeather();

                        }else if (setID == 4){
                            new Preferences(getActivity()).setWind(4);
                            selectedWind = 4;
                            updateWeather();

                        }else {

                        }
                    }
                });
                builder.show();
            }
        });

        mForecastButton = getActivity().findViewById(R.id.forecastBtn);
        mForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ForecastActivity.class));
            }
        });


        LoadPreferences();

    }

    private void LoadPreferences() {
        if (loadCity() != null){
            city = loadCity();
        }

        if (loadTemp() != -1){
            selectedTemp = loadTemp();
        }

        if (loadWind() != -1){
            selectedWind = loadWind();
        }

        updateWeather();
    }

    public void changeCity(String c){

        city = c;
        //save to preferences
        new Preferences(getActivity()).setCity(city);

        //update the weather TODO
        updateWeather();
    }

    private void updateWeather(){
        new Thread(){
            @Override
            public void run() {
                if (isNetworkAvailable()) {
                    mForecastButton.setEnabled(true);
                    final JSONObject json = FetchWeather.getJSON(getActivity(), city, returnTemperatureName());
                    if (json == null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Place not found", Toast.LENGTH_LONG).show();
                                mCityBtn.performClick();
                            }
                        });
                    } else {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                //show the weather
                                showWeather(json);
                            }
                        });
                    }
                }
                else {
                    mForecastButton.setEnabled(false);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"You do not have internet connection. Please connect to internet and try again later!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        }.start();
    }

    String returnTemperatureName(){
        if (selectedTemp == 1)
            return FetchWeather.WEATHER_CELSIUS;
        else
            return FetchWeather.WEATHER_FAHRENHEIT;
    }
    private String loadCity(){
        return new Preferences(getActivity()).getCity();
    }

    private int loadTemp(){
        return new Preferences(getActivity()).getTemp();
    }

    private int loadWind(){
        return new Preferences(getActivity()).getWind();
    }

    private void showWeather(JSONObject json){
        String tempChar = "";
        if (selectedTemp == 1)
            tempChar =" ℃";
        else
            tempChar= " °F";

        try {
            mCityTxt.setText(json.getString("name").toUpperCase(Locale.US)+ " , " + json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            JSONObject wind = json.getJSONObject("wind");
            JSONObject sys = json.getJSONObject("sys");



            mCurrentTempTxt.setText(String.format("%.2f", main.getDouble("temp")) + tempChar);

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            mUpdateTxt.setText("Last update: " + updatedOn + " " +WeatherTools.timeZoneToString()+
                    "\n" + "Sunrise: "+ WeatherTools.convertUnixTimeToTime(sys.getLong("sunrise"))+
                    "    -    " + "Sunset: " + WeatherTools.convertUnixTimeToTime(sys.getLong("sunset"))
            );

            mWindText.setText(WeatherTools.convertWind(getActivity(), wind.getDouble("speed"),
                    selectedTemp, selectedWind, mBfIcon));
            WeatherTools.setWindyIcon(getActivity(), wind.getInt("deg"), mWindIcon);

            mDetailsTxt.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa"
            );

            mCityID = json.getInt("id");
            new Preferences(getActivity()).setCityID(mCityID);

            WeatherTools.setWeatherIcon(getActivity(),
                    details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000,
                    mWeatherIcon);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }







    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }






}
