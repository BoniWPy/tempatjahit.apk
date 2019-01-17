/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.LayoutInflater
 *  android.widget.BaseAdapter
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.darsh.multipleimageselect.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public abstract class CustomGenericAdapter<T>
extends BaseAdapter {
    protected ArrayList<T> arrayList;
    protected Context context;
    protected LayoutInflater layoutInflater;
    protected int size;

    public CustomGenericAdapter(Context context, ArrayList<T> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from((Context)this.context);
    }

    public int getCount() {
        return this.arrayList.size();
    }

    public T getItem(int n) {
        return (T)this.arrayList.get(n);
    }

    public long getItemId(int n) {
        return n;
    }

    public void releaseResources() {
        this.arrayList = null;
        this.context = null;
    }

    public void setLayoutParams(int n) {
        this.size = n;
    }
}

