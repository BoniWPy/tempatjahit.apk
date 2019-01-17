/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.os.Build
 *  android.os.Build$VERSION
 *  dalvik.system.DexFile
 *  java.io.File
 *  java.io.IOException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Enumeration
 *  java.util.Iterator
 *  java.util.List
 */
package com.orm.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class MultiDexHelper {
    private static final String EXTRACTED_NAME_EXT = ".classes";
    private static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String PREFS_FILE = "multidex.version";
    private static final String SECONDARY_FOLDER_NAME = "code_cache" + File.separator + "secondary-dexes";

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static List<String> getAllClasses(Context context) throws PackageManager.NameNotFoundException, IOException {
        String string2;
        ArrayList arrayList = new ArrayList();
        Iterator iterator = MultiDexHelper.getSourcePaths(context).iterator();
        block3 : do {
            if (!iterator.hasNext()) return arrayList;
            string2 = (String)iterator.next();
            DexFile dexFile = string2.endsWith(EXTRACTED_SUFFIX) ? DexFile.loadDex((String)string2, (String)(string2 + ".tmp"), (int)0) : new DexFile(string2);
            Enumeration enumeration = dexFile.entries();
            do {
                if (!enumeration.hasMoreElements()) continue block3;
                arrayList.add(enumeration.nextElement());
            } while (true);
            break;
        } while (true);
        catch (IOException iOException) {
            throw new IOException("Error at loading dex file '" + string2 + "'");
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static SharedPreferences getMultiDexPreferences(Context context) {
        int n;
        if (Build.VERSION.SDK_INT < 11) {
            n = 0;
            do {
                return context.getSharedPreferences(PREFS_FILE, n);
                break;
            } while (true);
        }
        n = 4;
        return context.getSharedPreferences(PREFS_FILE, n);
    }

    public static List<String> getSourcePaths(Context context) throws PackageManager.NameNotFoundException, IOException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        File file2 = new File(applicationInfo.dataDir, SECONDARY_FOLDER_NAME);
        ArrayList arrayList = new ArrayList();
        arrayList.add((Object)applicationInfo.sourceDir);
        String string2 = file.getName() + EXTRACTED_NAME_EXT;
        int n = MultiDexHelper.getMultiDexPreferences(context).getInt(KEY_DEX_NUMBER, 1);
        for (int i = 2; i <= n; ++i) {
            File file3 = new File(file2, string2 + i + EXTRACTED_SUFFIX);
            if (!file3.isFile()) continue;
            arrayList.add((Object)file3.getAbsolutePath());
        }
        return arrayList;
    }
}

