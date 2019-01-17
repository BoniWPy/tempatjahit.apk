/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  java.lang.Object
 */
package com.whl.handytabbar;

import android.content.Context;
import android.content.res.Resources;

public final class TabBarStyle {
    public static final int BOTHLINE = 2;
    public static final int INDICATOR_LINE = 0;
    public static final int INDICATOR_NONE = 1;
    public static final int INDICATOR_TRIANGLE = 2;
    public static final int NONELINE = 3;
    public static final int TOPLINE = 1;
    public static final int UNDERLINE;
    public int dividerColor;
    public int dividerPadding;
    public int dividerWidth;
    public boolean drawDivider;
    public int drawIndicator;
    public int drawLine;
    public final int indicatorColor;
    public int indicatorHeight;
    public int lineColor;
    public int lineHeight;
    public int scrollOffset;

    private TabBarStyle(Builder builder) {
        this.indicatorColor = builder.indicatorColor;
        this.lineColor = builder.lineColor;
        this.dividerColor = builder.dividerColor;
        this.indicatorHeight = builder.indicatorHeight;
        this.lineHeight = builder.lineHeight;
        this.dividerPadding = builder.dividerPadding;
        this.dividerWidth = builder.dividerWidth;
        this.scrollOffset = builder.scrollOffset;
        this.drawIndicator = builder.drawIndicator;
        this.drawDivider = builder.drawDivider;
        this.drawLine = builder.drawLine;
    }

    public static class Builder {
        private int dividerColor = -1;
        private int dividerPadding = 12;
        private int dividerWidth = 1;
        private boolean drawDivider = false;
        private int drawIndicator;
        private int drawLine = 0;
        private int indicatorColor = -1;
        private int indicatorHeight = 12;
        private int lineColor = -1;
        private int lineHeight = 2;
        private Context mContext;
        private int scrollOffset = 52;

        public Builder(Context context) {
            this.mContext = context;
        }

        public TabBarStyle build() {
            return new TabBarStyle(this);
        }

        public Builder setDividerColor(int n) {
            this.dividerColor = n;
            return this;
        }

        public Builder setDividerColorResource(int n) {
            this.dividerColor = this.mContext.getResources().getColor(n);
            return this;
        }

        public Builder setDividerPadding(int n) {
            this.dividerPadding = n;
            return this;
        }

        public Builder setDividerWidth(int n) {
            this.dividerWidth = n;
            return this;
        }

        public Builder setDrawDivider(boolean bl) {
            this.drawDivider = bl;
            return this;
        }

        public Builder setDrawIndicator(int n) {
            this.drawIndicator = n;
            return this;
        }

        public Builder setDrawLine(int n) {
            this.drawLine = n;
            return this;
        }

        public Builder setIndicatorColor(int n) {
            this.indicatorColor = n;
            return this;
        }

        public Builder setIndicatorColorResource(int n) {
            this.indicatorColor = this.mContext.getResources().getColor(n);
            return this;
        }

        public Builder setIndicatorHeight(int n) {
            this.indicatorHeight = n;
            return this;
        }

        public Builder setScrollOffset(int n) {
            this.scrollOffset = n;
            return this;
        }

        public Builder setlineColor(int n) {
            this.lineColor = n;
            return this;
        }

        public Builder setlineColorResource(int n) {
            this.lineColor = this.mContext.getResources().getColor(n);
            return this;
        }

        public Builder setlineHeight(int n) {
            this.lineHeight = n;
            return this;
        }
    }

}

