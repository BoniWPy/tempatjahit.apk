/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.sine;

import com.daimajia.easing.BaseEasingMethod;

public class SineEaseOut
extends BaseEasingMethod {
    public SineEaseOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        return Float.valueOf((float)(f2 + f3 * (float)Math.sin((double)(1.5707963267948966 * (double)(f / f4)))));
    }
}

