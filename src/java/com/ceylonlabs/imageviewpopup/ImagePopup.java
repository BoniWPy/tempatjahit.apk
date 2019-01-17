/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.Display
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.widget.ImageView
 *  android.widget.PopupWindow
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 */
package com.ceylonlabs.imageviewpopup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.ceylonlabs.imageviewpopup.R;

public class ImagePopup
extends ImageView {
    private int backgroundColor = Color.parseColor((String)"#FFFFFF");
    private Context context;
    private boolean hideCloseIcon;
    private boolean imageOnClickClose;
    private PopupWindow popupWindow;
    private int windowHeight = 0;
    private int windowWidth = 0;

    public ImagePopup(Context context) {
        super(context);
        this.context = context;
    }

    public ImagePopup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getWindowHeight() {
        return this.windowHeight;
    }

    public int getWindowWidth() {
        return this.windowWidth;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void initiatePopup(Drawable drawable2) {
        try {
            Context context = this.context;
            View view = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.popup, (ViewGroup)this.findViewById(R.id.popup));
            view.setBackgroundColor(this.getBackgroundColor());
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            imageView.setImageDrawable(drawable2);
            Log.e((String)"Image", (String)("Height--> " + imageView.getDrawable().getMinimumHeight()));
            Log.e((String)"Image", (String)("Width--> " + imageView.getDrawable().getMinimumWidth()));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity)this.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            Log.e((String)"Phone Height", (String)("-->" + displayMetrics.heightPixels));
            Log.e((String)"Phone Width", (String)("-->" + displayMetrics.widthPixels));
            if (this.windowWidth == 0 || this.windowHeight == 0) {
                this.windowHeight = imageView.getDrawable().getMinimumHeight();
                this.windowWidth = imageView.getDrawable().getMinimumWidth();
                if (displayMetrics.heightPixels < imageView.getDrawable().getMinimumHeight() && displayMetrics.widthPixels < imageView.getDrawable().getMinimumWidth()) {
                    if (this.windowHeight < this.windowWidth) {
                        this.windowHeight = -1310 + displayMetrics.heightPixels;
                        this.windowWidth = -250 + displayMetrics.widthPixels;
                    } else {
                        this.windowHeight = -460 + displayMetrics.heightPixels;
                        this.windowWidth = -200 + displayMetrics.widthPixels;
                    }
                }
            }
            this.popupWindow = new PopupWindow(view, this.windowWidth, this.windowHeight, true);
            this.popupWindow.showAtLocation(view, 17, 0, 0);
            ImageView imageView2 = (ImageView)view.findViewById(R.id.closeBtn);
            if (this.isHideCloseIcon()) {
                imageView2.setVisibility(8);
            }
            imageView2.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ImagePopup.this.popupWindow.dismiss();
                }
            });
            if (this.isImageOnClickClose()) {
                imageView.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        ImagePopup.this.popupWindow.dismiss();
                    }
                });
            }
            WindowManager windowManager = (WindowManager)this.context.getSystemService("window");
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams)view.getLayoutParams();
            layoutParams.flags = 2;
            layoutParams.dimAmount = 0.3f;
            windowManager.updateViewLayout(view, (ViewGroup.LayoutParams)layoutParams);
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public boolean isHideCloseIcon() {
        return this.hideCloseIcon;
    }

    public boolean isImageOnClickClose() {
        return this.imageOnClickClose;
    }

    public void setBackgroundColor(int n) {
        this.backgroundColor = n;
    }

    public void setHideCloseIcon(boolean bl) {
        this.hideCloseIcon = bl;
    }

    public void setImageOnClickClose(boolean bl) {
        this.imageOnClickClose = bl;
    }

    public void setWindowHeight(int n) {
        this.windowHeight = n;
    }

    public void setWindowWidth(int n) {
        this.windowWidth = n;
    }

}

