/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.DataSetObserver
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ListAdapter
 *  android.widget.SpinnerAdapter
 *  java.lang.Object
 */
package com.zopim.android.sdk.prechat;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

class HintSpinnerAdapter
implements SpinnerAdapter,
ListAdapter {
    protected static final int EXTRA = 1;
    protected SpinnerAdapter adapter;
    protected Context context;
    protected int hintLayout;
    protected int itemDropdownLayout;
    protected LayoutInflater layoutInflater;

    public HintSpinnerAdapter(SpinnerAdapter spinnerAdapter, int n, int n2, Context context) {
        this.adapter = spinnerAdapter;
        this.context = context;
        this.hintLayout = n;
        this.itemDropdownLayout = n2;
        this.layoutInflater = LayoutInflater.from((Context)context);
    }

    public HintSpinnerAdapter(SpinnerAdapter spinnerAdapter, int n, Context context) {
        this(spinnerAdapter, n, -1, context);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public int getCount() {
        int n = this.adapter.getCount();
        if (n == 0) {
            return 0;
        }
        return n + 1;
    }

    public View getDropDownView(int n, View view, ViewGroup viewGroup) {
        if (n == 0) {
            if (this.itemDropdownLayout == -1) {
                return new View(this.context);
            }
            return this.getItemDropdownView(viewGroup);
        }
        return this.adapter.getDropDownView(n - 1, null, viewGroup);
    }

    protected View getHintView(ViewGroup viewGroup) {
        return this.layoutInflater.inflate(this.hintLayout, viewGroup, false);
    }

    public Object getItem(int n) {
        if (n == 0) {
            return null;
        }
        return this.adapter.getItem(n - 1);
    }

    public View getItemDropdownView(ViewGroup viewGroup) {
        return this.layoutInflater.inflate(this.itemDropdownLayout, viewGroup, false);
    }

    public long getItemId(int n) {
        if (n >= 1) {
            return this.adapter.getItemId(n - 1);
        }
        return n - 1;
    }

    public int getItemViewType(int n) {
        return 0;
    }

    public final View getView(int n, View view, ViewGroup viewGroup) {
        if (n == 0) {
            return this.getHintView(viewGroup);
        }
        return this.adapter.getView(n - 1, null, viewGroup);
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean hasStableIds() {
        return this.adapter.hasStableIds();
    }

    public boolean isEmpty() {
        return this.adapter.isEmpty();
    }

    public boolean isEnabled(int n) {
        return n != 0;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.adapter.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.adapter.unregisterDataSetObserver(dataSetObserver);
    }
}

