/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Bundle
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  com.squareup.picasso.Callback
 *  com.squareup.picasso.Picasso
 *  com.squareup.picasso.RequestCreator
 *  java.io.File
 *  java.lang.Enum
 *  java.lang.IllegalStateException
 *  java.lang.NoSuchFieldError
 *  java.lang.Object
 *  java.lang.String
 */
package com.daimajia.slider.library.SliderTypes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.daimajia.slider.library.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.File;

public abstract class BaseSliderView {
    private Bundle mBundle;
    protected Context mContext;
    private String mDescription;
    private int mEmptyPlaceHolderRes;
    private boolean mErrorDisappear;
    private int mErrorPlaceHolderRes;
    private File mFile;
    private ImageLoadListener mLoadListener;
    protected OnSliderClickListener mOnSliderClickListener;
    private Picasso mPicasso;
    private int mRes;
    private ScaleType mScaleType = ScaleType.Fit;
    private String mUrl;

    protected BaseSliderView(Context context) {
        this.mContext = context;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void bindEventAndShow(final View var1_1, ImageView var2_2) {
        block13 : {
            var1_1.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    if (BaseSliderView.this.mOnSliderClickListener != null) {
                        BaseSliderView.this.mOnSliderClickListener.onSliderClick(this);
                    }
                }
            });
            if (var2_2 == null) {
                return;
            }
            if (this.mLoadListener != null) {
                this.mLoadListener.onStart(this);
            }
            var3_3 = this.mPicasso != null ? this.mPicasso : Picasso.with((Context)this.mContext);
            if (this.mUrl != null) {
                var4_4 = var3_3.load(this.mUrl);
            } else if (this.mFile != null) {
                var4_4 = var3_3.load(this.mFile);
            } else {
                if (this.mRes == 0) return;
                var4_4 = var3_3.load(this.mRes);
            }
            if (var4_4 == null) return;
            if (this.getEmpty() != 0) {
                var4_4.placeholder(this.getEmpty());
            }
            if (this.getError() != 0) {
                var4_4.error(this.getError());
            }
            switch (3.$SwitchMap$com$daimajia$slider$library$SliderTypes$BaseSliderView$ScaleType[this.mScaleType.ordinal()]) {
                case 1: {
                    var4_4.fit();
                    ** break;
                }
                case 2: {
                    var4_4.fit().centerCrop();
                }
lbl26: // 3 sources:
                default: {
                    break block13;
                }
                case 3: 
            }
            var4_4.fit().centerInside();
        }
        var4_4.into(var2_2, new Callback(){

            public void onError() {
                if (BaseSliderView.this.mLoadListener != null) {
                    BaseSliderView.this.mLoadListener.onEnd(false, this);
                }
                if (var1_1.findViewById(R.id.loading_bar) != null) {
                    var1_1.findViewById(R.id.loading_bar).setVisibility(4);
                }
            }

            public void onSuccess() {
                if (var1_1.findViewById(R.id.loading_bar) != null) {
                    var1_1.findViewById(R.id.loading_bar).setVisibility(4);
                }
            }
        });
    }

    public BaseSliderView bundle(Bundle bundle) {
        this.mBundle = bundle;
        return this;
    }

    public BaseSliderView description(String string2) {
        this.mDescription = string2;
        return this;
    }

    public BaseSliderView empty(int n) {
        this.mEmptyPlaceHolderRes = n;
        return this;
    }

    public BaseSliderView error(int n) {
        this.mErrorPlaceHolderRes = n;
        return this;
    }

    public BaseSliderView errorDisappear(boolean bl) {
        this.mErrorDisappear = bl;
        return this;
    }

    public Bundle getBundle() {
        return this.mBundle;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getEmpty() {
        return this.mEmptyPlaceHolderRes;
    }

    public int getError() {
        return this.mErrorPlaceHolderRes;
    }

    public Picasso getPicasso() {
        return this.mPicasso;
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public abstract View getView();

    public BaseSliderView image(int n) {
        if (this.mUrl != null || this.mFile != null) {
            throw new IllegalStateException("Call multi image function,you only have permission to call it once");
        }
        this.mRes = n;
        return this;
    }

    public BaseSliderView image(File file) {
        if (this.mUrl != null || this.mRes != 0) {
            throw new IllegalStateException("Call multi image function,you only have permission to call it once");
        }
        this.mFile = file;
        return this;
    }

    public BaseSliderView image(String string2) {
        if (this.mFile != null || this.mRes != 0) {
            throw new IllegalStateException("Call multi image function,you only have permission to call it once");
        }
        this.mUrl = string2;
        return this;
    }

    public boolean isErrorDisappear() {
        return this.mErrorDisappear;
    }

    public void setOnImageLoadListener(ImageLoadListener imageLoadListener) {
        this.mLoadListener = imageLoadListener;
    }

    public BaseSliderView setOnSliderClickListener(OnSliderClickListener onSliderClickListener) {
        this.mOnSliderClickListener = onSliderClickListener;
        return this;
    }

    public void setPicasso(Picasso picasso) {
        this.mPicasso = picasso;
    }

    public BaseSliderView setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
        return this;
    }

    public static interface ImageLoadListener {
        public void onEnd(boolean var1, BaseSliderView var2);

        public void onStart(BaseSliderView var1);
    }

    public static interface OnSliderClickListener {
        public void onSliderClick(BaseSliderView var1);
    }

    public static final class ScaleType
    extends Enum<ScaleType> {
        private static final /* synthetic */ ScaleType[] $VALUES;
        public static final /* enum */ ScaleType CenterCrop = new ScaleType();
        public static final /* enum */ ScaleType CenterInside = new ScaleType();
        public static final /* enum */ ScaleType Fit = new ScaleType();
        public static final /* enum */ ScaleType FitCenterCrop = new ScaleType();

        static {
            ScaleType[] arrscaleType = new ScaleType[]{CenterCrop, CenterInside, Fit, FitCenterCrop};
            $VALUES = arrscaleType;
        }

        public static ScaleType valueOf(String string2) {
            return (ScaleType)Enum.valueOf(ScaleType.class, (String)string2);
        }

        public static ScaleType[] values() {
            return (ScaleType[])$VALUES.clone();
        }
    }

}

