package com.lucasurbas.masterdetail.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by Lucas on 04/01/2017.
 */

public class ViewUtils {

    ViewUtils() {
    }

    /**
     * Returns whether or not the view has been laid out
     **/
    static boolean isLaidOut(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view.isLaidOut();
        }

        return view.getWidth() > 0 && view.getHeight() > 0;
    }

    /**
     * Executes the given {@link java.lang.Runnable} when the view is laid out
     **/
    public static void onLaidOut(final View view, final Runnable runnable) {
        if (isLaidOut(view)) {
            runnable.run();
            return;
        }

        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final ViewTreeObserver trueObserver;

                if (observer.isAlive()) {
                    trueObserver = observer;
                } else {
                    trueObserver = view.getViewTreeObserver();
                }

                if (Build.VERSION.SDK_INT >= 16) {
                    trueObserver.removeOnGlobalLayoutListener(this);
                } else {
                    //noinspection deprecation
                    trueObserver.removeGlobalOnLayoutListener(this);
                }
                runnable.run();
            }
        });
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
