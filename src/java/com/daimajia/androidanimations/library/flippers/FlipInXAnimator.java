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
package com.daimajia.androidanimations.library.flippers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class FlipInXAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[]{ObjectAnimator.ofFloat((Object)view, (String)"rotationX", (float[])new float[]{90.0f, -15.0f, 15.0f, 0.0f}), ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{0.25f, 0.5f, 0.75f, 1.0f})};
        animatorSet.playTogether(arranimator);
    }
}

