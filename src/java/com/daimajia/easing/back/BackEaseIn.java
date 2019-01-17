/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.back;

import com.daimajia.easing.BaseEasingMethod;

public class BackEaseIn
extends BaseEasingMethod {
    private float s = 1.70158f;

    public BackEaseIn(float f) {
        super(f);
    }

    public BackEaseIn(float f, float f2) {
        this(f);
        this.s = f2;
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4;
        return Float.valueOf((float)(f2 + f5 * (f3 * f5) * (f5 * (1.0f + this.s) - this.s)));
    }
}

