package me.kandz.WindConverter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private ArrayList<Beaufort> beaufortArrayList;
    ListView beaufortListView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        BeaufortList beaufortList = new BeaufortList();
        beaufortArrayList = beaufortList.getBeaufortArrayList();
        beaufortListView = (ListView)getActivity().findViewById(R.id.beaufort_listview);
        BeaufortAdapter beaufortAdapter = new BeaufortAdapter(getContext(),beaufortArrayList);
        beaufortListView.setAdapter(beaufortAdapter);

    }
}
