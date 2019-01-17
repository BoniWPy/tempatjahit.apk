/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.annotation.NonNull
 *  android.support.annotation.StringRes
 *  com.zendesk.logger.Logger
 *  com.zopim.android.sdk.api.R
 *  com.zopim.android.sdk.api.R$string
 *  com.zopim.android.sdk.api.ZopimChatApi
 *  com.zopim.android.sdk.model.items.AgentAttachment
 *  com.zopim.android.sdk.model.items.AgentItem
 *  com.zopim.android.sdk.model.items.AgentMessage
 *  com.zopim.android.sdk.model.items.AgentOptions
 *  com.zopim.android.sdk.model.items.AgentTyping
 *  com.zopim.android.sdk.model.items.RowItem
 *  com.zopim.android.sdk.model.items.VisitorAttachment
 *  com.zopim.android.sdk.model.items.VisitorItem
 *  com.zopim.android.sdk.model.items.VisitorMessage
 *  java.io.File
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.NoSuchFieldError
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.net.URL
 *  java.util.Iterator
 *  java.util.LinkedHashMap
 *  java.util.Map
 *  java.util.Map$Entry
 *  java.util.Set
 *  java.util.TreeMap
 */
package com.zopim.android.sdk.data.observers;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import com.zendesk.logger.Logger;
import com.zopim.android.sdk.api.R;
import com.zopim.android.sdk.api.ZopimChatApi;
import com.zopim.android.sdk.model.Agent;
import com.zopim.android.sdk.model.Attachment;
import com.zopim.android.sdk.model.ChatLog;
import com.zopim.android.sdk.model.items.AgentAttachment;
import com.zopim.android.sdk.model.items.AgentItem;
import com.zopim.android.sdk.model.items.AgentMessage;
import com.zopim.android.sdk.model.items.AgentOptions;
import com.zopim.android.sdk.model.items.AgentTyping;
import com.zopim.android.sdk.model.items.ChatEvent;
import com.zopim.android.sdk.model.items.ChatMemberEvent;
import com.zopim.android.sdk.model.items.ChatRating;
import com.zopim.android.sdk.model.items.RowItem;
import com.zopim.android.sdk.model.items.VisitorAttachment;
import com.zopim.android.sdk.model.items.VisitorItem;
import com.zopim.android.sdk.model.items.VisitorMessage;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

final class ViewModelFactory {
    private static final String LOG_TAG = "ViewModelFactory";
    private final Context context;

    ViewModelFactory(@NonNull Context context) {
        this.context = context;
    }

    private ChatEvent createAccountOfflineEvent(@NonNull String string2, @NonNull ChatLog chatLog) {
        ChatEvent chatEvent = new ChatEvent();
        chatEvent.setMessage(String.format((String)this.getStringResource(R.string.chat_account_offline_message), (Object[])new Object[0]));
        this.updateRowItem(chatEvent, string2, chatLog);
        return chatEvent;
    }

    /*
     * Enabled aggressive block sorting
     */
    private AgentAttachment createAgentAttachment(@NonNull String string2, @NonNull ChatLog chatLog) {
        AgentAttachment agentAttachment = new AgentAttachment();
        URL uRL = chatLog.getAttachment() != null ? chatLog.getAttachment().getUrl() : null;
        agentAttachment.setAttachmentUrl(uRL);
        String string3 = chatLog.getAttachment() != null ? chatLog.getAttachment().getName() : null;
        agentAttachment.setAttachmentName(string3);
        agentAttachment.setAttachmentFile(chatLog.getFile());
        Attachment attachment = chatLog.getAttachment();
        Long l = null;
        if (attachment != null) {
            l = chatLog.getAttachment().getSize();
        }
        agentAttachment.setAttachmentSize(l);
        this.updateRowItem((RowItem)agentAttachment, string2, chatLog);
        this.updateAgentItem((AgentItem)agentAttachment);
        return agentAttachment;
    }

    private AgentMessage createAgentMessage(@NonNull String string2, @NonNull ChatLog chatLog) {
        AgentMessage agentMessage = new AgentMessage();
        agentMessage.setMessage(chatLog.getMessage());
        this.updateRowItem((RowItem)agentMessage, string2, chatLog);
        this.updateAgentItem((AgentItem)agentMessage);
        return agentMessage;
    }

    private AgentOptions createAgentOptions(@NonNull String string2, @NonNull ChatLog chatLog) {
        AgentOptions agentOptions = new AgentOptions();
        agentOptions.setMessage(chatLog.getMessage());
        agentOptions.setOptions(new String[chatLog.getOptions().length]);
        int n = 0;
        do {
            block4 : {
                block3 : {
                    if (n >= chatLog.getOptions().length) break block3;
                    ChatLog.Option option = chatLog.getOptions()[n];
                    agentOptions.getOptions()[n] = option.getLabel();
                    if (!option.isSelected()) break block4;
                    String[] arrstring = new String[]{option.getLabel()};
                    agentOptions.setOptions(arrstring);
                }
                this.updateRowItem((RowItem)agentOptions, string2, chatLog);
                this.updateAgentItem((AgentItem)agentOptions);
                return agentOptions;
            }
            ++n;
        } while (true);
    }

