/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.animation.ValueAnimator
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package com.daimajia.androidanimations.library.specials;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

public class HingeAnimator
extends BaseViewAnimator {
    @Override
    public void prepare(View view) {
        float f = view.getPaddingLeft();
        float f2 = view.getPaddingTop();
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[]{Glider.glide(Skill.SineEaseInOut, 1300.0f, (ValueAnimator)ObjectAnimator.ofFloat((Object)view, (String)"rotation", (float[])new float[]{0.0f, 80.0f, 60.0f, 80.0f, 60.0f, 60.0f})), ObjectAnimator.ofFloat((Object)view, (String)"translationY", (float[])new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 700.0f}), ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}), ObjectAnimator.ofFloat((Object)view, (String)"pivotX", (float[])new float[]{f, f, f, f, f, f}), ObjectAnimator.ofFloat((Object)view, (String)"pivotY", (float[])new float[]{f2, f2, f2, f2, f2, f2})};
        animatorSet.playTogether(arranimator);
        this.setDuration(1300L);
    }
}

