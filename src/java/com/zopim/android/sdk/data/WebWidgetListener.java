/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.webkit.JavascriptInterface
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  com.zendesk.logger.Logger
 *  java.io.UnsupportedEncodingException
 *  java.lang.CharSequence
 *  java.lang.IllegalStateException
 *  java.lang.IndexOutOfBoundsException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Throwable
 *  java.net.URLDecoder
 *  java.util.concurrent.Executor
 *  java.util.concurrent.Executors
 *  java.util.concurrent.RejectedExecutionException
 */
package com.zopim.android.sdk.data;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.zendesk.logger.Logger;
import com.zopim.android.sdk.data.PathName;
import com.zopim.android.sdk.data.PathUpdater;
import com.zopim.android.sdk.data.PathUpdaterTask;
import com.zopim.android.sdk.data.SerialExecutor;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class WebWidgetListener
extends WebViewClient {
    private static final boolean DEBUG = false;
    private static final String DELIMITER = "z://";
    private static final String ENCODING = "utf-8";
    private static final String LOG_TAG = "WebWidgetListener";
    private static final Executor SERIAL_EXECUTOR = new SerialExecutor((Executor)Executors.newCachedThreadPool());

    @TargetApi(value=11)
    private void executePathUpdate(final String string) {
        if (Build.VERSION.SDK_INT >= 11) {
            new PathUpdaterTask().executeOnExecutor(SERIAL_EXECUTOR, (Object[])new String[]{string});
            return;
        }
        try {
            SERIAL_EXECUTOR.execute(new Runnable(){

                public void run() {
                    new PathUpdater().updatePath(string);
                }
            });
            return;
        }
        catch (RejectedExecutionException rejectedExecutionException) {
            try {
                Logger.w((String)LOG_TAG, (String)"Could not execute path update", (Throwable)rejectedExecutionException, (Object[])new Object[0]);
                return;
            }
            catch (IllegalStateException illegalStateException) {
                Logger.w((String)LOG_TAG, (String)"Could not execute path update due to a state error", (Object[])new Object[0]);
                illegalStateException.printStackTrace();
                return;
            }
        }
    }

    @JavascriptInterface
    public void msg(String string) {
        this.executePathUpdate(string);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean shouldOverrideUrlLoading(WebView webView, String string) {
        if (string != null && string.contains((CharSequence)DELIMITER)) {
            String string2;
            int n = string.indexOf(DELIMITER) + DELIMITER.length();
            try {
                String string3;
                string2 = string3 = string.substring(n);
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                Logger.w((String)LOG_TAG, (String)("Error parsing url. " + indexOutOfBoundsException.getMessage()), (Object[])new Object[0]);
                return true;
            }
            try {
                String string4;
                string2 = string4 = URLDecoder.decode((String)string2, (String)ENCODING);
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                Logger.e((String)LOG_TAG, (String)("Error encoding " + string2), (Object[])new Object[0]);
                unsupportedEncodingException.printStackTrace();
            }
            this.executePathUpdate(string2);
            return true;
        }
        Logger.d((String)LOG_TAG, (String)"Not interested in handling URL loading", (Object[])new Object[0]);
        return true;
    }

}

