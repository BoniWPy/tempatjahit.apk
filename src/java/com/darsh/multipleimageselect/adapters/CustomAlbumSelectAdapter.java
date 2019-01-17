/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.bumptech.glide.DrawableRequestBuilder
 *  com.bumptech.glide.DrawableTypeRequest
 *  com.bumptech.glide.Glide
 *  com.bumptech.glide.request.target.Target
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.darsh.multipleimageselect.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.darsh.multipleimageselect.R;
import com.darsh.multipleimageselect.adapters.CustomGenericAdapter;
import com.darsh.multipleimageselect.models.Album;
import java.util.ArrayList;

public class CustomAlbumSelectAdapter
extends CustomGenericAdapter<Album> {
    public CustomAlbumSelectAdapter(Context context, ArrayList<Album> arrayList) {
        super(context, arrayList);
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.layoutInflater.inflate(R.layout.grid_view_item_album_select, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)view.findViewById(R.id.image_view_album_image);
            viewHolder.textView = (TextView)view.findViewById(R.id.text_view_album_name);
            view.setTag((Object)viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.imageView.getLayoutParams().width = this.size;
        viewHolder.imageView.getLayoutParams().height = this.size;
        viewHolder.textView.setText((CharSequence)((Album)this.arrayList.get((int)n)).name);
        Glide.with((Context)this.context).load(((Album)this.arrayList.get((int)n)).cover).placeholder(R.drawable.image_placeholder).centerCrop().into(viewHolder.imageView);
        return view;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView textView;

        private ViewHolder() {
        }
    }

}

