/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.quad;

import com.daimajia.easing.BaseEasingMethod;

public class QuadEaseIn
extends BaseEasingMethod {
    public QuadEaseIn(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4;
        return Float.valueOf((float)(f2 + f5 * (f3 * f5)));
    }
}

