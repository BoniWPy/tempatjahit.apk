/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Arrays
 *  java.util.Collection
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.List
 *  java.util.Map
 *  java.util.Set
 */
package com.orm.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Collection {
    public static <K, V> Entry<K, V> entry(K k, V v) {
        return new Entry<K, V>(k, v);
    }

    public static /* varargs */ <T> List<T> list(T ... arrT) {
        return Arrays.asList((Object[])arrT);
    }

    public static /* varargs */ <K, V> Map<K, V> map(Entry<? extends K, ? extends V> ... arrentry) {
        HashMap hashMap = new HashMap(arrentry.length);
        for (Entry<? extends K, ? extends V> entry : arrentry) {
            if (entry.value == null) continue;
            hashMap.put(entry.key, entry.value);
        }
        return hashMap;
    }

    public static /* varargs */ <T> Set<T> set(T ... arrT) {
        HashSet hashSet = new HashSet(arrT.length);
        hashSet.addAll((java.util.Collection)Arrays.asList((Object[])arrT));
        return hashSet;
    }

    public static class Entry<K, V> {
        K key;
        V value;

        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

}

