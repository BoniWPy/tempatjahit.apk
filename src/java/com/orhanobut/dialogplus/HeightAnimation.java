/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.Animation
 *  android.view.animation.Transformation
 */
package com.orhanobut.dialogplus;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class HeightAnimation
extends Animation {
    protected final int originalHeight;
    protected float perValue;
    protected final View view;

    public HeightAnimation(View view, int n, int n2) {
        this.view = view;
        this.originalHeight = n;
        this.perValue = n2 - n;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        this.view.getLayoutParams().height = (int)((float)this.originalHeight + f * this.perValue);
        this.view.requestLayout();
    }

    public boolean willChangeBounds() {
        return true;
    }
}

