/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.os.Bundle
 *  android.util.Log
 *  java.lang.Boolean
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.orm.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class ManifestHelper {
    public static final String DATABASE_DEFAULT_NAME = "Sugar.db";
    public static final String METADATA_DATABASE = "DATABASE";
    public static final String METADATA_DOMAIN_PACKAGE_NAME = "DOMAIN_PACKAGE_NAME";
    public static final String METADATA_QUERY_LOG = "QUERY_LOG";
    public static final String METADATA_VERSION = "VERSION";

    public static String getDatabaseName(Context context) {
        String string2 = ManifestHelper.getMetaDataString(context, METADATA_DATABASE);
        if (string2 == null) {
            string2 = DATABASE_DEFAULT_NAME;
        }
        return string2;
    }

    public static int getDatabaseVersion(Context context) {
        Integer n = ManifestHelper.getMetaDataInteger(context, METADATA_VERSION);
        if (n == null || n == 0) {
            n = 1;
        }
        return n;
    }

    public static boolean getDebugEnabled(Context context) {
        return ManifestHelper.getMetaDataBoolean(context, METADATA_QUERY_LOG);
    }

    public static String getDomainPackageName(Context context) {
        String string2 = ManifestHelper.getMetaDataString(context, METADATA_DOMAIN_PACKAGE_NAME);
        if (string2 == null) {
            string2 = "";
        }
        return string2;
    }

    private static Boolean getMetaDataBoolean(Context context, String string2) {
        Boolean bl = false;
        PackageManager packageManager = context.getPackageManager();
        try {
            Boolean bl2 = packageManager.getApplicationInfo((String)context.getPackageName(), (int)128).metaData.getBoolean(string2);
            return bl2;
        }
        catch (Exception exception) {
            Log.d((String)"sugar", (String)("Couldn't find config value: " + string2));
            return bl;
        }
    }

    private static Integer getMetaDataInteger(Context context, String string2) {
        PackageManager packageManager = context.getPackageManager();
        try {
            Integer n = packageManager.getApplicationInfo((String)context.getPackageName(), (int)128).metaData.getInt(string2);
            return n;
        }
        catch (Exception exception) {
            Log.d((String)"sugar", (String)("Couldn't find config value: " + string2));
            return null;
        }
    }

    private static String getMetaDataString(Context context, String string2) {
        PackageManager packageManager = context.getPackageManager();
        try {
            String string3 = packageManager.getApplicationInfo((String)context.getPackageName(), (int)128).metaData.getString(string2);
            return string3;
        }
        catch (Exception exception) {
            Log.d((String)"sugar", (String)("Couldn't find config value: " + string2));
            return null;
        }
    }
}

