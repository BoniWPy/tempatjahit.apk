/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.database.Cursor
 *  com.orm.SugarRecord
 *  java.lang.Class
 *  java.lang.Iterable
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuilder
 *  java.util.ArrayList
 *  java.util.Iterator
 *  java.util.List
 */
package com.orm.query;

import android.database.Cursor;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.util.NamingHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Select<T>
implements Iterable {
    private static final String GROUP_BY = "GROUP BY ";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String LIMIT = "LIMIT ";
    private static final String OFFSET = "OFFSET ";
    private static final String ORDER_BY = "ORDER BY ";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String SELECT_FROM = "SELECT * FROM ";
    private static final String SINGLE_QUOTE = "'";
    private static final String SPACE = " ";
    private static final String WHERE = "WHERE ";
    private List<Object> args = new ArrayList();
    private String[] arguments;
    private String groupBy = "";
    private String limit = "";
    private String offset = "";
    private String orderBy = "";
    private Class<T> record;
    private String whereClause = "";

    public Select(Class<T> class_) {
        this.record = class_;
    }

    private String[] convertArgs(List<Object> list) {
        String[] arrstring = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            arrstring[i] = list.get(i).toString();
        }
        return arrstring;
    }

    public static <T> Select<T> from(Class<T> class_) {
        return new Select<T>(class_);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void mergeConditions(Condition[] arrcondition, Condition.Type type) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Condition condition : arrcondition) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(SPACE).append(type.name()).append(SPACE);
            }
            if (Condition.Check.LIKE.equals((Object)condition.getCheck()) || Condition.Check.NOT_LIKE.equals((Object)condition.getCheck())) {
                stringBuilder.append(condition.getProperty()).append(condition.getCheckSymbol()).append(SINGLE_QUOTE).append(condition.getValue().toString()).append(SINGLE_QUOTE);
                continue;
            }
            if (Condition.Check.IS_NULL.equals((Object)condition.getCheck()) || Condition.Check.IS_NOT_NULL.equals((Object)condition.getCheck())) {
                stringBuilder.append(condition.getProperty()).append(condition.getCheckSymbol());
                continue;
            }
            stringBuilder.append(condition.getProperty()).append(condition.getCheckSymbol()).append("? ");
            this.args.add(condition.getValue());
        }
        if (!this.whereClause.isEmpty()) {
            this.whereClause = this.whereClause + SPACE + type.name() + SPACE;
        }
        this.whereClause = this.whereClause + LEFT_PARENTHESIS + (Object)stringBuilder + RIGHT_PARENTHESIS;
    }

    public /* varargs */ Select<T> and(Condition ... arrcondition) {
        this.mergeConditions(arrcondition, Condition.Type.AND);
        return this;
    }

    public long count() {
        if (this.arguments == null) {
            this.arguments = this.convertArgs(this.args);
        }
        return SugarRecord.count(this.record, (String)this.whereClause, (String[])this.arguments, (String)this.groupBy, (String)this.orderBy, (String)this.limit);
    }

    public T first() {
        List list;
        if (this.arguments == null) {
            this.arguments = this.convertArgs(this.args);
        }
        if ((list = SugarRecord.find(this.record, (String)this.whereClause, (String[])this.arguments, (String)this.groupBy, (String)this.orderBy, (String)"1")).size() > 0) {
            return (T)list.get(0);
        }
        return null;
    }

    String[] getArgs() {
        return this.convertArgs(this.args);
    }

    public Cursor getCursor() {
        return SugarRecord.getCursor(this.record, (String)this.whereClause, (String[])this.arguments, (String)this.groupBy, (String)this.orderBy, (String)this.limit);
    }

    String getWhereCond() {
        return this.whereClause;
    }

    public Select<T> groupBy(String string2) {
        this.groupBy = string2;
        return this;
    }

    public Iterator<T> iterator() {
        if (this.arguments == null) {
            this.arguments = this.convertArgs(this.args);
        }
        return SugarRecord.findAsIterator(this.record, (String)this.whereClause, (String[])this.arguments, (String)this.groupBy, (String)this.orderBy, (String)this.limit);
    }

    public Select<T> limit(String string2) {
        this.limit = string2;
        return this;
    }

    public List<T> list() {
        if (this.arguments == null) {
            this.arguments = this.convertArgs(this.args);
        }
        return SugarRecord.find(this.record, (String)this.whereClause, (String[])this.arguments, (String)this.groupBy, (String)this.orderBy, (String)this.limit);
    }

    public Select<T> offset(String string2) {
        this.offset = string2;
        return this;
    }

    public /* varargs */ Select<T> or(Condition ... arrcondition) {
        this.mergeConditions(arrcondition, Condition.Type.OR);
        return this;
    }

    public Select<T> orderBy(String string2) {
        this.orderBy = string2;
        return this;
    }

    String toSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SELECT_FROM).append(NamingHelper.toSQLName(this.record)).append(SPACE);
        if (!this.whereClause.isEmpty()) {
            stringBuilder.append(WHERE).append(this.whereClause).append(SPACE);
        }
        if (!this.orderBy.isEmpty()) {
            stringBuilder.append(ORDER_BY).append(this.orderBy).append(SPACE);
        }
        if (!this.groupBy.isEmpty()) {
            stringBuilder.append(GROUP_BY).append(this.groupBy).append(SPACE);
        }
        if (!this.limit.isEmpty()) {
            stringBuilder.append(LIMIT).append(this.limit).append(SPACE);
        }
        if (!this.offset.isEmpty()) {
            stringBuilder.append(OFFSET).append(this.offset).append(SPACE);
        }
        return stringBuilder.toString();
    }

    public Select<T> where(String string2) {
        this.whereClause = string2;
        return this;
    }

    public Select<T> where(String string2, String[] arrstring) {
        this.whereClause = string2;
        this.arguments = arrstring;
        return this;
    }

    public /* varargs */ Select<T> where(Condition ... arrcondition) {
        this.mergeConditions(arrcondition, Condition.Type.AND);
        return this;
    }

    public /* varargs */ Select<T> whereOr(Condition ... arrcondition) {
        this.mergeConditions(arrcondition, Condition.Type.OR);
        return this;
    }
}

