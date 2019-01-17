/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 */
package com.zopim.android.sdk.prechat;

public final class EmailTranscript
extends Enum<EmailTranscript> {
    private static final /* synthetic */ EmailTranscript[] $VALUES;
    public static final /* enum */ EmailTranscript DISABLED = new EmailTranscript();
    public static final /* enum */ EmailTranscript PROMPT = new EmailTranscript();

    static {
        EmailTranscript[] arremailTranscript = new EmailTranscript[]{DISABLED, PROMPT};
        $VALUES = arremailTranscript;
    }

    public static EmailTranscript valueOf(String string2) {
        return (EmailTranscript)Enum.valueOf(EmailTranscript.class, (String)string2);
    }

    public static EmailTranscript[] values() {
        return (EmailTranscript[])$VALUES.clone();
    }
}

