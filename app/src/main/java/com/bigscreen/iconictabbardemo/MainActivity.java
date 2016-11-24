package com.bigscreen.iconictabbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.bigscreen.iconictabbar.view.IconicTab;
import com.bigscreen.iconictabbar.view.IconicTabBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private IconicTabBar iconicTabBar;
    private TextView textDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initViews();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews() {
        iconicTabBar = (IconicTabBar) findViewById(R.id.bottom_bar);
        textDemo = (TextView) findViewById(R.id.text_demo);
        textDemo.setText(R.string.chats);

        iconicTabBar.setOnTabSelectedListener(new IconicTabBar.OnTabSelectedListener() {
            @Override
            public void onSelected(IconicTab tab, int position) {
                Log.d(TAG, "selected tab on= " + position);
                String demoText = "";
                int tabId = tab.getId();
                switch (tabId) {
                    case R.id.bottom_chats:
                        demoText = getString(R.string.chats);
                        break;
                    case R.id.bottom_calls:
                        demoText = getString(R.string.calls);
                        break;
                    case R.id.bottom_contacts:
                        demoText = getString(R.string.contacts);
                        break;
                    case R.id.bottom_settings:
                        demoText = getString(R.string.settings);
                        break;
                    default:
                        break;
                }
                textDemo.setText(demoText);
            }

            @Override
            public void onUnselected(IconicTab tab, int position) {
                Log.d(TAG, "unselected tab on= " + position);
            }
        });
    }
}
