/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.support.v4.view.ViewCompat
 *  android.view.View
 *  android.view.animation.Interpolator
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.List
 */
package com.daimajia.androidanimations.library;

import android.animation.Animator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;
import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.androidanimations.library.Techniques;
import java.util.ArrayList;
import java.util.List;

public class YoYo {
    public static final float CENTER_PIVOT = Float.MAX_VALUE;
    private static final long DURATION = 1000L;
    public static final int INFINITE = -1;
    private static final long NO_DELAY;
    private BaseViewAnimator animator;
    private List<Animator.AnimatorListener> callbacks;
    private long delay;
    private long duration;
    private Interpolator interpolator;
    private float pivotX;
    private float pivotY;
    private boolean repeat;
    private long repeatTimes;
    private View target;

    private YoYo(AnimationComposer animationComposer) {
        this.animator = animationComposer.animator;
        this.duration = animationComposer.duration;
        this.delay = animationComposer.delay;
        this.repeat = animationComposer.repeat;
        this.repeatTimes = animationComposer.repeatTimes;
        this.interpolator = animationComposer.interpolator;
        this.pivotX = animationComposer.pivotX;
        this.pivotY = animationComposer.pivotY;
        this.callbacks = animationComposer.callbacks;
        this.target = animationComposer.target;
    }

    /*
     * Enabled aggressive block sorting
     */
    private BaseViewAnimator play() {
        this.animator.setTarget(this.target);
        if (this.pivotX == Float.MAX_VALUE) {
            ViewCompat.setPivotX((View)this.target, (float)((float)this.target.getMeasuredWidth() / 2.0f));
        } else {
            this.target.setPivotX(this.pivotX);
        }
        if (this.pivotY == Float.MAX_VALUE) {
            ViewCompat.setPivotY((View)this.target, (float)((float)this.target.getMeasuredHeight() / 2.0f));
        } else {
            this.target.setPivotY(this.pivotY);
        }
        this.animator.setDuration(this.duration).setInterpolator(this.interpolator).setStartDelay(this.delay);
        if (this.callbacks.size() > 0) {
            for (Animator.AnimatorListener animatorListener : this.callbacks) {
                this.animator.addAnimatorListener(animatorListener);
            }
        }
        if (this.repeat) {
            this.animator.addAnimatorListener(new Animator.AnimatorListener(){
                private long currentTimes = 0L;

                public void onAnimationCancel(Animator animator) {
                    YoYo.this.repeatTimes = 0L;
                    YoYo.this.repeat = false;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void onAnimationEnd(Animator animator) {
                    if (!YoYo.this.repeat || YoYo.this.repeatTimes != -1L && this.currentTimes >= YoYo.this.repeatTimes) {
                        return;
                    }
                    YoYo.this.animator.restart();
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    this.currentTimes = 1L + this.currentTimes;
                }
            });
        }
        this.animator.animate();
        return this.animator;
    }

    public static AnimationComposer with(BaseViewAnimator baseViewAnimator) {
        return new AnimationComposer(baseViewAnimator);
    }

    public static AnimationComposer with(Techniques techniques) {
        return new AnimationComposer(techniques);
    }

    public static final class AnimationComposer {
        private BaseViewAnimator animator;
        private List<Animator.AnimatorListener> callbacks = new ArrayList();
        private long delay = 0L;
        private long duration = 1000L;
        private Interpolator interpolator;
        private float pivotX = Float.MAX_VALUE;
        private float pivotY = Float.MAX_VALUE;
        private boolean repeat = false;
        private long repeatTimes = 0L;
        private View target;

        private AnimationComposer(BaseViewAnimator baseViewAnimator) {
            this.animator = baseViewAnimator;
        }

        private AnimationComposer(Techniques techniques) {
            this.animator = techniques.getAnimator();
        }

        public AnimationComposer delay(long l) {
            this.delay = l;
            return this;
        }

        public AnimationComposer duration(long l) {
            this.duration = l;
            return this;
        }

        public AnimationComposer interpolate(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public AnimationComposer onCancel(final AnimatorCallback animatorCallback) {
            this.callbacks.add((Object)new EmptyAnimatorListener(){

                @Override
                public void onAnimationCancel(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onEnd(final AnimatorCallback animatorCallback) {
            this.callbacks.add((Object)new EmptyAnimatorListener(){

                @Override
                public void onAnimationEnd(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onRepeat(final AnimatorCallback animatorCallback) {
            this.callbacks.add((Object)new EmptyAnimatorListener(){

                @Override
                public void onAnimationRepeat(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onStart(final AnimatorCallback animatorCallback) {
            this.callbacks.add((Object)new EmptyAnimatorListener(){

                @Override
                public void onAnimationStart(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer pivot(float f, float f2) {
            this.pivotX = f;
            this.pivotY = f2;
            return this;
        }

        public AnimationComposer pivotX(float f) {
            this.pivotX = f;
            return this;
        }

        public AnimationComposer pivotY(float f) {
            this.pivotY = f;
            return this;
        }

        public YoYoString playOn(View view) {
            this.target = view;
            return new YoYoString(new YoYo(this).play(), this.target);
        }

        /*
         * Enabled aggressive block sorting
         */
        public AnimationComposer repeat(int n) {
            if (n < -1) {
                throw new RuntimeException("Can not be less than -1, -1 is infinite loop");
            }
            boolean bl = n != 0;
            this.repeat = bl;
            this.repeatTimes = n;
            return this;
        }

        public AnimationComposer withListener(Animator.AnimatorListener animatorListener) {
            this.callbacks.add((Object)animatorListener);
            return this;
        }

    }

    public static interface AnimatorCallback {
        public void call(Animator var1);
    }

    private static class EmptyAnimatorListener
    implements Animator.AnimatorListener {
        private EmptyAnimatorListener() {
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }
    }

    public static final class YoYoString {
        private BaseViewAnimator animator;
        private View target;

        private YoYoString(BaseViewAnimator baseViewAnimator, View view) {
            this.target = view;
            this.animator = baseViewAnimator;
        }

        public boolean isRunning() {
            return this.animator.isRunning();
        }

        public boolean isStarted() {
            return this.animator.isStarted();
        }

        public void stop() {
            this.stop(true);
        }

        public void stop(boolean bl) {
            this.animator.cancel();
            if (bl) {
                this.animator.reset(this.target);
            }
        }
    }

}

