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
import com.zopim.android.sdk.data.LivechatFileSendingPath;
import com.zopim.android.sdk.model.FileSending;
import java.util.Observable;
import java.util.Observer;

public abstract class FileSendingObserver
implements Observer {
    private static final String LOG_TAG = "FileSendingObserver";

    protected abstract void update(FileSending var1);

    public final void update(Observable observable, Object object) {
        if (observable instanceof LivechatFileSendingPath) {
            if (object instanceof FileSending) {
                this.update((FileSending)object);
                return;
            }
            Logger.i((String)"FileSendingObserver", (String)("Unexpected broadcast object " + object + " Broadcast object should be of type " + FileSending.class), (Object[])new Object[0]);
            return;
        }
        Logger.i((String)"FileSendingObserver", (String)("Unexpected broadcast observable " + (Object)observable + " Observable should be of type " + LivechatFileSendingPath.class), (Object[])new Object[0]);
    }
}

