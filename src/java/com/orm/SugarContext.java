/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Long
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collections
 *  java.util.Map
 *  java.util.WeakHashMap
 */
package com.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.orm.SugarDb;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class SugarContext {
    private static SugarContext instance = null;
    private Map<Object, Long> entitiesMap;
    private SugarDb sugarDb;

    private SugarContext(Context context) {
        this.sugarDb = new SugarDb(context);
        this.entitiesMap = Collections.synchronizedMap((Map)new WeakHashMap());
    }

    private void doTerminate() {
        if (this.sugarDb != null) {
            this.sugarDb.getDB().close();
        }
    }

    public static SugarContext getSugarContext() {
        if (instance == null) {
            throw new NullPointerException("SugarContext has not been initialized properly. Call SugarContext.init(Context) in your Application.onCreate() method and SugarContext.terminate() in your Application.onTerminate() method.");
        }
        return instance;
    }

    public static void init(Context context) {
        instance = new SugarContext(context);
    }

    public static void terminate() {
        if (instance == null) {
            return;
        }
        instance.doTerminate();
    }

    Map<Object, Long> getEntitiesMap() {
        return this.entitiesMap;
    }

    protected SugarDb getSugarDb() {
        return this.sugarDb;
    }
}

