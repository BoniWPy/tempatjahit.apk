/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 */
package com.daimajia.easing.bounce;

import com.daimajia.easing.BaseEasingMethod;
import com.daimajia.easing.bounce.BounceEaseOut;

public class BounceEaseIn
extends BaseEasingMethod {
    private BounceEaseOut mBounceEaseOut;

    public BounceEaseIn(float f) {
        super(f);
        this.mBounceEaseOut = new BounceEaseOut(f);
    }

    @Override
    public Float calculate(float f, float f2, float f3, float f4) {
        return Float.valueOf((float)(f2 + (f3 - this.mBounceEaseOut.calculate(f4 - f, 0.0f, f3, f4).floatValue())));
    }
}

