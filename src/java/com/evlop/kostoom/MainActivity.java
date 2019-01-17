/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.graphics.Rect
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.support.v4.app.Fragment
 *  android.support.v4.app.FragmentManager
 *  android.support.v4.app.FragmentTransaction
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.EditText
 *  com.evlop.kostoom.fragmets.ItemList
 *  com.evlop.kostoom.fragmets.ProjectFeeds
 *  com.evlop.kostoom.interfaces.GoBack
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedList
 *  java.util.Queue
 */
package com.evlop.kostoom;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.evlop.kostoom.BaseAppCompatActivity;
import com.evlop.kostoom.fragmets.ItemList;
import com.evlop.kostoom.fragmets.ProjectFeeds;
import com.evlop.kostoom.interfaces.GoBack;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity
extends BaseAppCompatActivity {
    public static MainActivity application;
    public Fragment current;
    public Queue<Fragment> next = new LinkedList();
    public Queue<Fragment> previous = new LinkedList();

    public static SharedPreferences applicationData(Context context) {
        return context.getSharedPreferences("data", 0);
    }

    private void startApp() {
        this.openHome();
    }

    public void clearSteps() {
        this.next.clear();
        this.previous.clear();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View view;
        if (motionEvent.getAction() == 0 && (view = this.getCurrentFocus()) instanceof EditText) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            if (!rect.contains((int)motionEvent.getRawX(), (int)motionEvent.getRawY())) {
                view.clearFocus();
                ((InputMethodManager)this.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onBackPressed() {
        if (this.current instanceof GoBack) {
            ((GoBack)this.current).goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setUpActionBar("");
        this.setContentView(2130968611);
        this.startApp();
    }

    public boolean onCreateOptionsMenu(Menu menu2) {
        this.getMenuInflater().inflate(2131755014, menu2);
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void openFargment(Fragment fragment) {
        this.current = fragment;
        this.getSupportFragmentManager().beginTransaction().replace(2131689686, fragment).commitAllowingStateLoss();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void openHome() {
        block16 : {
            var1_1 = MainActivity.applicationData(this.getApplicationContext()).getString("user-type", this.getString(2131230971));
            var2_2 = -1;
            switch (var1_1.hashCode()) {
                case 606175198: {
                    if (var1_1.equals((Object)"customer")) {
                        var2_2 = 0;
                        ** break;
                    }
                    ** GOTO lbl17
                }
                case -693316975: {
                    if (var1_1.equals((Object)"pelanggan")) {
                        var2_2 = 1;
                        ** break;
                    }
                    ** GOTO lbl17
                }
                case -881168845: {
                    if (var1_1.equals((Object)"tailor")) {
                        var2_2 = 2;
                    }
                }
lbl17: // 8 sources:
                default: {
                    break block16;
                }
                case 319913347: 
            }
            if (var1_1.equals((Object)"penjahit")) {
                var2_2 = 3;
            }
        }
        switch (var2_2) {
            default: {
                return;
            }
            case 0: {
                this.openFargment((Fragment)new ItemList());
                return;
            }
            case 1: {
                this.openFargment((Fragment)new ItemList());
                return;
            }
            case 2: {
                this.openFargment((Fragment)new ProjectFeeds());
            }
            case 3: 
        }
        this.openFargment((Fragment)new ProjectFeeds());
    }
}

