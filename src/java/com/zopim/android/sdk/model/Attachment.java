/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  com.zendesk.logger.Logger
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.MalformedURLException
 *  java.net.URL
 */
package com.zopim.android.sdk.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zendesk.logger.Logger;
import java.net.MalformedURLException;
import java.net.URL;

public class Attachment {
    private static final String LOG_TAG = "Attachment";
    @Expose
    @SerializedName(value="mime_type$string")
    private String mimeType;
    @Expose
    @SerializedName(value="name$string")
    private String name;
    @Expose
    @SerializedName(value="size$int")
    private Long size;
    @Expose
    @SerializedName(value="thumb$string")
    private String thumbnail;
    @Expose
    @SerializedName(value="type$string")
    private String type;
    @Expose
    @SerializedName(value="url$string")
    private String url;

    @Nullable
    public String getMimeType() {
        return this.mimeType;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @Nullable
    public Long getSize() {
        return this.size;
    }

    @Nullable
    public URL getThumbnail() {
        if (this.thumbnail != null) {
            try {
                URL uRL = new URL(this.thumbnail);
                return uRL;
            }
            catch (MalformedURLException malformedURLException) {
                Logger.w((String)LOG_TAG, (String)"Can not retrieve url. ", (Throwable)malformedURLException, (Object[])new Object[0]);
                return null;
            }
        }
        return null;
    }

    @Nullable
    public String getType() {
        return this.type;
    }

    @Nullable
    public URL getUrl() {
        if (this.url != null) {
            try {
                URL uRL = new URL(this.url);
                return uRL;
            }
            catch (MalformedURLException malformedURLException) {
                Logger.w((String)LOG_TAG, (String)"Can not retrieve url. ", (Throwable)malformedURLException, (Object[])new Object[0]);
                return null;
            }
        }
        return null;
    }
}

