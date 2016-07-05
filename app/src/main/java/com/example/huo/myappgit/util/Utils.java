package com.example.huo.myappgit.util;

import android.content.res.Resources;

/**
 * Created by huo on 02/07/16.
 */

public class Utils {
    public static int dpToPx(float pxValue, Resources resources) {
        return (int) (pxValue / resources.getDisplayMetrics().density + 0.5f);
    }
}
