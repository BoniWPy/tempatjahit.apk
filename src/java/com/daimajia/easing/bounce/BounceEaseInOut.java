/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.bounce;

import com.daimajia.easing.BaseEasingMethod;
import com.daimajia.easing.bounce.BounceEaseIn;
import com.daimajia.easing.bounce.BounceEaseOut;

public class BounceEaseInOut
extends BaseEasingMethod {
    private BounceEaseIn mBounceEaseIn;
    private BounceEaseOut mBounceEaseOut;

    public BounceEaseInOut(float f) {
        super(f);
        this.mBounceEaseIn = new BounceEaseIn(f);
        this.mBounceEaseOut = new BounceEaseOut(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        if (f < f4 / 2.0f) {
            return Float.valueOf((float)(f2 + 0.5f * this.mBounceEaseIn.calculate(2.0f * f, 0.0f, f3, f4).floatValue()));
        }
        return Float.valueOf((float)(f2 + (0.5f * this.mBounceEaseOut.calculate(2.0f * f - f4, 0.0f, f3, f4).floatValue() + f3 * 0.5f)));
    }
}

