/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnKeyListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.widget.AbsListView
 *  android.widget.AdapterView
 *  android.widget.BaseAdapter
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  java.lang.Object
 *  java.lang.Runnable
 */
package com.orhanobut.dialogplus;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ExpandTouchListener;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.HolderAdapter;
import com.orhanobut.dialogplus.OnBackPressListener;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnHolderListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.R;
import com.orhanobut.dialogplus.Utils;
import com.orhanobut.dialogplus.ViewHolder;

public class DialogPlus {
    private static final int INVALID = -1;
    private final ViewGroup contentContainer;
    private final ViewGroup decorView;
    private final Holder holder;
    private final Animation inAnim;
    private final boolean isCancelable;
    private boolean isDismissing;
    private final OnBackPressListener onBackPressListener;
    private final OnCancelListener onCancelListener;
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener(){

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                if (DialogPlus.this.onCancelListener != null) {
                    DialogPlus.this.onCancelListener.onCancel(DialogPlus.this);
                }
                DialogPlus.this.dismiss();
            }
            return false;
        }
    };
    private final OnClickListener onClickListener;
    private final OnDismissListener onDismissListener;
    private final OnItemClickListener onItemClickListener;
    private final Animation outAnim;
    private final ViewGroup rootView;

    DialogPlus(DialogPlusBuilder dialogPlusBuilder) {
        LayoutInflater layoutInflater = LayoutInflater.from((Context)dialogPlusBuilder.getContext());
        Activity activity = (Activity)dialogPlusBuilder.getContext();
        this.holder = dialogPlusBuilder.getHolder();
        this.onItemClickListener = dialogPlusBuilder.getOnItemClickListener();
        this.onClickListener = dialogPlusBuilder.getOnClickListener();
        this.onDismissListener = dialogPlusBuilder.getOnDismissListener();
        this.onCancelListener = dialogPlusBuilder.getOnCancelListener();
        this.onBackPressListener = dialogPlusBuilder.getOnBackPressListener();
        this.isCancelable = dialogPlusBuilder.isCancelable();
        this.decorView = (ViewGroup)activity.getWindow().getDecorView().findViewById(16908290);
        this.rootView = (ViewGroup)layoutInflater.inflate(R.layout.base_container, this.decorView, false);
        this.rootView.setLayoutParams((ViewGroup.LayoutParams)dialogPlusBuilder.getOutmostLayoutParams());
        this.contentContainer = (ViewGroup)this.rootView.findViewById(R.id.content_container);
        this.contentContainer.setLayoutParams((ViewGroup.LayoutParams)dialogPlusBuilder.getContentParams());
        this.outAnim = dialogPlusBuilder.getOutAnimation();
        this.inAnim = dialogPlusBuilder.getInAnimation();
        this.initContentView(layoutInflater, dialogPlusBuilder.getHeaderView(), dialogPlusBuilder.getFooterView(), dialogPlusBuilder.getAdapter(), dialogPlusBuilder.getContentPadding(), dialogPlusBuilder.getContentMargin());
        this.initCancelable();
        if (dialogPlusBuilder.isExpanded()) {
            this.initExpandAnimator(activity, dialogPlusBuilder.getDefaultContentHeight(), dialogPlusBuilder.getContentParams().gravity);
        }
    }

    private void assignClickListenerRecursively(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup)view;
            for (int i = -1 + viewGroup.getChildCount(); i >= 0; --i) {
                this.assignClickListenerRecursively(viewGroup.getChildAt(i));
            }
        }
        this.setClickListener(view);
    }

    private View createView(LayoutInflater layoutInflater, View view, View view2, BaseAdapter baseAdapter) {
        View view3 = this.holder.getView(layoutInflater, this.rootView);
        if (this.holder instanceof ViewHolder) {
            this.assignClickListenerRecursively(view3);
        }
        this.assignClickListenerRecursively(view);
        this.holder.addHeader(view);
        this.assignClickListenerRecursively(view2);
        this.holder.addFooter(view2);
        if (baseAdapter != null && this.holder instanceof HolderAdapter) {
            HolderAdapter holderAdapter = (HolderAdapter)this.holder;
            holderAdapter.setAdapter(baseAdapter);
            holderAdapter.setOnItemClickListener(new OnHolderListener(){

                @Override
                public void onItemClick(Object object, View view, int n) {
                    if (DialogPlus.this.onItemClickListener == null) {
                        return;
                    }
                    DialogPlus.this.onItemClickListener.onItemClick(DialogPlus.this, object, view, n);
                }
            });
        }
        return view3;
    }

    private void initCancelable() {
        if (!this.isCancelable) {
            return;
        }
        this.rootView.findViewById(R.id.outmost_container).setOnTouchListener(this.onCancelableTouchListener);
    }

    private void initContentView(LayoutInflater layoutInflater, View view, View view2, BaseAdapter baseAdapter, int[] arrn, int[] arrn2) {
        View view3 = this.createView(layoutInflater, view, view2, baseAdapter);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(arrn2[0], arrn2[1], arrn2[2], arrn2[3]);
        view3.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.getHolderView().setPadding(arrn[0], arrn[1], arrn[2], arrn[3]);
        this.contentContainer.addView(view3);
    }

    private void initExpandAnimator(Activity activity, int n, int n2) {
        View view;
        int n3 = activity.getWindowManager().getDefaultDisplay().getHeight() - Utils.getStatusBarHeight((Context)activity);
        if (n == 0) {
            n = n3 * 2 / 5;
        }
        if (!((view = this.holder.getInflatedView()) instanceof AbsListView)) {
            return;
        }
        view.setOnTouchListener((View.OnTouchListener)ExpandTouchListener.newListener((Context)activity, (AbsListView)view, (View)this.contentContainer, n2, n3, n));
    }

    public static DialogPlusBuilder newDialog(Context context) {
        return new DialogPlusBuilder(context);
    }

    private void onAttached(View view) {
        this.decorView.addView(view);
        this.contentContainer.startAnimation(this.inAnim);
        this.contentContainer.requestFocus();
        this.holder.setOnKeyListener(new View.OnKeyListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                switch (keyEvent.getAction()) {
                    default: {
                        return false;
                    }
                    case 1: {
                        if (n != 4) return false;
                        if (DialogPlus.this.onBackPressListener != null) {
                            DialogPlus.this.onBackPressListener.onBackPressed(DialogPlus.this);
                        }
                        if (!DialogPlus.this.isCancelable) return true;
                        {
                            DialogPlus.this.onBackPressed(DialogPlus.this);
                        }
                        return true;
                    }
                }
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setClickListener(View view) {
        if (view.getId() == -1 || view instanceof AdapterView) {
            return;
        }
        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (DialogPlus.this.onClickListener == null) {
                    return;
                }
                DialogPlus.this.onClickListener.onClick(DialogPlus.this, view);
            }
        });
    }

    public void dismiss() {
        if (this.isDismissing) {
            return;
        }
        this.outAnim.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                DialogPlus.this.decorView.post(new Runnable(){

                    public void run() {
                        DialogPlus.this.decorView.removeView((View)DialogPlus.this.rootView);
                        DialogPlus.this.isDismissing = false;
                        if (DialogPlus.this.onDismissListener != null) {
                            DialogPlus.this.onDismissListener.onDismiss(DialogPlus.this);
                        }
                    }
                });
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

        });
        this.contentContainer.startAnimation(this.outAnim);
        this.isDismissing = true;
    }

    public View findViewById(int n) {
        return this.contentContainer.findViewById(n);
    }

    public View getFooterView() {
        return this.holder.getFooter();
    }

    public View getHeaderView() {
        return this.holder.getHeader();
    }

    public View getHolderView() {
        return this.holder.getInflatedView();
    }

    public boolean isShowing() {
        return this.decorView.findViewById(R.id.outmost_container) != null;
    }

    public void onBackPressed(DialogPlus dialogPlus) {
        if (this.onCancelListener != null) {
            this.onCancelListener.onCancel(this);
        }
        this.dismiss();
    }

    public void show() {
        if (this.isShowing()) {
            return;
        }
        this.onAttached((View)this.rootView);
    }

}

