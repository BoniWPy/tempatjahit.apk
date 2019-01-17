/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Math
 */
package com.daimajia.easing.elastic;

import com.daimajia.easing.BaseEasingMethod;

public class ElasticEaseIn
extends BaseEasingMethod {
    public ElasticEaseIn(float f) {
        super(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        if (f == 0.0f) {
            return Float.valueOf((float)f2);
        }
        float f5 = f / f4;
        if (f5 == 1.0f) {
            return Float.valueOf((float)(f2 + f3));
        }
        float f6 = f4 * 0.3f;
        float f7 = f6 / 4.0f;
        float f8 = f5 - 1.0f;
        return Float.valueOf((float)(f2 + -(f3 * (float)Math.pow((double)2.0, (double)(10.0f * f8)) * (float)Math.sin((double)(6.2831855f * (f8 * f4 - f7) / f6)))));
    }
}

