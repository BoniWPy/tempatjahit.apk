/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.reflect.Field
 *  java.util.Collections
 *  java.util.HashMap
 *  java.util.List
 *  java.util.Map
 */
package com.orm.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SugarConfig {
    static Map<Class<?>, List<Field>> fields = new HashMap();

    public static void clearCache() {
        fields.clear();
        fields = new HashMap();
    }

    public static List<Field> getFields(Class<?> class_) {
        if (fields.containsKey(class_)) {
            return Collections.synchronizedList((List)((List)fields.get(class_)));
        }
        return null;
    }

    public static void setFields(Class<?> class_, List<Field> list) {
        fields.put(class_, list);
    }
}

