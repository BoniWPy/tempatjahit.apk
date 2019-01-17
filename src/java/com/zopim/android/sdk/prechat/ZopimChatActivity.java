/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.support.v4.app.Fragment
 *  android.support.v4.app.FragmentActivity
 *  android.support.v4.app.FragmentManager
 *  android.support.v4.app.FragmentTransaction
 *  android.support.v7.app.ActionBar
 *  android.support.v7.app.AppCompatActivity
 *  android.support.v7.widget.Toolbar
 *  android.view.MenuItem
 *  android.view.View
 *  com.zendesk.logger.Logger
 *  com.zopim.android.sdk.R
 *  com.zopim.android.sdk.R$id
 *  com.zopim.android.sdk.R$layout
 *  com.zopim.android.sdk.api.Chat
 *  com.zopim.android.sdk.api.ZopimChat
 *  com.zopim.android.sdk.api.ZopimChat$SessionConfig
 *  com.zopim.android.sdk.chatlog.ZopimChatLogFragment
 *  com.zopim.android.sdk.prechat.ZopimChatFragment
 *  com.zopim.android.sdk.widget.ChatWidgetService
 *  java.io.Serializable
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.prechat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.zendesk.logger.Logger;
import com.zopim.android.sdk.R;
import com.zopim.android.sdk.api.Chat;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.chatlog.ZopimChatLogFragment;
import com.zopim.android.sdk.prechat.ChatListener;
import com.zopim.android.sdk.prechat.ZopimChatFragment;
import com.zopim.android.sdk.widget.ChatWidgetService;
import java.io.Serializable;

public class ZopimChatActivity
extends AppCompatActivity
implements ChatListener {
    private static final String EXTRA_CHAT_CONFIG = "CHAT_CONFIG";
    private static final String LOG_TAG = "ZopimChatActivity";
    private static final String STATE_CHAT_INITIALIZED = "CHAT_INITIALIZED";
    private Chat mChat;
    private boolean mChatInitialized = false;

    public static void startActivity(Context context, ZopimChat.SessionConfig sessionConfig) {
        Intent intent = new Intent(context, ZopimChatActivity.class);
        intent.putExtra(EXTRA_CHAT_CONFIG, (Serializable)sessionConfig);
        context.startActivity(intent);
    }

    @Override
    public void onChatEnded() {
        this.finish();
    }

    @Override
    public void onChatInitialized() {
        this.mChatInitialized = true;
    }

    @Override
    public void onChatLoaded(Chat chat) {
        this.mChat = chat;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        FragmentManager fragmentManager;
        ZopimChatFragment zopimChatFragment;
        boolean bl = true;
        super.onCreate(bundle);
        this.setContentView(R.layout.zopim_chat_activity);
        this.setSupportActionBar((Toolbar)this.findViewById(R.id.toolbar));
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(bl);
        if (bundle != null) {
            this.mChatInitialized = bundle.getBoolean(STATE_CHAT_INITIALIZED, false);
            this.mChat = ZopimChat.resume((FragmentActivity)this);
            return;
        } else {
            if (this.getIntent() != null && "zopim.action.RESUME_CHAT".equals((Object)this.getIntent().getAction())) {
                Logger.v((String)LOG_TAG, (String)"Resume chat request received", (Object[])new Object[0]);
            }
            this.mChat = ZopimChat.resume((FragmentActivity)this);
            if (this.mChat.hasEnded()) {
                bl = false;
            }
            this.mChatInitialized = bl;
            if (!this.mChat.hasEnded()) {
                Logger.v((String)LOG_TAG, (String)"Resuming chat log", (Object[])new Object[0]);
                FragmentManager fragmentManager2 = this.getSupportFragmentManager();
                if (fragmentManager2.findFragmentByTag(ZopimChatLogFragment.class.getName()) != null) return;
                {
                    ZopimChatLogFragment zopimChatLogFragment = new ZopimChatLogFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();
                    fragmentTransaction.add(R.id.chat_fragment_container, (Fragment)zopimChatLogFragment, ZopimChatLogFragment.class.getName());
                    fragmentTransaction.commit();
                    return;
                }
            } else {
                Logger.v((String)LOG_TAG, (String)"Starting new chat", (Object[])new Object[0]);
                fragmentManager = this.getSupportFragmentManager();
                if (fragmentManager.findFragmentByTag(ZopimChatFragment.class.getName()) != null) return;
                {
                    Intent intent = this.getIntent();
                    ZopimChat.SessionConfig sessionConfig = null;
                    if (intent != null) {
                        boolean bl2 = this.getIntent().hasExtra(EXTRA_CHAT_CONFIG);
                        sessionConfig = null;
                        if (bl2) {
                            sessionConfig = (ZopimChat.SessionConfig)this.getIntent().getSerializableExtra(EXTRA_CHAT_CONFIG);
                        }
                    }
                    zopimChatFragment = sessionConfig != null ? ZopimChatFragment.newInstance(sessionConfig) : new ZopimChatFragment();
                }
            }
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.chat_fragment_container, (Fragment)zopimChatFragment, ZopimChatFragment.class.getName());
        fragmentTransaction.commit();
    }

    protected void onDestroy() {
        Logger.v((String)LOG_TAG, (String)"Activity destroyed", (Object[])new Object[0]);
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (16908332 == menuItem.getItemId()) {
            this.finish();
            return super.onOptionsItemSelected(menuItem);
        }
        return false;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(STATE_CHAT_INITIALIZED, this.mChatInitialized);
    }

    protected void onStart() {
        super.onStart();
        this.stopService(new Intent((Context)this, ChatWidgetService.class));
    }
}

