/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.Pair
 *  com.zendesk.logger.Logger
 *  com.zopim.android.sdk.api.FileTransfers
 *  com.zopim.android.sdk.data.ChatGson
 *  com.zopim.android.sdk.data.LivechatAccountPath
 *  com.zopim.android.sdk.data.LivechatChatLogPath$1
 *  com.zopim.android.sdk.data.LivechatChatLogPath$2
 *  com.zopim.android.sdk.data.LivechatChatLogPath$SendTimeout
 *  com.zopim.android.sdk.data.Path
 *  com.zopim.android.sdk.data.observers.AccountObserver
 *  java.io.File
 *  java.lang.Boolean
 *  java.lang.Exception
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Type
 *  java.util.Collection
 *  java.util.HashMap
 *  java.util.Iterator
 *  java.util.LinkedHashMap
 *  java.util.LinkedList
 *  java.util.Map
 *  java.util.Map$Entry
 *  java.util.Observer
 *  java.util.Set
 */
package com.zopim.android.sdk.data;

import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import com.zendesk.logger.Logger;
import com.zopim.android.sdk.api.FileTransfers;
import com.zopim.android.sdk.data.ChatGson;
import com.zopim.android.sdk.data.LivechatAccountPath;
import com.zopim.android.sdk.data.LivechatChatLogPath;
import com.zopim.android.sdk.data.Path;
import com.zopim.android.sdk.data.observers.AccountObserver;
import com.zopim.android.sdk.model.Attachment;
import com.zopim.android.sdk.model.ChatLog;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

