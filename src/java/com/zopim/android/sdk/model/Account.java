/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.model;

import android.support.annotation.Nullable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {
    @Expose
    @SerializedName(value="status$string")
    private String status;

    @Nullable
    public Status getStatus() {
        return Status.getStatus(this.status);
    }

    public static final class Status
    extends Enum<Status> {
        private static final /* synthetic */ Status[] $VALUES;
        public static final /* enum */ Status OFFLINE;
        public static final /* enum */ Status ONLINE;
        public static final /* enum */ Status UNKNOWN;
        final String value;

        static {
            ONLINE = new Status("online");
            OFFLINE = new Status("offline");
            UNKNOWN = new Status("unknown");
            Status[] arrstatus = new Status[]{ONLINE, OFFLINE, UNKNOWN};
            $VALUES = arrstatus;
        }

        private Status(String string3) {
            this.value = string3;
        }

        public static Status getStatus(String string2) {
            for (Status status : Status.values()) {
                if (!status.getValue().equals((Object)string2)) continue;
                return status;
            }
            return UNKNOWN;
        }

        public static Status valueOf(String string2) {
            return (Status)Enum.valueOf(Status.class, (String)string2);
        }

        public static Status[] values() {
            return (Status[])$VALUES.clone();
        }

        public String getValue() {
            return this.value;
        }
    }

}

