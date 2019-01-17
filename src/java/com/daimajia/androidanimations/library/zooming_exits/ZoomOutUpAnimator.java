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
package com.daimajia.androidanimations.library.zooming_exits;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class ZoomOutUpAnimator
extends BaseViewAnimator {
    @Override
    protected void prepare(View view) {
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[4];
        arranimator[0] = ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{1.0f, 1.0f, 0.0f});
        arranimator[1] = ObjectAnimator.ofFloat((Object)view, (String)"scaleX", (float[])new float[]{1.0f, 0.475f, 0.1f});
        arranimator[2] = ObjectAnimator.ofFloat((Object)view, (String)"scaleY", (float[])new float[]{1.0f, 0.475f, 0.1f});
        float[] arrf = new float[]{0.0f, 60.0f, -view.getBottom()};
        arranimator[3] = ObjectAnimator.ofFloat((Object)view, (String)"translationY", (float[])arrf);
        animatorSet.playTogether(arranimator);
    }
}

