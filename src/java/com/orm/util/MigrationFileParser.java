/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.orm.util;

public class MigrationFileParser {
    private String content;

    public MigrationFileParser(String string2) {
        this.content = string2.replaceAll("(\\/\\*([\\s\\S]*?)\\*\\/)|(--(.)*)|(\n)", "");
    }

    public String[] getStatements() {
        return this.content.split(";");
    }
}

