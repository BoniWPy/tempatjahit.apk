/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.zendesk.logger.Logger
 *  java.lang.CharSequence
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.data;

import com.zendesk.logger.Logger;

final class PathName
extends Enum<PathName> {
    private static final /* synthetic */ PathName[] $VALUES;
    public static final /* enum */ PathName CONNECTION;
    public static final /* enum */ PathName LIVECHAT_ACCOUNT;
    public static final /* enum */ PathName LIVECHAT_AGENTS;
    public static final /* enum */ PathName LIVECHAT_CHANNEL_LOG;
    public static final /* enum */ PathName LIVECHAT_DEPARTMENTS;
    public static final /* enum */ PathName LIVECHAT_PROFILE;
    public static final /* enum */ PathName LIVECHAT_SETTINGS_FILE_SENDING;
    public static final /* enum */ PathName LIVECHAT_SETTINGS_FORMS;
    public static final /* enum */ PathName LIVECHAT_UI;
    private static final String LOG_TAG = "PathName";
    public static final /* enum */ PathName UNKNOWN;
    private final String value;

    static {
        LIVECHAT_CHANNEL_LOG = new PathName("livechat.channel.log");
        LIVECHAT_PROFILE = new PathName("livechat.profile");
        LIVECHAT_AGENTS = new PathName("livechat.agents");
        LIVECHAT_UI = new PathName("livechat.ui");
        LIVECHAT_DEPARTMENTS = new PathName("livechat.departments");
        LIVECHAT_ACCOUNT = new PathName("livechat.account");
        LIVECHAT_SETTINGS_FORMS = new PathName("livechat.settings.forms");
        LIVECHAT_SETTINGS_FILE_SENDING = new PathName("livechat.settings.file_sending");
        CONNECTION = new PathName("connection");
        UNKNOWN = new PathName("unknown");
        PathName[] arrpathName = new PathName[]{LIVECHAT_CHANNEL_LOG, LIVECHAT_PROFILE, LIVECHAT_AGENTS, LIVECHAT_UI, LIVECHAT_DEPARTMENTS, LIVECHAT_ACCOUNT, LIVECHAT_SETTINGS_FORMS, LIVECHAT_SETTINGS_FILE_SENDING, CONNECTION, UNKNOWN};
        $VALUES = arrpathName;
    }

    private PathName(String string2) {
        this.value = string2;
    }

    static PathName get(String string) {
        for (PathName pathName : PathName.values()) {
            if (!pathName.value.contentEquals((CharSequence)string)) continue;
            return pathName;
        }
        Logger.i((String)LOG_TAG, (String)("Unknown protocol path, will return " + (Object)((Object)UNKNOWN) + ": " + string), (Object[])new Object[0]);
        return UNKNOWN;
    }

    public static PathName valueOf(String string) {
        return (PathName)Enum.valueOf(PathName.class, (String)string);
    }

    public static PathName[] values() {
        return (PathName[])$VALUES.clone();
    }
}

