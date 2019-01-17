/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.database.sqlite.SQLiteDatabase
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.orm;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.orm.SugarContext;
import com.orm.SugarDb;

public class SugarTransactionHelper {
    public static void doInTransaction(Callback callback) {
        SQLiteDatabase sQLiteDatabase = SugarContext.getSugarContext().getSugarDb().getDB();
        sQLiteDatabase.beginTransaction();
        try {
            Log.d((String)SugarTransactionHelper.class.getSimpleName(), (String)"Callback executing within transaction");
            callback.manipulateInTransaction();
            sQLiteDatabase.setTransactionSuccessful();
            Log.d((String)SugarTransactionHelper.class.getSimpleName(), (String)"Callback successfully executed within transaction");
            return;
        }
        catch (Throwable throwable) {
            Log.d((String)SugarTransactionHelper.class.getSimpleName(), (String)"Could execute callback within transaction", (Throwable)throwable);
            return;
        }
        finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public static interface Callback {
        public void manipulateInTransaction();
    }

}

