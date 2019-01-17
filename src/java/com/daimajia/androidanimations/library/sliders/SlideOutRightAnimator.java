/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  java.lang.Object
 *  java.lang.String
 */
package com.daimajia.androidanimations.library.sliders;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class SlideOutRightAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        int n = ((ViewGroup)view.getParent()).getWidth() - view.getLeft();
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[2];
        arranimator[0] = ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{1.0f, 0.0f});
        float[] arrf = new float[]{0.0f, n};
        arranimator[1] = ObjectAnimator.ofFloat((Object)view, (String)"translationX", (float[])arrf);
        animatorSet.playTogether(arranimator);
    }
}

