/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteCursor
 *  android.database.sqlite.SQLiteCursorDriver
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteQuery
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.orm.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

public class SugarCursorFactory
implements SQLiteDatabase.CursorFactory {
    private boolean debugEnabled;

    public SugarCursorFactory() {
        this.debugEnabled = false;
    }

    public SugarCursorFactory(boolean bl) {
        this.debugEnabled = bl;
    }

    public Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String string2, SQLiteQuery sQLiteQuery) {
        if (this.debugEnabled) {
            Log.d((String)"SQL Log", (String)sQLiteQuery.toString());
        }
        return new SQLiteCursor(sQLiteDatabase, sQLiteCursorDriver, string2, sQLiteQuery);
    }
}

