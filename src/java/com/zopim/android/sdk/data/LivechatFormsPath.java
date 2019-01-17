/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.zopim.android.sdk.data.ChatGson
 *  com.zopim.android.sdk.data.Path
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.data;

import com.zopim.android.sdk.data.ChatGson;
import com.zopim.android.sdk.data.Path;
import com.zopim.android.sdk.model.Forms;

public class LivechatFormsPath
extends Path<Forms> {
    private static final LivechatFormsPath INSTANCE = new LivechatFormsPath();

    private LivechatFormsPath() {
    }

    public static LivechatFormsPath getInstance() {
        return INSTANCE;
    }

    void clear() {
        this.data = null;
    }

    public Forms getData() {
        return (Forms)this.data;
    }

    /*
     * Enabled aggressive block sorting
     */
    void update(String string) {
        if (this.isClearRequired(string)) {
            this.clear();
            return;
        } else {
            if (string == null || string.isEmpty()) return;
            {
                if (this.data == null) {
                    this.data = new Forms();
                }
                this.data = ChatGson.performUpdate((Object)this.data, (String)string, Forms.class);
                this.broadcast((Object)this.getData());
                return;
            }
        }
    }
}

