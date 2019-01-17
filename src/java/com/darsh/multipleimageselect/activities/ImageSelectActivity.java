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
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.Display
 *  android.view.Menu
 *  android.view.MenuInflater
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
 *  android.widget.Toast
 *  java.io.File
 *  java.lang.CharSequence
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
import android.view.ActionMode;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.darsh.multipleimageselect.R;
import com.darsh.multipleimageselect.adapters.CustomImageSelectAdapter;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class ImageSelectActivity
extends AppCompatActivity {
    private ActionBar actionBar;
    private ActionMode actionMode;
    private CustomImageSelectAdapter adapter;
    private String album;
    private ActionMode.Callback callback = new ActionMode.Callback(){

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.menu_item_add_image) {
                ImageSelectActivity.this.sendIntent();
                return true;
            }
            return false;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu2) {
            actionMode.getMenuInflater().inflate(R.menu.menu_contextual_action_bar, menu2);
            ImageSelectActivity.this.actionMode = actionMode;
            ImageSelectActivity.this.countSelected = 0;
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            if (ImageSelectActivity.this.countSelected > 0) {
                ImageSelectActivity.this.deselectAll();
            }
            ImageSelectActivity.this.actionMode = null;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu2) {
            return false;
        }
    };
    private int countSelected;
    private TextView errorDisplay;
    private Button grantPermission;
    private GridView gridView;
    private Handler handler;
    private ArrayList<Image> images;
    private ContentObserver observer;
    private ProgressBar progressBar;
    private final String[] projection = new String[]{"_id", "_display_name", "_data"};
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

    private void deselectAll() {
        int n = this.images.size();
        for (int i = 0; i < n; ++i) {
            ((Image)this.images.get((int)i)).isSelected = false;
        }
        this.countSelected = 0;
        this.adapter.notifyDataSetChanged();
    }

    private ArrayList<Image> getSelected() {
        ArrayList arrayList = new ArrayList();
        int n = this.images.size();
        for (int i = 0; i < n; ++i) {
            if (!((Image)this.images.get((int)i)).isSelected) continue;
            arrayList.add(this.images.get(i));
        }
        return arrayList;
    }

    private void hidePermissionHelperUI() {
        this.requestPermission.setVisibility(4);
        this.grantPermission.setVisibility(4);
    }

    private void loadImages() {
        this.abortLoading();
        this.thread = new Thread((Runnable)new ImageLoaderRunnable());
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
            int n2 = n == 1 ? displayMetrics.widthPixels / 3 : displayMetrics.widthPixels / 5;
            this.adapter.setLayoutParams(n2);
        }
        GridView gridView = this.gridView;
        int n3 = n == 1 ? 3 : 5;
        gridView.setNumColumns(n3);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity)this, (String[])this.requiredPermissions, (int)23);
    }

    private void sendIntent() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("images", this.getSelected());
        this.setResult(-1, intent);
        this.finish();
    }

    private void showPermissionHelperUI() {
        this.requestPermission.setVisibility(0);
        this.grantPermission.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void toggleSelection(int n) {
        if (!((Image)this.images.get((int)n)).isSelected && this.countSelected >= Constants.limit) {
            Context context = this.getApplicationContext();
            String string2 = this.getString(R.string.limit_exceeded);
            Object[] arrobject = new Object[]{Constants.limit};
            Toast.makeText((Context)context, (CharSequence)String.format((String)string2, (Object[])arrobject), (int)0).show();
            return;
        }
        Image image = (Image)this.images.get(n);
        boolean bl = !((Image)this.images.get((int)n)).isSelected;
        image.isSelected = bl;
        this.countSelected = ((Image)this.images.get((int)n)).isSelected ? 1 + this.countSelected : -1 + this.countSelected;
        this.adapter.notifyDataSetChanged();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.orientationBasedUI(configuration.orientation);
    }

    protected void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_image_select);
        this.setSupportActionBar((Toolbar)this.findViewById(R.id.toolbar));
        this.actionBar = this.getSupportActionBar();
        if (this.actionBar != null) {
            this.actionBar.setDisplayHomeAsUpEnabled(true);
            this.actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            this.actionBar.setDisplayShowTitleEnabled(true);
            this.actionBar.setTitle(R.string.image_view);
        }
        if ((intent = this.getIntent()) == null) {
            this.finish();
        }
        this.album = intent.getStringExtra("album");
        this.errorDisplay = (TextView)this.findViewById(R.id.text_view_error);
        this.errorDisplay.setVisibility(4);
        this.requestPermission = (TextView)this.findViewById(R.id.text_view_request_permission);
        this.grantPermission = (Button)this.findViewById(R.id.button_grant_permission);
        this.grantPermission.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                ImageSelectActivity.this.requestPermission();
            }
        });
        this.hidePermissionHelperUI();
        this.progressBar = (ProgressBar)this.findViewById(R.id.progress_bar_image_select);
        this.gridView = (GridView)this.findViewById(R.id.grid_view_image_select);
        this.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                if (ImageSelectActivity.this.actionMode == null) {
                    ImageSelectActivity.this.actionMode = ImageSelectActivity.this.startActionMode(ImageSelectActivity.this.callback);
                }
                ImageSelectActivity.this.toggleSelection(n);
                ImageSelectActivity.this.actionMode.setTitle((CharSequence)(ImageSelectActivity.this.countSelected + " " + ImageSelectActivity.this.getString(R.string.selected)));
                if (ImageSelectActivity.this.countSelected == 0) {
                    ImageSelectActivity.this.actionMode.finish();
                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.actionBar != null) {
            this.actionBar.setHomeAsUpIndicator(null);
        }
        this.images = null;
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

            /*
             * Enabled aggressive block sorting
             */
            public void handleMessage(Message message) {
                switch (message.what) {
                    default: {
                        break;
                    }
                    case 2003: {
                        ImageSelectActivity.this.hidePermissionHelperUI();
                        ImageSelectActivity.this.loadImages();
                        return;
                    }
                    case 2004: {
                        ImageSelectActivity.this.showPermissionHelperUI();
                        ImageSelectActivity.this.progressBar.setVisibility(4);
                        ImageSelectActivity.this.gridView.setVisibility(4);
                        return;
                    }
                    case 2001: {
                        ImageSelectActivity.this.progressBar.setVisibility(0);
                        ImageSelectActivity.this.gridView.setVisibility(4);
                        return;
                    }
                    case 2002: {
                        if (ImageSelectActivity.this.adapter == null) {
                            ImageSelectActivity.this.adapter = new CustomImageSelectAdapter(ImageSelectActivity.this.getApplicationContext(), (ArrayList<Image>)ImageSelectActivity.this.images);
                            ImageSelectActivity.this.gridView.setAdapter((ListAdapter)ImageSelectActivity.this.adapter);
                            ImageSelectActivity.this.progressBar.setVisibility(4);
                            ImageSelectActivity.this.gridView.setVisibility(0);
                            ImageSelectActivity.this.orientationBasedUI(ImageSelectActivity.this.getResources().getConfiguration().orientation);
                            return;
                        }
                        ImageSelectActivity.this.adapter.notifyDataSetChanged();
                        if (ImageSelectActivity.this.actionMode == null) return;
                        {
                            ImageSelectActivity.this.countSelected = message.arg1;
                            ImageSelectActivity.this.actionMode.setTitle((CharSequence)(ImageSelectActivity.this.countSelected + " " + ImageSelectActivity.this.getString(R.string.selected)));
                            return;
                        }
                    }
                    case 2005: {
                        ImageSelectActivity.this.progressBar.setVisibility(4);
                        ImageSelectActivity.this.errorDisplay.setVisibility(0);
                    }
                }
                super.handleMessage(message);
            }
        };
        this.observer = new ContentObserver(this.handler){

            public void onChange(boolean bl) {
                ImageSelectActivity.this.loadImages();
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

    private class ImageLoaderRunnable
    implements Runnable {
        private ImageLoaderRunnable() {
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public void run() {
            Process.setThreadPriority((int)10);
            if (ImageSelectActivity.this.adapter == null) {
                Message message = ImageSelectActivity.this.handler.obtainMessage();
                message.what = 2001;
                message.sendToTarget();
            }
            if (Thread.interrupted()) {
                return;
            }
            HashSet hashSet = new HashSet();
            if (ImageSelectActivity.this.images != null) {
                int n = ImageSelectActivity.this.images.size();
                for (int i = 0; i < n; ++i) {
                    Image image = (Image)ImageSelectActivity.this.images.get(i);
                    if (!new File(image.path).exists() || !image.isSelected) continue;
                    hashSet.add((Object)image.id);
                }
            }
            ContentResolver contentResolver = ImageSelectActivity.this.getContentResolver();
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] arrstring = ImageSelectActivity.this.projection;
            String[] arrstring2 = new String[]{ImageSelectActivity.this.album};
            Cursor cursor = contentResolver.query(uri, arrstring, "bucket_display_name =?", arrstring2, "date_added");
            if (cursor == null) {
                Message message = ImageSelectActivity.this.handler.obtainMessage();
                message.what = 2005;
                message.sendToTarget();
                return;
            }
            ArrayList arrayList = new ArrayList(cursor.getCount());
            boolean bl = cursor.moveToLast();
            int n = 0;
            if (bl) {
                do {
                    if (Thread.interrupted()) return;
                    long l = cursor.getLong(cursor.getColumnIndex(ImageSelectActivity.this.projection[0]));
                    String string2 = cursor.getString(cursor.getColumnIndex(ImageSelectActivity.this.projection[1]));
                    String string3 = cursor.getString(cursor.getColumnIndex(ImageSelectActivity.this.projection[2]));
                    boolean bl2 = hashSet.contains((Object)l);
                    if (bl2) {
                        ++n;
                    }
                    if (!new File(string3).exists()) continue;
                    arrayList.add((Object)new Image(l, string2, string3, bl2));
                } while (cursor.moveToPrevious());
            }
            cursor.close();
            if (ImageSelectActivity.this.images == null) {
                ImageSelectActivity.this.images = new ArrayList();
            }
            ImageSelectActivity.this.images.clear();
            ImageSelectActivity.this.images.addAll((Collection)arrayList);
            Message message = ImageSelectActivity.this.handler.obtainMessage();
            message.what = 2002;
            message.arg1 = n;
            message.sendToTarget();
            Thread.interrupted();
        }
    }

}

