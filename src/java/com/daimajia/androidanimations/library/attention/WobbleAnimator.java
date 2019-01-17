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

public class WobbleAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        float f = (float)((double)view.getWidth() / 100.0);
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[2];
        float[] arrf = new float[]{0.0f * f, -25.0f * f, 20.0f * f, -15.0f * f, 10.0f * f, -5.0f * f, 0.0f * f, 0.0f};
        arranimator[0] = ObjectAnimator.ofFloat((Object)view, (String)"translationX", (float[])arrf);
        arranimator[1] = ObjectAnimator.ofFloat((Object)view, (String)"rotation", (float[])new float[]{0.0f, -5.0f, 3.0f, -3.0f, 2.0f, -1.0f, 0.0f});
        animatorSet.playTogether(arranimator);
    }
}

