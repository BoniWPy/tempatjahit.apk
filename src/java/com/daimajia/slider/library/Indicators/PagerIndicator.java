/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.database.DataSetObserver
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.graphics.drawable.LayerDrawable
 *  android.support.v4.view.PagerAdapter
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  java.lang.Enum
 *  java.lang.IllegalStateException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Iterator
 */
package com.daimajia.slider.library.Indicators;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.daimajia.slider.library.R;
import com.daimajia.slider.library.SliderAdapter;
import com.daimajia.slider.library.Tricks.InfinitePagerAdapter;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import java.util.ArrayList;
import java.util.Iterator;

public class PagerIndicator
extends LinearLayout
implements ViewPagerEx.OnPageChangeListener {
    private DataSetObserver dataChangeObserver = new DataSetObserver(){

        /*
         * Enabled aggressive block sorting
         */
        public void onChanged() {
            PagerAdapter pagerAdapter = PagerIndicator.this.mPager.getAdapter();
            int n = pagerAdapter instanceof InfinitePagerAdapter ? ((InfinitePagerAdapter)pagerAdapter).getRealCount() : pagerAdapter.getCount();
            if (n > PagerIndicator.this.mItemCount) {
                for (int i = 0; i < n - PagerIndicator.this.mItemCount; ++i) {
                    ImageView imageView = new ImageView(PagerIndicator.this.mContext);
                    imageView.setImageDrawable(PagerIndicator.this.mUnselectedDrawable);
                    imageView.setPadding((int)PagerIndicator.this.mUnSelectedPadding_Left, (int)PagerIndicator.this.mUnSelectedPadding_Top, (int)PagerIndicator.this.mUnSelectedPadding_Right, (int)PagerIndicator.this.mUnSelectedPadding_Bottom);
                    PagerIndicator.this.addView((View)imageView);
                    PagerIndicator.this.mIndicators.add((Object)imageView);
                }
            } else if (n < PagerIndicator.this.mItemCount) {
                for (int i = 0; i < PagerIndicator.this.mItemCount - n; ++i) {
                    PagerIndicator.this.removeView((View)PagerIndicator.this.mIndicators.get(0));
                    PagerIndicator.this.mIndicators.remove(0);
                }
            }
            PagerIndicator.this.mItemCount = n;
            PagerIndicator.this.mPager.setCurrentItem(20 * PagerIndicator.this.mItemCount + PagerIndicator.this.mPager.getCurrentItem());
        }

        public void onInvalidated() {
            super.onInvalidated();
            PagerIndicator.this.redraw();
        }
    };
    private Context mContext;
    private int mDefaultSelectedColor;
    private float mDefaultSelectedHeight;
    private float mDefaultSelectedWidth;
    private int mDefaultUnSelectedColor;
    private float mDefaultUnSelectedHeight;
    private float mDefaultUnSelectedWidth;
    private Shape mIndicatorShape = Shape.Oval;
    private ArrayList<ImageView> mIndicators = new ArrayList();
    private int mItemCount = 0;
    private float mPadding_bottom;
    private float mPadding_left;
    private float mPadding_right;
    private float mPadding_top;
    private ViewPagerEx mPager;
    private ImageView mPreviousSelectedIndicator;
    private int mPreviousSelectedPosition;
    private Drawable mSelectedDrawable;
    private GradientDrawable mSelectedGradientDrawable;
    private LayerDrawable mSelectedLayerDrawable;
    private float mSelectedPadding_Bottom;
    private float mSelectedPadding_Left;
    private float mSelectedPadding_Right;
    private float mSelectedPadding_Top;
    private GradientDrawable mUnSelectedGradientDrawable;
    private LayerDrawable mUnSelectedLayerDrawable;
    private float mUnSelectedPadding_Bottom;
    private float mUnSelectedPadding_Left;
    private float mUnSelectedPadding_Right;
    private float mUnSelectedPadding_Top;
    private Drawable mUnselectedDrawable;
    private int mUserSetSelectedIndicatorResId;
    private int mUserSetUnSelectedIndicatorResId;
    private IndicatorVisibility mVisibility = IndicatorVisibility.Visible;

    public PagerIndicator(Context context) {
        this(context, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public PagerIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.PagerIndicator, 0, 0);
        int n = typedArray.getInt(R.styleable.PagerIndicator_visibility, IndicatorVisibility.Visible.ordinal());
        IndicatorVisibility[] arrindicatorVisibility = IndicatorVisibility.values();
        int n2 = arrindicatorVisibility.length;
        int n3 = 0;
        do {
            if (n3 >= n2) break;
            IndicatorVisibility indicatorVisibility = arrindicatorVisibility[n3];
            if (indicatorVisibility.ordinal() == n) {
                this.mVisibility = indicatorVisibility;
                break;
            }
            ++n3;
        } while (true);
        int n4 = typedArray.getInt(R.styleable.PagerIndicator_shape, Shape.Oval.ordinal());
        Shape[] arrshape = Shape.values();
        int n5 = arrshape.length;
        int n6 = 0;
        do {
            block7 : {
                block6 : {
                    if (n6 >= n5) break block6;
                    Shape shape = arrshape[n6];
                    if (shape.ordinal() != n4) break block7;
                    this.mIndicatorShape = shape;
                }
                this.mUserSetSelectedIndicatorResId = typedArray.getResourceId(R.styleable.PagerIndicator_selected_drawable, 0);
                this.mUserSetUnSelectedIndicatorResId = typedArray.getResourceId(R.styleable.PagerIndicator_unselected_drawable, 0);
                this.mDefaultSelectedColor = typedArray.getColor(R.styleable.PagerIndicator_selected_color, Color.rgb((int)255, (int)255, (int)255));
                this.mDefaultUnSelectedColor = typedArray.getColor(R.styleable.PagerIndicator_unselected_color, Color.argb((int)33, (int)255, (int)255, (int)255));
                this.mDefaultSelectedWidth = typedArray.getDimension(R.styleable.PagerIndicator_selected_width, (float)((int)this.pxFromDp(6.0f)));
                this.mDefaultSelectedHeight = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_selected_height, (int)this.pxFromDp(6.0f));
                this.mDefaultUnSelectedWidth = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_unselected_width, (int)this.pxFromDp(6.0f));
                this.mDefaultUnSelectedHeight = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_unselected_height, (int)this.pxFromDp(6.0f));
                this.mSelectedGradientDrawable = new GradientDrawable();
                this.mUnSelectedGradientDrawable = new GradientDrawable();
                this.mPadding_left = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_padding_left, (int)this.pxFromDp(3.0f));
                this.mPadding_right = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_padding_right, (int)this.pxFromDp(3.0f));
                this.mPadding_top = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_padding_top, (int)this.pxFromDp(0.0f));
                this.mPadding_bottom = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_padding_bottom, (int)this.pxFromDp(0.0f));
                this.mSelectedPadding_Left = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_selected_padding_left, (int)this.mPadding_left);
                this.mSelectedPadding_Right = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_selected_padding_right, (int)this.mPadding_right);
                this.mSelectedPadding_Top = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_selected_padding_top, (int)this.mPadding_top);
                this.mSelectedPadding_Bottom = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_selected_padding_bottom, (int)this.mPadding_bottom);
                this.mUnSelectedPadding_Left = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_unselected_padding_left, (int)this.mPadding_left);
                this.mUnSelectedPadding_Right = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_unselected_padding_right, (int)this.mPadding_right);
                this.mUnSelectedPadding_Top = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_unselected_padding_top, (int)this.mPadding_top);
                this.mUnSelectedPadding_Bottom = typedArray.getDimensionPixelSize(R.styleable.PagerIndicator_unselected_padding_bottom, (int)this.mPadding_bottom);
                Drawable[] arrdrawable = new Drawable[]{this.mSelectedGradientDrawable};
                this.mSelectedLayerDrawable = new LayerDrawable(arrdrawable);
                Drawable[] arrdrawable2 = new Drawable[]{this.mUnSelectedGradientDrawable};
                this.mUnSelectedLayerDrawable = new LayerDrawable(arrdrawable2);
                this.setIndicatorStyleResource(this.mUserSetSelectedIndicatorResId, this.mUserSetUnSelectedIndicatorResId);
                this.setDefaultIndicatorShape(this.mIndicatorShape);
                this.setDefaultSelectedIndicatorSize(this.mDefaultSelectedWidth, this.mDefaultSelectedHeight, Unit.Px);
                this.setDefaultUnselectedIndicatorSize(this.mDefaultUnSelectedWidth, this.mDefaultUnSelectedHeight, Unit.Px);
                this.setDefaultIndicatorColor(this.mDefaultSelectedColor, this.mDefaultUnSelectedColor);
                this.setIndicatorVisibility(this.mVisibility);
                typedArray.recycle();
                return;
            }
            ++n6;
        } while (true);
    }

    private float dpFromPx(float f) {
        return f / this.getContext().getResources().getDisplayMetrics().density;
    }

    private int getShouldDrawCount() {
        if (this.mPager.getAdapter() instanceof InfinitePagerAdapter) {
            return ((InfinitePagerAdapter)this.mPager.getAdapter()).getRealCount();
        }
        return this.mPager.getAdapter().getCount();
    }

    private float pxFromDp(float f) {
        return f * this.getContext().getResources().getDisplayMetrics().density;
    }

    private void resetDrawable() {
        for (View view : this.mIndicators) {
            if (this.mPreviousSelectedIndicator != null && this.mPreviousSelectedIndicator.equals((Object)view)) {
                ((ImageView)view).setImageDrawable(this.mSelectedDrawable);
                continue;
            }
            ((ImageView)view).setImageDrawable(this.mUnselectedDrawable);
        }
    }

    private void setItemAsSelected(int n) {
        ImageView imageView;
        if (this.mPreviousSelectedIndicator != null) {
            this.mPreviousSelectedIndicator.setImageDrawable(this.mUnselectedDrawable);
            this.mPreviousSelectedIndicator.setPadding((int)this.mUnSelectedPadding_Left, (int)this.mUnSelectedPadding_Top, (int)this.mUnSelectedPadding_Right, (int)this.mUnSelectedPadding_Bottom);
        }
        if ((imageView = (ImageView)this.getChildAt(n + 1)) != null) {
            imageView.setImageDrawable(this.mSelectedDrawable);
            imageView.setPadding((int)this.mSelectedPadding_Left, (int)this.mSelectedPadding_Top, (int)this.mSelectedPadding_Right, (int)this.mSelectedPadding_Bottom);
            this.mPreviousSelectedIndicator = imageView;
        }
        this.mPreviousSelectedPosition = n;
    }

    public void destroySelf() {
        if (this.mPager == null || this.mPager.getAdapter() == null) {
            return;
        }
        SliderAdapter sliderAdapter = ((InfinitePagerAdapter)this.mPager.getAdapter()).getRealAdapter();
        if (sliderAdapter != null) {
            sliderAdapter.unregisterDataSetObserver(this.dataChangeObserver);
        }
        this.removeAllViews();
    }

    public IndicatorVisibility getIndicatorVisibility() {
        return this.mVisibility;
    }

    public int getSelectedIndicatorResId() {
        return this.mUserSetSelectedIndicatorResId;
    }

    public int getUnSelectedIndicatorResId() {
        return this.mUserSetUnSelectedIndicatorResId;
    }

    @Override
    public void onPageScrollStateChanged(int n) {
    }

    @Override
    public void onPageScrolled(int n, float f, int n2) {
    }

    @Override
    public void onPageSelected(int n) {
        if (this.mItemCount == 0) {
            return;
        }
        this.setItemAsSelected(n - 1);
    }

    public void redraw() {
        this.mItemCount = this.getShouldDrawCount();
        this.mPreviousSelectedIndicator = null;
        Iterator iterator = this.mIndicators.iterator();
        while (iterator.hasNext()) {
            this.removeView((View)iterator.next());
        }
        for (int i = 0; i < this.mItemCount; ++i) {
            ImageView imageView = new ImageView(this.mContext);
            imageView.setImageDrawable(this.mUnselectedDrawable);
            imageView.setPadding((int)this.mUnSelectedPadding_Left, (int)this.mUnSelectedPadding_Top, (int)this.mUnSelectedPadding_Right, (int)this.mUnSelectedPadding_Bottom);
            this.addView((View)imageView);
            this.mIndicators.add((Object)imageView);
        }
        this.setItemAsSelected(this.mPreviousSelectedPosition);
    }

    public void setDefaultIndicatorColor(int n, int n2) {
        if (this.mUserSetSelectedIndicatorResId == 0) {
            this.mSelectedGradientDrawable.setColor(n);
        }
        if (this.mUserSetUnSelectedIndicatorResId == 0) {
            this.mUnSelectedGradientDrawable.setColor(n2);
        }
        this.resetDrawable();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setDefaultIndicatorShape(Shape shape) {
        if (this.mUserSetSelectedIndicatorResId == 0) {
            if (shape == Shape.Oval) {
                this.mSelectedGradientDrawable.setShape(1);
            } else {
                this.mSelectedGradientDrawable.setShape(0);
            }
        }
        if (this.mUserSetUnSelectedIndicatorResId == 0) {
            if (shape == Shape.Oval) {
                this.mUnSelectedGradientDrawable.setShape(1);
            } else {
                this.mUnSelectedGradientDrawable.setShape(0);
            }
        }
        this.resetDrawable();
    }

    public void setDefaultIndicatorSize(float f, float f2, Unit unit) {
        this.setDefaultSelectedIndicatorSize(f, f2, unit);
        this.setDefaultUnselectedIndicatorSize(f, f2, unit);
    }

    public void setDefaultSelectedIndicatorSize(float f, float f2, Unit unit) {
        if (this.mUserSetSelectedIndicatorResId == 0) {
            float f3 = f;
            float f4 = f2;
            if (unit == Unit.DP) {
                f3 = this.pxFromDp(f);
                f4 = this.pxFromDp(f2);
            }
            this.mSelectedGradientDrawable.setSize((int)f3, (int)f4);
            this.resetDrawable();
        }
    }

    public void setDefaultUnselectedIndicatorSize(float f, float f2, Unit unit) {
        if (this.mUserSetUnSelectedIndicatorResId == 0) {
            float f3 = f;
            float f4 = f2;
            if (unit == Unit.DP) {
                f3 = this.pxFromDp(f);
                f4 = this.pxFromDp(f2);
            }
            this.mUnSelectedGradientDrawable.setSize((int)f3, (int)f4);
            this.resetDrawable();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setIndicatorStyleResource(int n, int n2) {
        this.mUserSetSelectedIndicatorResId = n;
        this.mUserSetUnSelectedIndicatorResId = n2;
        this.mSelectedDrawable = n == 0 ? this.mSelectedLayerDrawable : this.mContext.getResources().getDrawable(this.mUserSetSelectedIndicatorResId);
        this.mUnselectedDrawable = n2 == 0 ? this.mUnSelectedLayerDrawable : this.mContext.getResources().getDrawable(this.mUserSetUnSelectedIndicatorResId);
        this.resetDrawable();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setIndicatorVisibility(IndicatorVisibility indicatorVisibility) {
        if (indicatorVisibility == IndicatorVisibility.Visible) {
            this.setVisibility(0);
        } else {
            this.setVisibility(4);
        }
        this.resetDrawable();
    }

    public void setViewPager(ViewPagerEx viewPagerEx) {
        if (viewPagerEx.getAdapter() == null) {
            throw new IllegalStateException("Viewpager does not have adapter instance");
        }
        this.mPager = viewPagerEx;
        this.mPager.addOnPageChangeListener(this);
        ((InfinitePagerAdapter)this.mPager.getAdapter()).getRealAdapter().registerDataSetObserver(this.dataChangeObserver);
    }

    public static final class IndicatorVisibility
    extends Enum<IndicatorVisibility> {
        private static final /* synthetic */ IndicatorVisibility[] $VALUES;
        public static final /* enum */ IndicatorVisibility Invisible;
        public static final /* enum */ IndicatorVisibility Visible;

        static {
            Visible = new IndicatorVisibility();
            Invisible = new IndicatorVisibility();
            IndicatorVisibility[] arrindicatorVisibility = new IndicatorVisibility[]{Visible, Invisible};
            $VALUES = arrindicatorVisibility;
        }

        public static IndicatorVisibility valueOf(String string2) {
            return (IndicatorVisibility)Enum.valueOf(IndicatorVisibility.class, (String)string2);
        }

        public static IndicatorVisibility[] values() {
            return (IndicatorVisibility[])$VALUES.clone();
        }
    }

    public static final class Shape
    extends Enum<Shape> {
        private static final /* synthetic */ Shape[] $VALUES;
        public static final /* enum */ Shape Oval = new Shape();
        public static final /* enum */ Shape Rectangle = new Shape();

        static {
            Shape[] arrshape = new Shape[]{Oval, Rectangle};
            $VALUES = arrshape;
        }

        public static Shape valueOf(String string2) {
            return (Shape)Enum.valueOf(Shape.class, (String)string2);
        }

        public static Shape[] values() {
            return (Shape[])$VALUES.clone();
        }
    }

    public static final class Unit
    extends Enum<Unit> {
        private static final /* synthetic */ Unit[] $VALUES;
        public static final /* enum */ Unit DP = new Unit();
        public static final /* enum */ Unit Px = new Unit();

        static {
            Unit[] arrunit = new Unit[]{DP, Px};
            $VALUES = arrunit;
        }

        public static Unit valueOf(String string2) {
            return (Unit)Enum.valueOf(Unit.class, (String)string2);
        }

        public static Unit[] values() {
            return (Unit[])$VALUES.clone();
        }
    }

}

