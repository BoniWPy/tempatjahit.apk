/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.Display
 *  android.view.View
 *  android.view.WindowManager
 *  android.view.animation.Animation
 *  android.view.animation.AnimationUtils
 *  android.widget.BaseAdapter
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  java.lang.Deprecated
 *  java.lang.NullPointerException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 */
package com.orhanobut.dialogplus;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.R;
import com.orhanobut.dialogplus.Utils;
import java.util.Arrays;

public class DialogPlusBuilder {
    private static final int INVALID = -1;
    private BaseAdapter adapter;
    private int backgroundColorResourceId = -1;
    private Context context;
    private int defaultContentHeight;
    private boolean expanded;
    private View footerView;
    private int footerViewResourceId = -1;
    private int gravity = 80;
    private View headerView;
    private int headerViewResourceId = -1;
    private Holder holder;
    private int inAnimation = -1;
    private boolean isCancelable = true;
    private final int[] margin = new int[4];
    private OnBackPressListener onBackPressListener;
    private OnCancelListener onCancelListener;
    private OnClickListener onClickListener;
    private OnDismissListener onDismissListener;
    private OnItemClickListener onItemClickListener;
    private int outAnimation = -1;
    private final int[] outMostMargin = new int[4];
    private final int[] padding = new int[4];
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -2, 80);

    private DialogPlusBuilder() {
    }

    DialogPlusBuilder(Context context) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        this.context = context;
        Arrays.fill((int[])this.margin, (int)-1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int getMargin(int n, int n2, int n3) {
        switch (n) {
            default: {
                if (n2 != -1) return n2;
                return 0;
            }
            case 17: 
        }
        if (n2 == -1) return n3;
        return n2;
    }

    private void init() {
        if (this.backgroundColorResourceId == -1) {
            this.backgroundColorResourceId = 17170443;
        }
        this.getHolder().setBackgroundColor(this.backgroundColorResourceId);
    }

    public DialogPlus create() {
        this.init();
        return new DialogPlus(this);
    }

    public BaseAdapter getAdapter() {
        return this.adapter;
    }

    public int[] getContentMargin() {
        int n = this.context.getResources().getDimensionPixelSize(R.dimen.default_center_margin);
        for (int i = 0; i < this.margin.length; ++i) {
            this.margin[i] = this.getMargin(this.gravity, this.margin[i], n);
        }
        return this.margin;
    }

    public int[] getContentPadding() {
        return this.padding;
    }

    public FrameLayout.LayoutParams getContentParams() {
        if (this.expanded) {
            this.params.height = this.defaultContentHeight;
        }
        return this.params;
    }

    public Context getContext() {
        return this.context;
    }

    public int getDefaultContentHeight() {
        Activity activity = (Activity)this.context;
        int n = activity.getWindowManager().getDefaultDisplay().getHeight() - Utils.getStatusBarHeight((Context)activity);
        if (this.defaultContentHeight == 0) {
            this.defaultContentHeight = n * 2 / 5;
        }
        return this.defaultContentHeight;
    }

    public View getFooterView() {
        return Utils.getView(this.context, this.footerViewResourceId, this.footerView);
    }

    public View getHeaderView() {
        return Utils.getView(this.context, this.headerViewResourceId, this.headerView);
    }

    public Holder getHolder() {
        if (this.holder == null) {
            this.holder = new ListHolder();
        }
        return this.holder;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Animation getInAnimation() {
        int n;
        if (this.inAnimation == -1) {
            n = Utils.getAnimationResource(this.gravity, true);
            do {
                return AnimationUtils.loadAnimation((Context)this.context, (int)n);
                break;
            } while (true);
        }
        n = this.inAnimation;
        return AnimationUtils.loadAnimation((Context)this.context, (int)n);
    }

    public OnBackPressListener getOnBackPressListener() {
        return this.onBackPressListener;
    }

    public OnCancelListener getOnCancelListener() {
        return this.onCancelListener;
    }

    public OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public OnDismissListener getOnDismissListener() {
        return this.onDismissListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Animation getOutAnimation() {
        int n;
        if (this.outAnimation == -1) {
            n = Utils.getAnimationResource(this.gravity, false);
            do {
                return AnimationUtils.loadAnimation((Context)this.context, (int)n);
                break;
            } while (true);
        }
        n = this.outAnimation;
        return AnimationUtils.loadAnimation((Context)this.context, (int)n);
    }

    public FrameLayout.LayoutParams getOutmostLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(this.outMostMargin[0], this.outMostMargin[1], this.outMostMargin[2], this.outMostMargin[3]);
        return layoutParams;
    }

    public boolean isCancelable() {
        return this.isCancelable;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public DialogPlusBuilder setAdapter(BaseAdapter baseAdapter) {
        if (baseAdapter == null) {
            throw new NullPointerException("Adapter may not be null");
        }
        this.adapter = baseAdapter;
        return this;
    }

    public DialogPlusBuilder setBackgroundColorResourceId(int n) {
        this.backgroundColorResourceId = n;
        return this;
    }

    public DialogPlusBuilder setCancelable(boolean bl) {
        this.isCancelable = bl;
        return this;
    }

    public DialogPlusBuilder setContentHeight(int n) {
        this.params.height = n;
        return this;
    }

    public DialogPlusBuilder setContentHolder(Holder holder) {
        this.holder = holder;
        return this;
    }

    public DialogPlusBuilder setContentWidth(int n) {
        this.params.width = n;
        return this;
    }

    public DialogPlusBuilder setExpanded(boolean bl) {
        this.expanded = bl;
        return this;
    }

    public DialogPlusBuilder setExpanded(boolean bl, int n) {
        this.expanded = bl;
        this.defaultContentHeight = n;
        return this;
    }

    public DialogPlusBuilder setFooter(int n) {
        this.footerViewResourceId = n;
        return this;
    }

    public DialogPlusBuilder setFooter(View view) {
        this.footerView = view;
        return this;
    }

    public DialogPlusBuilder setGravity(int n) {
        this.gravity = n;
        this.params.gravity = n;
        return this;
    }

    public DialogPlusBuilder setHeader(int n) {
        this.headerViewResourceId = n;
        return this;
    }

    public DialogPlusBuilder setHeader(View view) {
        this.headerView = view;
        return this;
    }

    public DialogPlusBuilder setInAnimation(int n) {
        this.inAnimation = n;
        return this;
    }

    public DialogPlusBuilder setMargin(int n, int n2, int n3, int n4) {
        this.margin[0] = n;
        this.margin[1] = n2;
        this.margin[2] = n3;
        this.margin[3] = n4;
        return this;
    }

    @Deprecated
    public DialogPlusBuilder setMargins(int n, int n2, int n3, int n4) {
        this.margin[0] = n;
        this.margin[1] = n2;
        this.margin[2] = n3;
        this.margin[3] = n4;
        return this;
    }

    public DialogPlusBuilder setOnBackPressListener(OnBackPressListener onBackPressListener) {
        this.onBackPressListener = onBackPressListener;
        return this;
    }

    public DialogPlusBuilder setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public DialogPlusBuilder setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public DialogPlusBuilder setOnDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public DialogPlusBuilder setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public DialogPlusBuilder setOutAnimation(int n) {
        this.outAnimation = n;
        return this;
    }

    public DialogPlusBuilder setOutMostMargin(int n, int n2, int n3, int n4) {
        this.outMostMargin[0] = n;
        this.outMostMargin[1] = n2;
        this.outMostMargin[2] = n3;
        this.outMostMargin[3] = n4;
        return this;
    }

    public DialogPlusBuilder setPadding(int n, int n2, int n3, int n4) {
        this.padding[0] = n;
        this.padding[1] = n2;
        this.padding[2] = n3;
        this.padding[3] = n4;
        return this;
    }
}

