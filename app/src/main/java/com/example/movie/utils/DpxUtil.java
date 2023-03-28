package com.example.movie.utils;

import android.content.Context;
import android.content.res.Resources;

public class DpxUtil {
    public static int dpx(int i) {
        return (int) (i * Resources.getSystem().getDisplayMetrics().density);
    }




    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

}
