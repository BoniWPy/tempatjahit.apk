/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.HorizontalScrollView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  com.whl.handytabbar.tablayout.BaseTabLayout
 *  java.lang.CharSequence
 *  java.lang.IllegalStateException
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.String
 */
package com.whl.handytabbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.whl.handytabbar.TabBarStyle;
import com.whl.handytabbar.tablayout.BaseTabLayout;
import com.whl.handytabbar.tablayout.DefaultTabLayout;

public class HandyTabBar
extends HorizontalScrollView {
    private int currentPosition = 0;
    private float currentPositionOffset = 0.0f;
    public ViewPager.OnPageChangeListener delegatePageListener;
    private Paint dividerPaint;
    private int lastScrollX = 0;
    private int mSelectedPosition;
    private TabBarStyle mTabBarStyle;
    private BaseTabLayout mTabLayout;
    private TabsContainer mTabsContainer;
    private ViewPager mViewPager;
    private final PageListener pageListener = new PageListener();
    private Paint rectPaint;
    private int tabCount;

    public HandyTabBar(Context context) {
        this(context, null);
    }

    public HandyTabBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HandyTabBar(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.setFillViewport(true);
        this.setWillNotDraw(false);
        this.init(context);
    }

    private void init(Context context) {
        this.mTabsContainer = new TabsContainer(context);
        this.addView((View)this.mTabsContainer.getContentView());
        this.rectPaint = new Paint();
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.dividerPaint = new Paint();
        this.dividerPaint.setAntiAlias(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void scrollToChild(int n, int n2) {
        int n3;
        block5 : {
            block4 : {
                if (this.tabCount == 0) break block4;
                n3 = n2 + this.mTabsContainer.getChildAt(n).getLeft();
                if (n > 0 || n2 > 0) {
                    n3 -= this.mTabBarStyle.scrollOffset;
                }
                if (n3 != this.lastScrollX) break block5;
            }
            return;
        }
        this.lastScrollX = n3;
        this.scrollTo(n3, 0);
    }

    private void setActiveTab(int n) {
        for (int i = 0; i < this.tabCount; ++i) {
            View view = this.mTabsContainer.getChildAt(i);
            boolean bl = false;
            if (n == i) {
                bl = true;
            }
            this.mTabLayout.onTabState(view, bl, n);
        }
    }

    private void setupTabs() {
        this.tabCount = this.mViewPager.getAdapter().getCount();
        for (int i = 0; i < this.tabCount; ++i) {
            final int n = i;
            this.mTabsContainer.addTab(i, this.mViewPager.getAdapter().getPageTitle(i), this.mTabLayout).setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    HandyTabBar.this.mViewPager.setCurrentItem(n);
                }
            });
        }
    }

    public void attachToViewPager(ViewPager viewPager) {
        this.attachToViewPager(viewPager, null, null);
    }

    public void attachToViewPager(ViewPager viewPager, TabBarStyle tabBarStyle) {
        this.attachToViewPager(viewPager, null, tabBarStyle);
    }

    public void attachToViewPager(ViewPager viewPager, BaseTabLayout baseTabLayout) {
        this.attachToViewPager(viewPager, baseTabLayout, null);
    }

    public void attachToViewPager(ViewPager viewPager, BaseTabLayout baseTabLayout, TabBarStyle tabBarStyle) {
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager must be set a adapter at first.");
        }
        this.mViewPager = viewPager;
        viewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener)this.pageListener);
        this.mTabLayout = baseTabLayout;
        if (this.mTabLayout == null) {
            this.mTabLayout = new DefaultTabLayout();
        }
        this.mTabBarStyle = tabBarStyle;
        if (this.mTabBarStyle == null) {
            this.mTabBarStyle = new TabBarStyle.Builder(this.getContext()).build();
        }
        this.setupTabs();
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            @SuppressLint(value={"NewApi"})
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    HandyTabBar.this.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
                } else {
                    HandyTabBar.this.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)this);
                }
                HandyTabBar.this.currentPosition = HandyTabBar.this.mViewPager.getCurrentItem();
                HandyTabBar.this.scrollToChild(HandyTabBar.this.currentPosition, 0);
                HandyTabBar.this.setActiveTab(HandyTabBar.this.mSelectedPosition);
            }
        });
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onDraw(Canvas var1_1) {
        block13 : {
            super.onDraw(var1_1);
            if (this.isInEditMode() != false) return;
            if (this.tabCount == 0) {
                return;
            }
            var2_2 = this.getHeight();
            if (this.mTabBarStyle.drawIndicator != 1) {
                this.rectPaint.setColor(this.mTabBarStyle.indicatorColor);
                var5_3 = this.mTabsContainer.getChildAt(this.currentPosition);
                var6_4 = var5_3.getLeft();
                var7_5 = var5_3.getRight();
                if (this.currentPositionOffset > 0.0f && this.currentPosition < -1 + this.tabCount) {
                    var10_6 = this.mTabsContainer.getChildAt(1 + this.currentPosition);
                    var11_7 = var10_6.getLeft();
                    var12_8 = var10_6.getRight();
                    var6_4 = var11_7 * this.currentPositionOffset + var6_4 * (1.0f - this.currentPositionOffset);
                    var7_5 = var12_8 * this.currentPositionOffset + var7_5 * (1.0f - this.currentPositionOffset);
                }
                switch (this.mTabBarStyle.drawIndicator) {
                    case 0: {
                        var1_1.drawRect(var6_4, (float)(var2_2 - this.mTabBarStyle.indicatorHeight), var7_5, (float)var2_2, this.rectPaint);
                    }
                    default: {
                        break;
                    }
                    case 2: {
                        var8_9 = new Path();
                        var9_10 = var7_5 + var6_4;
                        var8_9.moveTo(var9_10 / 2.0f - (float)this.mTabBarStyle.indicatorHeight, (float)var2_2);
                        var8_9.lineTo(var9_10 / 2.0f + (float)this.mTabBarStyle.indicatorHeight, (float)var2_2);
                        var8_9.lineTo(var9_10 / 2.0f, (float)(var2_2 - this.mTabBarStyle.indicatorHeight));
                        var8_9.close();
                        var1_1.drawPath(var8_9, this.rectPaint);
                    }
                }
            }
            switch (this.mTabBarStyle.drawLine) {
                case 0: {
                    this.rectPaint.setColor(this.mTabBarStyle.lineColor);
                    var1_1.drawRect(0.0f, (float)(var2_2 - this.mTabBarStyle.lineHeight), (float)this.mTabsContainer.getWidth(), (float)var2_2, this.rectPaint);
                    ** break;
                }
                case 1: {
                    this.rectPaint.setColor(this.mTabBarStyle.lineColor);
                    var1_1.drawRect(0.0f, 0.0f, (float)this.mTabsContainer.getWidth(), (float)this.mTabBarStyle.lineHeight, this.rectPaint);
                }
lbl38: // 3 sources:
                default: {
                    break block13;
                }
                case 2: 
            }
            this.rectPaint.setColor(this.mTabBarStyle.lineColor);
            var1_1.drawRect(0.0f, (float)(var2_2 - this.mTabBarStyle.lineHeight), (float)this.mTabsContainer.getWidth(), (float)var2_2, this.rectPaint);
            var1_1.drawRect(0.0f, 0.0f, (float)this.mTabsContainer.getWidth(), (float)this.mTabBarStyle.lineHeight, this.rectPaint);
        }
        if (this.mTabBarStyle.drawDivider == false) return;
        this.dividerPaint.setColor(this.mTabBarStyle.dividerColor);
        this.dividerPaint.setStrokeWidth((float)this.mTabBarStyle.dividerWidth);
        var3_11 = 0;
        while (var3_11 < -1 + this.tabCount) {
            var4_12 = this.mTabsContainer.getChildAt(var3_11);
            var1_1.drawLine((float)var4_12.getRight(), (float)this.mTabBarStyle.dividerPadding, (float)var4_12.getRight(), (float)(var2_2 - this.mTabBarStyle.dividerPadding), this.dividerPaint);
            ++var3_11;
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.delegatePageListener = onPageChangeListener;
    }

    private class PageListener
    implements ViewPager.OnPageChangeListener {
        private PageListener() {
        }

        public void onPageScrollStateChanged(int n) {
            if (n == 0) {
                HandyTabBar.this.scrollToChild(HandyTabBar.this.mViewPager.getCurrentItem(), 0);
            }
            if (HandyTabBar.this.delegatePageListener != null) {
                HandyTabBar.this.delegatePageListener.onPageScrollStateChanged(n);
            }
        }

        public void onPageScrolled(int n, float f, int n2) {
            HandyTabBar.this.currentPosition = n;
            HandyTabBar.this.currentPositionOffset = f;
            HandyTabBar.this.scrollToChild(n, (int)(f * (float)HandyTabBar.this.mTabsContainer.getChildAt(n).getWidth()));
            HandyTabBar.this.invalidate();
            if (HandyTabBar.this.delegatePageListener != null) {
                HandyTabBar.this.delegatePageListener.onPageScrolled(n, f, n2);
            }
        }

        public void onPageSelected(int n) {
            if (HandyTabBar.this.mSelectedPosition != n) {
                HandyTabBar.this.setActiveTab(n);
            }
            HandyTabBar.this.mSelectedPosition = n;
            if (HandyTabBar.this.delegatePageListener != null) {
                HandyTabBar.this.delegatePageListener.onPageSelected(n);
            }
        }
    }

    private class TabsContainer {
        private Context context;
        private LinearLayout mContentView;

        public TabsContainer(Context context) {
            this.context = context;
            this.mContentView = new LinearLayout(context);
            this.mContentView.setOrientation(0);
            this.mContentView.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        }

        public View addTab(int n, CharSequence charSequence, BaseTabLayout baseTabLayout) {
            baseTabLayout.init(this.context, n, charSequence, (ViewGroup)this.mContentView);
            View view = baseTabLayout.getView();
            if (view == null) {
                throw new NullPointerException("the BaseTabLayout getView() must not be null.");
            }
            this.mContentView.addView(view, n, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(0, -1, 1.0f));
            return view;
        }

        public View getChildAt(int n) {
            return this.mContentView.getChildAt(n);
        }

        public LinearLayout getContentView() {
            return this.mContentView;
        }

        public int getWidth() {
            return this.mContentView.getWidth();
        }
    }

}

