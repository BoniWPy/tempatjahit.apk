/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  java.lang.Object
 */
package com.zopim.android.sdk.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forms {
    @Expose
    @SerializedName(value="offline_form")
    OfflineForm offlineForm;

    public OfflineForm getOfflineForm() {
        return this.offlineForm;
    }

    public static class FormSubmitted {
    }

    public static class OfflineForm {
        @Expose
        @SerializedName(value="form_submitted")
        FormSubmitted formSubmitted;

        @Nullable
        public FormSubmitted getFormSubmitted() {
            return this.formSubmitted;
        }
    }

}

