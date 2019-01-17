/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.Nullable
 *  java.io.Serializable
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.model;

import android.support.annotation.Nullable;
import java.io.Serializable;

public class VisitorInfo
implements Serializable {
    private static final long serialVersionUID = 8250425043423370849L;
    private String email;
    private String name;
    private String note;
    private String phoneNumber;

    private VisitorInfo() {
    }

    private VisitorInfo(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.note = builder.note;
    }

    @Nullable
    public String getEmail() {
        return this.email;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    public String getNote() {
        return this.note;
    }

    @Nullable
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setEmail(String string2) {
        this.email = string2;
    }

    public void setName(String string2) {
        this.name = string2;
    }

    public void setNote(String string2) {
        this.note = string2;
    }

    public void setPhoneNumber(String string2) {
        this.phoneNumber = string2;
    }

    public static class Builder {
        String email;
        String name;
        String note;
        String phoneNumber;

        public VisitorInfo build() {
            return new VisitorInfo(this);
        }

        public Builder email(String string2) {
            this.email = string2;
            return this;
        }

        public Builder name(String string2) {
            this.name = string2;
            return this;
        }

        public Builder note(String string2) {
            this.note = string2;
            return this;
        }

        public Builder phoneNumber(String string2) {
            this.phoneNumber = string2;
            return this;
        }
    }

}

