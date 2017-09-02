package com.hash.android.connekt;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_search:
//                    mTextMessage.setText("Search");
                    return true;
                case R.id.navigation_message:
//                    mTextMessage.setText("Message");
                    return true;
                case R.id.navigation_profile:
//                    mTextMessage.setText("Profile");
                    return true;
                case R.id.navigation_project:
//                    mTextMessage.setText("Projects");
                    return true;

            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "pacifico.ttf");

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
        TextView mTitle = toolbarTop.findViewById(R.id.toolbar_title);
        mTitle.setTypeface(custom_font);

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "opensans.ttf");

        TextView text1 = (TextView) findViewById(R.id.Text1);
        TextView text2 =(TextView)findViewById(R.id.Text2);
        TextView text3 = (TextView) findViewById(R.id.Image1Text);
        TextView text4 = (TextView) findViewById(R.id.Image2Text);

        text1.setTypeface(custom_font2);
        text2.setTypeface(custom_font2);
        text3.setTypeface(custom_font2);
        text4.setTypeface(custom_font2);

    }

}
