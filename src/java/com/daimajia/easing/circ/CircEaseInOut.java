/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.circ;

import com.daimajia.easing.BaseEasingMethod;

public class CircEaseInOut
extends BaseEasingMethod {
    public CircEaseInOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / (f4 / 2.0f);
        if (f5 < 1.0f) {
            return Float.valueOf((float)(f2 + -f3 / 2.0f * ((float)Math.sqrt((double)(1.0f - f5 * f5)) - 1.0f)));
        }
        float f6 = f3 / 2.0f;
        float f7 = f5 - 2.0f;
        return Float.valueOf((float)(f2 + f6 * (1.0f + (float)Math.sqrt((double)(1.0f - f7 * f7)))));
    }
}

