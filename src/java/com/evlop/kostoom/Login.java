/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Rect
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.support.annotation.Nullable
 *  android.support.v7.app.AppCompatActivity
 *  android.text.Editable
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.view.inputmethod.InputMethodManager
 *  android.webkit.WebView
 *  android.webkit.WebViewClient
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.evlop.kostoom.Register
 *  com.evlop.kostoom.async.UserLogin
 *  com.evlop.kostoom.home.CustomerHomeActivity
 *  com.evlop.kostoom.home.TailorHomeActivity
 *  com.facebook.AccessToken
 *  com.facebook.CallbackManager
 *  com.facebook.CallbackManager$Factory
 *  com.facebook.FacebookCallback
 *  com.facebook.FacebookException
 *  com.facebook.FacebookSdk
 *  com.facebook.GraphRequest
 *  com.facebook.GraphRequest$GraphJSONObjectCallback
 *  com.facebook.GraphRequestAsyncTask
 *  com.facebook.GraphResponse
 *  com.facebook.Profile
 *  com.facebook.appevents.AppEventsLogger
 *  com.facebook.login.LoginManager
 *  com.facebook.login.LoginResult
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Arrays
 *  java.util.Collection
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.evlop.kostoom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.evlop.kostoom.BaseAppCompatActivity;
import com.evlop.kostoom.ForgotPassword;
import com.evlop.kostoom.Register;
import com.evlop.kostoom.async.UserLogin;
import com.evlop.kostoom.home.CustomerHomeActivity;
import com.evlop.kostoom.home.TailorHomeActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.Arrays;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public class Login
extends BaseAppCompatActivity
implements View.OnClickListener {
    CallbackManager callbackManager;
    TextView forgotPassword;
    Button login;
    EditText password;
    private ImageView passwordShow;
    boolean passwordVisible = false;
    TextView signup;
    EditText username;

    public static FacebookCallback<LoginResult> facebookLogin(final AppCompatActivity appCompatActivity) {
        return new FacebookCallback<LoginResult>(){

            public void onCancel() {
            }

            public void onError(FacebookException facebookException) {
            }

            public void onSuccess(LoginResult loginResult) {
                final String string2 = loginResult.getAccessToken().getToken();
                GraphRequest graphRequest = GraphRequest.newMeRequest((AccessToken)loginResult.getAccessToken(), (GraphRequest.GraphJSONObjectCallback)new GraphRequest.GraphJSONObjectCallback(){

                    public void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
                        try {
                            UserLogin userLogin = new UserLogin((Context)appCompatActivity, jSONObject.getString("email"), null);
                            userLogin.setToken(string2);
                            userLogin.execute((Object[])new String[0]);
                            return;
                        }
                        catch (JSONException jSONException) {
                            jSONException.printStackTrace();
                            return;
                        }
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fields", "id,name,email");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();
            }

        };
    }

    public void callTermCondition(View view) {
        if (this.isNetworkAvailable(this.getApplicationContext())) {
            AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
            builder.setTitle((CharSequence)this.getString(2131231212));
            final View view2 = this.getLayoutInflater().inflate(2130968803, null);
            builder.setView(view2);
            builder.setPositiveButton((CharSequence)this.getString(2131230984), new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialogInterface, int n) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
            final WebView webView = (WebView)view2.findViewById(2131690242);
            webView.loadUrl("http://163.172.165.10/tc");
            webView.setWebViewClient(new WebViewClient(){

                public void onPageFinished(WebView webView2, String string2) {
                    view2.findViewById(2131689837).setVisibility(8);
                    webView.setVisibility(0);
                    super.onPageFinished(webView2, string2);
                }

                public boolean shouldOverrideUrlLoading(WebView webView2, String string2) {
                    webView2.loadUrl(string2);
                    return true;
                }
            });
            return;
        }
        this.preToast(this.getString(2131231081));
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

    protected void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        this.callbackManager.onActivityResult(n, n2, intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View view) {
        int n = 1;
        switch (view.getId()) {
            default: {
                return;
            }
            case 2131689939: {
                if (this.isNetworkAvailable(this.getApplicationContext())) {
                    new UserLogin((Context)this, this.username.getText().toString(), this.password.getText().toString()).execute((Object[])new String[0]);
                    return;
                }
                this.preToast(this.getString(2131231081));
                return;
            }
            case 2131689937: {
                this.startActivity(new Intent(this.getApplicationContext(), ForgotPassword.class));
                return;
            }
            case 2131689938: {
                this.startActivity(new Intent(this.getApplicationContext(), Register.class));
                return;
            }
            case 2131689940: {
                if (this.isNetworkAvailable(this.getApplicationContext())) {
                    LoginManager loginManager = LoginManager.getInstance();
                    Object[] arrobject = new String[2];
                    arrobject[0] = "public_profile";
                    arrobject[n] = "email";
                    loginManager.logInWithReadPermissions((Activity)this, (Collection)Arrays.asList((Object[])arrobject));
                    return;
                }
                this.preToast(this.getString(2131231081));
                return;
            }
            case 2131689936: 
        }
        if (this.passwordVisible) {
            this.password.setInputType(129);
        } else {
            this.password.setInputType(128);
        }
        if (this.passwordVisible) {
            n = 0;
        }
        this.passwordVisible = n;
        ImageView imageView = this.passwordShow;
        float f = this.passwordVisible ? 0.5f : 1.0f;
        imageView.setAlpha(f);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (CustomerHomeActivity.applicationData((Context)this).getBoolean("user-logged", false)) {
            if (CustomerHomeActivity.applicationData((Context)this.getApplicationContext()).getString("user-type", "customer").equals((Object)"customer")) {
                this.startActivity(new Intent((Context)this, CustomerHomeActivity.class));
            } else {
                this.startActivity(new Intent((Context)this, TailorHomeActivity.class));
            }
            this.finish();
        }
        FacebookSdk.sdkInitialize((Context)this.getApplicationContext());
        AppEventsLogger.activateApp((Context)this);
        this.setContentView(2130968694);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(this.getResources().getColor(2131623972));
        }
        this.forgotPassword = (TextView)this.findViewById(2131689937);
        this.signup = (TextView)this.findViewById(2131689938);
        this.username = (EditText)this.findViewById(2131689934);
        this.password = (EditText)this.findViewById(2131689935);
        this.passwordShow = (ImageView)this.findViewById(2131689936);
        this.passwordShow.setOnClickListener((View.OnClickListener)this);
        this.login = (Button)this.findViewById(2131689939);
        this.forgotPassword.setOnClickListener((View.OnClickListener)this);
        this.signup.setOnClickListener((View.OnClickListener)this);
        this.login.setOnClickListener((View.OnClickListener)this);
        this.callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(this.callbackManager, Login.facebookLogin(this));
        ((Button)this.findViewById(2131689940)).setOnClickListener((View.OnClickListener)this);
        if (Profile.getCurrentProfile() != null) {
            LoginManager.getInstance().logOut();
        }
    }

}

