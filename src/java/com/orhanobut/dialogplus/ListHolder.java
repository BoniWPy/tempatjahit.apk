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
 *  android.widget.ListAdapter
 *  android.widget.ListView
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
import android.widget.ListAdapter;
import android.widget.ListView;
import com.orhanobut.dialogplus.HolderAdapter;
import com.orhanobut.dialogplus.OnHolderListener;
import com.orhanobut.dialogplus.R;

public class ListHolder
implements HolderAdapter,
AdapterView.OnItemClickListener {
    private int backgroundColor;
    private View footerView;
    private View headerView;
    private View.OnKeyListener keyListener;
    private ListView listView;
    private OnHolderListener listener;

    @Override
    public void addFooter(View view) {
        if (view == null) {
            return;
        }
        this.listView.addFooterView(view);
        this.footerView = view;
    }

    @Override
    public void addHeader(View view) {
        if (view == null) {
            return;
        }
        this.listView.addHeaderView(view);
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
        return this.listView;
    }

    @Override
    public View getView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_list, viewGroup, false);
        this.listView = (ListView)view.findViewById(R.id.list);
        this.listView.setBackgroundColor(viewGroup.getResources().getColor(this.backgroundColor));
        this.listView.setOnItemClickListener((AdapterView.OnItemClickListener)this);
        this.listView.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (ListHolder.this.keyListener == null) {
                    throw new NullPointerException("keyListener should not be null");
                }
                return ListHolder.this.keyListener.onKey(view, n, keyEvent);
            }
        });
        return view;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
        this.listener.onItemClick(adapterView.getItemAtPosition(n), view, n);
    }

    @Override
    public void setAdapter(BaseAdapter baseAdapter) {
        this.listView.setAdapter((ListAdapter)baseAdapter);
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

