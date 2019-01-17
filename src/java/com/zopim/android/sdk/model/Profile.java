/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @Expose
    @SerializedName(value="department_id$int")
    private String departmentId;
    @Expose
    @SerializedName(value="display_name$string")
    private String displayName;
    @Expose
    @SerializedName(value="email$string")
    private String email;
    @Expose
    @SerializedName(value="mid$string")
    private String machineId;
    @Expose
    @SerializedName(value="phone$string")
    private String phoneNumber;

    @Nullable
    public String getDepartmentId() {
        return this.departmentId;
    }

    @Nullable
    public String getDisplayName() {
        return this.displayName;
    }

    @Nullable
    public String getEmail() {
        return this.email;
    }

    @Nullable
    public String getMachineId() {
        return this.machineId;
    }

    @Nullable
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String toString() {
        return " mid:" + this.machineId + " email:" + this.email + " name:" + this.displayName + " depId:" + this.departmentId;
    }
}

