/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.expo;

import com.daimajia.easing.BaseEasingMethod;

public class ExpoEaseOut
extends BaseEasingMethod {
    public ExpoEaseOut(float f) {
        super(f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5;
        if (f == f4) {
            f5 = f2 + f3;
            do {
                return Float.valueOf((float)f5);
                break;
            } while (true);
        }
        f5 = f2 + f3 * (1.0f + -((float)Math.pow((double)2.0, (double)(-10.0f * f / f4))));
        return Float.valueOf((float)f5);
    }
}

