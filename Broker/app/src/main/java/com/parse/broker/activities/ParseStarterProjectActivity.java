package com.parse.broker.activities;

import android.app.Activity;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.broker.R;

public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Parse.initialize(this, "x4Iq2AaH0h0hKAl68H9MqZF13ZvwmutU7D85P8I3", "lDC4ToTMNi8Q4qmbpvFvZHSiAPAJvOwcwSd1PyPZ");
		ParseInstallation.getCurrentInstallation().saveInBackground();
		ParseAnalytics.trackAppOpenedInBackground(getIntent());
	}
}
