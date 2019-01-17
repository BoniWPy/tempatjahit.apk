/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.database.Cursor
 *  android.database.CursorWrapper
 *  java.lang.IllegalArgumentException
 *  java.lang.Object
 *  java.lang.String
 */
package com.orm.util;

import android.database.Cursor;
import android.database.CursorWrapper;

public class SugarCursor
extends CursorWrapper {
    public SugarCursor(Cursor cursor) {
        super(cursor);
    }

    public int getColumnIndex(String string2) {
        if (string2.equals((Object)"_id")) {
            string2 = "ID";
        }
        return super.getColumnIndex(string2);
    }

    public int getColumnIndexOrThrow(String string2) throws IllegalArgumentException {
        try {
            int n = super.getColumnIndexOrThrow(string2);
            return n;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            if (string2.equals((Object)"_id")) {
                return super.getColumnIndexOrThrow("ID");
            }
            throw illegalArgumentException;
        }
    }
}

