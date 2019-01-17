/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.os.Handler
 *  android.os.Message
 *  android.support.v4.view.PagerAdapter
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.animation.Interpolator
 *  android.widget.RelativeLayout
 *  java.lang.Enum
 *  java.lang.Exception
 *  java.lang.IllegalStateException
 *  java.lang.NoSuchFieldError
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 *  java.util.Timer
 *  java.util.TimerTask
 */
package com.daimajia.slider.library;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import com.daimajia.slider.library.Animations.BaseAnimationInterface;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.R;
import com.daimajia.slider.library.SliderAdapter;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Transformers.AccordionTransformer;
import com.daimajia.slider.library.Transformers.BackgroundToForegroundTransformer;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.daimajia.slider.library.Transformers.CubeInTransformer;
import com.daimajia.slider.library.Transformers.DefaultTransformer;
import com.daimajia.slider.library.Transformers.DepthPageTransformer;
import com.daimajia.slider.library.Transformers.FadeTransformer;
import com.daimajia.slider.library.Transformers.FlipHorizontalTransformer;
import com.daimajia.slider.library.Transformers.FlipPageViewTransformer;
import com.daimajia.slider.library.Transformers.ForegroundToBackgroundTransformer;
import com.daimajia.slider.library.Transformers.RotateDownTransformer;
import com.daimajia.slider.library.Transformers.RotateUpTransformer;
import com.daimajia.slider.library.Transformers.StackTransformer;
import com.daimajia.slider.library.Transformers.TabletTransformer;
import com.daimajia.slider.library.Transformers.ZoomInTransformer;
import com.daimajia.slider.library.Transformers.ZoomOutSlideTransformer;
import com.daimajia.slider.library.Transformers.ZoomOutTransformer;
import com.daimajia.slider.library.Tricks.FixedSpeedScroller;
import com.daimajia.slider.library.Tricks.InfinitePagerAdapter;
import com.daimajia.slider.library.Tricks.InfiniteViewPager;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class SliderLayout
extends RelativeLayout {
    private boolean mAutoCycle;
    private boolean mAutoRecover = true;
    private Context mContext;
    private BaseAnimationInterface mCustomAnimation;
    private TimerTask mCycleTask;
    private Timer mCycleTimer;
    private boolean mCycling;
    private PagerIndicator mIndicator;
    private PagerIndicator.IndicatorVisibility mIndicatorVisibility = PagerIndicator.IndicatorVisibility.Visible;
    private TimerTask mResumingTask;
    private Timer mResumingTimer;
    private SliderAdapter mSliderAdapter;
    private long mSliderDuration = 4000L;
    private int mTransformerId;
    private int mTransformerSpan = 1100;
    private InfiniteViewPager mViewPager;
    private BaseTransformer mViewPagerTransformer;
    private Handler mh = new Handler(){

        public void handleMessage(Message message) {
            super.handleMessage(message);
            SliderLayout.this.moveNextPosition(true);
        }
    };

    public SliderLayout(Context context) {
        this(context, null);
    }

    public SliderLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.SliderStyle);
    }

    public SliderLayout(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.mContext = context;
        LayoutInflater.from((Context)context).inflate(R.layout.slider_layout, (ViewGroup)this, true);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.SliderLayout, n, 0);
        this.mTransformerSpan = typedArray.getInteger(R.styleable.SliderLayout_pager_animation_span, 1100);
        this.mTransformerId = typedArray.getInt(R.styleable.SliderLayout_pager_animation, Transformer.Default.ordinal());
        this.mAutoCycle = typedArray.getBoolean(R.styleable.SliderLayout_auto_cycle, true);
        int n2 = typedArray.getInt(R.styleable.SliderLayout_indicator_visibility, 0);
        PagerIndicator.IndicatorVisibility[] arrindicatorVisibility = PagerIndicator.IndicatorVisibility.values();
        int n3 = arrindicatorVisibility.length;
        int n4 = 0;
        do {
            block6 : {
                block5 : {
                    if (n4 >= n3) break block5;
                    PagerIndicator.IndicatorVisibility indicatorVisibility = arrindicatorVisibility[n4];
                    if (indicatorVisibility.ordinal() != n2) break block6;
                    this.mIndicatorVisibility = indicatorVisibility;
                }
                this.mSliderAdapter = new SliderAdapter(this.mContext);
                InfinitePagerAdapter infinitePagerAdapter = new InfinitePagerAdapter(this.mSliderAdapter);
                this.mViewPager = (InfiniteViewPager)this.findViewById(R.id.daimajia_slider_viewpager);
                this.mViewPager.setAdapter(infinitePagerAdapter);
                this.mViewPager.setOnTouchListener(new View.OnTouchListener(){

                    /*
                     * Enabled force condition propagation
                     * Lifted jumps to return sites
                     */
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            default: {
                                do {
                                    return false;
                                    break;
                                } while (true);
                            }
                            case 1: 
                        }
                        SliderLayout.this.recoverCycle();
                        return false;
                    }
                });
                typedArray.recycle();
                this.setPresetIndicator(PresetIndicators.Center_Bottom);
                this.setPresetTransformer(this.mTransformerId);
                this.setSliderTransformDuration(this.mTransformerSpan, null);
                this.setIndicatorVisibility(this.mIndicatorVisibility);
                if (this.mAutoCycle) {
                    this.startAutoCycle();
                }
                return;
            }
            ++n4;
        } while (true);
    }

    private SliderAdapter getRealAdapter() {
        PagerAdapter pagerAdapter = this.mViewPager.getAdapter();
        if (pagerAdapter != null) {
            return ((InfinitePagerAdapter)pagerAdapter).getRealAdapter();
        }
        return null;
    }

    private InfinitePagerAdapter getWrapperAdapter() {
        PagerAdapter pagerAdapter = this.mViewPager.getAdapter();
        if (pagerAdapter != null) {
            return (InfinitePagerAdapter)pagerAdapter;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void pauseAutoCycle() {
        if (this.mCycling) {
            this.mCycleTimer.cancel();
            this.mCycleTask.cancel();
            this.mCycling = false;
            return;
        } else {
            if (this.mResumingTimer == null || this.mResumingTask == null) return;
            {
                this.recoverCycle();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void recoverCycle() {
        if (!this.mAutoRecover || !this.mAutoCycle || this.mCycling) {
            return;
        }
        if (this.mResumingTask != null && this.mResumingTimer != null) {
            this.mResumingTimer.cancel();
            this.mResumingTask.cancel();
        }
        this.mResumingTimer = new Timer();
        this.mResumingTask = new TimerTask(){

            public void run() {
                SliderLayout.this.startAutoCycle();
            }
        };
        this.mResumingTimer.schedule(this.mResumingTask, 6000L);
    }

    public void addOnPageChangeListener(ViewPagerEx.OnPageChangeListener onPageChangeListener) {
        if (onPageChangeListener != null) {
            this.mViewPager.addOnPageChangeListener(onPageChangeListener);
        }
    }

    public <T extends BaseSliderView> void addSlider(T t) {
        this.mSliderAdapter.addSlider(t);
    }

    public int getCurrentPosition() {
        if (this.getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        return this.mViewPager.getCurrentItem() % this.getRealAdapter().getCount();
    }

    public BaseSliderView getCurrentSlider() {
        if (this.getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        int n = this.getRealAdapter().getCount();
        int n2 = this.mViewPager.getCurrentItem() % n;
        return this.getRealAdapter().getSliderView(n2);
    }

    public PagerIndicator.IndicatorVisibility getIndicatorVisibility() {
        if (this.mIndicator == null) {
            return this.mIndicator.getIndicatorVisibility();
        }
        return PagerIndicator.IndicatorVisibility.Invisible;
    }

    public PagerIndicator getPagerIndicator() {
        return this.mIndicator;
    }

    public void moveNextPosition() {
        this.moveNextPosition(true);
    }

    public void moveNextPosition(boolean bl) {
        if (this.getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        this.mViewPager.setCurrentItem(1 + this.mViewPager.getCurrentItem(), bl);
    }

    public void movePrevPosition() {
        this.movePrevPosition(true);
    }

    public void movePrevPosition(boolean bl) {
        if (this.getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        this.mViewPager.setCurrentItem(-1 + this.mViewPager.getCurrentItem(), bl);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            default: {
                do {
                    return false;
                    break;
                } while (true);
            }
            case 0: 
        }
        this.pauseAutoCycle();
        return false;
    }

    public void removeAllSliders() {
        if (this.getRealAdapter() != null) {
            int n = this.getRealAdapter().getCount();
            this.getRealAdapter().removeAllSliders();
            this.mViewPager.setCurrentItem(n + this.mViewPager.getCurrentItem(), false);
        }
    }

    public void removeOnPageChangeListener(ViewPagerEx.OnPageChangeListener onPageChangeListener) {
        this.mViewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    public void removeSliderAt(int n) {
        if (this.getRealAdapter() != null) {
            this.getRealAdapter().removeSliderAt(n);
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem(), false);
        }
    }

    public void setCurrentPosition(int n) {
        this.setCurrentPosition(n, true);
    }

    public void setCurrentPosition(int n, boolean bl) {
        if (this.getRealAdapter() == null) {
            throw new IllegalStateException("You did not set a slider adapter");
        }
        if (n >= this.getRealAdapter().getCount()) {
            throw new IllegalStateException("Item position is not exist");
        }
        int n2 = n - this.mViewPager.getCurrentItem() % this.getRealAdapter().getCount() + this.mViewPager.getCurrentItem();
        this.mViewPager.setCurrentItem(n2, bl);
    }

    public void setCustomAnimation(BaseAnimationInterface baseAnimationInterface) {
        this.mCustomAnimation = baseAnimationInterface;
        if (this.mViewPagerTransformer != null) {
            this.mViewPagerTransformer.setCustomAnimationInterface(this.mCustomAnimation);
        }
    }

    public void setCustomIndicator(PagerIndicator pagerIndicator) {
        if (this.mIndicator != null) {
            this.mIndicator.destroySelf();
        }
        this.mIndicator = pagerIndicator;
        this.mIndicator.setIndicatorVisibility(this.mIndicatorVisibility);
        this.mIndicator.setViewPager(this.mViewPager);
        this.mIndicator.redraw();
    }

    public void setDuration(long l) {
        if (l >= 500L) {
            this.mSliderDuration = l;
            if (this.mAutoCycle && this.mCycling) {
                this.startAutoCycle();
            }
        }
    }

    public void setIndicatorVisibility(PagerIndicator.IndicatorVisibility indicatorVisibility) {
        if (this.mIndicator == null) {
            return;
        }
        this.mIndicator.setIndicatorVisibility(indicatorVisibility);
    }

    public void setPagerTransformer(boolean bl, BaseTransformer baseTransformer) {
        this.mViewPagerTransformer = baseTransformer;
        this.mViewPagerTransformer.setCustomAnimationInterface(this.mCustomAnimation);
        this.mViewPager.setPageTransformer(bl, this.mViewPagerTransformer);
    }

    public void setPresetIndicator(PresetIndicators presetIndicators) {
        this.setCustomIndicator((PagerIndicator)this.findViewById(presetIndicators.getResourceId()));
    }

    public void setPresetTransformer(int n) {
        Transformer[] arrtransformer = Transformer.values();
        int n2 = arrtransformer.length;
        int n3 = 0;
        do {
            block4 : {
                block3 : {
                    if (n3 >= n2) break block3;
                    Transformer transformer = arrtransformer[n3];
                    if (transformer.ordinal() != n) break block4;
                    this.setPresetTransformer(transformer);
                }
                return;
            }
            ++n3;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void setPresetTransformer(Transformer var1_1) {
        block18 : {
            var2_2 = 5.$SwitchMap$com$daimajia$slider$library$SliderLayout$Transformer[var1_1.ordinal()];
            var3_3 = null;
            switch (var2_2) {
                case 1: {
                    var3_5 = new DefaultTransformer();
                    ** break;
                }
                case 2: {
                    var3_6 = new AccordionTransformer();
                    ** break;
                }
                case 3: {
                    var3_7 = new BackgroundToForegroundTransformer();
                    ** break;
                }
                case 4: {
                    var3_8 = new CubeInTransformer();
                    ** break;
                }
                case 5: {
                    var3_9 = new DepthPageTransformer();
                    ** break;
                }
                case 6: {
                    var3_10 = new FadeTransformer();
                    ** break;
                }
                case 7: {
                    var3_11 = new FlipHorizontalTransformer();
                    ** break;
                }
                case 8: {
                    var3_12 = new FlipPageViewTransformer();
                    ** break;
                }
                case 9: {
                    var3_13 = new ForegroundToBackgroundTransformer();
                    ** break;
                }
                case 10: {
                    var3_14 = new RotateDownTransformer();
                    ** break;
                }
                case 11: {
                    var3_15 = new RotateUpTransformer();
                    ** break;
                }
                case 12: {
                    var3_16 = new StackTransformer();
                    ** break;
                }
                case 13: {
                    var3_17 = new TabletTransformer();
                    ** break;
                }
                case 14: {
                    var3_18 = new ZoomInTransformer();
                    ** break;
                }
                case 15: {
                    var3_19 = new ZoomOutSlideTransformer();
                }
lbl48: // 16 sources:
                default: {
                    break block18;
                }
                case 16: 
            }
            var3_20 = new ZoomOutTransformer();
        }
        this.setPagerTransformer(true, (BaseTransformer)var3_4);
    }

    public void setPresetTransformer(String string2) {
        Transformer[] arrtransformer = Transformer.values();
        int n = arrtransformer.length;
        int n2 = 0;
        do {
            block4 : {
                block3 : {
                    if (n2 >= n) break block3;
                    Transformer transformer = arrtransformer[n2];
                    if (!transformer.equals(string2)) break block4;
                    this.setPresetTransformer(transformer);
                }
                return;
            }
            ++n2;
        } while (true);
    }

    public void setSliderTransformDuration(int n, Interpolator interpolator) {
        try {
            Field field = ViewPagerEx.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(this.mViewPager.getContext(), interpolator, n);
            field.set((Object)this.mViewPager, (Object)fixedSpeedScroller);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public void startAutoCycle() {
        this.startAutoCycle(1000L, this.mSliderDuration, this.mAutoRecover);
    }

    public void startAutoCycle(long l, long l2, boolean bl) {
        if (this.mCycleTimer != null) {
            this.mCycleTimer.cancel();
        }
        if (this.mCycleTask != null) {
            this.mCycleTask.cancel();
        }
        if (this.mResumingTask != null) {
            this.mResumingTask.cancel();
        }
        if (this.mResumingTimer != null) {
            this.mResumingTimer.cancel();
        }
        this.mSliderDuration = l2;
        this.mCycleTimer = new Timer();
        this.mAutoRecover = bl;
        this.mCycleTask = new TimerTask(){

            public void run() {
                SliderLayout.this.mh.sendEmptyMessage(0);
            }
        };
        this.mCycleTimer.schedule(this.mCycleTask, l, this.mSliderDuration);
        this.mCycling = true;
        this.mAutoCycle = true;
    }

    public void stopAutoCycle() {
        if (this.mCycleTask != null) {
            this.mCycleTask.cancel();
        }
        if (this.mCycleTimer != null) {
            this.mCycleTimer.cancel();
        }
        if (this.mResumingTimer != null) {
            this.mResumingTimer.cancel();
        }
        if (this.mResumingTask != null) {
            this.mResumingTask.cancel();
        }
        this.mAutoCycle = false;
        this.mCycling = false;
    }

    public static final class PresetIndicators
    extends Enum<PresetIndicators> {
        private static final /* synthetic */ PresetIndicators[] $VALUES;
        public static final /* enum */ PresetIndicators Center_Bottom = new PresetIndicators("Center_Bottom", R.id.default_center_bottom_indicator);
        public static final /* enum */ PresetIndicators Center_Top;
        public static final /* enum */ PresetIndicators Left_Bottom;
        public static final /* enum */ PresetIndicators Left_Top;
        public static final /* enum */ PresetIndicators Right_Bottom;
        public static final /* enum */ PresetIndicators Right_Top;
        private final int id;
        private final String name;

        static {
            Right_Bottom = new PresetIndicators("Right_Bottom", R.id.default_bottom_right_indicator);
            Left_Bottom = new PresetIndicators("Left_Bottom", R.id.default_bottom_left_indicator);
            Center_Top = new PresetIndicators("Center_Top", R.id.default_center_top_indicator);
            Right_Top = new PresetIndicators("Right_Top", R.id.default_center_top_right_indicator);
            Left_Top = new PresetIndicators("Left_Top", R.id.default_center_top_left_indicator);
            PresetIndicators[] arrpresetIndicators = new PresetIndicators[]{Center_Bottom, Right_Bottom, Left_Bottom, Center_Top, Right_Top, Left_Top};
            $VALUES = arrpresetIndicators;
        }

        private PresetIndicators(String string3, int n2) {
            this.name = string3;
            this.id = n2;
        }

        public static PresetIndicators valueOf(String string2) {
            return (PresetIndicators)Enum.valueOf(PresetIndicators.class, (String)string2);
        }

        public static PresetIndicators[] values() {
            return (PresetIndicators[])$VALUES.clone();
        }

        public int getResourceId() {
            return this.id;
        }

        public String toString() {
            return this.name;
        }
    }

    public static final class Transformer
    extends Enum<Transformer> {
        private static final /* synthetic */ Transformer[] $VALUES;
        public static final /* enum */ Transformer Accordion;
        public static final /* enum */ Transformer Background2Foreground;
        public static final /* enum */ Transformer CubeIn;
        public static final /* enum */ Transformer Default;
        public static final /* enum */ Transformer DepthPage;
        public static final /* enum */ Transformer Fade;
        public static final /* enum */ Transformer FlipHorizontal;
        public static final /* enum */ Transformer FlipPage;
        public static final /* enum */ Transformer Foreground2Background;
        public static final /* enum */ Transformer RotateDown;
        public static final /* enum */ Transformer RotateUp;
        public static final /* enum */ Transformer Stack;
        public static final /* enum */ Transformer Tablet;
        public static final /* enum */ Transformer ZoomIn;
        public static final /* enum */ Transformer ZoomOut;
        public static final /* enum */ Transformer ZoomOutSlide;
        private final String name;

        static {
            Default = new Transformer("Default");
            Accordion = new Transformer("Accordion");
            Background2Foreground = new Transformer("Background2Foreground");
            CubeIn = new Transformer("CubeIn");
            DepthPage = new Transformer("DepthPage");
            Fade = new Transformer("Fade");
            FlipHorizontal = new Transformer("FlipHorizontal");
            FlipPage = new Transformer("FlipPage");
            Foreground2Background = new Transformer("Foreground2Background");
            RotateDown = new Transformer("RotateDown");
            RotateUp = new Transformer("RotateUp");
            Stack = new Transformer("Stack");
            Tablet = new Transformer("Tablet");
            ZoomIn = new Transformer("ZoomIn");
            ZoomOutSlide = new Transformer("ZoomOutSlide");
            ZoomOut = new Transformer("ZoomOut");
            Transformer[] arrtransformer = new Transformer[]{Default, Accordion, Background2Foreground, CubeIn, DepthPage, Fade, FlipHorizontal, FlipPage, Foreground2Background, RotateDown, RotateUp, Stack, Tablet, ZoomIn, ZoomOutSlide, ZoomOut};
            $VALUES = arrtransformer;
        }

        private Transformer(String string3) {
            this.name = string3;
        }

        public static Transformer valueOf(String string2) {
            return (Transformer)Enum.valueOf(Transformer.class, (String)string2);
        }

        public static Transformer[] values() {
            return (Transformer[])$VALUES.clone();
        }

        public boolean equals(String string2) {
            if (string2 == null) {
                return false;
            }
            return this.name.equals((Object)string2);
        }

        public String toString() {
            return this.name;
        }
    }

}

