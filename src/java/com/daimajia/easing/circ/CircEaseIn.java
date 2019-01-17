/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.circ;

import com.daimajia.easing.BaseEasingMethod;

public class CircEaseIn
extends BaseEasingMethod {
    public CircEaseIn(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = -f3;
        float f6 = f / f4;
        return Float.valueOf((float)(f2 + f5 * ((float)Math.sqrt((double)(1.0f - f6 * f6)) - 1.0f)));
    }
}

