/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.expo;

import com.daimajia.easing.BaseEasingMethod;

public class ExpoEaseIn
extends BaseEasingMethod {
    public ExpoEaseIn(float f) {
        super(f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        if (f == 0.0f) {
            do {
                return Float.valueOf((float)f2);
                break;
            } while (true);
        }
        f2 += f3 * (float)Math.pow((double)2.0, (double)(10.0f * (f / f4 - 1.0f)));
        return Float.valueOf((float)f2);
    }
}

