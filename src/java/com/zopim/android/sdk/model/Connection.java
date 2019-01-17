/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Connection {
    @Expose
    @SerializedName(value="status$string")
    private String status;

    public Connection() {
    }

    public Connection(Status status) {
        this.status = status.getValue();
    }

    @NonNull
    public Status getStatus() {
        return Status.getStatus(this.status);
    }

    public String toString() {
        return this.status;
    }

    public static final class Status
    extends Enum<Status> {
        private static final /* synthetic */ Status[] $VALUES;
        public static final /* enum */ Status CLOSED;
        public static final /* enum */ Status CONNECTED;
        public static final /* enum */ Status CONNECTING;
        public static final /* enum */ Status DISCONNECTED;
        public static final /* enum */ Status NO_CONNECTION;
        public static final /* enum */ Status UNKNOWN;
        final String value;

        static {
            NO_CONNECTION = new Status("noConnection");
            CLOSED = new Status("closed");
            DISCONNECTED = new Status("disconnected");
            CONNECTING = new Status("connecting");
            CONNECTED = new Status("connected");
            UNKNOWN = new Status("unknown");
            Status[] arrstatus = new Status[]{NO_CONNECTION, CLOSED, DISCONNECTED, CONNECTING, CONNECTED, UNKNOWN};
            $VALUES = arrstatus;
        }

        private Status(String string3) {
            this.value = string3;
        }

        @NonNull
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

