/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedList
 *  java.util.StringTokenizer
 */
package com.zopim.android.sdk.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class FileSending {
    @Expose
    @SerializedName(value="enabled$bool")
    private boolean enabled;
    @Expose
    @SerializedName(value="allowed_extensions$string")
    private String extensions;

    @Nullable
    public String[] getExtensions() {
        if (this.extensions == null) {
            return new String[0];
        }
        StringTokenizer stringTokenizer = new StringTokenizer(this.extensions, ",");
        stringTokenizer.countTokens();
        LinkedList linkedList = new LinkedList();
        while (stringTokenizer.hasMoreTokens()) {
            linkedList.add((Object)stringTokenizer.nextToken());
        }
        return (String[])linkedList.toArray((Object[])new String[linkedList.size()]);
    }

    @Nullable
    public boolean isEnabled() {
        return this.enabled;
    }

    public String toString() {
        return "File Sending. Enabled: " + this.enabled + " Ext: " + this.extensions;
    }
}

