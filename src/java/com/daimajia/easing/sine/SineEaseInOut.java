/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.sine;

import com.daimajia.easing.BaseEasingMethod;

public class SineEaseInOut
extends BaseEasingMethod {
    public SineEaseInOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        return Float.valueOf((float)(f2 + -f3 / 2.0f * ((float)Math.cos((double)(3.141592653589793 * (double)f / (double)f4)) - 1.0f)));
    }
}

