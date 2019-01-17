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
 *  android.view.ViewParent
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
import android.view.ViewParent;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.R;

public class ViewHolder
implements Holder {
    private static final int INVALID = -1;
    private int backgroundColor;
    private View contentView;
    private ViewGroup footerContainer;
    private View footerView;
    private ViewGroup headerContainer;
    private View headerView;
    private View.OnKeyListener keyListener;
    private int viewResourceId = -1;

    public ViewHolder(int n) {
        this.viewResourceId = n;
    }

    public ViewHolder(View view) {
        this.contentView = view;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void addContent(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewGroup viewGroup2) {
        if (this.viewResourceId != -1) {
            this.contentView = layoutInflater.inflate(this.viewResourceId, viewGroup, false);
        } else {
            ViewGroup viewGroup3 = (ViewGroup)this.contentView.getParent();
            if (viewGroup3 != null) {
                viewGroup3.removeView(this.contentView);
            }
        }
        viewGroup2.addView(this.contentView);
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
        return this.contentView;
    }

    @Override
    public View getView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.dialog_view, viewGroup, false);
        ViewGroup viewGroup2 = (ViewGroup)view.findViewById(R.id.view_container);
        viewGroup2.setBackgroundColor(viewGroup.getResources().getColor(this.backgroundColor));
        viewGroup2.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (ViewHolder.this.keyListener == null) {
                    throw new NullPointerException("keyListener should not be null");
                }
                return ViewHolder.this.keyListener.onKey(view, n, keyEvent);
            }
        });
        this.addContent(layoutInflater, viewGroup, viewGroup2);
        this.headerContainer = (ViewGroup)view.findViewById(R.id.header_container);
        this.footerContainer = (ViewGroup)view.findViewById(R.id.footer_container);
        return view;
    }

    @Override
    public void setBackgroundColor(int n) {
        this.backgroundColor = n;
    }

    @Override
    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        this.keyListener = onKeyListener;
    }

}

