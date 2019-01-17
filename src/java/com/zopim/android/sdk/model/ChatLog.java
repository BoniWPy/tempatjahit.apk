/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.annotation.VisibleForTesting
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 *  com.zendesk.logger.Logger
 *  java.io.File
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Comparable
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.NoSuchFieldError
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.net.MalformedURLException
 *  java.net.URL
 */
package com.zopim.android.sdk.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zendesk.logger.Logger;
import com.zopim.android.sdk.model.Attachment;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ChatLog
implements Comparable<ChatLog> {
    private static final String LOG_TAG = "ChatLog";
    @Expose
    @SerializedName(value="attachment")
    private Attachment attachment;
    @Expose
    @SerializedName(value="new_comment$string")
    private String comment;
    private Option[] convertedOptions;
    @Expose
    @SerializedName(value="display_name$string")
    private String displayName;
    @Expose
    @SerializedName(value="error$string")
    private String error;
    @Expose
    @SerializedName(value="failed$bool")
    private Boolean failed;
    private File file;
    @Expose
    @SerializedName(value="file_name$string")
    private String fileName;
    @Expose
    @SerializedName(value="file_size$int")
    private Integer fileSize;
    @Expose
    @SerializedName(value="file_type$string")
    private String fileType;
    @Expose
    @SerializedName(value="msg$string")
    private String message;
    @Expose
    @SerializedName(value="nick$string")
    private String nick;
    @Expose
    @SerializedName(value="options$string")
    private String options;
    @Expose
    @SerializedName(value="new_rating$string")
    private String rawNewRating;
    @Expose
    @SerializedName(value="rating$string")
    private String rawRating;
    @Expose
    @SerializedName(value="type$string")
    private String rawType;
    @Expose
    @SerializedName(value="timestamp$int")
    private Long timestamp;
    private Type type;
    @Expose
    @SerializedName(value="unverified$bool")
    private Boolean unverified;
    private int uploadProgress = 0;
    @Expose
    @SerializedName(value="post_url$string")
    private String uploadUrl;
    @Expose
    @SerializedName(value="visitor_queue$int")
    private Integer visitorQueue;

    public ChatLog() {
    }

    public ChatLog(String string2, Type type, String string3) {
        this.timestamp = System.currentTimeMillis();
        this.displayName = string2;
        this.message = string3;
        this.unverified = true;
        this.failed = false;
        this.type = type;
    }

    public int compareTo(@Nullable ChatLog chatLog) {
        if (chatLog == null) {
            Logger.w((String)LOG_TAG, (String)"Passed parameter must not be null. Can not compare. Declaring them as same.", (Object[])new Object[0]);
            return 0;
        }
        if (this.timestamp == null || chatLog.getTimestamp() == null) {
            Logger.w((String)LOG_TAG, (String)"Error comparing chat logs. Timestamp was null. Declaring them as same.", (Object[])new Object[0]);
            return 0;
        }
        try {
            int n = this.timestamp.compareTo(chatLog.getTimestamp());
            return n;
        }
        catch (NullPointerException nullPointerException) {
            Logger.w((String)LOG_TAG, (String)"Error comparing chat logs. Timestamp was not initialized. Declaring them as same.", (Throwable)nullPointerException, (Object[])new Object[0]);
            return 0;
        }
    }

    @Nullable
    public Attachment getAttachment() {
        return this.attachment;
    }

    public String getComment() {
        return this.comment;
    }

    @Nullable
    public String getDisplayName() {
        return this.displayName;
    }

    @NonNull
    public Error getError() {
        return Error.getType(this.error);
    }

    @Nullable
    public File getFile() {
        return this.file;
    }

    @Nullable
    public String getFileName() {
        return this.fileName;
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }

    @Nullable
    public String getNick() {
        return this.nick;
    }

    @NonNull
    public Option[] getOptions() {
        if (this.convertedOptions != null) {
            return this.convertedOptions;
        }
        if (this.options == null || this.options.isEmpty()) {
            return new Option[0];
        }
        String[] arrstring = this.options.split("/");
        Option[] arroption = new Option[arrstring.length];
        for (int i = 0; i < arrstring.length; ++i) {
            arroption[i] = new Option(arrstring[i]);
        }
        this.convertedOptions = arroption;
        return arroption;
    }

    @NonNull
    public int getProgress() {
        return this.uploadProgress;
    }

    public Rating getRating() {
        if (this.rawRating == null && (Rating.getRating(this.rawNewRating) == Rating.GOOD || Rating.getRating(this.rawNewRating) == Rating.BAD)) {
            return Rating.getRating(this.rawNewRating);
        }
        if (this.rawNewRating == null && (Rating.getRating(this.rawRating) == Rating.GOOD || Rating.getRating(this.rawRating) == Rating.BAD)) {
            return Rating.UNRATED;
        }
        if (!(Rating.getRating(this.rawNewRating) != Rating.GOOD && Rating.getRating(this.rawNewRating) != Rating.BAD || Rating.getRating(this.rawRating) != Rating.GOOD && Rating.getRating(this.rawRating) != Rating.BAD || Rating.getRating(this.rawRating) == Rating.getRating(this.rawNewRating))) {
            return Rating.getRating(this.rawNewRating);
        }
        return Rating.UNRATED;
    }

    @Nullable
    public Long getTimestamp() {
        return this.timestamp;
    }

    @NonNull
    public Type getType() {
        if (this.type != null) {
            return this.type;
        }
        switch (RawType.getType(this.rawType)) {
            default: {
                return Type.UNKNOWN;
            }
            case CHAT_MSG: {
                switch (1.$SwitchMap$com$zopim$android$sdk$model$ChatLog$Nick[Nick.getType(this.nick).ordinal()]) {
                    default: {
                        return Type.UNKNOWN;
                    }
                    case 1: {
                        return Type.CHAT_MSG_SYSTEM;
                    }
                    case 2: {
                        return Type.CHAT_MSG_TRIGGER;
                    }
                    case 3: {
                        return Type.CHAT_MSG_AGENT;
                    }
                    case 4: 
                }
                return Type.CHAT_MSG_VISITOR;
            }
            case CHAT_EVENT: {
                return Type.CHAT_MSG_SYSTEM;
            }
            case MEMBER_JOIN: {
                return Type.MEMBER_JOIN;
            }
            case MEMBER_LEAVE: {
                return Type.MEMBER_LEAVE;
            }
            case SYSTEM_OFFLINE: {
                return Type.SYSTEM_OFFLINE;
            }
            case FILE_UPLOAD: {
                return Type.VISITOR_ATTACHMENT;
            }
            case CHAT_RATING_REQUEST: 
            case CHAT_RATING: 
            case CHAT_COMMENT: 
        }
        return Type.CHAT_RATING;
    }

    @Nullable
    public URL getUploadUrl() {
        if (this.uploadUrl != null) {
            try {
                URL uRL = new URL(this.uploadUrl);
                return uRL;
            }
            catch (MalformedURLException malformedURLException) {
                Logger.w((String)LOG_TAG, (String)"Can not retrieve url. ", (Throwable)malformedURLException, (Object[])new Object[0]);
                return null;
            }
        }
        return null;
    }

    @Nullable
    public Integer getVisitorQueue() {
        return this.visitorQueue;
    }

    @Nullable
    public Boolean isFailed() {
        return this.failed;
    }

    @Nullable
    public Boolean isUnverified() {
        return this.unverified;
    }

    public void setComment(String string2) {
        this.comment = string2;
    }

    public void setError(Error error) {
        this.error = error.getValue();
    }

    public void setFailed(boolean bl) {
        this.failed = bl;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @VisibleForTesting
    void setOptions(String string2) {
        this.options = string2;
    }

    public void setProgress(int n) {
        if (n < 0 || n > 100) {
            Logger.i((String)LOG_TAG, (String)"Supplied progress must be in range 0 - 100. Progress will not be updated.", (Object[])new Object[0]);
            return;
        }
        if (n < this.uploadProgress) {
            Logger.i((String)LOG_TAG, (String)"Supplied progress must not be less then current progress. Progress will not be updated.", (Object[])new Object[0]);
            return;
        }
        this.uploadProgress = n;
    }

    public void setRawNewRating(String string2) {
        this.rawNewRating = string2;
    }

    public void setRawRating(String string2) {
        this.rawRating = string2;
    }

    public String toString() {
        return "type:" + this.rawType + ", name:" + this.displayName + ", msg:" + this.message + ", time:" + (Object)this.timestamp + ", url:" + this.uploadUrl;
    }

    public static final class Error
    extends Enum<Error> {
        private static final /* synthetic */ Error[] $VALUES;
        public static final /* enum */ Error UNKNOWN;
        public static final /* enum */ Error UPLOAD_FAILED_ERROR;
        public static final /* enum */ Error UPLOAD_FILE_EXTENSION_ERROR;
        public static final /* enum */ Error UPLOAD_SIZE_ERROR;
        final String error;

        static {
            UPLOAD_SIZE_ERROR = new Error("size");
            UPLOAD_FILE_EXTENSION_ERROR = new Error("type");
            UPLOAD_FAILED_ERROR = new Error("upload_request_failed");
            UNKNOWN = new Error("unknown");
            Error[] arrerror = new Error[]{UPLOAD_SIZE_ERROR, UPLOAD_FILE_EXTENSION_ERROR, UPLOAD_FAILED_ERROR, UNKNOWN};
            $VALUES = arrerror;
        }

        private Error(String string3) {
            this.error = string3;
        }

        @NonNull
        public static Error getType(String string2) {
            for (Error error : Error.values()) {
                if (!error.getValue().equals((Object)string2)) continue;
                return error;
            }
            return UNKNOWN;
        }

        public static Error valueOf(String string2) {
            return (Error)Enum.valueOf(Error.class, (String)string2);
        }

        public static Error[] values() {
            return (Error[])$VALUES.clone();
        }

        public String getValue() {
            return this.error;
        }
    }

    private static final class Nick
    extends Enum<Nick> {
        private static final /* synthetic */ Nick[] $VALUES;
        public static final /* enum */ Nick AGENT_MSG;
        public static final /* enum */ Nick AGENT_SYSTEM;
        public static final /* enum */ Nick AGENT_TRIGGER;
        public static final /* enum */ Nick UNKNOWN;
        public static final /* enum */ Nick VISITOR_MSG;
        final String nick;

        static {
            AGENT_SYSTEM = new Nick("agent:system");
            AGENT_TRIGGER = new Nick("agent:trigger");
            AGENT_MSG = new Nick("agent");
            VISITOR_MSG = new Nick("visitor");
            UNKNOWN = new Nick("unknown");
            Nick[] arrnick = new Nick[]{AGENT_SYSTEM, AGENT_TRIGGER, AGENT_MSG, VISITOR_MSG, UNKNOWN};
            $VALUES = arrnick;
        }

        private Nick(String string3) {
            this.nick = string3;
        }

        @NonNull
        public static Nick getType(String string2) {
            if (string2 == null || string2.isEmpty()) {
                return UNKNOWN;
            }
            if ("agent:system".equals((Object)string2)) {
                return AGENT_SYSTEM;
            }
            if ("agent:trigger".equals((Object)string2)) {
                return AGENT_TRIGGER;
            }
            if (string2.contains((CharSequence)"agent")) {
                return AGENT_MSG;
            }
            if (string2.contains((CharSequence)"visitor")) {
                return VISITOR_MSG;
            }
            return UNKNOWN;
        }

        public static Nick valueOf(String string2) {
            return (Nick)Enum.valueOf(Nick.class, (String)string2);
        }

        public static Nick[] values() {
            return (Nick[])$VALUES.clone();
        }

        public String getValue() {
            return this.nick;
        }
    }

    public static class Option {
        private String label;
        private boolean selected;

        private Option() {
        }

        public Option(String string2) {
            if (string2 == null) {
                Logger.w((String)ChatLog.LOG_TAG, (String)"Option label not assigned", (Object[])new Object[0]);
                this.label = "";
            }
            this.label = string2;
            this.selected = false;
        }

        @NonNull
        public String getLabel() {
            return this.label;
        }

        @NonNull
        public boolean isSelected() {
            return this.selected;
        }

        public void select() {
            this.selected = true;
        }
    }

    public static final class Rating
    extends Enum<Rating> {
        private static final /* synthetic */ Rating[] $VALUES;
        public static final /* enum */ Rating BAD;
        public static final /* enum */ Rating GOOD;
        public static final /* enum */ Rating UNKNOWN;
        public static final /* enum */ Rating UNRATED;
        final String rating;

        static {
            GOOD = new Rating("good");
            BAD = new Rating("bad");
            UNRATED = new Rating("unrated");
            UNKNOWN = new Rating("unknown");
            Rating[] arrrating = new Rating[]{GOOD, BAD, UNRATED, UNKNOWN};
            $VALUES = arrrating;
        }

        private Rating(String string3) {
            this.rating = string3;
        }

        @NonNull
        public static Rating getRating(String string2) {
            for (Rating rating : Rating.values()) {
                if (!rating.getValue().equals((Object)string2)) continue;
                return rating;
            }
            return UNKNOWN;
        }

        public static Rating valueOf(String string2) {
            return (Rating)Enum.valueOf(Rating.class, (String)string2);
        }

        public static Rating[] values() {
            return (Rating[])$VALUES.clone();
        }

        public String getValue() {
            return this.rating;
        }
    }

    private static final class RawType
    extends Enum<RawType> {
        private static final /* synthetic */ RawType[] $VALUES;
        public static final /* enum */ RawType CHAT_COMMENT;
        public static final /* enum */ RawType CHAT_EVENT;
        public static final /* enum */ RawType CHAT_MSG;
        public static final /* enum */ RawType CHAT_RATING;
        public static final /* enum */ RawType CHAT_RATING_REQUEST;
        public static final /* enum */ RawType FILE_UPLOAD;
        public static final /* enum */ RawType MEMBER_JOIN;
        public static final /* enum */ RawType MEMBER_LEAVE;
        public static final /* enum */ RawType SYSTEM_OFFLINE;
        public static final /* enum */ RawType UNKNOWN;
        final String type;

        static {
            CHAT_MSG = new RawType("chat.msg");
            MEMBER_JOIN = new RawType("chat.memberjoin");
            MEMBER_LEAVE = new RawType("chat.memberleave");
            CHAT_EVENT = new RawType("chat.event");
            SYSTEM_OFFLINE = new RawType("system.offline");
            FILE_UPLOAD = new RawType("chat.file.upload");
            CHAT_RATING_REQUEST = new RawType("chat.request.rating");
            CHAT_RATING = new RawType("chat.rating");
            CHAT_COMMENT = new RawType("chat.comment");
            UNKNOWN = new RawType("unknown");
            RawType[] arrrawType = new RawType[]{CHAT_MSG, MEMBER_JOIN, MEMBER_LEAVE, CHAT_EVENT, SYSTEM_OFFLINE, FILE_UPLOAD, CHAT_RATING_REQUEST, CHAT_RATING, CHAT_COMMENT, UNKNOWN};
            $VALUES = arrrawType;
        }

        private RawType(String string3) {
            this.type = string3;
        }

        @NonNull
        public static RawType getType(String string2) {
            for (RawType rawType : RawType.values()) {
                if (!rawType.getValue().equals((Object)string2)) continue;
                return rawType;
            }
            return UNKNOWN;
        }

        public static RawType valueOf(String string2) {
            return (RawType)Enum.valueOf(RawType.class, (String)string2);
        }

        public static RawType[] values() {
            return (RawType[])$VALUES.clone();
        }

        public String getValue() {
            return this.type;
        }
    }

    public static final class Type
    extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES;
        public static final /* enum */ Type ACCOUNT_OFFLINE;
        public static final /* enum */ Type CHAT_MSG_AGENT;
        public static final /* enum */ Type CHAT_MSG_SYSTEM;
        public static final /* enum */ Type CHAT_MSG_TRIGGER;
        public static final /* enum */ Type CHAT_MSG_VISITOR;
        public static final /* enum */ Type CHAT_RATING;
        public static final /* enum */ Type MEMBER_JOIN;
        public static final /* enum */ Type MEMBER_LEAVE;
        public static final /* enum */ Type SYSTEM_OFFLINE;
        public static final /* enum */ Type UNKNOWN;
        public static final /* enum */ Type VISITOR_ATTACHMENT;

        static {
            CHAT_MSG_AGENT = new Type();
            CHAT_MSG_VISITOR = new Type();
            CHAT_MSG_TRIGGER = new Type();
            CHAT_MSG_SYSTEM = new Type();
            MEMBER_LEAVE = new Type();
            MEMBER_JOIN = new Type();
            SYSTEM_OFFLINE = new Type();
            ACCOUNT_OFFLINE = new Type();
            VISITOR_ATTACHMENT = new Type();
            CHAT_RATING = new Type();
            UNKNOWN = new Type();
            Type[] arrtype = new Type[]{CHAT_MSG_AGENT, CHAT_MSG_VISITOR, CHAT_MSG_TRIGGER, CHAT_MSG_SYSTEM, MEMBER_LEAVE, MEMBER_JOIN, SYSTEM_OFFLINE, ACCOUNT_OFFLINE, VISITOR_ATTACHMENT, CHAT_RATING, UNKNOWN};
            $VALUES = arrtype;
        }

        public static Type valueOf(String string2) {
            return (Type)Enum.valueOf(Type.class, (String)string2);
        }

        public static Type[] values() {
            return (Type[])$VALUES.clone();
        }
    }

}

