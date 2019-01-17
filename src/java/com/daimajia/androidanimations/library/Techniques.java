/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Error
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 */
package com.daimajia.androidanimations.library;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.androidanimations.library.attention.BounceAnimator;
import com.daimajia.androidanimations.library.attention.FlashAnimator;
import com.daimajia.androidanimations.library.attention.PulseAnimator;
import com.daimajia.androidanimations.library.attention.RubberBandAnimator;
import com.daimajia.androidanimations.library.attention.ShakeAnimator;
import com.daimajia.androidanimations.library.attention.StandUpAnimator;
import com.daimajia.androidanimations.library.attention.SwingAnimator;
import com.daimajia.androidanimations.library.attention.TadaAnimator;
import com.daimajia.androidanimations.library.attention.WaveAnimator;
import com.daimajia.androidanimations.library.attention.WobbleAnimator;
import com.daimajia.androidanimations.library.bouncing_entrances.BounceInAnimator;
import com.daimajia.androidanimations.library.bouncing_entrances.BounceInDownAnimator;
import com.daimajia.androidanimations.library.bouncing_entrances.BounceInLeftAnimator;
import com.daimajia.androidanimations.library.bouncing_entrances.BounceInRightAnimator;
import com.daimajia.androidanimations.library.bouncing_entrances.BounceInUpAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInDownAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInLeftAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInRightAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInUpAnimator;
import com.daimajia.androidanimations.library.fading_exits.FadeOutAnimator;
import com.daimajia.androidanimations.library.fading_exits.FadeOutDownAnimator;
import com.daimajia.androidanimations.library.fading_exits.FadeOutLeftAnimator;
import com.daimajia.androidanimations.library.fading_exits.FadeOutRightAnimator;
import com.daimajia.androidanimations.library.fading_exits.FadeOutUpAnimator;
import com.daimajia.androidanimations.library.flippers.FlipInXAnimator;
import com.daimajia.androidanimations.library.flippers.FlipInYAnimator;
import com.daimajia.androidanimations.library.flippers.FlipOutXAnimator;
import com.daimajia.androidanimations.library.flippers.FlipOutYAnimator;
import com.daimajia.androidanimations.library.rotating_entrances.RotateInAnimator;
import com.daimajia.androidanimations.library.rotating_entrances.RotateInDownLeftAnimator;
import com.daimajia.androidanimations.library.rotating_entrances.RotateInDownRightAnimator;
import com.daimajia.androidanimations.library.rotating_entrances.RotateInUpLeftAnimator;
import com.daimajia.androidanimations.library.rotating_entrances.RotateInUpRightAnimator;
import com.daimajia.androidanimations.library.rotating_exits.RotateOutAnimator;
import com.daimajia.androidanimations.library.rotating_exits.RotateOutDownLeftAnimator;
import com.daimajia.androidanimations.library.rotating_exits.RotateOutDownRightAnimator;
import com.daimajia.androidanimations.library.rotating_exits.RotateOutUpLeftAnimator;
import com.daimajia.androidanimations.library.rotating_exits.RotateOutUpRightAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInDownAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInLeftAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInRightAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInUpAnimator;
import com.daimajia.androidanimations.library.sliders.SlideOutDownAnimator;
import com.daimajia.androidanimations.library.sliders.SlideOutLeftAnimator;
import com.daimajia.androidanimations.library.sliders.SlideOutRightAnimator;
import com.daimajia.androidanimations.library.sliders.SlideOutUpAnimator;
import com.daimajia.androidanimations.library.specials.HingeAnimator;
import com.daimajia.androidanimations.library.specials.RollInAnimator;
import com.daimajia.androidanimations.library.specials.RollOutAnimator;
import com.daimajia.androidanimations.library.specials.in.DropOutAnimator;
import com.daimajia.androidanimations.library.specials.in.LandingAnimator;
import com.daimajia.androidanimations.library.specials.out.TakingOffAnimator;
import com.daimajia.androidanimations.library.zooming_entrances.ZoomInAnimator;
import com.daimajia.androidanimations.library.zooming_entrances.ZoomInDownAnimator;
import com.daimajia.androidanimations.library.zooming_entrances.ZoomInLeftAnimator;
import com.daimajia.androidanimations.library.zooming_entrances.ZoomInRightAnimator;
import com.daimajia.androidanimations.library.zooming_entrances.ZoomInUpAnimator;
import com.daimajia.androidanimations.library.zooming_exits.ZoomOutAnimator;
import com.daimajia.androidanimations.library.zooming_exits.ZoomOutDownAnimator;
import com.daimajia.androidanimations.library.zooming_exits.ZoomOutLeftAnimator;
import com.daimajia.androidanimations.library.zooming_exits.ZoomOutRightAnimator;
import com.daimajia.androidanimations.library.zooming_exits.ZoomOutUpAnimator;

