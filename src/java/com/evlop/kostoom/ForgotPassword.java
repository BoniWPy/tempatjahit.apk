/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Rect
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.support.annotation.Nullable
 *  android.text.Editable
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.Window
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.Button
 *  android.widget.EditText
 *  com.android.volley.DefaultRetryPolicy
 *  com.android.volley.Request
 *  com.android.volley.RequestQueue
 *  com.android.volley.Response
 *  com.android.volley.Response$ErrorListener
 *  com.android.volley.Response$Listener
 *  com.android.volley.RetryPolicy
 *  com.android.volley.VolleyError
 *  com.android.volley.toolbox.Volley
 *  com.evlop.kostoom.volleyRequest.CustomRequest
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.Map
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.evlop.kostoom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.evlop.kostoom.BaseAppCompatActivity;
import com.evlop.kostoom.volleyRequest.CustomRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPassword
extends BaseAppCompatActivity {
    Button btnForgotPwd;
    private ProgressDialog progress;
    EditText userEmail;

    private void callForgotPwdApi() {
        RequestQueue requestQueue = Volley.newRequestQueue((Context)this.getApplicationContext());
        HashMap hashMap = new HashMap();
        hashMap.put((Object)"email", (Object)this.userEmail.getText().toString());
        CustomRequest customRequest = new CustomRequest(1, "http://social.kostoom.com/api/v1/forgotPwd", (Map)hashMap, (Response.Listener)new Response.Listener<JSONObject>(){

            public void onResponse(JSONObject jSONObject) {
                ForgotPassword.this.progress.dismiss();
                try {
                    String string2 = jSONObject.getString("error");
                    String string3 = jSONObject.getString("message");
                    if (string2.toUpperCase().equals((Object)"FALSE")) {
                        ForgotPassword.this.preToast(string3);
                        ForgotPassword.this.finish();
                        return;
                    }
                    ForgotPassword.this.preToast(string3);
                    return;
                }
                catch (JSONException jSONException) {
                    ForgotPassword.this.progress.dismiss();
                    ForgotPassword.this.preToast("Try again");
                    jSONException.printStackTrace();
                    return;
                }
            }
        }, new Response.ErrorListener(){

            public void onErrorResponse(VolleyError volleyError) {
                ForgotPassword.this.progress.dismiss();
                ForgotPassword.this.preToast("Try again");
                Log.e((String)"TAG", (String)"ERROR in Forgot Password RESPONSE LISTENER");
            }
        });
        customRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(60000, 0, 1.0f));
        requestQueue.add((Request)customRequest);
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

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968674);
        this.userEmail = (EditText)this.findViewById(2131689847);
        this.btnForgotPwd = (Button)this.findViewById(2131689848);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(this.getResources().getColor(2131623972));
        }
        this.btnForgotPwd.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (ForgotPassword.this.userEmail.getText().toString().trim().equals((Object)"")) {
                    ForgotPassword.this.preToast(ForgotPassword.this.getString(2131230999));
                    return;
                }
                if (!BaseAppCompatActivity.isValidEmail(ForgotPassword.this.userEmail.getText().toString().trim())) {
                    ForgotPassword.this.preToast(ForgotPassword.this.getString(2131231002));
                    return;
                }
                if (ForgotPassword.this.isNetworkAvailable(ForgotPassword.this.getApplicationContext())) {
                    ForgotPassword.this.progress = ProgressDialog.show((Context)ForgotPassword.this, (CharSequence)ForgotPassword.this.getString(2131231237), (CharSequence)ForgotPassword.this.getString(2131231124), (boolean)true);
                    ForgotPassword.this.callForgotPwdApi();
                    return;
                }
                ForgotPassword.this.preToast(ForgotPassword.this.getString(2131231081));
            }
        });
    }

}

