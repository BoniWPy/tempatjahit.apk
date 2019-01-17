/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.net.Uri
 *  android.os.AsyncTask
 *  com.vansuita.library.CheckNewAppVersion$ITaskComplete
 *  java.lang.Exception
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Void
 *  org.jsoup.Connection
 *  org.jsoup.Jsoup
 *  org.jsoup.nodes.Document
 *  org.jsoup.nodes.Element
 *  org.jsoup.select.Elements
 */
package com.vansuita.library;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import com.vansuita.library.CheckNewAppVersion;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CheckNewAppVersion
extends AsyncTask<Void, Void, Result> {
    private static final String DIV = "div[itemprop=softwareVersion]";
    private static final String PLAY_STORE_LINK = "http://play.google.com/store/apps/details?id=%s&hl=en";
    private static final String REFERRER = "http://www.google.com";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private Context context;
    private ITaskComplete listener;
    private Result result;

    public CheckNewAppVersion(Context context) {
        this.context = context;
        this.result = new Result(context);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected /* varargs */ Result doInBackground(Void ... arrvoid) {
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            this.result.setOldVersionCode(packageInfo.versionName);
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {}
        try {
            String string2 = Jsoup.connect((String)this.getExternalAppLink()).timeout(30000).userAgent(USER_AGENT).referrer(REFERRER).get().select(DIV).first().ownText();
            this.result.setNewVersionCode(string2);
            return this.result;
        }
        catch (Exception exception) {
            return this.result;
        }
    }

    public String getExternalAppLink() {
        Object[] arrobject = new Object[]{this.context.getPackageName()};
        return String.format((String)PLAY_STORE_LINK, (Object[])arrobject);
    }

    protected void onPostExecute(Result result) {
        if (this.listener != null) {
            this.listener.onTaskComplete(result);
        }
    }

    public CheckNewAppVersion setOnTaskCompleteListener(ITaskComplete iTaskComplete) {
        this.listener = iTaskComplete;
        return this;
    }

    public class Result {
        private Context context;
        private String newVersionCode;
        private String oldVersionCode;

        public Result(Context context) {
            this(context, "", "");
        }

        public Result(Context context, String string2, String string3) {
            this.context = context;
            this.newVersionCode = string2;
            this.oldVersionCode = string3;
        }

        public Context getContext() {
            return this.context;
        }

        public String getNewVersionCode() {
            return this.newVersionCode;
        }

        public String getOldVersionCode() {
            return this.oldVersionCode;
        }

        public boolean hasNewVersion() {
            return this.onlyNumbers(this.getOldVersionCode()) < this.onlyNumbers(this.getNewVersionCode());
        }

        public long onlyNumbers(String string2) {
            String string3 = string2.replaceAll("\\D+", "");
            try {
                long l = Long.valueOf((String)string3);
                return l;
            }
            catch (Exception exception) {
                return 0L;
            }
        }

        public void openUpdateLink() {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)CheckNewAppVersion.this.getExternalAppLink())));
        }

        public void setNewVersionCode(String string2) {
            this.newVersionCode = string2;
        }

        public void setOldVersionCode(String string2) {
            this.oldVersionCode = string2;
        }
    }

}

