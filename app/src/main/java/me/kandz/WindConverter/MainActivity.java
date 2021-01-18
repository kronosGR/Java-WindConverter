package me.kandz.WindConverter;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import me.kandz.kz.KZActivity;

public class MainActivity extends AppCompatActivity {

    private AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,"ca-app-pub-0717744179319214/4519527964");
        mAdview = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        //Load homeFragment
        if (findViewById(R.id.frag_cont) != null){
            WeatherFragment weatherFragment = new WeatherFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frag_cont,weatherFragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()){
            case R.id.option_home:
                MainFragment homeFragment = new MainFragment();
                transaction.replace(R.id.frag_cont,homeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.option_weather:
                WeatherFragment weatherFragment = new WeatherFragment();
                transaction.replace(R.id.frag_cont,weatherFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.options_convert:
                ConvertFragment convertFragment  = new ConvertFragment();
                transaction.replace(R.id.frag_cont,convertFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.options_windrose:
                WindroseFragment windroseFragment = new WindroseFragment();
                transaction.replace(R.id.frag_cont,windroseFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.options_kz:
                Intent intent = new Intent(this, KZActivity.class);
                startActivity(intent);
                break;
            case R.id.options_exit:
                finish();
                break;
        }

        return true;
    }
}
