/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.cubic;

import com.daimajia.easing.BaseEasingMethod;

public class CubicEaseInOut
extends BaseEasingMethod {
    public CubicEaseInOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / (f4 / 2.0f);
        if (f5 < 1.0f) {
            return Float.valueOf((float)(f2 + f5 * (f5 * (f5 * (f3 / 2.0f)))));
        }
        float f6 = f3 / 2.0f;
        float f7 = f5 - 2.0f;
        return Float.valueOf((float)(f2 + f6 * (2.0f + f7 * (f7 * f7))));
    }
}

