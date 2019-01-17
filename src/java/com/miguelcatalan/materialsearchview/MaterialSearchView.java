/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.res.TypedArray
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.IBinder
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.MenuItem
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.View
 *  android.view.View$BaseSavedState
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.ViewGroup
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.EditText
 *  android.widget.Filter
 *  android.widget.Filter$FilterListener
 *  android.widget.Filterable
 *  android.widget.FrameLayout
 *  android.widget.ImageButton
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$1
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$2
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$3
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$4
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$5
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$6
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$7
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$OnQueryTextListener
 *  com.miguelcatalan.materialsearchview.MaterialSearchView$SearchViewListener
 *  com.miguelcatalan.materialsearchview.utils.AnimationUtil$AnimationListener
 *  java.lang.CharSequence
 *  java.lang.Exception
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 *  java.util.List
 */
package com.miguelcatalan.materialsearchview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.miguelcatalan.materialsearchview.R;
import com.miguelcatalan.materialsearchview.SearchAdapter;
import com.miguelcatalan.materialsearchview.utils.AnimationUtil;
import java.lang.reflect.Field;
import java.util.List;

public class MaterialSearchView
extends FrameLayout
implements Filter.FilterListener {
    public static final int REQUEST_VOICE = 9999;
    private boolean allowVoiceSearch;
    private boolean ellipsize = false;
    private ListAdapter mAdapter;
    private int mAnimationDuration;
    private ImageButton mBackBtn;
    private boolean mClearingFocus;
    private Context mContext;
    private ImageButton mEmptyBtn;
    private boolean mIsSearchOpen = false;
    private MenuItem mMenuItem;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener = new 4(this);
    private OnQueryTextListener mOnQueryChangeListener;
    private SavedState mSavedState;
    private View mSearchLayout;
    private EditText mSearchSrcTextView;
    private RelativeLayout mSearchTopBar;
    private SearchViewListener mSearchViewListener;
    private ListView mSuggestionsListView;
    private View mTintView;
    private CharSequence mUserQuery;
    private ImageButton mVoiceBtn;
    private boolean submit = false;
    private Drawable suggestionIcon;

    public MaterialSearchView(Context context) {
        this(context, null);
    }

    public MaterialSearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialSearchView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet);
        this.mContext = context;
        this.initiateView();
        this.initStyle(attributeSet, n);
    }

    static /* synthetic */ void access$000(MaterialSearchView materialSearchView) {
        materialSearchView.onSubmitQuery();
    }

    static /* synthetic */ boolean access$1000(MaterialSearchView materialSearchView) {
        return materialSearchView.submit;
    }

    static /* synthetic */ CharSequence access$102(MaterialSearchView materialSearchView, CharSequence charSequence) {
        materialSearchView.mUserQuery = charSequence;
        return charSequence;
    }

    static /* synthetic */ SearchViewListener access$1100(MaterialSearchView materialSearchView) {
        return materialSearchView.mSearchViewListener;
    }

    static /* synthetic */ void access$200(MaterialSearchView materialSearchView, CharSequence charSequence) {
        materialSearchView.startFilter(charSequence);
    }

    static /* synthetic */ void access$300(MaterialSearchView materialSearchView, CharSequence charSequence) {
        materialSearchView.onTextChanged(charSequence);
    }

    static /* synthetic */ EditText access$400(MaterialSearchView materialSearchView) {
        return materialSearchView.mSearchSrcTextView;
    }

    static /* synthetic */ ImageButton access$500(MaterialSearchView materialSearchView) {
        return materialSearchView.mBackBtn;
    }

    static /* synthetic */ ImageButton access$600(MaterialSearchView materialSearchView) {
        return materialSearchView.mVoiceBtn;
    }

    static /* synthetic */ void access$700(MaterialSearchView materialSearchView) {
        materialSearchView.onVoiceClicked();
    }

    static /* synthetic */ ImageButton access$800(MaterialSearchView materialSearchView) {
        return materialSearchView.mEmptyBtn;
    }

    static /* synthetic */ View access$900(MaterialSearchView materialSearchView) {
        return materialSearchView.mTintView;
    }

    private void initSearchView() {
        this.mSearchSrcTextView.setOnEditorActionListener((TextView.OnEditorActionListener)new 1(this));
        this.mSearchSrcTextView.addTextChangedListener((TextWatcher)new 2(this));
        this.mSearchSrcTextView.setOnFocusChangeListener((View.OnFocusChangeListener)new 3(this));
    }

    private void initStyle(AttributeSet attributeSet, int n) {
        TypedArray typedArray = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.MaterialSearchView, n, 0);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.MaterialSearchView_searchBackground)) {
                this.setBackground(typedArray.getDrawable(R.styleable.MaterialSearchView_searchBackground));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_android_textColor)) {
                this.setTextColor(typedArray.getColor(R.styleable.MaterialSearchView_android_textColor, 0));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_android_textColorHint)) {
                this.setHintTextColor(typedArray.getColor(R.styleable.MaterialSearchView_android_textColorHint, 0));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_android_hint)) {
                this.setHint(typedArray.getString(R.styleable.MaterialSearchView_android_hint));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_searchVoiceIcon)) {
                this.setVoiceIcon(typedArray.getDrawable(R.styleable.MaterialSearchView_searchVoiceIcon));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_searchCloseIcon)) {
                this.setCloseIcon(typedArray.getDrawable(R.styleable.MaterialSearchView_searchCloseIcon));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_searchBackIcon)) {
                this.setBackIcon(typedArray.getDrawable(R.styleable.MaterialSearchView_searchBackIcon));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_searchSuggestionBackground)) {
                this.setSuggestionBackground(typedArray.getDrawable(R.styleable.MaterialSearchView_searchSuggestionBackground));
            }
            if (typedArray.hasValue(R.styleable.MaterialSearchView_searchSuggestionIcon)) {
                this.setSuggestionIcon(typedArray.getDrawable(R.styleable.MaterialSearchView_searchSuggestionIcon));
            }
            typedArray.recycle();
        }
    }

    private void initiateView() {
        LayoutInflater.from((Context)this.mContext).inflate(R.layout.search_view, (ViewGroup)this, true);
        this.mSearchLayout = this.findViewById(R.id.search_layout);
        this.mSearchTopBar = (RelativeLayout)this.mSearchLayout.findViewById(R.id.search_top_bar);
        this.mSuggestionsListView = (ListView)this.mSearchLayout.findViewById(R.id.suggestion_list);
        this.mSearchSrcTextView = (EditText)this.mSearchLayout.findViewById(R.id.searchTextView);
        this.mBackBtn = (ImageButton)this.mSearchLayout.findViewById(R.id.action_up_btn);
        this.mVoiceBtn = (ImageButton)this.mSearchLayout.findViewById(R.id.action_voice_btn);
        this.mEmptyBtn = (ImageButton)this.mSearchLayout.findViewById(R.id.action_empty_btn);
        this.mTintView = this.mSearchLayout.findViewById(R.id.transparent_view);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mBackBtn.setOnClickListener(this.mOnClickListener);
        this.mVoiceBtn.setOnClickListener(this.mOnClickListener);
        this.mEmptyBtn.setOnClickListener(this.mOnClickListener);
        this.mTintView.setOnClickListener(this.mOnClickListener);
        this.allowVoiceSearch = false;
        this.showVoice(true);
        this.initSearchView();
        this.mSuggestionsListView.setVisibility(8);
        this.setAnimationDuration(AnimationUtil.ANIMATION_DURATION_MEDIUM);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean isVoiceAvailable() {
        return this.isInEditMode() || this.getContext().getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 0).size() == 0;
    }

    private void onSubmitQuery() {
        Editable editable = this.mSearchSrcTextView.getText();
        if (!(editable == null || TextUtils.getTrimmedLength((CharSequence)editable) <= 0 || this.mOnQueryChangeListener != null && this.mOnQueryChangeListener.onQueryTextSubmit(editable.toString()))) {
            this.closeSearch();
            this.mSearchSrcTextView.setText(null);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onTextChanged(CharSequence charSequence) {
        Editable editable = this.mSearchSrcTextView.getText();
        this.mUserQuery = editable;
        boolean bl = !TextUtils.isEmpty((CharSequence)editable);
        if (bl) {
            this.mEmptyBtn.setVisibility(0);
            this.showVoice(false);
        } else {
            this.mEmptyBtn.setVisibility(8);
            this.showVoice(true);
        }
        if (this.mOnQueryChangeListener != null && !TextUtils.equals((CharSequence)charSequence, (CharSequence)this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }

    private void onVoiceClicked() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        intent.putExtra("android.speech.extra.MAX_RESULTS", 1);
        if (this.mContext instanceof Activity) {
            ((Activity)this.mContext).startActivityForResult(intent, 9999);
        }
    }

    private void setVisibleWithAnimation() {
        7 var1_1 = new 7(this);
        if (Build.VERSION.SDK_INT >= 21) {
            this.mSearchLayout.setVisibility(0);
            AnimationUtil.reveal((View)this.mSearchTopBar, var1_1);
            return;
        }
        AnimationUtil.fadeInView(this.mSearchLayout, this.mAnimationDuration, var1_1);
    }

    private void startFilter(CharSequence charSequence) {
        if (this.mAdapter != null && this.mAdapter instanceof Filterable) {
            ((Filterable)this.mAdapter).getFilter().filter(charSequence, (Filter.FilterListener)this);
        }
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        this.hideKeyboard((View)this);
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mClearingFocus = false;
    }

    public void closeSearch() {
        if (!this.isSearchOpen()) {
            return;
        }
        this.mSearchSrcTextView.setText(null);
        this.dismissSuggestions();
        this.clearFocus();
        this.mSearchLayout.setVisibility(8);
        if (this.mSearchViewListener != null) {
            this.mSearchViewListener.onSearchViewClosed();
        }
        this.mIsSearchOpen = false;
    }

    public void dismissSuggestions() {
        if (this.mSuggestionsListView.getVisibility() == 0) {
            this.mSuggestionsListView.setVisibility(8);
        }
    }

    public void hideKeyboard(View view) {
        ((InputMethodManager)view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean isSearchOpen() {
        return this.mIsSearchOpen;
    }

    public void onFilterComplete(int n) {
        if (n > 0) {
            this.showSuggestions();
            return;
        }
        this.dismissSuggestions();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        this.mSavedState = (SavedState)parcelable;
        if (this.mSavedState.isSearchOpen) {
            this.showSearch(false);
            this.setQuery(this.mSavedState.query, false);
        }
        super.onRestoreInstanceState(this.mSavedState.getSuperState());
    }

    /*
     * Enabled aggressive block sorting
     */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = this.mSavedState = new SavedState(super.onSaveInstanceState());
        String string2 = this.mUserQuery != null ? this.mUserQuery.toString() : null;
        savedState.query = string2;
        this.mSavedState.isSearchOpen = this.mIsSearchOpen;
        return this.mSavedState;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean requestFocus(int n, Rect rect) {
        if (this.mClearingFocus || !this.isFocusable()) {
            return false;
        }
        return this.mSearchSrcTextView.requestFocus(n, rect);
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
        this.mSuggestionsListView.setAdapter(listAdapter);
        this.startFilter((CharSequence)this.mSearchSrcTextView.getText());
    }

    public void setAnimationDuration(int n) {
        this.mAnimationDuration = n;
    }

    public void setBackIcon(Drawable drawable2) {
        this.mBackBtn.setImageDrawable(drawable2);
    }

    public void setBackground(Drawable drawable2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mSearchTopBar.setBackground(drawable2);
            return;
        }
        this.mSearchTopBar.setBackgroundDrawable(drawable2);
    }

    public void setBackgroundColor(int n) {
        this.mSearchTopBar.setBackgroundColor(n);
    }

    public void setCloseIcon(Drawable drawable2) {
        this.mEmptyBtn.setImageDrawable(drawable2);
    }

    public void setCursorDrawable(int n) {
        try {
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            field.set((Object)this.mSearchSrcTextView, (Object)n);
            return;
        }
        catch (Exception exception) {
            Log.e((String)"MaterialSearchView", (String)exception.toString());
            return;
        }
    }

    public void setEllipsize(boolean bl) {
        this.ellipsize = bl;
    }

    public void setHint(CharSequence charSequence) {
        this.mSearchSrcTextView.setHint(charSequence);
    }

    public void setHintTextColor(int n) {
        this.mSearchSrcTextView.setHintTextColor(n);
    }

    public void setMenuItem(MenuItem menuItem) {
        this.mMenuItem = menuItem;
        this.mMenuItem.setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener)new 6(this));
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mSuggestionsListView.setOnItemClickListener(onItemClickListener);
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnSearchViewListener(SearchViewListener searchViewListener) {
        this.mSearchViewListener = searchViewListener;
    }

    public void setQuery(CharSequence charSequence, boolean bl) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            this.mSearchSrcTextView.setSelection(this.mSearchSrcTextView.length());
            this.mUserQuery = charSequence;
        }
        if (bl && !TextUtils.isEmpty((CharSequence)charSequence)) {
            this.onSubmitQuery();
        }
    }

    public void setSubmitOnClick(boolean bl) {
        this.submit = bl;
    }

    public void setSuggestionBackground(Drawable drawable2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mSuggestionsListView.setBackground(drawable2);
            return;
        }
        this.mSuggestionsListView.setBackgroundDrawable(drawable2);
    }

    public void setSuggestionIcon(Drawable drawable2) {
        this.suggestionIcon = drawable2;
    }

    public void setSuggestions(String[] arrstring) {
        if (arrstring != null && arrstring.length > 0) {
            this.mTintView.setVisibility(0);
            SearchAdapter searchAdapter = new SearchAdapter(this.mContext, arrstring, this.suggestionIcon, this.ellipsize);
            this.setAdapter((ListAdapter)searchAdapter);
            this.setOnItemClickListener((AdapterView.OnItemClickListener)new 5(this, searchAdapter));
            return;
        }
        this.mTintView.setVisibility(8);
    }

    public void setTextColor(int n) {
        this.mSearchSrcTextView.setTextColor(n);
    }

    public void setVoiceIcon(Drawable drawable2) {
        this.mVoiceBtn.setImageDrawable(drawable2);
    }

    public void setVoiceSearch(boolean bl) {
        this.allowVoiceSearch = bl;
    }

    public void showKeyboard(View view) {
        if (Build.VERSION.SDK_INT <= 10 && view.hasFocus()) {
            view.clearFocus();
        }
        view.requestFocus();
        ((InputMethodManager)view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public void showSearch() {
        this.showSearch(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void showSearch(boolean bl) {
        if (this.isSearchOpen()) {
            return;
        }
        this.mSearchSrcTextView.setText(null);
        this.mSearchSrcTextView.requestFocus();
        if (bl) {
            this.setVisibleWithAnimation();
        } else {
            this.mSearchLayout.setVisibility(0);
            if (this.mSearchViewListener != null) {
                this.mSearchViewListener.onSearchViewShown();
            }
        }
        this.mIsSearchOpen = true;
    }

    public void showSuggestions() {
        if (this.mAdapter != null && this.mAdapter.getCount() > 0 && this.mSuggestionsListView.getVisibility() == 8) {
            this.mSuggestionsListView.setVisibility(0);
        }
    }

    public void showVoice(boolean bl) {
        if (bl && this.isVoiceAvailable() && this.allowVoiceSearch) {
            this.mVoiceBtn.setVisibility(0);
            return;
        }
        this.mVoiceBtn.setVisibility(8);
    }

    static class SavedState
    extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>(){

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int n) {
                return new SavedState[n];
            }
        };
        boolean isSearchOpen;
        String query;

        /*
         * Enabled aggressive block sorting
         */
        private SavedState(Parcel parcel) {
            int n = 1;
            super(parcel);
            this.query = parcel.readString();
            if (parcel.readInt() != n) {
                n = 0;
            }
            this.isSearchOpen = n;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void writeToParcel(Parcel parcel, int n) {
            super.writeToParcel(parcel, n);
            parcel.writeString(this.query);
            int n2 = this.isSearchOpen ? 1 : 0;
            parcel.writeInt(n2);
        }

    }

}

