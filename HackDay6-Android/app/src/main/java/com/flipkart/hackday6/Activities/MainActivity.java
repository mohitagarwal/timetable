package com.flipkart.hackday6.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.flipkart.hackday6.Service.ServiceFloating;

import com.flipkart.hackday6.R;
import com.flipkart.hackday6.R.id;
import com.flipkart.hackday6.R.layout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.getString("LAUNCH").equals("YES")) {
            startService(new Intent(MainActivity.this, ServiceFloating.class));
        }

        Button launch = (Button) findViewById(R.id.button1);
        launch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, ServiceFloating.class));
            }
        });

        Button stop = (Button) findViewById(R.id.button2);
        stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, ServiceFloating.class));
            }
        });

        ImageView github = (ImageView) findViewById(R.id.imageView1);
        github.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "https://github.com/marshallino16/FloatingNotification";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button config = (Button) findViewById(R.id.button_config);
        config.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, Configurations.class);
                startActivity(intent);
                stopService(new Intent(MainActivity.this, ServiceFloating.class));
            }
        });
    }

    @Override
    protected void onResume() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.getString("LAUNCH").equals("YES")) {
            startService(new Intent(MainActivity.this, ServiceFloating.class));
        }
        super.onResume();
    }
}
