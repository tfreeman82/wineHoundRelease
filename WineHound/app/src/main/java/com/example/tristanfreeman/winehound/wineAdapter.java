package com.example.tristanfreeman.winehound;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tristanfreeman on 3/4/15.
 */
public class wineAdapter extends BaseAdapter{
    private List<Wine> wineItems;
    private Context context;


    public wineAdapter(Context context, List<Wine> wineItems) {
        this.context = context;
        this.wineItems = wineItems;
    }

    @Override
    public int getCount() {
        return wineItems.size();
    }

    @Override
    public Object getItem(int i) {
        return wineItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return wineItems.indexOf(getItem(i));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.wine_list_cell, null);
        }


        TextView wineNameView = (TextView)view.findViewById(R.id.wineNameView);
        RatingBar wineRatingBar = (RatingBar)view.findViewById(R.id.rating);

        Wine wineItem = this.wineItems.get(position);
        wineNameView.setText(wineItem.getName());
       // double ratingDouble = wineItem.getRating();
        //float rating = (float)ratingDouble;
        Log.i("RATING", String.valueOf(wineItem.getRating()));
        wineRatingBar.setRating(wineItem.getRating());

        return view;
    }
}
