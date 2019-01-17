/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.animation.PropertyValuesHolder
 *  android.animation.TypeEvaluator
 *  android.animation.ValueAnimator
 *  java.lang.Object
 */
package com.daimajia.easing;

import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import com.daimajia.easing.BaseEasingMethod;
import com.daimajia.easing.Skill;

public class Glider {
    public static PropertyValuesHolder glide(Skill skill, float f, PropertyValuesHolder propertyValuesHolder) {
        propertyValuesHolder.setEvaluator((TypeEvaluator)skill.getMethod(f));
        return propertyValuesHolder;
    }

    public static ValueAnimator glide(Skill skill, float f, ValueAnimator valueAnimator) {
        return Glider.glide(skill, f, valueAnimator, null);
    }

    public static /* varargs */ ValueAnimator glide(Skill skill, float f, ValueAnimator valueAnimator, BaseEasingMethod.EasingListener ... arreasingListener) {
        BaseEasingMethod baseEasingMethod = skill.getMethod(f);
        if (arreasingListener != null) {
            baseEasingMethod.addEasingListeners(arreasingListener);
        }
        valueAnimator.setEvaluator((TypeEvaluator)baseEasingMethod);
        return valueAnimator;
    }
}

