package com.parse.broker.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import com.parse.broker.init.ParseApplication;

/**
 * Created by mohit.tibrewal on 22/10/14.
 */
@SuppressWarnings("SameParameterValue")
public class FetchResources {

    public static String getString(int resId) {
        return ParseApplication.getAppContext().getString(resId);
    }

    public static String getString(int resId, Object... formatArgs) {
        return ParseApplication.getAppContext().getString(resId, formatArgs);
    }

    public static int getInteger(int resId) {
        return ParseApplication.getAppContext().getResources().getInteger(resId);
    }

    public static int getDimension(int resId) {
        return ParseApplication.getAppContext().getResources().getDimensionPixelSize(resId);
    }

    public static int getColor(int resId) {
        return ParseApplication.getAppContext().getResources().getColor(resId);
    }

    public static ColorStateList getColorStateList(int resId) {
        return ParseApplication.getAppContext().getResources().getColorStateList(resId);
    }

    public static String getQuantityString(int resId, int quantity, Object... formatArgs) {
        return ParseApplication.getAppContext().getResources().getQuantityString(resId, quantity, formatArgs);
    }

    public static Drawable getDrawable(int resId) {
        return ParseApplication.getAppContext().getResources().getDrawable(resId);
    }
}
