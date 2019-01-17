/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.zendesk.logger.Logger
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Observable
 *  java.util.Observer
 */
package com.zopim.android.sdk.data.observers;

import com.zendesk.logger.Logger;
import com.zopim.android.sdk.data.LivechatProfilePath;
import com.zopim.android.sdk.model.Profile;
import java.util.Observable;
import java.util.Observer;

public abstract class ProfileObserver
implements Observer {
    private static final String LOG_TAG = "ProfileObserver";

    protected abstract void update(Profile var1);

    public final void update(Observable observable, Object object) {
        if (observable instanceof LivechatProfilePath) {
            if (object instanceof Profile) {
                this.update((Profile)object);
                return;
            }
            Logger.i((String)"ProfileObserver", (String)("Unexpected broadcast object " + object + " Broadcast object should be of type " + Profile.class), (Object[])new Object[0]);
            return;
        }
        Logger.i((String)"ProfileObserver", (String)("Unexpected broadcast observable " + (Object)observable + " Observable should be of type " + LivechatProfilePath.class), (Object[])new Object[0]);
    }
}

