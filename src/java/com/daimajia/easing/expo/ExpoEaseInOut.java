/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.expo;

import com.daimajia.easing.BaseEasingMethod;

public class ExpoEaseInOut
extends BaseEasingMethod {
    public ExpoEaseInOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        if (f == 0.0f) {
            return Float.valueOf((float)f2);
        }
        if (f == f4) {
            return Float.valueOf((float)(f2 + f3));
        }
        float f5 = f / (f4 / 2.0f);
        if (f5 < 1.0f) {
            return Float.valueOf((float)(f2 + f3 / 2.0f * (float)Math.pow((double)2.0, (double)(10.0f * (f5 - 1.0f)))));
        }
        return Float.valueOf((float)(f2 + f3 / 2.0f * (2.0f + -((float)Math.pow((double)2.0, (double)(-10.0f * (f5 - 1.0f)))))));
    }
}

