/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.Context
 */
package com.orm;

import android.app.Application;
import android.content.Context;
import com.orm.SugarContext;

public class SugarApp
extends Application {
    public void onCreate() {
        super.onCreate();
        SugarContext.init((Context)this);
    }

    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}

