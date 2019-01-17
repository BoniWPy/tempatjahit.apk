/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.Context
 *  android.support.multidex.MultiDex
 *  com.orm.SugarContext
 *  com.zendesk.belvedere.BelvedereFileProvider
 *  com.zopim.android.sdk.api.ZopimChat
 *  com.zopim.android.sdk.api.ZopimChat$DefaultConfig
 *  com.zopim.android.sdk.prechat.EmailTranscript
 *  com.zopim.android.sdk.prechat.PreChatForm
 *  com.zopim.android.sdk.prechat.PreChatForm$Builder
 *  com.zopim.android.sdk.prechat.PreChatForm$Field
 *  java.lang.Class
 *  java.lang.String
 */
package com.evlop;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.orm.SugarContext;
import com.zendesk.belvedere.BelvedereFileProvider;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.prechat.EmailTranscript;
import com.zopim.android.sdk.prechat.PreChatForm;

public class App
extends Application {
    public static Class<BelvedereFileProvider> clazz = BelvedereFileProvider.class;

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install((Context)this);
    }

    public void onCreate() {
        super.onCreate();
        SugarContext.init((Context)this);
        PreChatForm preChatForm = new PreChatForm.Builder().name(PreChatForm.Field.REQUIRED).email(PreChatForm.Field.REQUIRED).phoneNumber(PreChatForm.Field.REQUIRED).build();
        ZopimChat.init((String)"4P3uAuzl7OP7V7zZ9rQkHch4NhYqKhMq").preChatForm(preChatForm).emailTranscript(EmailTranscript.DISABLED);
    }

    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}

