/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.quint;

import com.daimajia.easing.BaseEasingMethod;

public class QuintEaseOut
extends BaseEasingMethod {
    public QuintEaseOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4 - 1.0f;
        return Float.valueOf((float)(f2 + f3 * (1.0f + f5 * (f5 * (f5 * (f5 * f5))))));
    }
}

