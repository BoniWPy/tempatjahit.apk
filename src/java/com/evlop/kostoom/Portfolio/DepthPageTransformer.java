/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$PageTransformer
 *  android.view.View
 *  java.lang.Math
 *  java.lang.Object
 */
package com.evlop.kostoom.Portfolio;

import android.support.v4.view.ViewPager;
import android.view.View;

public class DepthPageTransformer
implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float f) {
        int n = view.getWidth();
        if (f < -1.0f) {
            view.setAlpha(0.0f);
            return;
        }
        if (f <= 0.0f) {
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            return;
        }
        if (f <= 1.0f) {
            view.setAlpha(1.0f - f);
            view.setTranslationX((float)n * -f);
            float f2 = 0.75f + 0.25f * (1.0f - Math.abs((float)f));
            view.setScaleX(f2);
            view.setScaleY(f2);
            return;
        }
        view.setAlpha(0.0f);
    }
}

