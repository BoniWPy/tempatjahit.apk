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
package com.daimajia.androidanimations.library.rotating_entrances;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class RotateInUpRightAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        float f = view.getWidth() - view.getPaddingRight();
        float f2 = view.getHeight() - view.getPaddingBottom();
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[]{ObjectAnimator.ofFloat((Object)view, (String)"rotation", (float[])new float[]{-90.0f, 0.0f}), ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat((Object)view, (String)"pivotX", (float[])new float[]{f, f}), ObjectAnimator.ofFloat((Object)view, (String)"pivotY", (float[])new float[]{f2, f2})};
        animatorSet.playTogether(arranimator);
    }
}

