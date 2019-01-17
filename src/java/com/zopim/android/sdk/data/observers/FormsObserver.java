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
import com.zopim.android.sdk.data.LivechatFormsPath;
import com.zopim.android.sdk.model.Forms;
import java.util.Observable;
import java.util.Observer;

public abstract class FormsObserver
implements Observer {
    private static final String LOG_TAG = "FormsObserver";

    protected abstract void update(Forms var1);

    public final void update(Observable observable, Object object) {
        if (observable instanceof LivechatFormsPath) {
            if (object instanceof Forms) {
                this.update((Forms)object);
                return;
            }
            Logger.i((String)"FormsObserver", (String)("Unexpected broadcast object " + object + " Broadcast object should be of type " + Forms.class), (Object[])new Object[0]);
            return;
        }
        Logger.i((String)"FormsObserver", (String)("Unexpected broadcast observable " + (Object)observable + " Observable should be of type " + LivechatFormsPath.class), (Object[])new Object[0]);
    }
}

