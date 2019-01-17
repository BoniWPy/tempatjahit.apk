/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  com.zopim.android.sdk.model.items.RowItem
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.model.items;

import android.support.annotation.NonNull;
import com.zopim.android.sdk.model.items.RowItem;

public class ChatMemberEvent
extends RowItem<ChatMemberEvent> {
    private String message;

    public ChatMemberEvent() {
        super.setType(RowItem.Type.MEMBER_EVENT);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        block7 : {
            block6 : {
                if (this == object) break block6;
                if (!(object instanceof ChatMemberEvent)) {
                    return false;
                }
                if (!super.equals(object)) {
                    return false;
                }
                ChatMemberEvent chatMemberEvent = (ChatMemberEvent)((Object)object);
                if (this.message != null) {
                    return this.message.equals((Object)chatMemberEvent.message);
                }
                if (chatMemberEvent.message != null) break block7;
            }
            return true;
        }
        return false;
    }

    @NonNull
    public String getMessage() {
        if (this.message != null) {
            return this.message;
        }
        return "";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int hashCode() {
        int n;
        int n2 = 31 * super.hashCode();
        if (this.message != null) {
            n = this.message.hashCode();
            do {
                return n2 + n;
                break;
            } while (true);
        }
        n = 0;
        return n2 + n;
    }

    public void setMessage(@NonNull String string2) {
        this.message = string2;
    }

    public String toString() {
        return super.toString() + " event: " + this.message;
    }

    public void update(@NonNull ChatMemberEvent chatMemberEvent) {
        super.update((RowItem)chatMemberEvent);
        this.message = chatMemberEvent.message;
    }
}

