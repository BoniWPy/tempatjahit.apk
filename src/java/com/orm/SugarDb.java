/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 *  android.util.Log
 *  java.lang.String
 */
package com.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.orm.SchemaGenerator;
import com.orm.util.ManifestHelper;
import com.orm.util.SugarCursorFactory;

public class SugarDb
extends SQLiteOpenHelper {
    private int openedConnections = 0;
    private final SchemaGenerator schemaGenerator;
    private SQLiteDatabase sqLiteDatabase;

    public SugarDb(Context context) {
        super(context, ManifestHelper.getDatabaseName(context), (SQLiteDatabase.CursorFactory)new SugarCursorFactory(ManifestHelper.getDebugEnabled(context)), ManifestHelper.getDatabaseVersion(context));
        this.schemaGenerator = new SchemaGenerator(context);
    }

    public void close() {
        SugarDb sugarDb = this;
        synchronized (sugarDb) {
            Log.d((String)"SUGAR", (String)"getReadableDatabase");
            this.openedConnections = -1 + this.openedConnections;
            if (this.openedConnections == 0) {
                Log.d((String)"SUGAR", (String)"closing");
                super.close();
            }
            return;
        }
    }

    public SQLiteDatabase getDB() {
        SugarDb sugarDb = this;
        synchronized (sugarDb) {
            if (this.sqLiteDatabase == null) {
                this.sqLiteDatabase = this.getWritableDatabase();
            }
            SQLiteDatabase sQLiteDatabase = this.sqLiteDatabase;
            return sQLiteDatabase;
        }
    }

    public SQLiteDatabase getReadableDatabase() {
        SugarDb sugarDb = this;
        synchronized (sugarDb) {
            Log.d((String)"SUGAR", (String)"getReadableDatabase");
            this.openedConnections = 1 + this.openedConnections;
            SQLiteDatabase sQLiteDatabase = super.getReadableDatabase();
            return sQLiteDatabase;
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.schemaGenerator.createDatabase(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
        this.schemaGenerator.doUpgrade(sQLiteDatabase, n, n2);
    }
}

