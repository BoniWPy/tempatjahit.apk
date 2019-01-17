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
import com.zopim.android.sdk.model.Profile;

public class LivechatProfilePath
extends Path<Profile> {
    private static final LivechatProfilePath INSTANCE = new LivechatProfilePath();

    private LivechatProfilePath() {
    }

    public static LivechatProfilePath getInstance() {
        return INSTANCE;
    }

    void clear() {
        this.data = null;
    }

    public Profile getData() {
        return (Profile)this.data;
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
                    this.data = new Profile();
                }
                this.data = ChatGson.performUpdate((Object)this.data, (String)string, Profile.class);
                this.broadcast((Object)this.getData());
                return;
            }
        }
    }
}

