package com.bigscreen.iconictabbar.helper;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class ConfigHelper {

    @SuppressWarnings("deprecation")
    private static int[] getScreenSize(Context context) {
        int width, height;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            windowManager.getDefaultDisplay().getSize(size);
            width = size.x;
            height = size.y;
        } else {
            Display display = windowManager.getDefaultDisplay();
            width = display.getWidth();
            height = display.getHeight();
        }
        Log.i("IconicTabBar", "Get screen size!, width= " + width + ", height= " + height);
        return new int[] {width, height};
    }

    public static int getScreenWidth(Context context) {
        return getScreenSize(context)[0];
    }

    public static int getScreenHeight(Context context) {
        return getScreenSize(context)[1];
    }

    public static int getPxFromDimenRes(@DimenRes int dimenRes, Context context) {
        return context.getResources().getDimensionPixelSize(dimenRes);
    }

    public static int getPxFromDp(float dp, Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }

}
