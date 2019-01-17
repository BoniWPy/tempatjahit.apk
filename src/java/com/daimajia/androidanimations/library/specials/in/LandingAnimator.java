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

public class LandingAnimator
extends BaseViewAnimator {
    @Override
    protected void prepare(View view) {
        AnimatorSet animatorSet = this.getAnimatorAgent();
        Animator[] arranimator = new Animator[]{Glider.glide(Skill.QuintEaseOut, (float)this.getDuration(), (ValueAnimator)ObjectAnimator.ofFloat((Object)view, (String)"scaleX", (float[])new float[]{1.5f, 1.0f})), Glider.glide(Skill.QuintEaseOut, (float)this.getDuration(), (ValueAnimator)ObjectAnimator.ofFloat((Object)view, (String)"scaleY", (float[])new float[]{1.5f, 1.0f})), Glider.glide(Skill.QuintEaseOut, (float)this.getDuration(), (ValueAnimator)ObjectAnimator.ofFloat((Object)view, (String)"alpha", (float[])new float[]{0.0f, 1.0f}))};
        animatorSet.playTogether(arranimator);
    }
}

