/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.os.Bundle
 *  android.support.annotation.Nullable
 *  android.util.Log
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ArrayAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.ProgressBar
 *  com.android.volley.DefaultRetryPolicy
 *  com.android.volley.Request
 *  com.android.volley.RequestQueue
 *  com.android.volley.Response
 *  com.android.volley.Response$ErrorListener
 *  com.android.volley.Response$Listener
 *  com.android.volley.RetryPolicy
 *  com.android.volley.VolleyError
 *  com.android.volley.toolbox.Volley
 *  com.evlop.kostoom.model_sewing.ModelSewingListActivity
 *  com.evlop.kostoom.volleyRequest.CustomRequest
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.List
 *  java.util.Map
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.evlop.kostoom.fabric;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.evlop.kostoom.BaseAppCompatActivity;
import com.evlop.kostoom.model_sewing.ModelSewingListActivity;
import com.evlop.kostoom.volleyRequest.CustomRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelSewingCategoryListActivity
extends BaseAppCompatActivity {
    private boolean IS_FROM_FABRIC = false;
    private ArrayList<String> catIdList = new ArrayList();
    private ListView catList;
    private ProgressBar catProgress;

    private void callCategoryApi() {
        RequestQueue requestQueue = Volley.newRequestQueue((Context)this.getApplicationContext());
        HashMap hashMap = new HashMap();
        hashMap.put((Object)"token", (Object)this.sharedPreferences.getString("user-token", ""));
        CustomRequest customRequest = new CustomRequest(1, "http://social.kostoom.com/api/v1/getModelCat", (Map)hashMap, (Response.Listener)new Response.Listener<JSONObject>(){

            public void onResponse(JSONObject jSONObject) {
                ModelSewingCategoryListActivity.this.catProgress.setVisibility(8);
                try {
                    String string = jSONObject.getString("error");
                    String string2 = jSONObject.getString("message");
                    if (string.toUpperCase().equals((Object)"FALSE")) {
                        ModelSewingCategoryListActivity.this.setCategoryData(jSONObject.getJSONArray("data"));
                        return;
                    }
                    ModelSewingCategoryListActivity.this.preToast(string2);
                    return;
                }
                catch (JSONException jSONException) {
                    ModelSewingCategoryListActivity.this.preToast(ModelSewingCategoryListActivity.this.getString(2131231223));
                    jSONException.printStackTrace();
                    return;
                }
            }
        }, new Response.ErrorListener(){

            public void onErrorResponse(VolleyError volleyError) {
                ModelSewingCategoryListActivity.this.catProgress.setVisibility(8);
                ModelSewingCategoryListActivity.this.preToast(ModelSewingCategoryListActivity.this.getString(2131231223));
                Log.e((String)"TAG", (String)"ERROR in get model cat RESPONSE LISTENER");
            }
        });
        customRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(60000, 0, 1.0f));
        requestQueue.add((Request)customRequest);
    }

    private void setCategoryData(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        int n = 0;
        do {
            block6 : {
                if (n < jSONArray.length()) {
                    try {
                        this.catIdList.add((Object)jSONArray.getJSONObject(n).getString("id"));
                        if (this.sharedPreferences.getString("langage", "").equals((Object)"en")) {
                            arrayList.add((Object)jSONArray.getJSONObject(n).getString("name"));
                            break block6;
                        }
                        arrayList.add((Object)jSONArray.getJSONObject(n).getString("in_name"));
                    }
                    catch (JSONException jSONException) {
                        jSONException.printStackTrace();
                    }
                } else {
                    this.catList.setAdapter((ListAdapter)new ArrayAdapter((Context)this, 2130968787, (List)arrayList));
                    return;
                }
            }
            ++n;
        } while (true);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968664);
        this.setUpActionBar(this.getString(2131230927));
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            this.IS_FROM_FABRIC = bundle2.getBoolean("FROM_FABRIC");
        }
        this.catList = (ListView)this.findViewById(2131689804);
        this.catProgress = (ProgressBar)this.findViewById(2131689805);
        this.catList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                if (ModelSewingCategoryListActivity.this.isNetworkAvailable(ModelSewingCategoryListActivity.this.getApplicationContext())) {
                    Intent intent = new Intent(ModelSewingCategoryListActivity.this.getApplicationContext(), ModelSewingListActivity.class);
                    intent.putExtra("ID", (String)ModelSewingCategoryListActivity.this.catIdList.get(n));
                    intent.putExtra("IS_FROM_FABRIC", ModelSewingCategoryListActivity.this.IS_FROM_FABRIC);
                    intent.putExtra("NAME", String.valueOf((Object)adapterView.getItemAtPosition(n)));
                    ModelSewingCategoryListActivity.this.startActivity(intent);
                    return;
                }
                ModelSewingCategoryListActivity.this.preToast(ModelSewingCategoryListActivity.this.getString(2131231081));
            }
        });
        if (this.isNetworkAvailable(this.getApplicationContext())) {
            this.callCategoryApi();
            return;
        }
        this.preToast(this.getString(2131231081));
        this.finish();
    }

}

