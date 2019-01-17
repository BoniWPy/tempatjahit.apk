/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  com.zendesk.util.StringUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collection
 *  java.util.List
 */
package com.zopim.android.sdk.prechat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.zendesk.util.StringUtils;
import com.zopim.android.sdk.model.Department;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class DepartmentUtil {
    DepartmentUtil() {
    }

    @NonNull
    static Department findDepartment(@NonNull Collection<Department> collection, @Nullable String string2) {
        Department department = new Department();
        for (Department department2 : collection) {
            if (StringUtils.isEmpty((String)department2.getName()) || !department2.getName().equals((Object)string2)) continue;
            department = department2;
            break;
        }
        return department;
    }

    @NonNull
    static List<String> findOnlineDepartments(@NonNull Collection<Department> collection) {
        ArrayList arrayList = new ArrayList();
        for (Department department : collection) {
            if (!Department.Status.ONLINE.equals((Object)department.getStatus())) continue;
            arrayList.add((Object)department.getName());
        }
        return arrayList;
    }
}

