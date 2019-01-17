/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  com.zopim.android.sdk.api.Chat
 *  java.lang.Object
 */
package com.zopim.android.sdk.prechat;

import com.zopim.android.sdk.api.Chat;

public interface ChatListener {
    public void onChatEnded();

    public void onChatInitialized();

    public void onChatLoaded(Chat var1);
}

