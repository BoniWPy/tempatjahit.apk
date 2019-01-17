/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.widget.AbsListView
 *  java.lang.Object
 *  java.lang.String
 */
package com.orhanobut.dialogplus;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import com.orhanobut.dialogplus.HeightAnimation;
import com.orhanobut.dialogplus.R;

final class Utils {
    private static final int INVALID = -1;

    Utils() {
    }

    static void animateContent(View view, int n, Animation.AnimationListener animationListener) {
        HeightAnimation heightAnimation = new HeightAnimation(view, view.getHeight(), n);
        heightAnimation.setAnimationListener(animationListener);
        heightAnimation.setDuration(200L);
        view.startAnimation((Animation)heightAnimation);
    }

    static int getAnimationResource(int n, boolean bl) {
        switch (n) {
            default: {
                return -1;
            }
            case 48: {
                if (bl) {
                    return R.anim.slide_in_top;
                }
                return R.anim.slide_out_top;
            }
            case 80: {
                if (bl) {
                    return R.anim.slide_in_bottom;
                }
                return R.anim.slide_out_bottom;
            }
            case 17: 
        }
        if (bl) {
            return R.anim.fade_in_center;
        }
        return R.anim.fade_out_center;
    }

    static int getStatusBarHeight(Context context) {
        int n = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int n2 = 0;
        if (n > 0) {
            n2 = context.getResources().getDimensionPixelSize(n);
        }
        return n2;
    }

    static View getView(Context context, int n, View view) {
        LayoutInflater layoutInflater = LayoutInflater.from((Context)context);
        if (view != null) {
            return view;
        }
        if (n != -1) {
            view = layoutInflater.inflate(n, null);
        }
        return view;
    }

    static boolean listIsAtTop(AbsListView absListView) {
        boolean bl;
        block3 : {
            block2 : {
                if (absListView.getChildCount() == 0) break block2;
                int n = absListView.getChildAt(0).getTop();
                bl = false;
                if (n != 0) break block3;
            }
            bl = true;
        }
        return bl;
    }
}

