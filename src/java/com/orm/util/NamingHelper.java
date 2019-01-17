/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.annotation.Annotation
 *  java.lang.reflect.Field
 */
package com.orm.util;

import com.orm.dsl.Column;
import com.orm.dsl.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NamingHelper {
    public static String toSQLName(Class<?> class_) {
        if (class_.isAnnotationPresent(Table.class)) {
            Table table = (Table)class_.getAnnotation(Table.class);
            if ("".equals((Object)table.name())) {
                return NamingHelper.toSQLNameDefault(class_.getSimpleName());
            }
            return table.name();
        }
        return NamingHelper.toSQLNameDefault(class_.getSimpleName());
    }

    public static String toSQLName(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            return ((Column)field.getAnnotation(Column.class)).name();
        }
        return NamingHelper.toSQLNameDefault(field.getName());
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String toSQLNameDefault(String string2) {
        if (string2.equalsIgnoreCase("_id")) {
            return "_id";
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] arrc = string2.toCharArray();
        int n = 0;
        while (n < arrc.length) {
            char c = n > 0 ? arrc[n - 1] : (char)'\u0000';
            char c2 = arrc[n];
            char c3 = n < -1 + arrc.length ? arrc[n + 1] : (char)'\u0000';
            boolean bl = n == 0;
            if (bl || Character.isLowerCase((char)c2) || Character.isDigit((char)c2)) {
                stringBuilder.append(Character.toUpperCase((char)c2));
            } else if (Character.isUpperCase((char)c2)) {
                if (Character.isLetterOrDigit((char)c)) {
                    if (Character.isLowerCase((char)c)) {
                        stringBuilder.append('_').append(c2);
                    } else if (c3 > '\u0000' && Character.isLowerCase((char)c3)) {
                        stringBuilder.append('_').append(c2);
                    } else {
                        stringBuilder.append(c2);
                    }
                } else {
                    stringBuilder.append(c2);
                }
            }
            ++n;
        }
        return stringBuilder.toString();
    }
}

