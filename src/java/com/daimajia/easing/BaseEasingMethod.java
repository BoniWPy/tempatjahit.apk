/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.TypeEvaluator
 *  java.lang.Float
 *  java.lang.Number
 *  java.lang.Object
 *  java.util.ArrayList
 *  java.util.Iterator
 */
package com.daimajia.easing;

import android.animation.TypeEvaluator;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseEasingMethod
implements TypeEvaluator<Number> {
    protected float mDuration;
    private ArrayList<EasingListener> mListeners = new ArrayList();

    public BaseEasingMethod(float f) {
        this.mDuration = f;
    }

    public void addEasingListener(EasingListener easingListener) {
        this.mListeners.add((Object)easingListener);
    }

    public /* varargs */ void addEasingListeners(EasingListener ... arreasingListener) {
        for (EasingListener easingListener : arreasingListener) {
            this.mListeners.add((Object)easingListener);
        }
    }

    public abstract Float calculate(float var1, float var2, float var3, float var4);

    public void clearEasingListeners() {
        this.mListeners.clear();
    }

    public final Float evaluate(float f, Number number, Number number2) {
        float f2 = f * this.mDuration;
        float f3 = number.floatValue();
        float f4 = number2.floatValue() - number.floatValue();
        float f5 = this.mDuration;
        float f6 = this.calculate(f2, f3, f4, f5).floatValue();
        Iterator iterator = this.mListeners.iterator();
        while (iterator.hasNext()) {
            ((EasingListener)iterator.next()).on(f2, f6, f3, f4, f5);
        }
        return Float.valueOf((float)f6);
    }

    public void removeEasingListener(EasingListener easingListener) {
        this.mListeners.remove((Object)easingListener);
    }

    public void setDuration(float f) {
        this.mDuration = f;
    }

    public static interface EasingListener {
        public void on(float var1, float var2, float var3, float var4, float var5);
    }

}

