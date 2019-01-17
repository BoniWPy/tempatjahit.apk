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
package com.daimajia.androidanimations.library.zooming_exits;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class ZoomOutRightAnimator
extends BaseViewAnimator {
    @Override
    protected void prepare(View view) {
        ViewGroup viewGroup = (ViewGroup)view.getParent();
        int n = viewGroup.getWidth() - viewGroup.getLeft();
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[4];
        arranimator[0] = ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{1.0f, 1.0f, 0.0f});
        arranimator[1] = ObjectAnimator.ofFloat((Object)view, (String)"scaleX", (float[])new float[]{1.0f, 0.475f, 0.1f});
        arranimator[2] = ObjectAnimator.ofFloat((Object)view, (String)"scaleY", (float[])new float[]{1.0f, 0.475f, 0.1f});
        float[] arrf = new float[]{0.0f, -42.0f, n};
        arranimator[3] = ObjectAnimator.ofFloat((Object)view, (String)"translationX", (float[])arrf);
        animatorSet.playTogether(arranimator);
    }
}

