/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.zendesk.logger.Logger
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map
 *  java.util.Observable
 *  java.util.Observer
 */
package com.zopim.android.sdk.data.observers;

import com.zendesk.logger.Logger;
import com.zopim.android.sdk.data.LivechatDepartmentsPath;
import com.zopim.android.sdk.model.Department;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public abstract class DepartmentsObserver
implements Observer {
    private static final String LOG_TAG = "DepartmentsObserver";

    protected abstract void update(Map<String, Department> var1);

    public final void update(Observable observable, Object object) {
        if (observable instanceof LivechatDepartmentsPath) {
            if (object instanceof Map) {
                this.update((Map<String, Department>)((Map)object));
                return;
            }
            Logger.i((String)"DepartmentsObserver", (String)("Unexpected broadcast object " + object + " Broadcast object should be of type " + Map.class), (Object[])new Object[0]);
            return;
        }
        Logger.i((String)"DepartmentsObserver", (String)("Unexpected broadcast observable " + (Object)observable + " Observable should be of type " + LivechatDepartmentsPath.class), (Object[])new Object[0]);
    }
}

