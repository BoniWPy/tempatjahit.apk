/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package com.daimajia.androidanimations.library.zooming_entrances;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class ZoomInAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[]{ObjectAnimator.ofFloat((Object)view, (String)"scaleX", (float[])new float[]{0.45f, 1.0f}), ObjectAnimator.ofFloat((Object)view, (String)"scaleY", (float[])new float[]{0.45f, 1.0f}), ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{0.0f, 1.0f})};
        animatorSet.playTogether(arranimator);
    }
}

