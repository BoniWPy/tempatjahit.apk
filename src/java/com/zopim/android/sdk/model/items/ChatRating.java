/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  com.zopim.android.sdk.model.items.Disableable
 *  com.zopim.android.sdk.model.items.RowItem
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.model.items;

import android.support.annotation.NonNull;
import com.zopim.android.sdk.model.ChatLog;
import com.zopim.android.sdk.model.items.Disableable;
import com.zopim.android.sdk.model.items.RowItem;

public class ChatRating
extends RowItem<ChatRating>
implements Disableable {
    private String comment;
    private boolean disabled;
    private ChatLog.Rating rating = ChatLog.Rating.UNKNOWN;

    public ChatRating() {
        super.setType(RowItem.Type.CHAT_RATING);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        boolean bl = true;
        if (this == object) {
            return bl;
        }
        boolean bl2 = object instanceof ChatRating;
        boolean bl3 = false;
        if (!bl2) return bl3;
        boolean bl4 = super.equals(object);
        bl3 = false;
        if (!bl4) return bl3;
        ChatRating chatRating = (ChatRating)((Object)object);
        boolean bl5 = this.disabled;
        boolean bl6 = chatRating.disabled;
        bl3 = false;
        if (bl5 != bl6) return bl3;
        ChatLog.Rating rating = this.rating;
        ChatLog.Rating rating2 = chatRating.rating;
        bl3 = false;
        if (rating != rating2) return bl3;
        if (this.comment != null) {
            return this.comment.equals((Object)chatRating.comment);
        }
        if (chatRating.comment == null) return bl;
        return false;
    }

    @NonNull
    public String getComment() {
        if (this.comment != null) {
            return this.comment;
        }
        return "";
    }

    @NonNull
    public ChatLog.Rating getRating() {
        if (this.rating != null) {
            return this.rating;
        }
        return ChatLog.Rating.UNKNOWN;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 31 * super.hashCode();
        int n2 = this.rating != null ? this.rating.hashCode() : 0;
        int n3 = 31 * (n + n2);
        int n4 = this.comment != null ? this.comment.hashCode() : 0;
        int n5 = 31 * (n3 + n4);
        boolean bl = this.disabled;
        int n6 = 0;
        if (bl) {
            n6 = 1;
        }
        return n5 + n6;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setComment(@NonNull String string2) {
        this.comment = string2;
    }

    public void setDisabled(boolean bl) {
        this.disabled = bl;
    }

    public void setRating(@NonNull ChatLog.Rating rating) {
        this.rating = rating;
    }

    public String toString() {
        return "rating:" + (Object)((Object)this.rating) + " comment:" + this.comment + super.toString();
    }

    public void update(@NonNull ChatRating chatRating) {
        super.update((RowItem)chatRating);
        this.rating = chatRating.rating;
        this.comment = chatRating.comment;
        this.disabled = chatRating.disabled;
    }
}