public class LivechatChatLogPath
extends Path<LinkedHashMap<String, ChatLog>> {
    private static final LivechatChatLogPath INSTANCE = new LivechatChatLogPath();
    private static final String LOG_TAG = "LivechatChatLogPath";
    AccountObserver accountObserver = new 2(this);
    Pair<String, ChatLog> chatRatingEntry;
    private final Object lock = new Object();
    private TimeoutManager timeoutManager = new TimeoutManager(this);
    private LinkedList<Pair<String, ChatLog>> unmatchedAgentQuestionnaire = new LinkedList();
    Map<String, String> uploadedFiles = new HashMap();

    private LivechatChatLogPath() {
        this.data = new LinkedHashMap();
        LivechatAccountPath.getInstance().addObserver((Observer)this.accountObserver);
    }

    static /* synthetic */ Object access$000(LivechatChatLogPath livechatChatLogPath) {
        return livechatChatLogPath.lock;
    }

    static /* synthetic */ LivechatChatLogPath access$200() {
        return INSTANCE;
    }

    static /* synthetic */ void access$300(LivechatChatLogPath livechatChatLogPath, LinkedHashMap linkedHashMap) {
        livechatChatLogPath.updateInternal((LinkedHashMap<String, ChatLog>)linkedHashMap);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private Pair<String, ChatLog> findAgentQuestionnaire(ChatLog var1_1) {
        if (var1_1 == null) {
            Logger.w((String)"LivechatChatLogPath", (String)"RowItem must not be null", (Object[])new Object[0]);
            return null;
        }
        if (var1_1.getType() != ChatLog.Type.CHAT_MSG_VISITOR) {
            return null;
        }
        if (var1_1.getMessage() == null) return null;
        if (var1_1.getMessage().isEmpty()) {
            return null;
        }
        var2_2 = this.unmatchedAgentQuestionnaire.iterator();
        block0 : do lbl-1000: // 3 sources:
        {
            if (var2_2.hasNext() == false) return null;
            var3_3 = (Pair)var2_2.next();
            var4_4 = (ChatLog)var3_3.second;
            if (var4_4.getType() != ChatLog.Type.CHAT_MSG_AGENT) ** GOTO lbl-1000
            var5_5 = var4_4.getOptions();
            var6_6 = var5_5.length;
            var7_7 = 0;
            do {
                if (var7_7 >= var6_6) continue block0;
                var8_8 = var5_5[var7_7];
                var9_9 = var1_1.getMessage().equals((Object)var8_8.getLabel());
                var10_10 = var1_1.getTimestamp() > var4_4.getTimestamp();
                if (var9_9 && var10_10) {
                    this.unmatchedAgentQuestionnaire.remove((Object)var3_3);
                    return var3_3;
                }
                ++var7_7;
            } while (true);
            break;
        } while (true);
    }

    public static LivechatChatLogPath getInstance() {
        return INSTANCE;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private ChatLog mergeEntries(ChatLog chatLog, ChatLog chatLog2) {
        if (chatLog == null) {
            return null;
        }
        if (chatLog2 == null) return chatLog;
        if (chatLog.getType() != ChatLog.Type.CHAT_RATING) return (ChatLog)ChatGson.performUpdate((Object)chatLog, (Object)chatLog2, ChatLog.class);
        if (chatLog2.getComment() != null) return (ChatLog)ChatGson.performUpdate((Object)chatLog, (Object)chatLog2, ChatLog.class);
        chatLog.setRawNewRating(null);
        chatLog.setRawRating(null);
        return (ChatLog)ChatGson.performUpdate((Object)chatLog, (Object)chatLog2, ChatLog.class);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void updateInternal(LinkedHashMap<String, ChatLog> var1_1) {
        if (var1_1 == null) {
            Logger.i((String)"LivechatChatLogPath", (String)"Passed parameter must not be null. Aborting update.", (Object[])new Object[0]);
            return;
        }
        var34_3 = var2_2 = this.lock;
        // MONITORENTER : var34_3
        var3_4 = 0;
        var5_5 = var1_1.entrySet().iterator();
        block9 : do {
            block26 : {
                block28 : {
                    block29 : {
                        block27 : {
                            if (!var5_5.hasNext()) ** GOTO lbl43
                            var6_6 = (Map.Entry)var5_5.next();
                            var7_7 = (String)var6_6.getKey();
                            var8_8 = (ChatLog)var6_6.getValue();
                            if (var8_8 != null && var8_8.getType() == ChatLog.Type.CHAT_RATING && this.chatRatingEntry != null) {
                                var7_7 = (String)this.chatRatingEntry.first;
                            }
                            if (!((LinkedHashMap)this.data).containsKey((Object)var7_7)) break block27;
                            var26_23 = (ChatLog)((LinkedHashMap)this.data).get((Object)var7_7);
                            if (var8_8 == null) {
                                if (((ChatLog)((LinkedHashMap)this.data).get((Object)var7_7)).getType() == ChatLog.Type.VISITOR_ATTACHMENT) continue;
                                ((LinkedHashMap)this.data).remove((Object)var7_7);
                                --var3_4;
                                this.timeoutManager.remove(var7_7);
                                continue;
                            }
                            var28_24 = this.mergeEntries(var26_23, var8_8);
                            if (var28_24 == null) {
                                ((LinkedHashMap)this.data).remove((Object)var7_7);
                            } else {
                                ((LinkedHashMap)this.data).put((Object)var7_7, (Object)var28_24);
                                var31_25 = ChatLog.Type.CHAT_MSG_VISITOR == var28_24.getType();
                                var32_26 = var28_24.isFailed() == null ? false : var28_24.isFailed();
                                if (var31_25 && !var32_26) {
                                    var33_27 = var28_24.isUnverified() == null ? true : var28_24.isUnverified();
                                    if (var33_27) {
                                        this.timeoutManager.schedule(var7_7, var8_8);
                                    } else {
                                        this.timeoutManager.remove(var7_7);
                                    }
                                }
                            }
                            break block28;
                        }
                        if (var8_8 == null) break block28;
                        var12_12 = ChatLog.Type.CHAT_MSG_VISITOR == var8_8.getType();
                        var13_13 = var8_8.getMessage() != null && var8_8.getMessage().trim().isEmpty();
                        break block29;
lbl43: // 1 sources:
                        if (var3_4 >= 0) {
                            this.broadcast(this.getData());
                        }
                        // MONITOREXIT : var34_3
                        return;
                    }
                    if (var12_12 && var13_13) continue;
                    var14_14 = this.findAgentQuestionnaire(var8_8);
                    if (var14_14 != null) {
                        var15_15 = ((ChatLog)var14_14.second).getOptions();
                        var16_16 = 0;
                        break block26;
                    }
                    if (var8_8 != null && var8_8.getType() == ChatLog.Type.VISITOR_ATTACHMENT && (var24_22 = var8_8.getFileName()) != null) {
                        var8_8.setFile(FileTransfers.INSTANCE.findFile(var24_22));
                        this.uploadedFiles.put((Object)var24_22, (Object)var7_7);
                    }
                    if (var8_8.getType() == ChatLog.Type.CHAT_MSG_VISITOR && (var21_18 = var8_8.getAttachment() != null ? var8_8.getAttachment().getName() : null) != null) {
                        var22_21 = (String)this.uploadedFiles.get((Object)var21_18);
                        var23_20 = (ChatLog)((LinkedHashMap)this.data).get((Object)var22_21);
                        if (var23_20 == null) continue;
                        var23_20.setProgress(100);
                        continue;
                    }
                    if (var8_8.getType() == ChatLog.Type.CHAT_RATING) {
                        this.chatRatingEntry = var18_17 = new Pair((Object)var7_7, (Object)var8_8);
                    }
                    ((LinkedHashMap)this.data).put((Object)var7_7, (Object)var8_8);
                    ++var3_4;
                    if (!var12_12) ** GOTO lbl75
                    try {
                        var20_19 = var8_8.isUnverified() != null ? var8_8.isUnverified() : false;
                    }
                    catch (Exception var9_9) {
                        Logger.w((String)"LivechatChatLogPath", (String)"Failed to process json. Chat log record could not be created.", (Throwable)var9_9, (Object[])new Object[0]);
                    }
                    break block28;
                    if (var20_19) {
                        this.timeoutManager.schedule(var7_7, var8_8);
                    }
                }
                if (var8_8 == null || var8_8.getOptions() == null || var8_8.getOptions().length <= 0) continue;
                var10_10 = this.unmatchedAgentQuestionnaire;
                var11_11 = new Pair((Object)var7_7, (Object)var8_8);
                var10_10.addFirst((Object)var11_11);
                continue;
            }
            do {
                if (var16_16 >= var15_15.length) continue block9;
                if (var8_8.getMessage().equals((Object)var15_15[var16_16].getLabel())) {
                    var15_15[var16_16].select();
                    ((LinkedHashMap)this.data).put(var14_14.first, var14_14.second);
                    continue block9;
                }
                ++var16_16;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void clear() {
        Object object;
        Object object2 = object = this.lock;
        synchronized (object2) {
            if (this.data != null) {
                ((LinkedHashMap)this.data).clear();
            }
            return;
        }
    }

    public /* varargs */ int countMessages(ChatLog.Type ... arrtype) {
        Collection collection = this.getData().values();
        int n = 0;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            ChatLog.Type type = ((ChatLog)iterator.next()).getType();
            int n2 = arrtype.length;
            for (int i = 0; i < n2; ++i) {
                if (!arrtype[i].equals((Object)type)) continue;
                ++n;
            }
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public LinkedHashMap<String, ChatLog> getData() {
        Object object;
        Object object2 = object = this.lock;
        synchronized (object2) {
            if (this.data == null) return new LinkedHashMap();
            return new LinkedHashMap((Map)this.data);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    void update(String string) {
        if (this.isClearRequired(string)) {
            this.clear();
            return;
        } else {
            if (string.isEmpty()) return;
            {
                Type type = new 1(this).getType();
                this.updateInternal((LinkedHashMap<String, ChatLog>)((LinkedHashMap)ChatGson.get().fromJson(string, type)));
                return;
            }
        }
    }

    /*
     * Exception performing whole class analysis.
     */
    class TimeoutManager {
        private static final long TIMEOUT = 5000L;
        private Handler handler;
        final /* synthetic */ LivechatChatLogPath this$0;
        private Map<String, SendTimeout> unverifiedLog;

        TimeoutManager(LivechatChatLogPath livechatChatLogPath) {
            this.this$0 = livechatChatLogPath;
            this.handler = new Handler(Looper.myLooper());
            this.unverifiedLog = new HashMap();
        }

        void remove(String string) {
            TimeoutManager timeoutManager = this;
            synchronized (timeoutManager) {
                Logger.v((String)LivechatChatLogPath.LOG_TAG, (String)"Removing timeout runnable", (Object[])new Object[0]);
                this.handler.removeCallbacks((Runnable)this.unverifiedLog.get((Object)string));
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        void schedule(String string, ChatLog chatLog) {
            TimeoutManager timeoutManager = this;
            synchronized (timeoutManager) {
                if (string == null) {
                    Logger.w((String)LivechatChatLogPath.LOG_TAG, (String)"Can not add chat log without an id", (Object[])new Object[0]);
                } else if (chatLog == null) {
                    Logger.w((String)LivechatChatLogPath.LOG_TAG, (String)"Can not add chat log that is null", (Object[])new Object[0]);
                } else {
                    SendTimeout sendTimeout = this.unverifiedLog.get((Object)string);
                    if (sendTimeout != null) {
                        Logger.v((String)LivechatChatLogPath.LOG_TAG, (String)"Removing previous timeout", (Object[])new Object[0]);
                        this.handler.removeCallbacks((Runnable)sendTimeout);
                    }
                    SendTimeout sendTimeout2 = new /* Unavailable Anonymous Inner Class!! */;
                    this.unverifiedLog.put((Object)string, (Object)sendTimeout2);
                    Logger.v((String)LivechatChatLogPath.LOG_TAG, (String)"Scheduling timeout runnable", (Object[])new Object[0]);
                    this.handler.postDelayed((Runnable)sendTimeout2, 5000L);
                }
                return;
            }
        }
    }

}

