/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorSet
 *  android.animation.TimeInterpolator
 *  android.support.v4.view.ViewCompat
 *  android.view.View
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package com.daimajia.androidanimations.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;

public abstract class BaseViewAnimator {
    public static final long DURATION = 1000L;
    private AnimatorSet mAnimatorSet = new AnimatorSet();
    private long mDuration = 1000L;

    public BaseViewAnimator addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorSet.addListener(animatorListener);
        return this;
    }

    public void animate() {
        this.start();
    }

    public void cancel() {
        this.mAnimatorSet.cancel();
    }

    public AnimatorSet getAnimatorAgent() {
        return this.mAnimatorSet;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getStartDelay() {
        return this.mAnimatorSet.getStartDelay();
    }

    public boolean isRunning() {
        return this.mAnimatorSet.isRunning();
    }

    public boolean isStarted() {
        return this.mAnimatorSet.isStarted();
    }

    protected abstract void prepare(View var1);

    public void removeAllListener() {
        this.mAnimatorSet.removeAllListeners();
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorSet.removeListener(animatorListener);
    }

    public void reset(View view) {
        ViewCompat.setAlpha((View)view, (float)1.0f);
        ViewCompat.setScaleX((View)view, (float)1.0f);
        ViewCompat.setScaleY((View)view, (float)1.0f);
        ViewCompat.setTranslationX((View)view, (float)0.0f);
        ViewCompat.setTranslationY((View)view, (float)0.0f);
        ViewCompat.setRotation((View)view, (float)0.0f);
        ViewCompat.setRotationY((View)view, (float)0.0f);
        ViewCompat.setRotationX((View)view, (float)0.0f);
    }

    public void restart() {
        this.mAnimatorSet = this.mAnimatorSet.clone();
        this.start();
    }

    public BaseViewAnimator setDuration(long l) {
        this.mDuration = l;
        return this;
    }

    public BaseViewAnimator setInterpolator(Interpolator interpolator) {
        this.mAnimatorSet.setInterpolator((TimeInterpolator)interpolator);
        return this;
    }

    public BaseViewAnimator setStartDelay(long l) {
        this.getAnimatorAgent().setStartDelay(l);
        return this;
    }

    public BaseViewAnimator setTarget(View view) {
        this.reset(view);
        this.prepare(view);
        return this;
    }

    public void start() {
        this.mAnimatorSet.setDuration(this.mDuration);
        this.mAnimatorSet.start();
    }
}

