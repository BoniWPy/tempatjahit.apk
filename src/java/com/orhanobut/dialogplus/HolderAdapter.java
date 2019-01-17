/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.widget.BaseAdapter
 *  java.lang.Object
 */
package com.orhanobut.dialogplus;

import android.widget.BaseAdapter;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnHolderListener;

public interface HolderAdapter
extends Holder {
    public void setAdapter(BaseAdapter var1);

    public void setOnItemClickListener(OnHolderListener var1);
}

