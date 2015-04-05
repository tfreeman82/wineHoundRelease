package com.example.tristanfreeman.winehound;

import android.content.Intent;
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
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class WineListActivity extends ActionBarActivity {

    private wineAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new wineListFragment())
                    .commit();
        }
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(0xFFC14276));
        setTitle("Wine Tracker");
    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class wineListFragment extends Fragment {
        ListView wineList;

        public static wineListFragment newInstance(){
            wineListFragment wineListFragment = new wineListFragment();
            return wineListFragment;
        }
        public wineListFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.menu_wine_list, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.wineAdd){
                Intent addWine = new Intent(getActivity(), wineAddActivity.class);
                addWine.putExtra("id", "add wine");
                startActivity(addWine);

                return true;
            }
            return super.onOptionsItemSelected(item);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_wine_list, container, false);

            wineList = (ListView)rootView.findViewById(R.id.wineList);

            wineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
            return rootView;
        }


        @Override
        public void onResume() {
            super.onResume();

            WineDataSource dataSource = new WineDataSource(this.getActivity());
            ArrayList<Wine> wines = dataSource.read();
            wineList.setAdapter(new wineAdapter(getActivity(), wines));
        }
    }
}
