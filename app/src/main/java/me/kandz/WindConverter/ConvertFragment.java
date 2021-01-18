package me.kandz.WindConverter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConvertFragment extends Fragment {

    private EditText editKnots;
    private EditText editMs;
    private EditText editKmh;
    private EditText editMph;

    private TextView resKnots;
    private TextView resMs;
    private TextView resKmh;
    private TextView resMph;

    private RelativeLayout bfFrame;
    private ArrayList<Beaufort> beaufortArrayList;


    public ConvertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_convert, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();

        editKnots = (EditText) getActivity().findViewById(R.id.editKnots);
        editMs = (EditText) getActivity().findViewById(R.id.editms);
        editKmh = (EditText) getActivity().findViewById(R.id.editKmh);
        editMph = (EditText) getActivity().findViewById(R.id.editMph);

        resKnots = (TextView) getActivity().findViewById(R.id.resKnots);
        resMs = (TextView) getActivity().findViewById(R.id.resms);
        resKmh = (TextView) getActivity().findViewById(R.id.resKmh);
        resMph = (TextView) getActivity().findViewById(R.id.resMph);

        bfFrame = getActivity().findViewById(R.id.beaufortFrame);

        BeaufortList beaufortList = new BeaufortList();
        beaufortArrayList = beaufortList.getBeaufortArrayList();


        editKnots.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                bfFrame.setVisibility(View.INVISIBLE);
                try {
                    if (Float.parseFloat(editKnots.getText().toString()) > 0) {
                        calculate("knots");
                    }
                } catch (Exception e){
                    Toast.makeText(getContext(),"Please enter a number!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        editMs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                bfFrame.setVisibility(View.INVISIBLE);
                try {
                    if (Float.parseFloat(editMs.getText().toString()) > 0) {
                        calculate("ms");
                    }
                } catch (Exception e){
                    Toast.makeText(getContext(),"Please enter a number!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editKmh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                bfFrame.setVisibility(View.INVISIBLE);
                try {
                    if (Float.parseFloat(editKmh.getText().toString()) > 0) {
                        calculate("kmh");
                    }
                } catch (Exception e){
                    Toast.makeText(getContext(),"Please enter a number!", Toast.LENGTH_SHORT).show();;
                }
            }
        });

        editMph.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                bfFrame.setVisibility(View.INVISIBLE);
                try {
                    if (Float.parseFloat(editMph.getText().toString()) > 0) {
                        calculate("mph");
                    }
                } catch (Exception e){
                    Toast.makeText(getContext(),"Please enter a number!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void calculate(String scale) {

        double knots = 0;
        double kmh = 0;
        double ms = 0;
        double mph = 0;

        switch (scale) {
            case "knots":
                knots = Float.parseFloat(editKnots.getText().toString());
                ms = knots * 0.51444444;
                kmh = knots * 1.852;
                mph = knots * 1.15077945;
                break;
            case "ms":
                ms = Float.parseFloat(editMs.getText().toString());
                knots = ms * 1.94384449;
                kmh = ms * 3.6;
                mph = ms * 2.23693629;
                break;
            case "kmh":
                kmh = Float.parseFloat(editKmh.getText().toString());
                knots = kmh * 0.539956803;
                ms = kmh * 0.27777778;
                mph = kmh * 0.621371192;
                break;
            case "mph":
                mph = Float.parseFloat(editMph.getText().toString());
                knots = mph * 0.868976242;
                ms = mph * 0.44704;
                kmh = mph * 1.609344;
                break;
        }


        resMph.setText((String.format("%.3f", mph)) + " mph");
        resKmh.setText((String.format("%.3f",kmh)) + " km/h");
        resMs.setText((String.format("%.3f",ms)) + " m/s");
        resKnots.setText((String.format("%.3f",knots)) + " knots");

        chooseBf(ms);
    }

    private void chooseBf(double windSpeed){

        bfFrame.setVisibility(View.VISIBLE);
        Beaufort tmpBf = null;

        ImageView imageView = (ImageView)getActivity().findViewById(R.id.flistItem_image);
        TextView desc = (TextView)getActivity().findViewById(R.id.flistItem_desc);
        TextView knots = (TextView)getActivity().findViewById(R.id.fitemList_knots);
        TextView kmh = (TextView)getActivity().findViewById(R.id.fitemList_kmh);
        TextView mph = (TextView)getActivity().findViewById(R.id.fitemList_mph);
        TextView ms = (TextView)getActivity().findViewById(R.id.fitemList_ms);
        TextView land = (TextView)getActivity().findViewById(R.id.fitemList_Land);
        TextView sea = (TextView)getActivity().findViewById(R.id.fitemList_sea);

        if (windSpeed <= 0.2) {  //0 bf
            tmpBf = beaufortArrayList.get(0);
        } else if (windSpeed>0.2 && windSpeed<=1.5){ //1 bf
            tmpBf = beaufortArrayList.get(1);
        }else if (windSpeed>1.5 && windSpeed<=3.3){ //2 bf
            tmpBf = beaufortArrayList.get(2);
        }else if (windSpeed>3.3 && windSpeed<=5.4){ //3 bf
            tmpBf = beaufortArrayList.get(3);
        }else if (windSpeed>5.4 && windSpeed<=7.9){ //4 bf
            tmpBf = beaufortArrayList.get(4);
        }else if (windSpeed>7.9 && windSpeed<=10.7){ //5 bf
            tmpBf = beaufortArrayList.get(5);
        }else if (windSpeed>10.7 && windSpeed<=13.8){ //6 bf
            tmpBf = beaufortArrayList.get(6);
        }else if (windSpeed>13.8 && windSpeed<=17.1){ //7 bf
            tmpBf = beaufortArrayList.get(7);
        }else if (windSpeed>17.1 && windSpeed<=20.7){ //8 bf
            tmpBf = beaufortArrayList.get(8);
        }else if (windSpeed>20.7 && windSpeed<=24.4){ //9 bf
            tmpBf = beaufortArrayList.get(9);
        }else if (windSpeed>24.4 && windSpeed<=28.4){ //10 bf
            tmpBf = beaufortArrayList.get(10);
        }else if (windSpeed>28.4 && windSpeed<=32.6){ //11 bf
            tmpBf = beaufortArrayList.get(11);
        }else if (windSpeed>32.6){ //12 bf
            tmpBf = beaufortArrayList.get(12);
        }
        
        imageView.setImageDrawable(getActivity().getResources().getDrawable(tmpBf.getPicture()));
        desc.setText(tmpBf.getDesc());
        knots.setText("Knots: "+ tmpBf.getKnots());
        kmh.setText("km/h: " + tmpBf.getKmh());
        ms.setText("m/s: "+tmpBf.getMs());
        mph.setText("mph: "+tmpBf.getMph());
        land.setText("Land: "+tmpBf.getLand());
        sea.setText("Sea: " +tmpBf.getSea());

    }

}
