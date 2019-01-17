/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.bounce;

import com.daimajia.easing.BaseEasingMethod;

public class BounceEaseOut
extends BaseEasingMethod {
    public BounceEaseOut(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        float f5 = f / f4;
        if (f5 < 0.36363637f) {
            return Float.valueOf((float)(f2 + f3 * (f5 * (7.5625f * f5))));
        }
        if (f5 < 0.72727275f) {
            float f6 = f5 - 0.54545456f;
            return Float.valueOf((float)(f2 + f3 * (0.75f + f6 * (7.5625f * f6))));
        }
        if ((double)f5 < 0.9090909090909091) {
            float f7 = f5 - 0.8181818f;
            return Float.valueOf((float)(f2 + f3 * (0.9375f + f7 * (7.5625f * f7))));
        }
        float f8 = f5 - 0.95454544f;
        return Float.valueOf((float)(f2 + f3 * (0.984375f + f8 * (7.5625f * f8))));
    }
}

