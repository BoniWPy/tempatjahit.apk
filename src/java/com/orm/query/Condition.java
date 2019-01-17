/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.orm.SugarRecord
 *  java.lang.Enum
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 */
package com.orm.query;

import com.orm.SugarRecord;

public class Condition {
    private Check check;
    private String property;
    private Object value;

    public Condition(String string2) {
        this.property = string2;
    }

    public static Condition prop(String string2) {
        return new Condition(string2);
    }

    private void setValue(Object object) {
        if (object instanceof SugarRecord) {
            this.value = ((SugarRecord)object).getId();
            return;
        }
        this.value = object;
    }

    public Condition eq(Object object) {
        if (object == null) {
            return this.isNull();
        }
        this.setValue(object);
        this.check = Check.EQUALS;
        return this;
    }

    public Check getCheck() {
        return this.check;
    }

    public String getCheckSymbol() {
        return this.check.getSymbol();
    }

    public String getProperty() {
        return this.property;
    }

    public Object getValue() {
        return this.value;
    }

    public Condition gt(Object object) {
        this.setValue(object);
        this.check = Check.GREATER_THAN;
        return this;
    }

    public Condition isNotNull() {
        this.setValue(null);
        this.check = Check.IS_NOT_NULL;
        return this;
    }

    public Condition isNull() {
        this.setValue(null);
        this.check = Check.IS_NULL;
        return this;
    }

    public Condition like(Object object) {
        this.setValue(object);
        this.check = Check.LIKE;
        return this;
    }

    public Condition lt(Object object) {
        this.setValue(object);
        this.check = Check.LESSER_THAN;
        return this;
    }

    public Condition notEq(Object object) {
        if (object == null) {
            return this.isNotNull();
        }
        this.setValue(object);
        this.check = Check.NOT_EQUALS;
        return this;
    }

    public Condition notLike(Object object) {
        this.setValue(object);
        this.check = Check.NOT_LIKE;
        return this;
    }

    static final class Check
    extends Enum<Check> {
        private static final /* synthetic */ Check[] $VALUES;
        public static final /* enum */ Check EQUALS = new Check(" = ");
        public static final /* enum */ Check GREATER_THAN = new Check(" > ");
        public static final /* enum */ Check IS_NOT_NULL;
        public static final /* enum */ Check IS_NULL;
        public static final /* enum */ Check LESSER_THAN;
        public static final /* enum */ Check LIKE;
        public static final /* enum */ Check NOT_EQUALS;
        public static final /* enum */ Check NOT_LIKE;
        private String symbol;

        static {
            LESSER_THAN = new Check(" < ");
            NOT_EQUALS = new Check(" != ");
            LIKE = new Check(" LIKE ");
            NOT_LIKE = new Check(" NOT LIKE ");
            IS_NULL = new Check(" IS NULL ");
            IS_NOT_NULL = new Check(" IS NOT NULL ");
            Check[] arrcheck = new Check[]{EQUALS, GREATER_THAN, LESSER_THAN, NOT_EQUALS, LIKE, NOT_LIKE, IS_NULL, IS_NOT_NULL};
            $VALUES = arrcheck;
        }

        private Check(String string3) {
            this.symbol = string3;
        }

        public static Check valueOf(String string2) {
            return (Check)Enum.valueOf(Check.class, (String)string2);
        }

        public static Check[] values() {
            return (Check[])$VALUES.clone();
        }

        public String getSymbol() {
            return this.symbol;
        }
    }

    static final class Type
    extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES;
        public static final /* enum */ Type AND = new Type();
        public static final /* enum */ Type NOT;
        public static final /* enum */ Type OR;

        static {
            OR = new Type();
            NOT = new Type();
            Type[] arrtype = new Type[]{AND, OR, NOT};
            $VALUES = arrtype;
        }

        public static Type valueOf(String string2) {
            return (Type)Enum.valueOf(Type.class, (String)string2);
        }

        public static Type[] values() {
            return (Type[])$VALUES.clone();
        }
    }

}

