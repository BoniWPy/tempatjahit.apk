/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.circ;

import com.daimajia.easing.BaseEasingMethod;

public class CircEaseOut
extends BaseEasingMethod {
    public CircEaseOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4 - 1.0f;
        return Float.valueOf((float)(f2 + f3 * (float)Math.sqrt((double)(1.0f - f5 * f5))));
    }
}

