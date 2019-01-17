/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Comparator
 */
package com.orm.util;

import java.util.Comparator;

public class NumberComparator
implements Comparator<Object> {
    private static char charAt(String string2, int n) {
        if (n >= string2.length()) {
            return '\u0000';
        }
        return string2.charAt(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private int compareRight(String string2, String string3) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        do {
            char c = NumberComparator.charAt(string2, n2);
            char c2 = NumberComparator.charAt(string3, n3);
            if (!Character.isDigit((char)c) && !Character.isDigit((char)c2)) {
                return n;
            }
            if (!Character.isDigit((char)c)) {
                return -1;
            }
            if (!Character.isDigit((char)c2)) {
                return 1;
            }
            if (c < c2) {
                if (n == 0) {
                    n = -1;
                }
            } else if (c > c2) {
                if (n == 0) {
                    n = 1;
                }
            } else if (c == '\u0000' && c2 == '\u0000') {
                return n;
            }
            ++n2;
            ++n3;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public int compare(Object object, Object object2) {
        String string2 = object.toString();
        String string3 = object2.toString();
        int n = 0;
        int n2 = 0;
        do {
            int n3;
            int n4;
            int n5 = 0;
            char c = NumberComparator.charAt(string2, n);
            char c2 = NumberComparator.charAt(string3, n2);
            do {
                if (!Character.isSpaceChar((char)c)) {
                    n4 = 0;
                    if (c != '0') break;
                }
                n5 = c == '0' ? ++n5 : 0;
                c = NumberComparator.charAt(string2, ++n);
            } while (true);
            while (Character.isSpaceChar((char)c2) || c2 == '0') {
                n4 = c2 == '0' ? ++n4 : 0;
                c2 = NumberComparator.charAt(string3, ++n2);
            }
            if (Character.isDigit((char)c) && Character.isDigit((char)c2) && (n3 = this.compareRight(string2.substring(n), string3.substring(n2))) != 0) {
                return n3;
            }
            if (c == '\u0000' && c2 == '\u0000') {
                return n5 - n4;
            }
            if (c < c2) {
                return -1;
            }
            if (c > c2) {
                return 1;
            }
            ++n;
            ++n2;
        } while (true);
    }
}

