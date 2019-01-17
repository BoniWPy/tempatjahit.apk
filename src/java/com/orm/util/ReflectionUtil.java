/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.database.Cursor
 *  android.util.Log
 *  com.orm.SugarRecord
 *  java.io.File
 *  java.io.IOException
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Double
 *  java.lang.Enum
 *  java.lang.Exception
 *  java.lang.Float
 *  java.lang.IllegalAccessException
 *  java.lang.IllegalArgumentException
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.NoSuchFieldException
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.Short
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 *  java.lang.reflect.Modifier
 *  java.math.BigDecimal
 *  java.net.URL
 *  java.sql.Timestamp
 *  java.util.ArrayList
 *  java.util.Calendar
 *  java.util.Collections
 *  java.util.Date
 *  java.util.Enumeration
 *  java.util.Iterator
 *  java.util.List
 *  java.util.Map
 */
package com.orm.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.util.Log;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;
import com.orm.util.ManifestHelper;
import com.orm.util.MultiDexHelper;
import com.orm.util.NamingHelper;
import com.orm.util.SugarConfig;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReflectionUtil {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addFieldValueToColumn(ContentValues contentValues, Field field, Object object, Map<Object, Long> map) {
        Object object2;
        String string2;
        Class class_;
        block30 : {
            String string3;
            field.setAccessible(true);
            class_ = field.getType();
            try {
                string2 = NamingHelper.toSQLName(field);
                object2 = field.get(object);
                boolean bl = class_.isAnnotationPresent(Table.class);
                if (bl) {
                    try {
                        Field field2 = class_.getDeclaredField("id");
                        field2.setAccessible(true);
                        String string4 = field2 != null ? String.valueOf((Object)field2.get(object2)) : "0";
                        contentValues.put(string2, string4);
                        return;
                    }
                    catch (NoSuchFieldException noSuchFieldException) {
                        if (!map.containsKey(object2)) return;
                        {
                            contentValues.put(string2, (Long)map.get(object2));
                            return;
                        }
                    }
                }
                if (SugarRecord.class.isAssignableFrom(class_)) {
                    string3 = object2 != null ? String.valueOf((Object)((SugarRecord)object2).getId()) : "0";
                }
                break block30;
            }
            catch (IllegalAccessException illegalAccessException) {
                Log.e((String)"Sugar", (String)illegalAccessException.getMessage());
                return;
            }
            contentValues.put(string2, string3);
            return;
        }
        if (class_.equals(Short.class) || class_.equals((Object)Short.TYPE)) {
            contentValues.put(string2, (Short)object2);
            return;
        }
        if (class_.equals(Integer.class) || class_.equals((Object)Integer.TYPE)) {
            contentValues.put(string2, (Integer)object2);
            return;
        }
        if (class_.equals(Long.class) || class_.equals((Object)Long.TYPE)) {
            contentValues.put(string2, (Long)object2);
            return;
        }
        if (class_.equals(Float.class) || class_.equals((Object)Float.TYPE)) {
            contentValues.put(string2, (Float)object2);
            return;
        }
        if (class_.equals(Double.class) || class_.equals((Object)Double.TYPE)) {
            contentValues.put(string2, (Double)object2);
            return;
        }
        if (class_.equals(Boolean.class) || class_.equals((Object)Boolean.TYPE)) {
            contentValues.put(string2, (Boolean)object2);
            return;
        }
        boolean bl = class_.equals(BigDecimal.class);
        if (bl) {
            try {
                contentValues.put(string2, field.get(object).toString());
                return;
            }
            catch (NullPointerException nullPointerException) {
                contentValues.putNull(string2);
                return;
            }
        }
        boolean bl2 = Timestamp.class.equals((Object)class_);
        if (bl2) {
            try {
                contentValues.put(string2, Long.valueOf((long)((Timestamp)field.get(object)).getTime()));
                return;
            }
            catch (NullPointerException nullPointerException) {
                contentValues.put(string2, (Long)null);
                return;
            }
        }
        boolean bl3 = Date.class.equals((Object)class_);
        if (bl3) {
            try {
                contentValues.put(string2, Long.valueOf((long)((Date)field.get(object)).getTime()));
                return;
            }
            catch (NullPointerException nullPointerException) {
                contentValues.put(string2, (Long)null);
                return;
            }
        }
        boolean bl4 = Calendar.class.equals((Object)class_);
        if (bl4) {
            try {
                contentValues.put(string2, Long.valueOf((long)((Calendar)field.get(object)).getTimeInMillis()));
                return;
            }
            catch (NullPointerException nullPointerException) {
                contentValues.put(string2, (Long)null);
                return;
            }
        }
        if (class_.equals(byte[].class)) {
            if (object2 == null) {
                contentValues.put(string2, "".getBytes());
                return;
            }
            contentValues.put(string2, (byte[])object2);
            return;
        }
        if (object2 == null) {
            contentValues.putNull(string2);
            return;
        }
        if (class_.isEnum()) {
            contentValues.put(string2, ((Enum)object2).name());
            return;
        }
        contentValues.put(string2, String.valueOf((Object)object2));
    }

    /*
     * Enabled aggressive exception aggregation
     */
    private static List<String> getAllClasses(Context context) throws PackageManager.NameNotFoundException, IOException {
        String string2 = ManifestHelper.getDomainPackageName(context);
        ArrayList arrayList = new ArrayList();
        try {
            for (String string3 : MultiDexHelper.getAllClasses(context)) {
                if (!string3.startsWith(string2)) continue;
                arrayList.add((Object)string3);
            }
        }
        catch (NullPointerException nullPointerException) {
            Enumeration enumeration = Thread.currentThread().getContextClassLoader().getResources("");
            while (enumeration.hasMoreElements()) {
                ArrayList arrayList2 = new ArrayList();
                String string4 = ((URL)enumeration.nextElement()).getFile();
                if (!string4.contains((CharSequence)"bin") && !string4.contains((CharSequence)"classes") && !string4.contains((CharSequence)"retrolambda")) continue;
                File[] arrfile = new File(string4).listFiles();
                int n = arrfile.length;
                for (int i = 0; i < n; ++i) {
                    ReflectionUtil.populateFiles(arrfile[i], (List<String>)arrayList2, "");
                }
                for (String string5 : arrayList2) {
                    if (!string5.startsWith(string2)) continue;
                    arrayList.add((Object)string5);
                }
            }
        }
        return arrayList;
    }

    private static List<Field> getAllFields(List<Field> list, Class<?> class_) {
        Collections.addAll(list, (Object[])class_.getDeclaredFields());
        if (class_.getSuperclass() != null) {
            list = ReflectionUtil.getAllFields(list, class_.getSuperclass());
        }
        return list;
    }

    private static Field getDeepField(String string2, Class<?> class_) throws NoSuchFieldException {
        try {
            Field field = class_.getDeclaredField(string2);
            return field;
        }
        catch (NoSuchFieldException noSuchFieldException) {
            Class class_2 = class_.getSuperclass();
            if (class_2 != null) {
                return ReflectionUtil.getDeepField(string2, class_2);
            }
            throw noSuchFieldException;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Class getDomainClass(String string2, Context context) {
        Class class_;
        try {
            Class class_2;
            class_ = class_2 = Class.forName((String)string2, (boolean)true, (ClassLoader)context.getClass().getClassLoader());
        }
        catch (Throwable throwable) {
            String string3 = throwable.getMessage() == null ? "getDomainClass " + string2 + " error" : throwable.getMessage();
            Log.e((String)"Sugar", (String)string3);
            return null;
        }
        if (class_ == null) return null;
        if (!SugarRecord.class.isAssignableFrom(class_) || SugarRecord.class.equals((Object)class_)) {
            if (!class_.isAnnotationPresent(Table.class)) return null;
        }
        if (Modifier.isAbstract((int)class_.getModifiers())) return null;
        Log.i((String)"Sugar", (String)("domain class : " + class_.getSimpleName()));
        return class_;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static List<Class> getDomainClasses(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator iterator = ReflectionUtil.getAllClasses(context).iterator();
            while (iterator.hasNext()) {
                Class class_ = ReflectionUtil.getDomainClass((String)iterator.next(), context);
                if (class_ == null) continue;
                arrayList.add((Object)class_);
            }
            return arrayList;
        }
        catch (IOException iOException) {
            Log.e((String)"Sugar", (String)iOException.getMessage());
        }
        return arrayList;
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            Log.e((String)"Sugar", (String)nameNotFoundException.getMessage());
            return arrayList;
        }
    }

    private static String getSourcePath(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo((String)context.getPackageName(), (int)0).sourceDir;
    }

    public static List<Field> getTableFields(Class class_) {
        List<Field> list = SugarConfig.getFields(class_);
        if (list != null) {
            return list;
        }
        Log.d((String)"Sugar", (String)"Fetching properties");
        ArrayList arrayList = new ArrayList();
        ReflectionUtil.getAllFields((List<Field>)arrayList, class_);
        ArrayList arrayList2 = new ArrayList();
        for (Field field : arrayList) {
            if (field.isAnnotationPresent(Ignore.class) || Modifier.isStatic((int)field.getModifiers()) || Modifier.isTransient((int)field.getModifiers())) continue;
            arrayList2.add((Object)field);
        }
        SugarConfig.setFields(class_, (List<Field>)arrayList2);
        return arrayList2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void populateFiles(File file, List<String> list, String string2) {
        if (!file.isDirectory()) {
            String string3 = file.getName();
            if (string3.endsWith(".class")) {
                string3 = string3.substring(0, string3.length() - ".class".length());
            }
            if (!"".equals((Object)string2)) {
                list.add((Object)(string2 + "." + string3));
                return;
            }
            list.add((Object)string3);
            return;
        } else {
            for (File file2 : file.listFiles()) {
                if ("".equals((Object)string2)) {
                    ReflectionUtil.populateFiles(file2, list, file.getName());
                    continue;
                }
                ReflectionUtil.populateFiles(file2, list, string2 + "." + file.getName());
            }
        }
    }

    public static void setFieldValueForId(Object object, Long l) {
        try {
            Field field = ReflectionUtil.getDeepField("id", object.getClass());
            field.setAccessible(true);
            field.set(object, (Object)l);
            return;
        }
        catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
            return;
        }
        catch (NoSuchFieldException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setFieldValueFromCursor(Cursor cursor, Field field, Object object) {
        block24 : {
            Class class_;
            int n;
            field.setAccessible(true);
            try {
                class_ = field.getType();
                String string2 = NamingHelper.toSQLName(field);
                n = cursor.getColumnIndex(string2);
                if (n < 0) {
                    Log.e((String)"SUGAR", (String)"Invalid colName, you should upgrade database");
                    return;
                }
                if (cursor.isNull(n)) break block24;
                if (string2.equalsIgnoreCase("id")) {
                    field.set(object, (Object)cursor.getLong(n));
                    return;
                }
                if (class_.equals((Object)Long.TYPE) || class_.equals(Long.class)) {
                    field.set(object, (Object)cursor.getLong(n));
                    return;
                }
                if (class_.equals(String.class)) {
                    String string3 = cursor.getString(n);
                    if (string3 != null && string3.equals((Object)"null")) {
                        string3 = null;
                    }
                    field.set(object, (Object)string3);
                    return;
                }
            }
            catch (IllegalArgumentException illegalArgumentException) {
                Log.e((String)"field set error", (String)illegalArgumentException.getMessage());
                return;
            }
            catch (IllegalAccessException illegalAccessException) {
                Log.e((String)"field set error", (String)illegalAccessException.getMessage());
                return;
            }
            if (class_.equals((Object)Double.TYPE) || class_.equals(Double.class)) {
                field.set(object, (Object)cursor.getDouble(n));
                return;
            }
            if (class_.equals((Object)Boolean.TYPE) || class_.equals(Boolean.class)) {
                field.set(object, (Object)cursor.getString(n).equals((Object)"1"));
                return;
            }
            if (class_.equals((Object)Integer.TYPE) || class_.equals(Integer.class)) {
                field.set(object, (Object)cursor.getInt(n));
                return;
            }
            if (class_.equals((Object)Float.TYPE) || class_.equals(Float.class)) {
                field.set(object, (Object)Float.valueOf((float)cursor.getFloat(n)));
                return;
            }
            if (class_.equals((Object)Short.TYPE) || class_.equals(Short.class)) {
                field.set(object, (Object)cursor.getShort(n));
                return;
            }
            if (class_.equals(BigDecimal.class)) {
                String string4 = cursor.getString(n);
                Object object2 = string4 != null && string4.equals((Object)"null") ? null : new BigDecimal(string4);
                field.set(object, object2);
                return;
            }
            if (class_.equals(Timestamp.class)) {
                long l = cursor.getLong(n);
                Timestamp timestamp = new Timestamp(l);
                field.set(object, (Object)timestamp);
                return;
            }
            if (class_.equals(Date.class)) {
                long l = cursor.getLong(n);
                Date date = new Date(l);
                field.set(object, (Object)date);
                return;
            }
            if (class_.equals(Calendar.class)) {
                long l = cursor.getLong(n);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(l);
                field.set(object, (Object)calendar);
                return;
            }
            if (class_.equals(byte[].class)) {
                if (cursor.getBlob(n) == null) {
                    field.set(object, (Object)"".getBytes());
                    return;
                }
                field.set(object, (Object)cursor.getBlob(n));
                return;
            }
            boolean bl = Enum.class.isAssignableFrom(class_);
            if (bl) {
                try {
                    Method method = field.getType().getMethod("valueOf", new Class[]{String.class});
                    String string5 = cursor.getString(n);
                    field.set(object, method.invoke((Object)field.getType(), new Object[]{string5}));
                    return;
                }
                catch (Exception exception) {
                    Log.e((String)"Sugar", (String)("Enum cannot be read from Sqlite3 database. Please check the type of field " + field.getName()));
                    return;
                }
            }
            Log.e((String)"Sugar", (String)("Class cannot be read from Sqlite3 database. Please check the type of field " + field.getName() + "(" + field.getType().getName() + ")"));
        }
    }
}

