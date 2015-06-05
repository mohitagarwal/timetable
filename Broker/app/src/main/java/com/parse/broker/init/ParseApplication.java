package com.parse.broker.init;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;

public class ParseApplication extends Application {

  /**
   * Global Context that can be used anywhere
   */
  private static Context mContext;

  @Override
  public void onCreate() {
    super.onCreate();

    mContext = this;

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this);


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }

  /**
   * get Application context
   * @return The application Context
   */
  public static Context getAppContext() {
    return mContext;
  }
}
