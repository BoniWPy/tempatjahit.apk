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
package com.daimajia.androidanimations.library.specials.in;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;

public class DropOutAnimator
extends BaseViewAnimator {
    @Override
    protected void prepare(View view) {
        int n = view.getTop() + view.getHeight();
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[2];
        arranimator[0] = ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{0.0f, 1.0f});
        Skill skill = Skill.BounceEaseOut;
        float f = this.getDuration();
        float[] arrf = new float[]{-n, 0.0f};
        arranimator[1] = Glider.glide(skill, f, (ValueAnimator)ObjectAnimator.ofFloat((Object)view, (String)"translationY", (float[])arrf));
        animatorSet.playTogether(arranimator);
    }
}

