/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  android.view.ViewGroup
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.BaseAdapter
 *  android.widget.GridView
 *  android.widget.ListAdapter
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.String
 */
package com.orhanobut.dialogplus;

import android.content.res.Resources;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.orhanobut.dialogplus.HolderAdapter;
import com.orhanobut.dialogplus.OnHolderListener;
import com.orhanobut.dialogplus.R;

public class GridHolder
implements HolderAdapter,
AdapterView.OnItemClickListener {
    private int backgroundColor;
    private final int columnNumber;
    private ViewGroup footerContainer;
    private View footerView;
    private GridView gridView;
    private ViewGroup headerContainer;
    private View headerView;
    private View.OnKeyListener keyListener;
    private OnHolderListener listener;

    public GridHolder(int n) {
        this.columnNumber = n;
    }

    @Override
    public void addFooter(View view) {
        if (view == null) {
            return;
        }
        this.footerContainer.addView(view);
        this.footerView = view;
    }

    @Override
    public void addHeader(View view) {
        if (view == null) {
            return;
        }
        this.headerContainer.addView(view);
        this.headerView = view;
    }

    @Override
    public View getFooter() {
        return this.footerView;
    }

    @Override
    public View getHeader() {
        return this.headerView;
    }

    @Override
    public View getInflatedView() {
        return this.gridView;
    }

    @Override
    public View getView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_grid, viewGroup, false);
        this.gridView = (GridView)view.findViewById(R.id.list);
        this.gridView.setBackgroundColor(viewGroup.getResources().getColor(this.backgroundColor));
        this.gridView.setNumColumns(this.columnNumber);
        this.gridView.setOnItemClickListener((AdapterView.OnItemClickListener)this);
        this.gridView.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (GridHolder.this.keyListener == null) {
                    throw new NullPointerException("keyListener should not be null");
                }
                return GridHolder.this.keyListener.onKey(view, n, keyEvent);
            }
        });
        this.headerContainer = (ViewGroup)view.findViewById(R.id.header_container);
        this.footerContainer = (ViewGroup)view.findViewById(R.id.footer_container);
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
        this.listener.onItemClick(adapterView.getItemAtPosition(n), view, n);
    }

    @Override
    public void setAdapter(BaseAdapter baseAdapter) {
        this.gridView.setAdapter((ListAdapter)baseAdapter);
    }

    @Override
    public void setBackgroundColor(int n) {
        this.backgroundColor = n;
    }

    @Override
    public void setOnItemClickListener(OnHolderListener onHolderListener) {
        this.listener = onHolderListener;
    }

    @Override
    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        this.keyListener = onKeyListener;
    }

}

