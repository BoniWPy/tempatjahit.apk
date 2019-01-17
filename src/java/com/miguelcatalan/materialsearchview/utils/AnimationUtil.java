/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorListenerAdapter
 *  android.annotation.TargetApi
 *  android.content.res.Resources
 *  android.support.v4.view.ViewCompat
 *  android.support.v4.view.ViewPropertyAnimatorCompat
 *  android.support.v4.view.ViewPropertyAnimatorListener
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.ViewAnimationUtils
 *  com.miguelcatalan.materialsearchview.utils.AnimationUtil$AnimationListener
 *  java.lang.Math
 *  java.lang.Object
 */
package com.miguelcatalan.materialsearchview.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.miguelcatalan.materialsearchview.utils.AnimationUtil;

public class AnimationUtil {
    public static int ANIMATION_DURATION_LONG;
    public static int ANIMATION_DURATION_MEDIUM;
    public static int ANIMATION_DURATION_SHORT;

    static {
        ANIMATION_DURATION_SHORT = 150;
        ANIMATION_DURATION_MEDIUM = 400;
        ANIMATION_DURATION_LONG = 800;
    }

    public static void crossFadeViews(View view, View view2) {
        AnimationUtil.crossFadeViews(view, view2, ANIMATION_DURATION_SHORT);
    }

    public static void crossFadeViews(View view, View view2, int n) {
        AnimationUtil.fadeInView(view, n);
        AnimationUtil.fadeOutView(view2, n);
    }

    public static void fadeInView(View view) {
        AnimationUtil.fadeInView(view, ANIMATION_DURATION_SHORT);
    }

    public static void fadeInView(View view, int n) {
        AnimationUtil.fadeInView(view, n, null);
    }

    public static void fadeInView(View view, int n, final AnimationListener animationListener) {
        view.setVisibility(0);
        view.setAlpha(0.0f);
        ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
        if (animationListener != null) {
            viewPropertyAnimatorListener = new ViewPropertyAnimatorListener(){

                public void onAnimationCancel(View view) {
                }

                public void onAnimationEnd(View view) {
                    if (!animationListener.onAnimationEnd(view)) {
                        view.setDrawingCacheEnabled(false);
                    }
                }

                public void onAnimationStart(View view) {
                    if (!animationListener.onAnimationStart(view)) {
                        view.setDrawingCacheEnabled(true);
                    }
                }
            };
        }
        ViewCompat.animate((View)view).alpha(1.0f).setDuration((long)n).setListener(viewPropertyAnimatorListener);
    }

    public static void fadeOutView(View view) {
        AnimationUtil.fadeOutView(view, ANIMATION_DURATION_SHORT);
    }

    public static void fadeOutView(View view, int n) {
        AnimationUtil.fadeOutView(view, n, null);
    }

    public static void fadeOutView(View view, int n, final AnimationListener animationListener) {
        ViewCompat.animate((View)view).alpha(0.0f).setDuration((long)n).setListener(new ViewPropertyAnimatorListener(){

            public void onAnimationCancel(View view) {
            }

            public void onAnimationEnd(View view) {
                if (animationListener == null || !animationListener.onAnimationEnd(view)) {
                    view.setVisibility(8);
                    view.setDrawingCacheEnabled(false);
                }
            }

            public void onAnimationStart(View view) {
                if (animationListener == null || !animationListener.onAnimationStart(view)) {
                    view.setDrawingCacheEnabled(true);
                }
            }
        });
    }

    @TargetApi(value=21)
    public static void reveal(final View view, final AnimationListener animationListener) {
        Animator animator = ViewAnimationUtils.createCircularReveal((View)view, (int)(view.getWidth() - (int)TypedValue.applyDimension((int)1, (float)24.0f, (DisplayMetrics)view.getResources().getDisplayMetrics())), (int)(view.getHeight() / 2), (float)0.0f, (float)Math.max((int)view.getWidth(), (int)view.getHeight()));
        view.setVisibility(0);
        animator.addListener((Animator.AnimatorListener)new AnimatorListenerAdapter(){

            public void onAnimationCancel(Animator animator) {
                animationListener.onAnimationCancel(view);
            }

            public void onAnimationEnd(Animator animator) {
                animationListener.onAnimationEnd(view);
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                animationListener.onAnimationStart(view);
            }
        });
        animator.start();
    }

}

