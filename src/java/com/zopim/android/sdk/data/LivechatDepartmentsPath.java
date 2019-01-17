/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.google.gson.reflect.TypeToken
 *  com.zendesk.logger.Logger
 *  com.zopim.android.sdk.data.ChatGson
 *  com.zopim.android.sdk.data.Path
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Type
 *  java.util.Iterator
 *  java.util.LinkedHashMap
 *  java.util.Map
 *  java.util.Map$Entry
 *  java.util.Set
 */
package com.zopim.android.sdk.data;

import com.google.gson.reflect.TypeToken;
import com.zendesk.logger.Logger;
import com.zopim.android.sdk.data.ChatGson;
import com.zopim.android.sdk.data.Path;
import com.zopim.android.sdk.model.Department;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LivechatDepartmentsPath
extends Path<LinkedHashMap<String, Department>> {
    private static final LivechatDepartmentsPath INSTANCE = new LivechatDepartmentsPath();
    private static final String LOG_TAG = "LivechatDepartmentsPath";
    private final Object lock = new Object();

    private LivechatDepartmentsPath() {
        this.data = new LinkedHashMap();
    }

    public static LivechatDepartmentsPath getInstance() {
        return INSTANCE;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void updateInternal(LinkedHashMap<String, Department> linkedHashMap) {
        Object object;
        if (linkedHashMap == null) {
            Logger.i((String)LOG_TAG, (String)"Passed parameter must not be null. Aborting update.", (Object[])new Object[0]);
            return;
        }
        Object object2 = object = this.lock;
        synchronized (object2) {
            Iterator iterator = linkedHashMap.entrySet().iterator();
            do {
                if (!iterator.hasNext()) {
                    this.broadcast(this.getData());
                    return;
                }
                Map.Entry entry = (Map.Entry)iterator.next();
                String string = (String)entry.getKey();
                Department department = (Department)entry.getValue();
                if (((LinkedHashMap)this.data).containsKey((Object)string)) {
                    if (department == null) {
                        ((LinkedHashMap)this.data).remove((Object)string);
                        continue;
                    }
                    Department department2 = (Department)((LinkedHashMap)this.data).get((Object)string);
                    if (department2 == null) {
                        ((LinkedHashMap)this.data).remove((Object)string);
                        continue;
                    }
                    Department department3 = (Department)ChatGson.performUpdate((Object)department2, (Object)department, Department.class);
                    ((LinkedHashMap)this.data).put((Object)string, (Object)department3);
                    continue;
                }
                if (department == null) continue;
                ((LinkedHashMap)this.data).put((Object)string, (Object)department);
            } while (true);
        }
    }

    void clear() {
        if (this.data != null) {
            ((LinkedHashMap)this.data).clear();
        }
    }

    public LinkedHashMap<String, Department> getData() {
        if (this.data != null) {
            return new LinkedHashMap((Map)this.data);
        }
        return new LinkedHashMap();
    }

    /*
     * Enabled aggressive block sorting
     */
    void update(String string) {
        if (this.isClearRequired(string)) {
            this.clear();
            return;
        } else {
            if (string == null || string.isEmpty()) return;
            {
                Type type = new TypeToken<LinkedHashMap<String, Department>>(){}.getType();
                this.updateInternal((LinkedHashMap<String, Department>)((LinkedHashMap)ChatGson.get().fromJson(string, type)));
                return;
            }
        }
    }

}

