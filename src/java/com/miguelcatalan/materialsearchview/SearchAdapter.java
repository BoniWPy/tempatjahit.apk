/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.Filter
 *  android.widget.Filter$FilterResults
 *  android.widget.Filterable
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.miguelcatalan.materialsearchview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.miguelcatalan.materialsearchview.R;
import java.util.ArrayList;

public class SearchAdapter
extends BaseAdapter
implements Filterable {
    private ArrayList<String> data;
    private boolean ellipsize;
    private LayoutInflater inflater;
    private Drawable suggestionIcon;
    private String[] suggestions;

    public SearchAdapter(Context context, String[] arrstring) {
        this.inflater = LayoutInflater.from((Context)context);
        this.data = new ArrayList();
        this.suggestions = arrstring;
    }

    public SearchAdapter(Context context, String[] arrstring, Drawable drawable2, boolean bl) {
        this.inflater = LayoutInflater.from((Context)context);
        this.data = new ArrayList();
        this.suggestions = arrstring;
        this.suggestionIcon = drawable2;
        this.ellipsize = bl;
    }

    public int getCount() {
        return this.data.size();
    }

    public Filter getFilter() {
        return new Filter(){

            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                Filter.FilterResults filterResults = new Filter.FilterResults();
                if (!TextUtils.isEmpty((CharSequence)charSequence)) {
                    ArrayList arrayList = new ArrayList();
                    for (String string2 : SearchAdapter.this.suggestions) {
                        if (!string2.toLowerCase().startsWith(charSequence.toString().toLowerCase())) continue;
                        arrayList.add((Object)string2);
                    }
                    filterResults.values = arrayList;
                    filterResults.count = arrayList.size();
                }
                return filterResults;
            }

            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                if (filterResults.values != null) {
                    SearchAdapter.this.data = (ArrayList)filterResults.values;
                    SearchAdapter.this.notifyDataSetChanged();
                }
            }
        };
    }

    public Object getItem(int n) {
        return this.data.get(n);
    }

    public long getItemId(int n) {
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View view, ViewGroup viewGroup) {
        SuggestionsViewHolder suggestionsViewHolder;
        if (view == null) {
            view = this.inflater.inflate(R.layout.suggest_item, viewGroup, false);
            suggestionsViewHolder = new SuggestionsViewHolder(view);
            view.setTag((Object)suggestionsViewHolder);
        } else {
            suggestionsViewHolder = (SuggestionsViewHolder)view.getTag();
        }
        String string2 = (String)this.getItem(n);
        suggestionsViewHolder.textView.setText((CharSequence)string2);
        if (this.ellipsize) {
            suggestionsViewHolder.textView.setSingleLine();
            suggestionsViewHolder.textView.setEllipsize(TextUtils.TruncateAt.END);
        }
        return view;
    }

    private class SuggestionsViewHolder {
        ImageView imageView;
        TextView textView;

        public SuggestionsViewHolder(View view) {
            this.textView = (TextView)view.findViewById(R.id.suggestion_text);
            if (SearchAdapter.this.suggestionIcon != null) {
                this.imageView = (ImageView)view.findViewById(R.id.suggestion_icon);
                this.imageView.setImageDrawable(SearchAdapter.this.suggestionIcon);
            }
        }
    }

}

