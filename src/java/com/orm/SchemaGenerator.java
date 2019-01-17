/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.database.Cursor
 *  android.database.SQLException
 *  android.database.sqlite.SQLiteDatabase
 *  android.util.Log
 *  java.io.BufferedReader
 *  java.io.IOException
 *  java.io.InputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.NumberFormatException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.lang.annotation.Annotation
 *  java.lang.reflect.Field
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.Iterator
 *  java.util.List
 */
package com.orm;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.orm.dsl.Column;
import com.orm.dsl.MultiUnique;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;
import com.orm.util.MigrationFileParser;
import com.orm.util.NamingHelper;
import com.orm.util.NumberComparator;
import com.orm.util.QueryBuilder;
import com.orm.util.ReflectionUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SchemaGenerator {
    public static final String NOT_NULL = " NOT NULL";
    public static final String NULL = " NULL";
    public static final String SUGAR = "Sugar";
    public static final String UNIQUE = " UNIQUE";
    private Context context;

    public SchemaGenerator(Context context) {
        this.context = context;
    }

    private void addColumns(Class<?> class_, SQLiteDatabase sQLiteDatabase) {
        List<Field> list = ReflectionUtil.getTableFields(class_);
        String string2 = NamingHelper.toSQLName(class_);
        ArrayList<String> arrayList = this.getColumnNames(sQLiteDatabase, string2);
        ArrayList arrayList2 = new ArrayList();
        for (Field field : list) {
            String string3 = NamingHelper.toSQLName(field);
            String string4 = QueryBuilder.getColumnType(field.getType());
            if (field.isAnnotationPresent(Column.class)) {
                string3 = ((Column)field.getAnnotation(Column.class)).name();
            }
            if (arrayList.contains((Object)string3)) continue;
            StringBuilder stringBuilder = new StringBuilder("ALTER TABLE ");
            stringBuilder.append(string2).append(" ADD COLUMN ").append(string3).append(" ").append(string4);
            if (field.isAnnotationPresent(NotNull.class)) {
                if (string4.endsWith(NULL)) {
                    stringBuilder.delete(-5 + stringBuilder.length(), stringBuilder.length());
                }
                stringBuilder.append(NOT_NULL);
            }
            arrayList2.add((Object)stringBuilder.toString());
        }
        for (String string5 : arrayList2) {
            Log.i((String)SUGAR, (String)string5);
            sQLiteDatabase.execSQL(string5);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void createTable(Class<?> class_, SQLiteDatabase sQLiteDatabase) {
        String string2 = this.createTableSQL(class_);
        if (string2.isEmpty()) return;
        try {
            sQLiteDatabase.execSQL(string2);
            return;
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void executeScript(SQLiteDatabase var1_1, String var2_2) {
        try {
            var3_3 = new BufferedReader((Reader)new InputStreamReader(this.context.getAssets().open("sugar_upgrades/" + var2_2)));
            var4_4 = new StringBuilder();
            while ((var8_5 = var3_3.readLine()) != null) {
                var4_4.append(var8_5);
            }
            var10_7 = new MigrationFileParser(var4_4.toString()).getStatements();
            var11_8 = var10_7.length;
            var12_9 = 0;
        }
        catch (IOException var5_6) {
            Log.e((String)"Sugar", (String)var5_6.getMessage());
            do {
                Log.i((String)"Sugar", (String)"Script executed");
                return;
                break;
            } while (true);
        }
        do {
            if (var12_9 >= var11_8) ** continue;
            var13_10 = var10_7[var12_9];
            Log.i((String)"Sugar script", (String)var13_10);
            if (!var13_10.isEmpty()) {
                var1_1.execSQL(var13_10);
            }
            ++var12_9;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean executeSugarUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
        boolean bl = false;
        try {
            List list = Arrays.asList((Object[])this.context.getAssets().list("sugar_upgrades"));
            Collections.sort((List)list, (Comparator)new NumberComparator());
            for (String string2 : list) {
                Log.i((String)SUGAR, (String)("filename : " + string2));
                try {
                    int n3 = Integer.valueOf((String)string2.replace((CharSequence)".sql", (CharSequence)""));
                    if (n3 <= n || n3 > n2) continue;
                    this.executeScript(sQLiteDatabase, string2);
                    bl = true;
                }
                catch (NumberFormatException numberFormatException) {
                    Log.i((String)SUGAR, (String)("not a sugar script. ignored." + string2));
                }
            }
            return bl;
        }
        catch (IOException iOException) {
            Log.e((String)SUGAR, (String)iOException.getMessage());
        }
        return bl;
    }

    private ArrayList<String> getColumnNames(SQLiteDatabase sQLiteDatabase, String string2) {
        Cursor cursor = sQLiteDatabase.query(string2, null, null, null, null, null, null);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cursor.getColumnCount(); ++i) {
            arrayList.add((Object)cursor.getColumnName(i));
        }
        cursor.close();
        return arrayList;
    }

    public void createDatabase(SQLiteDatabase sQLiteDatabase) {
        Iterator iterator = ReflectionUtil.getDomainClasses(this.context).iterator();
        while (iterator.hasNext()) {
            this.createTable((Class)iterator.next(), sQLiteDatabase);
        }
    }

    protected String createTableSQL(Class<?> class_) {
        Log.i((String)SUGAR, (String)"Create table if not exists");
        List<Field> list = ReflectionUtil.getTableFields(class_);
        String string2 = NamingHelper.toSQLName(class_);
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(string2).append(" ( ID INTEGER PRIMARY KEY AUTOINCREMENT ");
        for (Field field : list) {
            String string3 = NamingHelper.toSQLName(field);
            String string4 = QueryBuilder.getColumnType(field.getType());
            if (string4 == null || string3.equalsIgnoreCase("Id")) continue;
            if (field.isAnnotationPresent(Column.class)) {
                Column column = (Column)field.getAnnotation(Column.class);
                String string5 = column.name();
                stringBuilder.append(", ").append(string5).append(" ").append(string4);
                if (column.notNull()) {
                    if (string4.endsWith(NULL)) {
                        stringBuilder.delete(-5 + stringBuilder.length(), stringBuilder.length());
                    }
                    stringBuilder.append(NOT_NULL);
                }
                if (!column.unique()) continue;
                stringBuilder.append(UNIQUE);
                continue;
            }
            stringBuilder.append(", ").append(string3).append(" ").append(string4);
            if (field.isAnnotationPresent(NotNull.class)) {
                if (string4.endsWith(NULL)) {
                    stringBuilder.delete(-5 + stringBuilder.length(), stringBuilder.length());
                }
                stringBuilder.append(NOT_NULL);
            }
            if (!field.isAnnotationPresent(Unique.class)) continue;
            stringBuilder.append(UNIQUE);
        }
        if (class_.isAnnotationPresent(MultiUnique.class)) {
            String string6 = ((MultiUnique)class_.getAnnotation(MultiUnique.class)).value();
            stringBuilder.append(", UNIQUE(");
            String[] arrstring = string6.split(",");
            for (int i = 0; i < arrstring.length; ++i) {
                stringBuilder.append(NamingHelper.toSQLNameDefault(arrstring[i]));
                if (i >= -1 + arrstring.length) continue;
                stringBuilder.append(",");
            }
            stringBuilder.append(") ON CONFLICT REPLACE");
        }
        stringBuilder.append(" ) ");
        Log.i((String)SUGAR, (String)("Creating table " + string2));
        return stringBuilder.toString();
    }

    public void deleteTables(SQLiteDatabase sQLiteDatabase) {
        for (Class class_ : ReflectionUtil.getDomainClasses(this.context)) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NamingHelper.toSQLName(class_));
        }
    }

    public void doUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
        for (Class class_ : ReflectionUtil.getDomainClasses(this.context)) {
            Cursor cursor = sQLiteDatabase.rawQuery(String.format((String)"select count(*) from sqlite_master where type='table' and name='%s';", (Object[])new Object[]{NamingHelper.toSQLName(class_)}), null);
            if (cursor.moveToFirst() && cursor.getInt(0) == 0) {
                this.createTable(class_, sQLiteDatabase);
                continue;
            }
            this.addColumns(class_, sQLiteDatabase);
        }
        this.executeSugarUpgrade(sQLiteDatabase, n, n2);
    }
}