    private ChatMemberEvent createJoinEvent(@NonNull String string2, @NonNull ChatLog chatLog) {
        ChatMemberEvent chatMemberEvent = new ChatMemberEvent();
        String string3 = this.getStringResource(R.string.chat_agent_joined_message);
        Object[] arrobject = new Object[]{chatLog.getDisplayName()};
        chatMemberEvent.setMessage(String.format((String)string3, (Object[])arrobject));
        this.updateRowItem(chatMemberEvent, string2, chatLog);
        return chatMemberEvent;
    }

    private ChatMemberEvent createLeaveEvent(@NonNull String string2, @NonNull ChatLog chatLog) {
        ChatMemberEvent chatMemberEvent = new ChatMemberEvent();
        String string3 = this.getStringResource(R.string.chat_agent_left_message);
        Object[] arrobject = new Object[]{chatLog.getDisplayName()};
        chatMemberEvent.setMessage(String.format((String)string3, (Object[])arrobject));
        this.updateRowItem(chatMemberEvent, string2, chatLog);
        return chatMemberEvent;
    }

    private ChatRating createRatingEvent(@NonNull String string2, @NonNull ChatLog chatLog) {
        ChatRating chatRating = new ChatRating();
        chatRating.setRating(chatLog.getRating());
        chatRating.setComment(chatLog.getComment());
        this.updateRowItem(chatRating, string2, chatLog);
        return chatRating;
    }

    private ChatEvent createSystemEvent(@NonNull String string2, @NonNull ChatLog chatLog) {
        ChatEvent chatEvent = new ChatEvent();
        String string3 = this.getStringResource(R.string.chat_visitor_queue_message);
        Object[] arrobject = new Object[]{chatLog.getVisitorQueue()};
        chatEvent.setMessage(String.format((String)string3, (Object[])arrobject));
        this.updateRowItem(chatEvent, string2, chatLog);
        return chatEvent;
    }

    private ChatEvent createTriggerEvent(@NonNull String string2, @NonNull ChatLog chatLog) {
        ChatEvent chatEvent = new ChatEvent();
        chatEvent.setMessage(chatLog.getMessage());
        this.updateRowItem(chatEvent, string2, chatLog);
        return chatEvent;
    }

    /*
     * Enabled aggressive block sorting
     */
    private VisitorAttachment createVisitorAttachment(@NonNull String string2, @NonNull ChatLog chatLog) {
        VisitorAttachment visitorAttachment = new VisitorAttachment();
        visitorAttachment.setUploadUrl(chatLog.getUploadUrl());
        visitorAttachment.setFile(chatLog.getFile());
        visitorAttachment.setUploadProgress(chatLog.getProgress());
        switch (chatLog.getError()) {
            case UPLOAD_SIZE_ERROR: {
                visitorAttachment.setError(this.getStringResource(R.string.attachment_upload_size_limit_error_message));
            }
            default: {
                break;
            }
            case UPLOAD_FILE_EXTENSION_ERROR: {
                visitorAttachment.setError(this.getStringResource(R.string.attachment_upload_type_error_message));
            }
        }
        this.updateVisitorItem((VisitorItem)visitorAttachment, chatLog);
        this.updateRowItem((RowItem)visitorAttachment, string2, chatLog);
        return visitorAttachment;
    }

    private VisitorMessage createVisitorMessage(@NonNull String string2, @NonNull ChatLog chatLog) {
        VisitorMessage visitorMessage = new VisitorMessage();
        visitorMessage.setMessage(chatLog.getMessage());
        this.updateVisitorItem((VisitorItem)visitorMessage, chatLog);
        this.updateRowItem((RowItem)visitorMessage, string2, chatLog);
        return visitorMessage;
    }

    private String getStringResource(@StringRes int n) {
        if (this.context != null) {
            return this.context.getResources().getString(n);
        }
        return "";
    }

    private void updateAgentItem(@NonNull AgentItem agentItem) {
        Agent agent = (Agent)ZopimChatApi.getDataSource().getAgents().get((Object)agentItem.getParticipantId());
        if (agent != null) {
            agentItem.setAvatarUri(agent.getAvatarUri());
        }
    }

