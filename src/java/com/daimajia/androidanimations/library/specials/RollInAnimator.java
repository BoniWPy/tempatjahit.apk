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
package com.daimajia.androidanimations.library.specials;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

public class RollInAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[3];
        arranimator[0] = ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{0.0f, 1.0f});
        float[] arrf = new float[]{-(view.getWidth() - view.getPaddingLeft() - view.getPaddingRight()), 0.0f};
        arranimator[1] = ObjectAnimator.ofFloat((Object)view, (String)"translationX", (float[])arrf);
        arranimator[2] = ObjectAnimator.ofFloat((Object)view, (String)"rotation", (float[])new float[]{-120.0f, 0.0f});
        animatorSet.playTogether(arranimator);
    }
}

