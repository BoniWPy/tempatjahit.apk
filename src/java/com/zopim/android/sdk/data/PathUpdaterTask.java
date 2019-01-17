/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Void
 */
package com.zopim.android.sdk.data;

import android.os.AsyncTask;
import com.zopim.android.sdk.data.PathName;
import com.zopim.android.sdk.data.PathUpdater;

class PathUpdaterTask
extends AsyncTask<String, Void, PathName> {
    private final PathUpdater mPathUpdater = new PathUpdater();

    PathUpdaterTask() {
    }

    protected /* varargs */ PathName doInBackground(String ... arrstring) {
        return this.mPathUpdater.updatePath(arrstring[0]);
    }
}

