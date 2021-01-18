package me.kandz.WindConverter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ForecastRecAdapter extends RecyclerView.Adapter<ForecastRecAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    public final Context mContext;
    private final List<Forecast> mForecasts;
    public Typeface weatherFont;

    public ForecastRecAdapter(Context context, List<Forecast> forecasts) {
        mContext = context;
        mForecasts = forecasts;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = mLayoutInflater.inflate(R.layout.forecast_list_item, viewGroup, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Forecast forecast = mForecasts.get(i);
        viewHolder.dateTimeTxt.setText(forecast.getDateTime());
        viewHolder.weatherIconTxt.setText(forecast.getWeatherIcon());
        viewHolder.windIconTxt.setText(forecast.getWindIcon());
        viewHolder.bfIconTxt.setText(forecast.getBfIcon());
        viewHolder.windSpeedTxt.setText(forecast.getWindSpeed());
        viewHolder.weatherDescTxt.setText(forecast.getWeatherDescription());
        viewHolder.humidityTxt.setText(forecast.getHumidity());
        viewHolder.pressureTxt.setText(forecast.getPressure());
        viewHolder.rainTxt.setText(forecast.getRain());
        viewHolder.snowTxt.setText(forecast.getSnow());
        viewHolder.tempTxt.setText(forecast.getTemp());
        viewHolder.tempMaxTxt.setText(forecast.getTempMax());
        viewHolder.tempMinTxt.setText(forecast.getTempMin());


    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView dateTimeTxt;
        public final TextView weatherIconTxt;
        public final TextView windIconTxt;
        public final TextView bfIconTxt;
        public final TextView windSpeedTxt;
        public final TextView weatherDescTxt;
        public final TextView humidityTxt;
        public final TextView pressureTxt;
        public final TextView rainTxt;
        public final TextView snowTxt;
        public final TextView tempTxt;
        public final TextView tempMaxTxt;
        public final TextView tempMinTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            weatherFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/weathericons.ttf");
            dateTimeTxt = itemView.findViewById(R.id.itemDate);
            weatherIconTxt = itemView.findViewById(R.id.itemWeatherIconTxt);
            weatherIconTxt.setTypeface(weatherFont);
            windIconTxt = itemView.findViewById(R.id.itemWindIconTxt);
            windIconTxt.setTypeface(weatherFont);
            bfIconTxt = itemView.findViewById(R.id.itemBfIconTxt);
            bfIconTxt.setTypeface( weatherFont);
            windSpeedTxt = itemView.findViewById(R.id.itemWindSpeedTxt);
            weatherDescTxt = itemView.findViewById(R.id.itemWeatherDesciptionTxt);
            humidityTxt = itemView.findViewById(R.id.itemHumidityTxt);
            pressureTxt =  itemView.findViewById(R.id.itemPressureTxt);
            rainTxt = itemView.findViewById(R.id.itemRainTxt);
            snowTxt = itemView.findViewById(R.id.itemSnowTxt);
            tempTxt = itemView.findViewById(R.id.itemNormalTempTxt);
            tempMaxTxt = itemView.findViewById(R.id.itemMaxTempTxt);
            tempMinTxt =  itemView.findViewById(R.id.itemMinTempTxt);
        }
    }
}
