/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  com.whl.handytabbar.tablayout.BaseTabLayout
 *  java.lang.CharSequence
 */
package com.whl.handytabbar.tablayout;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.whl.handytabbar.tablayout.BaseTabLayout;

public class DefaultTabLayout
extends BaseTabLayout {
    public View createView(LayoutInflater layoutInflater, int n, ViewGroup viewGroup, CharSequence charSequence) {
        TextView textView = new TextView(this.getContext());
        textView.setGravity(17);
        textView.setText(charSequence);
        textView.setTextColor(this.getContext().getResources().getColor(17170443));
        textView.setSingleLine();
        textView.setMinWidth(160);
        return textView;
    }
}

