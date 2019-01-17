/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.GestureDetector
 *  android.view.GestureDetector$OnGestureListener
 *  android.view.GestureDetector$SimpleOnGestureListener
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.Animation
 *  android.widget.AbsListView
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  java.lang.Object
 */
package com.orhanobut.dialogplus;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import com.orhanobut.dialogplus.SimpleAnimationListener;
import com.orhanobut.dialogplus.Utils;

class ExpandTouchListener
implements View.OnTouchListener {
    private final AbsListView absListView;
    private final View contentContainer;
    private final int defaultContentHeight;
    private final int displayHeight;
    private boolean fullScreen;
    private final GestureDetector gestureDetector;
    private final int gravity;
    private FrameLayout.LayoutParams params;
    private boolean scrollUp;
    private boolean touchUp;
    private float y;

    private ExpandTouchListener(Context context, AbsListView absListView, View view, int n, int n2, int n3) {
        this.absListView = absListView;
        this.contentContainer = view;
        this.gravity = n;
        this.displayHeight = n2;
        this.defaultContentHeight = n3;
        this.params = (FrameLayout.LayoutParams)view.getLayoutParams();
        this.gestureDetector = new GestureDetector(context, (GestureDetector.OnGestureListener)new GestureDetector.SimpleOnGestureListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                ExpandTouchListener expandTouchListener = ExpandTouchListener.this;
                boolean bl = f2 > 0.0f;
                expandTouchListener.scrollUp = bl;
                return false;
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public static ExpandTouchListener newListener(Context context, AbsListView absListView, View view, int n, int n2, int n3) {
        return new ExpandTouchListener(context, absListView, view, n, n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onTouchMove(View view, MotionEvent motionEvent) {
        float f;
        boolean bl = true;
        if (this.y == -1.0f) {
            this.y = motionEvent.getRawY();
        }
        boolean bl2 = (f = this.y - motionEvent.getRawY()) > 0.0f ? bl : false;
        this.touchUp = bl2;
        if (this.gravity == 48) {
            f = -f;
        }
        this.y = motionEvent.getRawY();
        int n = this.params.height + (int)f;
        if (n > this.displayHeight) {
            n = this.displayHeight;
        }
        if (n < this.defaultContentHeight) {
            n = this.defaultContentHeight;
        }
        this.params.height = n;
        this.contentContainer.setLayoutParams((ViewGroup.LayoutParams)this.params);
        if (this.params.height != this.displayHeight) {
            bl = false;
        }
        this.fullScreen = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onTouchUp(View view, MotionEvent motionEvent) {
        this.y = -1.0f;
        if (!this.touchUp && this.params.height < this.displayHeight && this.params.height > 4 * this.displayHeight / 5) {
            Utils.animateContent(this.contentContainer, this.displayHeight, new SimpleAnimationListener(){

                @Override
                public void onAnimationEnd(Animation animation) {
                    ExpandTouchListener.this.fullScreen = true;
                }
            });
            return;
        } else {
            if (this.touchUp && this.params.height > 50 + this.defaultContentHeight) {
                Utils.animateContent(this.contentContainer, this.displayHeight, new SimpleAnimationListener(){

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ExpandTouchListener.this.fullScreen = true;
                    }
                });
                return;
            }
            if (this.touchUp && this.params.height <= 50 + this.defaultContentHeight) {
                Utils.animateContent(this.contentContainer, this.defaultContentHeight, new SimpleAnimationListener());
                return;
            }
            if (this.touchUp || this.params.height <= this.defaultContentHeight) return;
            {
                Utils.animateContent(this.contentContainer, this.defaultContentHeight, new SimpleAnimationListener());
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.gestureDetector.onTouchEvent(motionEvent)) {
            return false;
        }
        if (this.scrollUp || !Utils.listIsAtTop(this.absListView)) {
            if (this.fullScreen) return false;
        }
        switch (motionEvent.getAction()) {
            case 0: {
                this.y = motionEvent.getRawY();
                return true;
            }
            case 2: {
                if (this.params.height == this.displayHeight) {
                    FrameLayout.LayoutParams layoutParams = this.params;
                    layoutParams.height = -1 + layoutParams.height;
                    this.contentContainer.setLayoutParams((ViewGroup.LayoutParams)this.params);
                    return false;
                }
                this.onTouchMove(view, motionEvent);
            }
            default: {
                return true;
            }
            case 1: 
        }
        this.onTouchUp(view, motionEvent);
        return true;
    }

}

