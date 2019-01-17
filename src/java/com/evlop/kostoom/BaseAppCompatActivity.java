/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.graphics.drawable.Drawable
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.NetworkInfo$State
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.provider.MediaStore
 *  android.provider.MediaStore$Images
 *  android.provider.MediaStore$Images$Media
 *  android.support.annotation.Nullable
 *  android.support.v7.app.ActionBar
 *  android.support.v7.app.AppCompatActivity
 *  android.util.Base64
 *  android.util.Log
 *  android.util.Patterns
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.Toast
 *  com.evlop.kostoom.custom.RupiahCurrencyFormat
 *  com.evlop.kostoom.home.CustomerHomeActivity
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map
 *  java.util.Map$Entry
 *  java.util.Set
 *  java.util.UUID
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.evlop.kostoom;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.evlop.kostoom.custom.RupiahCurrencyFormat;
import com.evlop.kostoom.home.CustomerHomeActivity;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseAppCompatActivity
extends AppCompatActivity {
    RupiahCurrencyFormat rcf = new RupiahCurrencyFormat();
    public SharedPreferences sharedPreferences;

    private boolean checkAppInstall(String string2) {
        PackageManager packageManager = this.getPackageManager();
        try {
            packageManager.getPackageInfo(string2, 1);
            return true;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException) {
            return false;
        }
    }

    public static final boolean isValidEmail(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public void SharingToSocialMedia(String string2, String string3, Uri uri) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.SUBJECT", "Kostoom App");
        intent.putExtra("android.intent.extra.TEXT", string3);
        intent.putExtra("android.intent.extra.STREAM", (Parcelable)uri);
        if (this.checkAppInstall(string2)) {
            intent.setPackage(string2);
            this.startActivity(intent);
            return;
        }
        this.preToast("Installed application first");
    }

    public Map<String, String> checkParams(Map<String, String> map) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() != null) continue;
            map.put(entry.getKey(), (Object)"");
        }
        return map;
    }

    public void deBug(String string2) {
        Log.e((String)"DEBUG", (String)string2);
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)byteArrayOutputStream);
        return Base64.encodeToString((byte[])byteArrayOutputStream.toByteArray(), (int)0);
    }

    public Uri getImageUri(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)byteArrayOutputStream);
        return Uri.parse((String)MediaStore.Images.Media.insertImage((ContentResolver)this.getContentResolver(), (Bitmap)bitmap, (String)(UUID.randomUUID().toString() + ".png"), (String)"drawing"));
    }

    public String getIndoRate(double d) {
        return "Rp. " + this.rcf.toRupiahFormat(String.valueOf((double)d));
    }

    public String getIndoRateNew(String string2) {
        return new RupiahCurrencyFormat().toRupiahFormat(String.valueOf((Object)string2));
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager)this.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isNetworkAvailable(Context context) {
        NetworkInfo[] arrnetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (connectivityManager != null && (arrnetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
            for (int i = 0; i < arrnetworkInfo.length; ++i) {
                if (arrnetworkInfo[i].getState() != NetworkInfo.State.CONNECTED) continue;
                return true;
            }
        }
        return false;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.sharedPreferences = CustomerHomeActivity.applicationData((Context)this.getApplicationContext());
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void preToast(String string2) {
        Toast.makeText((Context)this.getApplicationContext(), (CharSequence)string2, (int)0).show();
    }

    public void setUpActionBar(String string2) {
        this.setTitle((CharSequence)string2);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable drawable2 = this.getResources().getDrawable(2130903067);
        this.getSupportActionBar().setHomeAsUpIndicator(drawable2);
    }

    public void showKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager)this.getSystemService("input_method")).showSoftInput(view, 1);
        }
    }
}

