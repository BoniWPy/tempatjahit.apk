/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  com.bumptech.glide.DrawableRequestBuilder
 *  com.bumptech.glide.DrawableTypeRequest
 *  com.bumptech.glide.Glide
 *  com.bumptech.glide.request.target.Target
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.darsh.multipleimageselect.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.darsh.multipleimageselect.R;
import com.darsh.multipleimageselect.adapters.CustomGenericAdapter;
import com.darsh.multipleimageselect.models.Image;
import java.util.ArrayList;

public class CustomImageSelectAdapter
extends CustomGenericAdapter<Image> {
    public CustomImageSelectAdapter(Context context, ArrayList<Image> arrayList) {
        super(context, arrayList);
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.layoutInflater.inflate(R.layout.grid_view_item_image_select, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)view.findViewById(R.id.image_view_image_select);
            viewHolder.view = view.findViewById(R.id.view_alpha);
            view.setTag((Object)viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.imageView.getLayoutParams().width = this.size;
        viewHolder.imageView.getLayoutParams().height = this.size;
        viewHolder.view.getLayoutParams().width = this.size;
        viewHolder.view.getLayoutParams().height = this.size;
        if (((Image)this.arrayList.get((int)n)).isSelected) {
            viewHolder.view.setAlpha(0.5f);
            ((FrameLayout)view).setForeground(this.context.getResources().getDrawable(R.drawable.ic_done_white));
        } else {
            viewHolder.view.setAlpha(0.0f);
            ((FrameLayout)view).setForeground(null);
        }
        Glide.with((Context)this.context).load(((Image)this.arrayList.get((int)n)).path).placeholder(R.drawable.image_placeholder).into(viewHolder.imageView);
        return view;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public View view;

        private ViewHolder() {
        }
    }

}

