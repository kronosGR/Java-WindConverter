package me.kandz.WindConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kronos on 27.02.18.
 */

public class BeaufortAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Beaufort> beaufortArrayList;
    private LayoutInflater inflater;

    public BeaufortAdapter(Context context, ArrayList<Beaufort> beaufortArrayList) {
        this.context = context;
        this.beaufortArrayList = beaufortArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return beaufortArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return beaufortArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.listitem_layout,parent,false);

        ImageView imageView = (ImageView)view.findViewById(R.id.listItem_image);
        TextView desc = (TextView)view.findViewById(R.id.listItem_desc);
        TextView knots = (TextView)view.findViewById(R.id.itemList_knots);
        TextView kmh = (TextView)view.findViewById(R.id.itemList_kmh);
        TextView mph = (TextView)view.findViewById(R.id.itemList_mph);
        TextView ms = (TextView)view.findViewById(R.id.itemList_ms);
        TextView land = (TextView)view.findViewById(R.id.itemList_Land);
        TextView sea = (TextView)view.findViewById(R.id.itemList_sea);

        Beaufort tmpBeaufort = (Beaufort)getItem(position);
        imageView.setImageDrawable(context.getResources().getDrawable(tmpBeaufort.getPicture()));
        desc.setText(tmpBeaufort.getDesc());
        knots.setText("Knots: "+ tmpBeaufort.getKnots());
        kmh.setText("km/h: " + tmpBeaufort.getKmh());
        ms.setText("m/s: "+tmpBeaufort.getMs());
        mph.setText("mph: "+tmpBeaufort.getMph());
        land.setText("Land: "+tmpBeaufort.getLand());
        sea.setText("Sea: " +tmpBeaufort.getSea());

        return view;
    }
}
