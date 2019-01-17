/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Error
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Constructor
 */
package com.daimajia.easing;

import com.daimajia.easing.BaseEasingMethod;
import com.daimajia.easing.back.BackEaseIn;
import com.daimajia.easing.back.BackEaseInOut;
import com.daimajia.easing.back.BackEaseOut;
import com.daimajia.easing.bounce.BounceEaseIn;
import com.daimajia.easing.bounce.BounceEaseInOut;
import com.daimajia.easing.bounce.BounceEaseOut;
import com.daimajia.easing.circ.CircEaseIn;
import com.daimajia.easing.circ.CircEaseInOut;
import com.daimajia.easing.circ.CircEaseOut;
import com.daimajia.easing.cubic.CubicEaseIn;
import com.daimajia.easing.cubic.CubicEaseInOut;
import com.daimajia.easing.cubic.CubicEaseOut;
import com.daimajia.easing.elastic.ElasticEaseIn;
import com.daimajia.easing.elastic.ElasticEaseOut;
import com.daimajia.easing.expo.ExpoEaseIn;
import com.daimajia.easing.expo.ExpoEaseInOut;
import com.daimajia.easing.expo.ExpoEaseOut;
import com.daimajia.easing.linear.Linear;
import com.daimajia.easing.quad.QuadEaseIn;
import com.daimajia.easing.quad.QuadEaseInOut;
import com.daimajia.easing.quad.QuadEaseOut;
import com.daimajia.easing.quint.QuintEaseIn;
import com.daimajia.easing.quint.QuintEaseInOut;
import com.daimajia.easing.quint.QuintEaseOut;
import com.daimajia.easing.sine.SineEaseIn;
import com.daimajia.easing.sine.SineEaseInOut;
import com.daimajia.easing.sine.SineEaseOut;
import java.lang.reflect.Constructor;

public final class Skill
extends Enum<Skill> {
    private static final /* synthetic */ Skill[] $VALUES;
    public static final /* enum */ Skill BackEaseIn = new Skill(BackEaseIn.class);
    public static final /* enum */ Skill BackEaseInOut;
    public static final /* enum */ Skill BackEaseOut;
    public static final /* enum */ Skill BounceEaseIn;
    public static final /* enum */ Skill BounceEaseInOut;
    public static final /* enum */ Skill BounceEaseOut;
    public static final /* enum */ Skill CircEaseIn;
    public static final /* enum */ Skill CircEaseInOut;
    public static final /* enum */ Skill CircEaseOut;
    public static final /* enum */ Skill CubicEaseIn;
    public static final /* enum */ Skill CubicEaseInOut;
    public static final /* enum */ Skill CubicEaseOut;
    public static final /* enum */ Skill ElasticEaseIn;
    public static final /* enum */ Skill ElasticEaseOut;
    public static final /* enum */ Skill ExpoEaseIn;
    public static final /* enum */ Skill ExpoEaseInOut;
    public static final /* enum */ Skill ExpoEaseOut;
    public static final /* enum */ Skill Linear;
    public static final /* enum */ Skill QuadEaseIn;
    public static final /* enum */ Skill QuadEaseInOut;
    public static final /* enum */ Skill QuadEaseOut;
    public static final /* enum */ Skill QuintEaseIn;
    public static final /* enum */ Skill QuintEaseInOut;
    public static final /* enum */ Skill QuintEaseOut;
    public static final /* enum */ Skill SineEaseIn;
    public static final /* enum */ Skill SineEaseInOut;
    public static final /* enum */ Skill SineEaseOut;
    private Class easingMethod;

    static {
        BackEaseOut = new Skill(BackEaseOut.class);
        BackEaseInOut = new Skill(BackEaseInOut.class);
        BounceEaseIn = new Skill(BounceEaseIn.class);
        BounceEaseOut = new Skill(BounceEaseOut.class);
        BounceEaseInOut = new Skill(BounceEaseInOut.class);
        CircEaseIn = new Skill(CircEaseIn.class);
        CircEaseOut = new Skill(CircEaseOut.class);
        CircEaseInOut = new Skill(CircEaseInOut.class);
        CubicEaseIn = new Skill(CubicEaseIn.class);
        CubicEaseOut = new Skill(CubicEaseOut.class);
        CubicEaseInOut = new Skill(CubicEaseInOut.class);
        ElasticEaseIn = new Skill(ElasticEaseIn.class);
        ElasticEaseOut = new Skill(ElasticEaseOut.class);
        ExpoEaseIn = new Skill(ExpoEaseIn.class);
        ExpoEaseOut = new Skill(ExpoEaseOut.class);
        ExpoEaseInOut = new Skill(ExpoEaseInOut.class);
        QuadEaseIn = new Skill(QuadEaseIn.class);
        QuadEaseOut = new Skill(QuadEaseOut.class);
        QuadEaseInOut = new Skill(QuadEaseInOut.class);
        QuintEaseIn = new Skill(QuintEaseIn.class);
        QuintEaseOut = new Skill(QuintEaseOut.class);
        QuintEaseInOut = new Skill(QuintEaseInOut.class);
        SineEaseIn = new Skill(SineEaseIn.class);
        SineEaseOut = new Skill(SineEaseOut.class);
        SineEaseInOut = new Skill(SineEaseInOut.class);
        Linear = new Skill(Linear.class);
        Skill[] arrskill = new Skill[]{BackEaseIn, BackEaseOut, BackEaseInOut, BounceEaseIn, BounceEaseOut, BounceEaseInOut, CircEaseIn, CircEaseOut, CircEaseInOut, CubicEaseIn, CubicEaseOut, CubicEaseInOut, ElasticEaseIn, ElasticEaseOut, ExpoEaseIn, ExpoEaseOut, ExpoEaseInOut, QuadEaseIn, QuadEaseOut, QuadEaseInOut, QuintEaseIn, QuintEaseOut, QuintEaseInOut, SineEaseIn, SineEaseOut, SineEaseInOut, Linear};
        $VALUES = arrskill;
    }

    private Skill(Class class_) {
        this.easingMethod = class_;
    }

    public static Skill valueOf(String string2) {
        return (Skill)Enum.valueOf(Skill.class, (String)string2);
    }

    public static Skill[] values() {
        return (Skill[])$VALUES.clone();
    }

    public BaseEasingMethod getMethod(float f) {
        try {
            Class class_ = this.easingMethod;
            Class[] arrclass = new Class[]{Float.TYPE};
            Constructor constructor = class_.getConstructor(arrclass);
            Object[] arrobject = new Object[]{Float.valueOf((float)f)};
            BaseEasingMethod baseEasingMethod = (BaseEasingMethod)constructor.newInstance(arrobject);
            return baseEasingMethod;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw new Error("Can not init easingMethod instance");
        }
    }
}

