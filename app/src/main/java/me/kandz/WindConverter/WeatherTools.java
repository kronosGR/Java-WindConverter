package me.kandz.WindConverter;

import android.content.Context;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class WeatherTools {

    public static void chooseBfForMs(Context context, double windSpeed, int type, TextView mBfIcon){ //1 ms // mph

        String icon = "";

        if (type == 2)
            windSpeed = windSpeed * 0.44704;

        if (windSpeed <= 0.2) {  //0 bf
            icon = context.getResources().getString(R.string.b0);
        } else if (windSpeed>0.2 && windSpeed<=1.5){ //1 bf
            icon = context.getResources().getString(R.string.b1);
        }else if (windSpeed>1.5 && windSpeed<=3.3){ //2 bf
            icon = context.getResources().getString(R.string.b2);
        }else if (windSpeed>3.3 && windSpeed<=5.4){ //3 bf
            icon = context.getResources().getString(R.string.b3);
        }else if (windSpeed>5.4 && windSpeed<=7.9){ //4 bf
            icon = context.getResources().getString(R.string.b4);
        }else if (windSpeed>7.9 && windSpeed<=10.7){ //5 bf
            icon = context.getResources().getString(R.string.b5);
        }else if (windSpeed>10.7 && windSpeed<=13.8){ //6 bf
            icon = context.getResources().getString(R.string.b6);
        }else if (windSpeed>13.8 && windSpeed<=17.1){ //7 bf
            icon = context.getResources().getString(R.string.b7);
        }else if (windSpeed>17.1 && windSpeed<=20.7){ //8 bf
            icon = context.getResources().getString(R.string.b8);
        }else if (windSpeed>20.7 && windSpeed<=24.4){ //9 bf
            icon = context.getResources().getString(R.string.b9);
        }else if (windSpeed>24.4 && windSpeed<=28.4){ //10 bf
            icon = context.getResources().getString(R.string.b10);
        }else if (windSpeed>28.4 && windSpeed<=32.6){ //11 bf
            icon = context.getResources().getString(R.string.b11);
        }else if (windSpeed>32.6){ //12 bf
            icon = context.getResources().getString(R.string.b12);
        }

        mBfIcon.setText(icon);
    }


    public static String chooseBfForRecycler(Context context, double windSpeed, int type){ //1 ms //2 mph

        String icon = "";

        if (type == 2)
            windSpeed = windSpeed * 0.44704;

//        if (type == 3)
//            windSpeed = windSpeed * 0.51444444;
//
//        if (type == 4)
//            windSpeed = windSpeed * 0.27777778;


        if (windSpeed <= 0.2) {  //0 bf
            icon = context.getResources().getString(R.string.b0);
        } else if (windSpeed>0.2 && windSpeed<=1.5){ //1 bf
            icon = context.getResources().getString(R.string.b1);
        }else if (windSpeed>1.5 && windSpeed<=3.3){ //2 bf
            icon = context.getResources().getString(R.string.b2);
        }else if (windSpeed>3.3 && windSpeed<=5.4){ //3 bf
            icon = context.getResources().getString(R.string.b3);
        }else if (windSpeed>5.4 && windSpeed<=7.9){ //4 bf
            icon = context.getResources().getString(R.string.b4);
        }else if (windSpeed>7.9 && windSpeed<=10.7){ //5 bf
            icon = context.getResources().getString(R.string.b5);
        }else if (windSpeed>10.7 && windSpeed<=13.8){ //6 bf
            icon = context.getResources().getString(R.string.b6);
        }else if (windSpeed>13.8 && windSpeed<=17.1){ //7 bf
            icon = context.getResources().getString(R.string.b7);
        }else if (windSpeed>17.1 && windSpeed<=20.7){ //8 bf
            icon = context.getResources().getString(R.string.b8);
        }else if (windSpeed>20.7 && windSpeed<=24.4){ //9 bf
            icon = context.getResources().getString(R.string.b9);
        }else if (windSpeed>24.4 && windSpeed<=28.4){ //10 bf
            icon = context.getResources().getString(R.string.b10);
        }else if (windSpeed>28.4 && windSpeed<=32.6){ //11 bf
            icon = context.getResources().getString(R.string.b11);
        }else if (windSpeed>32.6){ //12 bf
            icon = context.getResources().getString(R.string.b12);
        }

        return icon;
    }


    public static TimeZone getCurrentTimezone(){
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        return tz;
    }

    public static String timeZoneToString(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("z");
        sdf.setTimeZone(getCurrentTimezone());
        return sdf.format(date);
    }

    public static String convertUnixTimeToTime(long unixSexonds){
        String time = "";
        Date date = new Date(unixSexonds*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(getCurrentTimezone());
        time = sdf.format(date);
        return time;
    }

    public static String convertWind(Context context, double wind, int selectedTemp, int selectedWind, TextView mBfIcon){
        String windString = "";
        //convert from m/s
        if (selectedTemp == 1){
            switch (selectedWind){
                // ms
                case 1:
                    windString = String.format("%.2f", wind)  + " m/s";
                    break;
                // mph
                case 2:
                    double mph = wind * 2.23693629;
                    windString = String.format("%.2f", mph)  + " mph";
                    break;
                // knots
                case 3:
                    double knots = wind * 1.94384449;
                    windString = String.format("%.2f", knots)  + " knots";
                    break;
                // kmh
                case 4:
                    double kmh = wind * 3.6;
                    windString = String.format("%.2f", kmh) + " km/h";
                    break;
            }
            chooseBfForMs(context,wind, 1, mBfIcon);
        }
        //convert from mph
        else if (selectedTemp == 2){
            switch (selectedWind){
                // ms
                case 1:
                    double ms = wind * 0.44704;
                    windString = String.format("%.2f", ms)  + " m/s";
                    break;
                // mph
                case 2:
                    windString = String.format("%.2f", wind)  + " mph";
                    break;
                // knots
                case 3:
                    double knots = wind * 0.868976242;
                    windString = String.format("%.2f", knots)  + " knots";
                    break;
                // kmh
                case 4:
                    double kmh = wind * 1.609344;
                    windString = String.format("%.2f", kmh) + " km/h";
                    break;

            }
            chooseBfForMs(context, wind, 2, mBfIcon);
        }
        return windString;
    }

    public static String convertWindForRecycler(double wind, int selectedTemp, int selectedWind){
        String windString = "";
        //convert from m/s
        if (selectedTemp == 1){
            switch (selectedWind){
                // ms
                case 1:
                    windString = String.format("%.2f", wind)  + " m/s";
                    break;
                // mph
                case 2:
                    double mph = wind * 2.23693629;
                    windString = String.format("%.2f", mph)  + " mph";
                    break;
                // knots
                case 3:
                    double knots = wind * 1.94384449;
                    windString = String.format("%.2f", knots)  + " knots";
                    break;
                // kmh
                case 4:
                    double kmh = wind * 3.6;
                    windString = String.format("%.2f", kmh) + " km/h";
                    break;
            }
        }
        //convert from mph
        else if (selectedTemp == 2){
            switch (selectedWind){
                // ms
                case 1:
                    double ms = wind * 0.44704;
                    windString = String.format("%.2f", ms)  + " m/s";
                    break;
                // mph
                case 2:
                    windString = String.format("%.2f", wind)  + " mph";
                    break;
                // knots
                case 3:
                    double knots = wind * 0.868976242;
                    windString = String.format("%.2f", knots)  + " knots";
                    break;
                // kmh
                case 4:
                    double kmh = wind * 1.609344;
                    windString = String.format("%.2f", kmh) + " km/h";
                    break;

            }
        }
        return windString;
    }

    public static void setWeatherIcon(Context context, int actualId, long sunrise, long sunset, TextView mWeatherIcon){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = context.getString(R.string.weather_sunny);
            } else {
                icon = context.getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
                case 2 : icon = context.getString(R.string.weather_thunder);
                    break;
                case 3 : icon = context.getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = context.getString(R.string.weather_foggy);
                    break;
                case 8 : icon = context.getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = context.getString(R.string.weather_snowy);
                    break;
                case 5 : icon = context.getString(R.string.weather_rainy);
                    break;
            }
        }
        mWeatherIcon.setText(icon);
    }

    public static String setWeatherIconForRecycler(Context context, int actualId, String weatherIconID){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(weatherIconID.contains("d")) {
                icon = context.getString(R.string.weather_sunny);
            } else {
                icon = context.getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
                case 2 : icon = context.getString(R.string.weather_thunder);
                    break;
                case 3 : icon = context.getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = context.getString(R.string.weather_foggy);
                    break;
                case 8 : icon = context.getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = context.getString(R.string.weather_snowy);
                    break;
                case 5 : icon = context.getString(R.string.weather_rainy);
                    break;
            }
        }
        return icon;
    }

    public static void setWindyIcon(Context context, int wind, TextView mWindIcon ){
        String icon = "";
        if ((wind<22.1) || (wind>337.1)){
            icon = context.getResources().getString(R.string.north);
        } else if ((wind>22.1) & (wind<67))  {
            icon = context.getResources().getString(R.string.north_east);
        } else if ((wind>67.1) & (wind<112))  {
            icon = context.getResources().getString(R.string.east);
        } else if ((wind>112.1) & (wind< 157))  {
            icon = context.getResources().getString(R.string.south_east);
        } else if ((wind>157.1) & (wind<202))  {
            icon = context.getResources().getString(R.string.south);
        } else if ((wind>202.1) & (wind<247))  {
            icon = context.getResources().getString(R.string.south_west);
        }  else if ((wind>247.1) & (wind<292))  {
            icon = context.getResources().getString(R.string.west);
        } else if ((wind>292.1) & (wind<337))  {
            icon = context.getResources().getString(R.string.north_west);
        }


        mWindIcon.setText(icon);

    }

    public static String setWindyIconForRecycler(Context context, int wind){
        String icon = "";
        if ((wind<22.1) || (wind>337.1)){
            icon = context.getResources().getString(R.string.north);
        } else if ((wind>22.1) & (wind<67))  {
            icon = context.getResources().getString(R.string.north_east);
        } else if ((wind>67.1) & (wind<112))  {
            icon = context.getResources().getString(R.string.east);
        } else if ((wind>112.1) & (wind< 157))  {
            icon = context.getResources().getString(R.string.south_east);
        } else if ((wind>157.1) & (wind<202))  {
            icon = context.getResources().getString(R.string.south);
        } else if ((wind>202.1) & (wind<247))  {
            icon = context.getResources().getString(R.string.south_west);
        }  else if ((wind>247.1) & (wind<292))  {
            icon = context.getResources().getString(R.string.west);
        } else if ((wind>292.1) & (wind<337))  {
            icon = context.getResources().getString(R.string.north_west);
        }


        return icon;
    }
}
