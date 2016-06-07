package com.bigscreen.mylibsrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bigscreen.iconictabbar.view.IconicTab;
import com.bigscreen.iconictabbar.view.IconicTabBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IconicTabBar iconicTabBar = (IconicTabBar) findViewById(R.id.bottom_bar);

        assert iconicTabBar != null;
        iconicTabBar.setOnTabSelectedListener(new IconicTabBar.OnTabSelectedListener() {
            @Override
            public void onSelected(IconicTab tab, int position) {
                Log.e(TAG, "selected= " + position);
            }

            @Override
            public void onUnselected(IconicTab tab, int position) {
                Log.e(TAG, "unselected= " + position);
            }
        });


    }
}
