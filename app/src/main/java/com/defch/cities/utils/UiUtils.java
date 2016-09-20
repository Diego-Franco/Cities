package com.defch.cities.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by DiegoFranco on 9/11/16.
 */

public class UiUtils
{
    /**
     * get the color and set the statusbar for the device
     * only if the android version is >= Lollipop
     */
    public static void setStatusBarColor(int statusBarColor, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity)context).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusBarColor);
        }
    }
}
