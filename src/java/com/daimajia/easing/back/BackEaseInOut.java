/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.back;

import com.daimajia.easing.BaseEasingMethod;

public class BackEaseInOut
extends BaseEasingMethod {
    private float s = 1.70158f;

    public BackEaseInOut(float f) {
        super(f);
    }

    public BackEaseInOut(float f, float f2) {
        this(f);
        this.s = f2;
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5;
        float f6 = f / (f4 / 2.0f);
        if (f6 < 1.0f) {
            float f7;
            float f8 = f3 / 2.0f;
            float f9 = f6 * f6;
            this.s = f7 = (float)(1.525 * (double)this.s);
            return Float.valueOf((float)(f2 + f8 * (f9 * (f6 * (f7 + 1.0f) - this.s))));
        }
        float f10 = f3 / 2.0f;
        float f11 = f6 - 2.0f;
        float f12 = f11 * f11;
        this.s = f5 = (float)(1.525 * (double)this.s);
        return Float.valueOf((float)(f2 + f10 * (2.0f + f12 * (f11 * (f5 + 1.0f) + this.s))));
    }
}

