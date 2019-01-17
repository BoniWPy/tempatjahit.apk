/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  android.view.ViewGroup
 *  java.lang.Object
 */
package com.orhanobut.dialogplus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface Holder {
    public void addFooter(View var1);

    public void addHeader(View var1);

    public View getFooter();

    public View getHeader();

    public View getInflatedView();

    public View getView(LayoutInflater var1, ViewGroup var2);

    public void setBackgroundColor(int var1);

    public void setOnKeyListener(View.OnKeyListener var1);
}

