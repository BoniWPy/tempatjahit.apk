/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.Canvas
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewParent
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.widget.ScrollView
 *  java.lang.CharSequence
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.emilsjolander.components.StickyScrollViewItems;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ScrollView;
import com.emilsjolander.components.StickyScrollViewItems.R;
import java.util.ArrayList;

public class StickyScrollView
extends ScrollView {
    private static final int DEFAULT_SHADOW_HEIGHT = 10;
    public static final String FLAG_HASTRANSPARANCY = "-hastransparancy";
    public static final String FLAG_NONCONSTANT = "-nonconstant";
    public static final String STICKY_TAG = "sticky";
    private boolean clipToPaddingHasBeenSet;
    private boolean clippingToPadding;
    private View currentlyStickingView;
    private boolean hasNotDoneActionDown = true;
    private final Runnable invalidateRunnable = new Runnable(){

        public void run() {
            if (StickyScrollView.this.currentlyStickingView != null) {
                int n = StickyScrollView.this.getLeftForViewRelativeOnlyChild(StickyScrollView.this.currentlyStickingView);
                int n2 = StickyScrollView.this.getBottomForViewRelativeOnlyChild(StickyScrollView.this.currentlyStickingView);
                int n3 = StickyScrollView.this.getRightForViewRelativeOnlyChild(StickyScrollView.this.currentlyStickingView);
                int n4 = (int)((float)StickyScrollView.this.getScrollY() + ((float)StickyScrollView.this.currentlyStickingView.getHeight() + StickyScrollView.this.stickyViewTopOffset));
                StickyScrollView.this.invalidate(n, n2, n3, n4);
            }
            StickyScrollView.this.postDelayed((Runnable)this, 16L);
        }
    };
    private Drawable mShadowDrawable;
    private int mShadowHeight;
    private boolean redirectTouchesToStickyView;
    private int stickyViewLeftOffset;
    private float stickyViewTopOffset;
    private ArrayList<View> stickyViews;

    public StickyScrollView(Context context) {
        this(context, null);
    }

    public StickyScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842880);
    }

    public StickyScrollView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.setup();
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.StickyScrollView, n, 0);
        this.mShadowHeight = typedArray.getDimensionPixelSize(0, (int)(0.5f + 10.0f * context.getResources().getDisplayMetrics().density));
        int n2 = typedArray.getResourceId(1, -1);
        if (n2 != -1) {
            this.mShadowDrawable = context.getResources().getDrawable(n2);
        }
        typedArray.recycle();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void doTheStickyThing() {
        View view = null;
        View view2 = null;
        for (View view3 : this.stickyViews) {
            int n;
            int n2;
            int n3;
            int n4 = this.getTopForViewRelativeOnlyChild(view3) - this.getScrollY();
            int n5 = n4 + (n2 = this.clippingToPadding ? 0 : this.getPaddingTop());
            if (n5 <= 0) {
                int n6;
                int n7;
                if (view != null && n5 <= (n6 = this.clippingToPadding ? 0 : this.getPaddingTop()) + (n7 = this.getTopForViewRelativeOnlyChild(view) - this.getScrollY())) continue;
                view = view3;
                continue;
            }
            if (view2 != null && n5 >= (n = this.clippingToPadding ? 0 : this.getPaddingTop()) + (n3 = this.getTopForViewRelativeOnlyChild(view2) - this.getScrollY())) continue;
            view2 = view3;
        }
        if (view != null) {
            float f;
            if (view2 == null) {
                f = 0.0f;
            } else {
                int n = this.getTopForViewRelativeOnlyChild(view2) - this.getScrollY();
                int n8 = this.clippingToPadding ? 0 : this.getPaddingTop();
                f = Math.min((int)0, (int)(n8 + n - view.getHeight()));
            }
            this.stickyViewTopOffset = f;
            if (view == this.currentlyStickingView) return;
            {
                if (this.currentlyStickingView != null) {
                    this.stopStickingCurrentlyStickingView();
                }
                this.stickyViewLeftOffset = this.getLeftForViewRelativeOnlyChild(view);
                this.startStickingView(view);
                return;
            }
        } else {
            if (this.currentlyStickingView == null) return;
            {
                this.stopStickingCurrentlyStickingView();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void findStickyViews(View view) {
        if (!(view instanceof ViewGroup)) {
            String string2 = (String)view.getTag();
            if (string2 == null || !string2.contains((CharSequence)STICKY_TAG)) return;
            {
                this.stickyViews.add((Object)view);
            }
            return;
        } else {
            ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                String string3 = this.getStringTagForView(viewGroup.getChildAt(i));
                if (string3 != null && string3.contains((CharSequence)STICKY_TAG)) {
                    this.stickyViews.add((Object)viewGroup.getChildAt(i));
                    continue;
                }
                if (!(viewGroup.getChildAt(i) instanceof ViewGroup)) continue;
                this.findStickyViews(viewGroup.getChildAt(i));
            }
        }
    }

    private int getBottomForViewRelativeOnlyChild(View view) {
        int n = view.getBottom();
        while (view.getParent() != this.getChildAt(0)) {
            view = (View)view.getParent();
            n += view.getBottom();
        }
        return n;
    }

    private int getLeftForViewRelativeOnlyChild(View view) {
        int n = view.getLeft();
        while (view.getParent() != this.getChildAt(0)) {
            view = (View)view.getParent();
            n += view.getLeft();
        }
        return n;
    }

    private int getRightForViewRelativeOnlyChild(View view) {
        int n = view.getRight();
        while (view.getParent() != this.getChildAt(0)) {
            view = (View)view.getParent();
            n += view.getRight();
        }
        return n;
    }

    private String getStringTagForView(View view) {
        return String.valueOf((Object)view.getTag());
    }

    private int getTopForViewRelativeOnlyChild(View view) {
        int n = view.getTop();
        while (view.getParent() != this.getChildAt(0)) {
            view = (View)view.getParent();
            n += view.getTop();
        }
        return n;
    }

    private void hideView(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setAlpha(0.0f);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation((Animation)alphaAnimation);
    }

    private void notifyHierarchyChanged() {
        if (this.currentlyStickingView != null) {
            this.stopStickingCurrentlyStickingView();
        }
        this.stickyViews.clear();
        this.findStickyViews(this.getChildAt(0));
        this.doTheStickyThing();
        this.invalidate();
    }

    private void showView(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setAlpha(1.0f);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        view.startAnimation((Animation)alphaAnimation);
    }

    private void startStickingView(View view) {
        this.currentlyStickingView = view;
        if (this.getStringTagForView(this.currentlyStickingView).contains((CharSequence)FLAG_HASTRANSPARANCY)) {
            this.hideView(this.currentlyStickingView);
        }
        if (((String)this.currentlyStickingView.getTag()).contains((CharSequence)FLAG_NONCONSTANT)) {
            this.post(this.invalidateRunnable);
        }
    }

    private void stopStickingCurrentlyStickingView() {
        if (this.getStringTagForView(this.currentlyStickingView).contains((CharSequence)FLAG_HASTRANSPARANCY)) {
            this.showView(this.currentlyStickingView);
        }
        this.currentlyStickingView = null;
        this.removeCallbacks(this.invalidateRunnable);
    }

    public void addView(View view) {
        super.addView(view);
        this.findStickyViews(view);
    }

    public void addView(View view, int n) {
        super.addView(view, n);
        this.findStickyViews(view);
    }

    public void addView(View view, int n, int n2) {
        super.addView(view, n, n2);
        this.findStickyViews(view);
    }

    public void addView(View view, int n, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, n, layoutParams);
        this.findStickyViews(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, layoutParams);
        this.findStickyViews(view);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.currentlyStickingView != null) {
            canvas.save();
            float f = this.getPaddingLeft() + this.stickyViewLeftOffset;
            float f2 = (float)this.getScrollY() + this.stickyViewTopOffset;
            int n = this.clippingToPadding ? this.getPaddingTop() : 0;
            canvas.translate(f, f2 + (float)n);
            float f3 = this.clippingToPadding ? -this.stickyViewTopOffset : 0.0f;
            canvas.clipRect(0.0f, f3, (float)(this.getWidth() - this.stickyViewLeftOffset), (float)(1 + (this.currentlyStickingView.getHeight() + this.mShadowHeight)));
            if (this.mShadowDrawable != null) {
                int n2 = this.currentlyStickingView.getWidth();
                int n3 = this.currentlyStickingView.getHeight();
                int n4 = this.currentlyStickingView.getHeight() + this.mShadowHeight;
                this.mShadowDrawable.setBounds(0, n3, n2, n4);
                this.mShadowDrawable.draw(canvas);
            }
            float f4 = this.clippingToPadding ? -this.stickyViewTopOffset : 0.0f;
            canvas.clipRect(0.0f, f4, (float)this.getWidth(), (float)this.currentlyStickingView.getHeight());
            if (this.getStringTagForView(this.currentlyStickingView).contains((CharSequence)FLAG_HASTRANSPARANCY)) {
                this.showView(this.currentlyStickingView);
                this.currentlyStickingView.draw(canvas);
                this.hideView(this.currentlyStickingView);
            } else {
                this.currentlyStickingView.draw(canvas);
            }
            canvas.restore();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean bl = true;
        if (motionEvent.getAction() == 0) {
            this.redirectTouchesToStickyView = bl;
        }
        if (this.redirectTouchesToStickyView) {
            boolean bl2 = this.currentlyStickingView != null ? bl : false;
            this.redirectTouchesToStickyView = bl2;
            if (this.redirectTouchesToStickyView) {
                if (motionEvent.getY() > (float)this.currentlyStickingView.getHeight() + this.stickyViewTopOffset || motionEvent.getX() < (float)this.getLeftForViewRelativeOnlyChild(this.currentlyStickingView) || motionEvent.getX() > (float)this.getRightForViewRelativeOnlyChild(this.currentlyStickingView)) {
                    bl = false;
                }
                this.redirectTouchesToStickyView = bl;
            }
        } else if (this.currentlyStickingView == null) {
            this.redirectTouchesToStickyView = false;
        }
        if (this.redirectTouchesToStickyView) {
            motionEvent.offsetLocation(0.0f, -1.0f * ((float)this.getScrollY() + this.stickyViewTopOffset - (float)this.getTopForViewRelativeOnlyChild(this.currentlyStickingView)));
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void notifyStickyAttributeChanged() {
        this.notifyHierarchyChanged();
    }

    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        super.onLayout(bl, n, n2, n3, n4);
        if (!this.clipToPaddingHasBeenSet) {
            this.clippingToPadding = true;
        }
        this.notifyHierarchyChanged();
    }

    protected void onScrollChanged(int n, int n2, int n3, int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        this.doTheStickyThing();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.redirectTouchesToStickyView) {
            motionEvent.offsetLocation(0.0f, (float)this.getScrollY() + this.stickyViewTopOffset - (float)this.getTopForViewRelativeOnlyChild(this.currentlyStickingView));
        }
        if (motionEvent.getAction() == 0) {
            this.hasNotDoneActionDown = false;
        }
        if (this.hasNotDoneActionDown) {
            MotionEvent motionEvent2 = MotionEvent.obtain((MotionEvent)motionEvent);
            motionEvent2.setAction(0);
            super.onTouchEvent(motionEvent2);
            this.hasNotDoneActionDown = false;
        }
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.hasNotDoneActionDown = true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setClipToPadding(boolean bl) {
        super.setClipToPadding(bl);
        this.clippingToPadding = bl;
        this.clipToPaddingHasBeenSet = true;
    }

    public void setShadowHeight(int n) {
        this.mShadowHeight = n;
    }

    public void setup() {
        this.stickyViews = new ArrayList();
    }

}

