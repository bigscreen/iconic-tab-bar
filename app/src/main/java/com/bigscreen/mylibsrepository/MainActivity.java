package com.bigscreen.mylibsrepository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
        textDemo.setText(String.format(getString(R.string.text_demo), "Chats"));

        Toast.makeText(MainActivity.this, "Chats is selected", Toast.LENGTH_SHORT).show();

        iconicTabBar.setOnTabSelectedListener(new IconicTabBar.OnTabSelectedListener() {
            @Override
            public void onSelected(IconicTab tab, int position) {
                Log.d(TAG, "selected tab on= " + position);
                String demoText = getString(R.string.text_demo);
                switch (position) {
                    case 0:
                        demoText = String.format(demoText, "Chats");
                        break;
                    case 1:
                        demoText = String.format(demoText, "Calls");
                        break;
                    case 2:
                        demoText = String.format(demoText, "Contacts");
                        break;
                    case 3:
                        demoText = String.format(demoText, "Settings");
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
