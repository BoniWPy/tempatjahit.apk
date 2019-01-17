/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.orm.SugarRecord
 *  java.lang.Boolean
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.RuntimeException
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.math.BigDecimal
 *  java.sql.Date
 *  java.util.Calendar
 *  java.util.Date
 */
package com.orm.util;

import com.orm.SugarRecord;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class QueryBuilder {
    public static String generatePlaceholders(int n) {
        if (n < 1) {
            throw new RuntimeException("The number of arguments must be greater than or equal to 1.");
        }
        StringBuilder stringBuilder = new StringBuilder(-1 + n * 2);
        stringBuilder.append("?");
        for (int i = 1; i < n; ++i) {
            stringBuilder.append(",?");
        }
        return stringBuilder.toString();
    }

    public static String getColumnType(Class<?> class_) {
        if (class_.equals(Boolean.class) || class_.equals((Object)Boolean.TYPE) || class_.equals(Integer.class) || class_.equals((Object)Integer.TYPE) || class_.equals(Long.class) || class_.equals((Object)Long.TYPE) || !class_.isPrimitive() && SugarRecord.class.isAssignableFrom(class_)) {
            return "INTEGER";
        }
        if (class_.equals(Date.class) || class_.equals(java.sql.Date.class) || class_.equals(Calendar.class)) {
            return "INTEGER NULL";
        }
        if (class_.getName().equals((Object)"[B")) {
            return "BLOB";
        }
        if (class_.equals(Double.class) || class_.equals((Object)Double.TYPE) || class_.equals(Float.class) || class_.equals((Object)Float.TYPE)) {
            return "FLOAT";
        }
        if (class_.equals(String.class) || class_.equals((Object)Character.TYPE) || class_.equals(BigDecimal.class)) {
            return "TEXT";
        }
        return "";
    }
}

