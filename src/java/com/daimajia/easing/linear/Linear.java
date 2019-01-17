/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.linear;

import com.daimajia.easing.BaseEasingMethod;

public class Linear
extends BaseEasingMethod {
    public Linear(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        return Float.valueOf((float)(f2 + f3 * f / f4));
    }
}