public final class Techniques
extends Enum<Techniques> {
    private static final /* synthetic */ Techniques[] $VALUES;
    public static final /* enum */ Techniques Bounce;
    public static final /* enum */ Techniques BounceIn;
    public static final /* enum */ Techniques BounceInDown;
    public static final /* enum */ Techniques BounceInLeft;
    public static final /* enum */ Techniques BounceInRight;
    public static final /* enum */ Techniques BounceInUp;
    public static final /* enum */ Techniques DropOut;
    public static final /* enum */ Techniques FadeIn;
    public static final /* enum */ Techniques FadeInDown;
    public static final /* enum */ Techniques FadeInLeft;
    public static final /* enum */ Techniques FadeInRight;
    public static final /* enum */ Techniques FadeInUp;
    public static final /* enum */ Techniques FadeOut;
    public static final /* enum */ Techniques FadeOutDown;
    public static final /* enum */ Techniques FadeOutLeft;
    public static final /* enum */ Techniques FadeOutRight;
    public static final /* enum */ Techniques FadeOutUp;
    public static final /* enum */ Techniques Flash;
    public static final /* enum */ Techniques FlipInX;
    public static final /* enum */ Techniques FlipInY;
    public static final /* enum */ Techniques FlipOutX;
    public static final /* enum */ Techniques FlipOutY;
    public static final /* enum */ Techniques Hinge;
    public static final /* enum */ Techniques Landing;
    public static final /* enum */ Techniques Pulse;
    public static final /* enum */ Techniques RollIn;
    public static final /* enum */ Techniques RollOut;
    public static final /* enum */ Techniques RotateIn;
    public static final /* enum */ Techniques RotateInDownLeft;
    public static final /* enum */ Techniques RotateInDownRight;
    public static final /* enum */ Techniques RotateInUpLeft;
    public static final /* enum */ Techniques RotateInUpRight;
    public static final /* enum */ Techniques RotateOut;
    public static final /* enum */ Techniques RotateOutDownLeft;
    public static final /* enum */ Techniques RotateOutDownRight;
    public static final /* enum */ Techniques RotateOutUpLeft;
    public static final /* enum */ Techniques RotateOutUpRight;
    public static final /* enum */ Techniques RubberBand;
    public static final /* enum */ Techniques Shake;
    public static final /* enum */ Techniques SlideInDown;
    public static final /* enum */ Techniques SlideInLeft;
    public static final /* enum */ Techniques SlideInRight;
    public static final /* enum */ Techniques SlideInUp;
    public static final /* enum */ Techniques SlideOutDown;
    public static final /* enum */ Techniques SlideOutLeft;
    public static final /* enum */ Techniques SlideOutRight;
    public static final /* enum */ Techniques SlideOutUp;
    public static final /* enum */ Techniques StandUp;
    public static final /* enum */ Techniques Swing;
    public static final /* enum */ Techniques Tada;
    public static final /* enum */ Techniques TakingOff;
    public static final /* enum */ Techniques Wave;
    public static final /* enum */ Techniques Wobble;
    public static final /* enum */ Techniques ZoomIn;
    public static final /* enum */ Techniques ZoomInDown;
    public static final /* enum */ Techniques ZoomInLeft;
    public static final /* enum */ Techniques ZoomInRight;
    public static final /* enum */ Techniques ZoomInUp;
    public static final /* enum */ Techniques ZoomOut;
    public static final /* enum */ Techniques ZoomOutDown;
    public static final /* enum */ Techniques ZoomOutLeft;
    public static final /* enum */ Techniques ZoomOutRight;
    public static final /* enum */ Techniques ZoomOutUp;
    private Class animatorClazz;

    static {
        DropOut = new Techniques(DropOutAnimator.class);
        Landing = new Techniques(LandingAnimator.class);
        TakingOff = new Techniques(TakingOffAnimator.class);
        Flash = new Techniques(FlashAnimator.class);
        Pulse = new Techniques(PulseAnimator.class);
        RubberBand = new Techniques(RubberBandAnimator.class);
        Shake = new Techniques(ShakeAnimator.class);
        Swing = new Techniques(SwingAnimator.class);
        Wobble = new Techniques(WobbleAnimator.class);
        Bounce = new Techniques(BounceAnimator.class);
        Tada = new Techniques(TadaAnimator.class);
        StandUp = new Techniques(StandUpAnimator.class);
        Wave = new Techniques(WaveAnimator.class);
        Hinge = new Techniques(HingeAnimator.class);
        RollIn = new Techniques(RollInAnimator.class);
        RollOut = new Techniques(RollOutAnimator.class);
        BounceIn = new Techniques(BounceInAnimator.class);
        BounceInDown = new Techniques(BounceInDownAnimator.class);
        BounceInLeft = new Techniques(BounceInLeftAnimator.class);
        BounceInRight = new Techniques(BounceInRightAnimator.class);
        BounceInUp = new Techniques(BounceInUpAnimator.class);
        FadeIn = new Techniques(FadeInAnimator.class);
        FadeInUp = new Techniques(FadeInUpAnimator.class);
        FadeInDown = new Techniques(FadeInDownAnimator.class);
        FadeInLeft = new Techniques(FadeInLeftAnimator.class);
        FadeInRight = new Techniques(FadeInRightAnimator.class);
        FadeOut = new Techniques(FadeOutAnimator.class);
        FadeOutDown = new Techniques(FadeOutDownAnimator.class);
        FadeOutLeft = new Techniques(FadeOutLeftAnimator.class);
        FadeOutRight = new Techniques(FadeOutRightAnimator.class);
        FadeOutUp = new Techniques(FadeOutUpAnimator.class);
        FlipInX = new Techniques(FlipInXAnimator.class);
        FlipOutX = new Techniques(FlipOutXAnimator.class);
        FlipInY = new Techniques(FlipInYAnimator.class);
        FlipOutY = new Techniques(FlipOutYAnimator.class);
        RotateIn = new Techniques(RotateInAnimator.class);
        RotateInDownLeft = new Techniques(RotateInDownLeftAnimator.class);
        RotateInDownRight = new Techniques(RotateInDownRightAnimator.class);
        RotateInUpLeft = new Techniques(RotateInUpLeftAnimator.class);
        RotateInUpRight = new Techniques(RotateInUpRightAnimator.class);
        RotateOut = new Techniques(RotateOutAnimator.class);
        RotateOutDownLeft = new Techniques(RotateOutDownLeftAnimator.class);
        RotateOutDownRight = new Techniques(RotateOutDownRightAnimator.class);
        RotateOutUpLeft = new Techniques(RotateOutUpLeftAnimator.class);
        RotateOutUpRight = new Techniques(RotateOutUpRightAnimator.class);
        SlideInLeft = new Techniques(SlideInLeftAnimator.class);
        SlideInRight = new Techniques(SlideInRightAnimator.class);
        SlideInUp = new Techniques(SlideInUpAnimator.class);
        SlideInDown = new Techniques(SlideInDownAnimator.class);
        SlideOutLeft = new Techniques(SlideOutLeftAnimator.class);
        SlideOutRight = new Techniques(SlideOutRightAnimator.class);
        SlideOutUp = new Techniques(SlideOutUpAnimator.class);
        SlideOutDown = new Techniques(SlideOutDownAnimator.class);
        ZoomIn = new Techniques(ZoomInAnimator.class);
        ZoomInDown = new Techniques(ZoomInDownAnimator.class);
        ZoomInLeft = new Techniques(ZoomInLeftAnimator.class);
        ZoomInRight = new Techniques(ZoomInRightAnimator.class);
        ZoomInUp = new Techniques(ZoomInUpAnimator.class);
        ZoomOut = new Techniques(ZoomOutAnimator.class);
        ZoomOutDown = new Techniques(ZoomOutDownAnimator.class);
        ZoomOutLeft = new Techniques(ZoomOutLeftAnimator.class);
        ZoomOutRight = new Techniques(ZoomOutRightAnimator.class);
        ZoomOutUp = new Techniques(ZoomOutUpAnimator.class);
        Techniques[] arrtechniques = new Techniques[]{DropOut, Landing, TakingOff, Flash, Pulse, RubberBand, Shake, Swing, Wobble, Bounce, Tada, StandUp, Wave, Hinge, RollIn, RollOut, BounceIn, BounceInDown, BounceInLeft, BounceInRight, BounceInUp, FadeIn, FadeInUp, FadeInDown, FadeInLeft, FadeInRight, FadeOut, FadeOutDown, FadeOutLeft, FadeOutRight, FadeOutUp, FlipInX, FlipOutX, FlipInY, FlipOutY, RotateIn, RotateInDownLeft, RotateInDownRight, RotateInUpLeft, RotateInUpRight, RotateOut, RotateOutDownLeft, RotateOutDownRight, RotateOutUpLeft, RotateOutUpRight, SlideInLeft, SlideInRight, SlideInUp, SlideInDown, SlideOutLeft, SlideOutRight, SlideOutUp, SlideOutDown, ZoomIn, ZoomInDown, ZoomInLeft, ZoomInRight, ZoomInUp, ZoomOut, ZoomOutDown, ZoomOutLeft, ZoomOutRight, ZoomOutUp};
        $VALUES = arrtechniques;
    }

    private Techniques(Class class_) {
        this.animatorClazz = class_;
    }

    public static Techniques valueOf(String string2) {
        return (Techniques)Enum.valueOf(Techniques.class, (String)string2);
    }

    public static Techniques[] values() {
        return (Techniques[])$VALUES.clone();
    }

    public BaseViewAnimator getAnimator() {
        try {
            BaseViewAnimator baseViewAnimator = (BaseViewAnimator)this.animatorClazz.newInstance();
            return baseViewAnimator;
        }
        catch (Exception exception) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}

