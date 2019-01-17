/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  java.io.Serializable
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.UnsupportedOperationException
 */
package com.zopim.android.sdk.prechat;

import android.support.annotation.NonNull;
import java.io.Serializable;

public class PreChatForm
implements Serializable {
    private static final long serialVersionUID = 9006657233172922772L;
    private final Field department;
    private final Field email;
    private final Field message;
    private final Field name;
    private final Field phoneNumber;

    private PreChatForm() {
        throw new UnsupportedOperationException("This constructor is not supported, use parametrized constructor");
    }

    private PreChatForm(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.department = builder.department;
        this.message = builder.message;
    }

    @NonNull
    public Field getDepartment() {
        if (this.department != null) {
            return this.department;
        }
        return Field.NOT_REQUIRED;
    }

    @NonNull
    public Field getEmail() {
        if (this.email != null) {
            return this.email;
        }
        return Field.NOT_REQUIRED;
    }

    @NonNull
    public Field getMessage() {
        if (this.message != null) {
            return this.message;
        }
        return Field.NOT_REQUIRED;
    }

    @NonNull
    public Field getName() {
        if (this.name != null) {
            return this.name;
        }
        return Field.NOT_REQUIRED;
    }

    @NonNull
    public Field getPhoneNumber() {
        if (this.phoneNumber != null) {
            return this.phoneNumber;
        }
        return Field.NOT_REQUIRED;
    }

    public static class Builder {
        private Field department = Field.NOT_REQUIRED;
        private Field email = Field.NOT_REQUIRED;
        private Field message = Field.NOT_REQUIRED;
        private Field name = Field.NOT_REQUIRED;
        private Field phoneNumber = Field.NOT_REQUIRED;

        @NonNull
        public PreChatForm build() {
            return new PreChatForm(this);
        }

        @NonNull
        public Builder department(@NonNull Field field) {
            this.department = field;
            return this;
        }

        @NonNull
        public Builder email(@NonNull Field field) {
            this.email = field;
            return this;
        }

        @NonNull
        public Builder message(@NonNull Field field) {
            this.message = field;
            return this;
        }

        @NonNull
        public Builder name(@NonNull Field field) {
            this.name = field;
            return this;
        }

        @NonNull
        public Builder phoneNumber(@NonNull Field field) {
            this.phoneNumber = field;
            return this;
        }
    }

    public static final class Field
    extends Enum<Field> {
        private static final /* synthetic */ Field[] $VALUES;
        public static final /* enum */ Field NOT_REQUIRED = new Field();
        public static final /* enum */ Field OPTIONAL = new Field();
        public static final /* enum */ Field OPTIONAL_EDITABLE;
        public static final /* enum */ Field REQUIRED;
        public static final /* enum */ Field REQUIRED_EDITABLE;

        static {
            REQUIRED = new Field();
            OPTIONAL_EDITABLE = new Field();
            REQUIRED_EDITABLE = new Field();
            Field[] arrfield = new Field[]{NOT_REQUIRED, OPTIONAL, REQUIRED, OPTIONAL_EDITABLE, REQUIRED_EDITABLE};
            $VALUES = arrfield;
        }

        public static Field valueOf(String string2) {
            return (Field)Enum.valueOf(Field.class, (String)string2);
        }

        public static Field[] values() {
            return (Field[])$VALUES.clone();
        }
    }

}

