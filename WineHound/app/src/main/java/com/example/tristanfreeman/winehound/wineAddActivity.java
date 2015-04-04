package com.example.tristanfreeman.winehound;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;


public class wineAddActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_add);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new wineAddFragment())
                    .commit();
            ActionBar bar = getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(0xFFC14276));
            bar.setTitle("Save A Wine");
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_wine_add, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class wineAddFragment extends Fragment {
    RatingBar wineRating;
    EditText wineName;
    EditText wineBrand;
    EditText wineMl;
    EditText wineRetailer;
    EditText wineNotes;
    private Wine currentWine;


        public wineAddFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.menu_wine_add, menu);
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_wine_add, container, false);

            currentWine = new Wine();
            wineRating = (RatingBar)rootView.findViewById(R.id.rating);
            wineName = (EditText)rootView.findViewById(R.id.wineName);
            wineBrand = (EditText)rootView.findViewById(R.id.wineBrand);
            wineMl = (EditText)rootView.findViewById(R.id.wineMl);
            wineRetailer = (EditText)rootView.findViewById(R.id.wineRetailer);
            wineNotes = (EditText)rootView.findViewById(R.id.wineNotes);
            return rootView;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.wineSave){
                String name = wineName.getText().toString();
                String brand = wineBrand.getText().toString();
                String mlString = wineMl.getText().toString();
                int ml = Integer.valueOf(mlString);
                String retailer = wineRetailer.getText().toString();
                String notes = wineNotes.getText().toString();
                float rating = wineRating.getRating();

                currentWine.setName(name);
                currentWine.setBrand(brand);
                currentWine.setMl(ml);
                currentWine.setRetailer(retailer);
                currentWine.setNotes(notes);
                currentWine.setRating(rating);

                WineDataSource dataSource = new WineDataSource(getActivity());
                dataSource.create(currentWine);
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
