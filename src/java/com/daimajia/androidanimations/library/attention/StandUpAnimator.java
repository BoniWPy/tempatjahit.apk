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
package com.daimajia.androidanimations.library.attention;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class StandUpAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        float f = (view.getWidth() - view.getPaddingLeft() - view.getPaddingRight()) / 2 + view.getPaddingLeft();
        float f2 = view.getHeight() - view.getPaddingBottom();
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[]{ObjectAnimator.ofFloat((Object)view, (String)"pivotX", (float[])new float[]{f, f, f, f, f}), ObjectAnimator.ofFloat((Object)view, (String)"pivotY", (float[])new float[]{f2, f2, f2, f2, f2}), ObjectAnimator.ofFloat((Object)view, (String)"rotationX", (float[])new float[]{55.0f, -30.0f, 15.0f, -15.0f, 0.0f})};
        animatorSet.playTogether(arranimator);
    }
}