    private void updateRowItem(@NonNull RowItem rowItem, @NonNull String string2, @NonNull ChatLog chatLog) {
        rowItem.setId(string2);
        rowItem.setDisplayName(chatLog.getDisplayName());
        rowItem.setParticipantId(chatLog.getNick());
        rowItem.setTimestamp(chatLog.getTimestamp());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateVisitorItem(@NonNull VisitorItem visitorItem, @NonNull ChatLog chatLog) {
        boolean bl = chatLog.isUnverified() != null ? chatLog.isUnverified() : true;
        visitorItem.setUnverified(bl);
        boolean bl2 = chatLog.isFailed() != null ? chatLog.isFailed() : false;
        visitorItem.setFailed(bl2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @NonNull
    final TreeMap<String, RowItem> createItems(@NonNull LinkedHashMap<String, ChatLog> linkedHashMap) {
        if (linkedHashMap == null) {
            return new TreeMap();
        }
        boolean bl = true;
        TreeMap treeMap = new TreeMap();
        Iterator iterator = linkedHashMap.entrySet().iterator();
        block11 : while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            String string2 = (String)entry.getKey();
            ChatLog chatLog = (ChatLog)entry.getValue();
            switch (1.$SwitchMap$com$zopim$android$sdk$model$ChatLog$Type[chatLog.getType().ordinal()]) {
                default: {
                    Object[] arrobject = new Object[]{chatLog.getType()};
                    Logger.v((String)LOG_TAG, (String)"Skipping build of an unknown item: ", (Object[])arrobject);
                    continue block11;
                }
                case 1: {
                    VisitorMessage visitorMessage = this.createVisitorMessage(string2, chatLog);
                    treeMap.put((Object)visitorMessage.getId(), (Object)visitorMessage);
                    continue block11;
                }
                case 2: {
                    VisitorAttachment visitorAttachment = this.createVisitorAttachment(string2, chatLog);
                    treeMap.put((Object)visitorAttachment.getId(), (Object)visitorAttachment);
                    continue block11;
                }
                case 3: {
                    boolean bl2 = chatLog.getAttachment() != null;
                    boolean bl3 = chatLog.getOptions().length > 0;
                    if (bl2) {
                        AgentAttachment agentAttachment = this.createAgentAttachment(string2, chatLog);
                        treeMap.put((Object)agentAttachment.getId(), (Object)agentAttachment);
                        continue block11;
                    }
                    if (bl3) {
                        AgentOptions agentOptions = this.createAgentOptions(string2, chatLog);
                        treeMap.put((Object)agentOptions.getId(), (Object)agentOptions);
                        continue block11;
                    }
                    AgentMessage agentMessage = this.createAgentMessage(string2, chatLog);
                    treeMap.put((Object)agentMessage.getId(), (Object)agentMessage);
                    continue block11;
                }
                case 4: {
                    ChatEvent chatEvent = this.createSystemEvent(string2, chatLog);
                    treeMap.put((Object)chatEvent.getId(), (Object)chatEvent);
                    continue block11;
                }
                case 5: {
                    ChatEvent chatEvent = this.createAccountOfflineEvent(string2, chatLog);
                    treeMap.put((Object)chatEvent.getId(), (Object)chatEvent);
                    continue block11;
                }
                case 6: {
                    ChatEvent chatEvent = this.createTriggerEvent(string2, chatLog);
                    treeMap.put((Object)chatEvent.getId(), (Object)chatEvent);
                    continue block11;
                }
                case 7: {
                    ChatMemberEvent chatMemberEvent = this.createJoinEvent(string2, chatLog);
                    Iterator iterator2 = ZopimChatApi.getDataSource().getAgents().keySet().iterator();
                    do {
                        if (!iterator2.hasNext()) continue block11;
                        if (!((String)iterator2.next()).equals((Object)chatMemberEvent.getParticipantId())) continue;
                        if (!bl) break;
                        bl = false;
                    } while (true);
                    treeMap.put((Object)chatMemberEvent.getId(), (Object)chatMemberEvent);
                    continue block11;
                }
                case 8: {
                    ChatMemberEvent chatMemberEvent = this.createLeaveEvent(string2, chatLog);
                    Iterator iterator3 = ZopimChatApi.getDataSource().getAgents().keySet().iterator();
                    do {
                        if (!iterator3.hasNext()) continue block11;
                    } while (!((String)iterator3.next()).equals((Object)chatMemberEvent.getParticipantId()));
                    treeMap.put((Object)chatMemberEvent.getId(), (Object)chatMemberEvent);
                    continue block11;
                }
                case 9: 
            }
            ChatRating chatRating = this.createRatingEvent(string2, chatLog);
            treeMap.put((Object)chatRating.getId(), (Object)chatRating);
        }
        return treeMap;
    }

    /*
     * Enabled aggressive block sorting
     */
    @NonNull
    final TreeMap<String, AgentTyping> createItems(@NonNull Map<String, Agent> map) {
        if (map == null) {
            return new TreeMap();
        }
        TreeMap treeMap = new TreeMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            String string2 = (String)entry.getKey();
            Agent agent = (Agent)entry.getValue();
            boolean bl = agent.isTyping() != null;
            if (!bl) continue;
            AgentTyping agentTyping = new AgentTyping();
            agentTyping.setParticipantId(string2);
            agentTyping.setTimestamp(Long.valueOf((long)System.currentTimeMillis()));
            agentTyping.setTyping(agent.isTyping().booleanValue());
            agentTyping.setAvatarUri(agent.getAvatarUri());
            agentTyping.setDisplayName(agent.getDisplayName());
            treeMap.put((Object)agentTyping.getId(), (Object)agentTyping);
        }
        return treeMap;
    }

}

