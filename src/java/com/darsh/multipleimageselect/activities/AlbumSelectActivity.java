/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.database.ContentObserver
 *  android.database.Cursor
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Message
 *  android.os.Process
 *  android.provider.MediaStore
 *  android.provider.MediaStore$Images
 *  android.provider.MediaStore$Images$Media
 *  android.support.v4.app.ActivityCompat
 *  android.support.v4.content.ContextCompat
 *  android.support.v7.app.ActionBar
 *  android.support.v7.app.AppCompatActivity
 *  android.support.v7.widget.Toolbar
 *  android.util.DisplayMetrics
 *  android.view.Display
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.WindowManager
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.Button
 *  android.widget.GridView
 *  android.widget.ListAdapter
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  java.io.File
 *  java.lang.Class
 *  java.lang.InterruptedException
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.Collection
 *  java.util.HashSet
 */
package com.darsh.multipleimageselect.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.darsh.multipleimageselect.R;
import com.darsh.multipleimageselect.activities.ImageSelectActivity;
import com.darsh.multipleimageselect.adapters.CustomAlbumSelectAdapter;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Album;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class AlbumSelectActivity
extends AppCompatActivity {
    private final String TAG = AlbumSelectActivity.class.getName();
    private ActionBar actionBar;
    private CustomAlbumSelectAdapter adapter;
    private ArrayList<Album> albums;
    private TextView errorDisplay;
    private Button grantPermission;
    private GridView gridView;
    private Handler handler;
    private ContentObserver observer;
    private ProgressBar progressBar;
    private final String[] projection = new String[]{"bucket_display_name", "_data"};
    private TextView requestPermission;
    private final String[] requiredPermissions = new String[]{"android.permission.READ_EXTERNAL_STORAGE"};
    private Thread thread;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void abortLoading() {
        if (this.thread == null || !this.thread.isAlive()) {
            return;
        }
        this.thread.interrupt();
        try {
            this.thread.join();
            return;
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            return;
        }
    }

    private void checkIfPermissionGranted() {
        if (ContextCompat.checkSelfPermission((Context)this, (String)"android.permission.READ_EXTERNAL_STORAGE") != 0) {
            this.requestPermission();
            return;
        }
        Message message = this.handler.obtainMessage();
        message.what = 2003;
        message.sendToTarget();
    }

    private void hidePermissionHelperUI() {
        this.requestPermission.setVisibility(4);
        this.grantPermission.setVisibility(4);
    }

    private void loadAlbums() {
        this.abortLoading();
        this.thread = new Thread((Runnable)new AlbumLoaderRunnable());
        this.thread.start();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void orientationBasedUI(int n) {
        WindowManager windowManager = (WindowManager)this.getApplicationContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (this.adapter != null) {
            int n2 = n == 1 ? displayMetrics.widthPixels / 2 : displayMetrics.widthPixels / 4;
            this.adapter.setLayoutParams(n2);
        }
        GridView gridView = this.gridView;
        int n3 = n == 1 ? 2 : 4;
        gridView.setNumColumns(n3);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity)this, (String[])this.requiredPermissions, (int)23);
    }

    private void showPermissionHelperUI() {
        this.requestPermission.setVisibility(0);
        this.grantPermission.setVisibility(0);
    }

    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 2000 && n2 == -1 && intent != null) {
            this.setResult(-1, intent);
            this.finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.setResult(0);
        this.finish();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.orientationBasedUI(configuration.orientation);
    }

    protected void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_album_select);
        this.setSupportActionBar((Toolbar)this.findViewById(R.id.toolbar));
        this.actionBar = this.getSupportActionBar();
        if (this.actionBar != null) {
            this.actionBar.setDisplayHomeAsUpEnabled(true);
            this.actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            this.actionBar.setDisplayShowTitleEnabled(true);
            this.actionBar.setTitle(R.string.album_view);
        }
        if ((intent = this.getIntent()) == null) {
            this.finish();
        }
        Constants.limit = intent.getIntExtra("limit", 10);
        this.errorDisplay = (TextView)this.findViewById(R.id.text_view_error);
        this.errorDisplay.setVisibility(4);
        this.requestPermission = (TextView)this.findViewById(R.id.text_view_request_permission);
        this.grantPermission = (Button)this.findViewById(R.id.button_grant_permission);
        this.grantPermission.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                AlbumSelectActivity.this.requestPermission();
            }
        });
        this.hidePermissionHelperUI();
        this.progressBar = (ProgressBar)this.findViewById(R.id.progress_bar_album_select);
        this.gridView = (GridView)this.findViewById(R.id.grid_view_album_select);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                Intent intent = new Intent(AlbumSelectActivity.this.getApplicationContext(), ImageSelectActivity.class);
                intent.putExtra("album", ((Album)AlbumSelectActivity.access$100((AlbumSelectActivity)AlbumSelectActivity.this).get((int)n)).name);
                AlbumSelectActivity.this.startActivityForResult(intent, 2000);
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.actionBar != null) {
            this.actionBar.setHomeAsUpIndicator(null);
        }
        this.albums = null;
        if (this.adapter != null) {
            this.adapter.releaseResources();
        }
        this.gridView.setOnItemClickListener(null);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return false;
            }
            case 16908332: 
        }
        this.onBackPressed();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onRequestPermissionsResult(int n, String[] arrstring, int[] arrn) {
        if (n == 23) {
            Message message = this.handler.obtainMessage();
            int n2 = arrn.length > 0 && arrn[0] == 0 ? 2003 : 2004;
            message.what = n2;
            message.sendToTarget();
        }
    }

    protected void onStart() {
        super.onStart();
        this.handler = new Handler(){

            public void handleMessage(Message message) {
                switch (message.what) {
                    default: {
                        super.handleMessage(message);
                        return;
                    }
                    case 2003: {
                        AlbumSelectActivity.this.hidePermissionHelperUI();
                        AlbumSelectActivity.this.loadAlbums();
                        return;
                    }
                    case 2004: {
                        AlbumSelectActivity.this.showPermissionHelperUI();
                        AlbumSelectActivity.this.progressBar.setVisibility(4);
                        AlbumSelectActivity.this.gridView.setVisibility(4);
                        return;
                    }
                    case 2001: {
                        AlbumSelectActivity.this.progressBar.setVisibility(0);
                        AlbumSelectActivity.this.gridView.setVisibility(4);
                        return;
                    }
                    case 2002: {
                        if (AlbumSelectActivity.this.adapter == null) {
                            AlbumSelectActivity.this.adapter = new CustomAlbumSelectAdapter(AlbumSelectActivity.this.getApplicationContext(), (ArrayList<Album>)AlbumSelectActivity.this.albums);
                            AlbumSelectActivity.this.gridView.setAdapter((ListAdapter)AlbumSelectActivity.this.adapter);
                            AlbumSelectActivity.this.progressBar.setVisibility(4);
                            AlbumSelectActivity.this.gridView.setVisibility(0);
                            AlbumSelectActivity.this.orientationBasedUI(AlbumSelectActivity.this.getResources().getConfiguration().orientation);
                            return;
                        }
                        AlbumSelectActivity.this.adapter.notifyDataSetChanged();
                        return;
                    }
                    case 2005: 
                }
                AlbumSelectActivity.this.progressBar.setVisibility(4);
                AlbumSelectActivity.this.errorDisplay.setVisibility(0);
            }
        };
        this.observer = new ContentObserver(this.handler){

            public void onChange(boolean bl, Uri uri) {
                AlbumSelectActivity.this.loadAlbums();
            }
        };
        this.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, this.observer);
        this.checkIfPermissionGranted();
    }

    protected void onStop() {
        super.onStop();
        this.abortLoading();
        this.getContentResolver().unregisterContentObserver(this.observer);
        this.observer = null;
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }

    private class AlbumLoaderRunnable
    implements Runnable {
        private AlbumLoaderRunnable() {
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public void run() {
            Process.setThreadPriority((int)10);
            if (AlbumSelectActivity.this.adapter == null) {
                Message message = AlbumSelectActivity.this.handler.obtainMessage();
                message.what = 2001;
                message.sendToTarget();
            }
            if (Thread.interrupted()) {
                return;
            }
            Cursor cursor = AlbumSelectActivity.this.getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, AlbumSelectActivity.this.projection, null, null, "date_added");
            if (cursor == null) {
                Message message = AlbumSelectActivity.this.handler.obtainMessage();
                message.what = 2005;
                message.sendToTarget();
                return;
            }
            ArrayList arrayList = new ArrayList(cursor.getCount());
            HashSet hashSet = new HashSet();
            if (cursor.moveToLast()) {
                do {
                    if (Thread.interrupted()) return;
                    String string2 = cursor.getString(cursor.getColumnIndex(AlbumSelectActivity.this.projection[0]));
                    String string3 = cursor.getString(cursor.getColumnIndex(AlbumSelectActivity.this.projection[1]));
                    if (!new File(string3).exists() || hashSet.contains((Object)string2)) continue;
                    arrayList.add((Object)new Album(string2, string3));
                    hashSet.add((Object)string2);
                } while (cursor.moveToPrevious());
            }
            cursor.close();
            if (AlbumSelectActivity.this.albums == null) {
                AlbumSelectActivity.this.albums = new ArrayList();
            }
            AlbumSelectActivity.this.albums.clear();
            AlbumSelectActivity.this.albums.addAll((Collection)arrayList);
            Message message = AlbumSelectActivity.this.handler.obtainMessage();
            message.what = 2002;
            message.sendToTarget();
            Thread.interrupted();
        }
    }

}

