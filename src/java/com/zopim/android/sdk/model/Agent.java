/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agent {
    @Expose
    @SerializedName(value="avatar_path$string")
    private String avatarUri;
    @Expose
    @SerializedName(value="display_name$string")
    private String displayName;
    @Expose
    @SerializedName(value="typing$bool")
    private Boolean isTyping;

    @Nullable
    public String getAvatarUri() {
        return this.avatarUri;
    }

    @Nullable
    public String getDisplayName() {
        return this.displayName;
    }

    @Nullable
    public Boolean isTyping() {
        return this.isTyping;
    }

    public String toString() {
        return this.displayName + ", typing: " + (Object)this.isTyping;
    }
}

