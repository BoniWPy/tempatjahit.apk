/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.app.ProgressDialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Bundle
 *  android.support.v7.app.ActionBar
 *  android.support.v7.widget.Toolbar
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.android.volley.DefaultRetryPolicy
 *  com.android.volley.Request
 *  com.android.volley.RequestQueue
 *  com.android.volley.Response
 *  com.android.volley.Response$ErrorListener
 *  com.android.volley.Response$Listener
 *  com.android.volley.RetryPolicy
 *  com.android.volley.toolbox.Volley
 *  com.bumptech.glide.BitmapTypeRequest
 *  com.bumptech.glide.DrawableRequestBuilder
 *  com.bumptech.glide.DrawableTypeRequest
 *  com.bumptech.glide.Glide
 *  com.bumptech.glide.request.animation.GlideAnimation
 *  com.bumptech.glide.request.target.SimpleTarget
 *  com.bumptech.glide.request.target.Target
 *  com.evlop.kostoom.Portfolio.ImageFullScreenActivity
 *  com.evlop.kostoom.fabric.FabricCartActivity
 *  com.evlop.kostoom.fabric.FabricSummaryActivity
 *  com.evlop.kostoom.fabric.FabricViewActivity$10
 *  com.evlop.kostoom.fabric.FabricViewActivity$11
 *  com.evlop.kostoom.fabric.FabricViewActivity$12
 *  com.evlop.kostoom.fabric.FabricViewActivity$13
 *  com.evlop.kostoom.home.CustomerHomeActivity
 *  com.evlop.kostoom.model.FabricCartModel
 *  com.evlop.kostoom.model.GalleryModel
 *  com.evlop.kostoom.model.ModelSewingCart
 *  com.evlop.kostoom.model_sewing.ModelSewingActivity
 *  com.evlop.kostoom.volleyRequest.CustomRequest
 *  com.facebook.share.model.ShareContent
 *  com.facebook.share.model.ShareContent$Builder
 *  com.facebook.share.model.ShareHashtag
 *  com.facebook.share.model.ShareHashtag$Builder
 *  com.facebook.share.model.ShareLinkContent
 *  com.facebook.share.model.ShareLinkContent$Builder
 *  com.facebook.share.widget.ShareDialog
 *  java.io.Serializable
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Date
 *  java.util.HashMap
 *  java.util.List
 *  java.util.Locale
 *  java.util.Map
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.evlop.kostoom.fabric;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.evlop.kostoom.BaseAppCompatActivity;
import com.evlop.kostoom.Portfolio.ImageFullScreenActivity;
import com.evlop.kostoom.fabric.FabricCartActivity;
import com.evlop.kostoom.fabric.FabricSummaryActivity;
import com.evlop.kostoom.fabric.FabricViewActivity;
import com.evlop.kostoom.home.CustomerHomeActivity;
import com.evlop.kostoom.model.FabricCartModel;
import com.evlop.kostoom.model.GalleryModel;
import com.evlop.kostoom.model.ModelSewingCart;
import com.evlop.kostoom.model_sewing.ModelSewingActivity;
import com.evlop.kostoom.volleyRequest.CustomRequest;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FabricViewActivity
extends BaseAppCompatActivity {
    private int AVAIL_STOCK = 0;
    private String BANNER = "";
    private String BRAND_ID = "0";
    private String COLOR_CODE = "";
    private String FABRIC_ID = "";
    private boolean IS_FROM_MODEL = false;
    private int QTY = 0;
    private int RATE = 0;
    private Bundle bundle;
    private ArrayList<GalleryModel> dataGalleryModelArrayList = new ArrayList();
    private ImageView imgFabricProduct1;
    private ImageView imgFabricProduct2;
    private ImageView imgFabricProduct3;
    private ImageView imgFabricProduct4;
    private ImageView imgFabricProduct5;
    private ImageView imgHeader;
    private ImageView imgMinusQty;
    private ImageView imgPlusQty;
    private ShareDialog shareDialog;
    private TextView txtBrandName;
    private TextView txtBuyFabric;
    private TextView txtColor;
    private TextView txtDesc;
    private TextView txtPrice;
    private TextView txtProductName;
    private TextView txtQty;
    private TextView txtSewingNow;
    private TextView txtTotal;
    private View viewColor;

    static /* synthetic */ int access$1300(FabricViewActivity fabricViewActivity) {
        return fabricViewActivity.AVAIL_STOCK;
    }

    static /* synthetic */ String access$1400(FabricViewActivity fabricViewActivity) {
        return fabricViewActivity.BRAND_ID;
    }

    static /* synthetic */ void access$1500(FabricViewActivity fabricViewActivity, JSONObject jSONObject) {
        fabricViewActivity.fillData(jSONObject);
    }

    static /* synthetic */ int access$308(FabricViewActivity fabricViewActivity) {
        int n = fabricViewActivity.QTY;
        fabricViewActivity.QTY = n + 1;
        return n;
    }

    static /* synthetic */ int access$310(FabricViewActivity fabricViewActivity) {
        int n = fabricViewActivity.QTY;
        fabricViewActivity.QTY = n - 1;
        return n;
    }

    private void callProductDetailApi() {
        ProgressDialog progressDialog = ProgressDialog.show((Context)this, (CharSequence)this.getString(2131231024), (CharSequence)this.getString(2131231124), (boolean)true);
        RequestQueue requestQueue = Volley.newRequestQueue((Context)this.getApplicationContext());
        HashMap hashMap = new HashMap();
        hashMap.put((Object)"product_id", (Object)this.bundle.getString("ID"));
        hashMap.put((Object)"token", (Object)this.sharedPreferences.getString("user-token", ""));
        this.deBug(hashMap.toString());
        CustomRequest customRequest = new CustomRequest(1, "http://social.kostoom.com/api/v1/getProduct", (Map)hashMap, (Response.Listener)new 12(this, progressDialog), (Response.ErrorListener)new 13(this, progressDialog));
        customRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(60000, 0, 1.0f));
        requestQueue.add((Request)customRequest);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void fillData(JSONObject jSONObject) {
        block18 : {
            try {
                this.FABRIC_ID = jSONObject.getString("id");
                if (jSONObject.getString("banner").equals((Object)"")) {
                    Glide.with((Context)this.getApplicationContext()).load("https://www.moh.gov.bh/Content/Upload/Image/636009821114059242-not-available.jpg").placeholder(2130837800).into(this.imgHeader);
                } else {
                    this.BANNER = jSONObject.getString("banner");
                    Glide.with((Context)this.getApplicationContext()).load(jSONObject.getString("banner")).asBitmap().into((Target)new SimpleTarget<Bitmap>(){

                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                            FabricViewActivity.this.findViewById(2131690295).setVisibility(8);
                            FabricViewActivity.this.imgHeader.setImageBitmap(bitmap);
                        }
                    });
                }
                if (this.sharedPreferences.getString("langage", "").equals((Object)"en")) {
                    this.txtDesc.setText((CharSequence)jSONObject.getString("description"));
                } else {
                    this.txtDesc.setText((CharSequence)jSONObject.getString("in_description"));
                }
                this.txtProductName.setText((CharSequence)jSONObject.getString("name"));
                this.AVAIL_STOCK = Integer.parseInt((String)jSONObject.getString("stock"));
                this.BRAND_ID = jSONObject.getString("brand_id");
                this.txtBrandName.setText((CharSequence)jSONObject.getString("brand_name"));
                this.txtPrice.setText((CharSequence)jSONObject.getString("price"));
                this.RATE = Integer.parseInt((String)jSONObject.getString("price_n"));
                this.txtColor.setText((CharSequence)jSONObject.getString("color_name"));
                this.COLOR_CODE = jSONObject.getString("color_code");
                this.viewColor.setBackgroundColor(Color.parseColor((String)jSONObject.getString("color_code")));
                if (jSONObject.getJSONArray("images").length() == 0) break block18;
                this.findViewById(2131690302).setVisibility(0);
            }
            catch (JSONException jSONException) {
                jSONException.printStackTrace();
                return;
            }
        }
        int n = 0;
        while (n < jSONObject.getJSONArray("images").length()) {
            GalleryModel galleryModel = new GalleryModel();
            galleryModel.setImage_path(jSONObject.getJSONArray("images").getJSONObject(n).getString("image"));
            if (n == 0) {
                this.imgFabricProduct1.setVisibility(0);
                Glide.with((Context)this.getApplicationContext()).load(jSONObject.getJSONArray("images").getJSONObject(n).getString("image")).placeholder(2130837800).into(this.imgFabricProduct1);
            } else if (n == 1) {
                this.imgFabricProduct2.setVisibility(0);
                Glide.with((Context)this.getApplicationContext()).load(jSONObject.getJSONArray("images").getJSONObject(n).getString("image")).placeholder(2130837800).into(this.imgFabricProduct2);
            } else if (n == 2) {
                this.imgFabricProduct3.setVisibility(0);
                Glide.with((Context)this.getApplicationContext()).load(jSONObject.getJSONArray("images").getJSONObject(n).getString("image")).placeholder(2130837800).into(this.imgFabricProduct3);
            } else if (n == 3) {
                this.imgFabricProduct4.setVisibility(0);
                Glide.with((Context)this.getApplicationContext()).load(jSONObject.getJSONArray("images").getJSONObject(n).getString("image")).placeholder(2130837800).into(this.imgFabricProduct4);
            } else if (n == 4) {
                this.imgFabricProduct5.setVisibility(0);
                Glide.with((Context)this.getApplicationContext()).load(jSONObject.getJSONArray("images").getJSONObject(n).getString("image")).placeholder(2130837800).into(this.imgFabricProduct5);
            }
            this.dataGalleryModelArrayList.add((Object)galleryModel);
            ++n;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initControl() {
        Toolbar toolbar = (Toolbar)this.findViewById(2131689666);
        toolbar.setNavigationIcon(this.getResources().getDrawable(2130903067));
        toolbar.setTitle((CharSequence)"");
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.imgHeader = (ImageView)this.findViewById(2131690296);
        ((LinearLayout)this.findViewById(2131690299)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                ShareHashtag shareHashtag = new ShareHashtag.Builder().setHashtag("#Kostoom").build();
                ShareLinkContent shareLinkContent = ((ShareLinkContent.Builder)((ShareLinkContent.Builder)new ShareLinkContent.Builder().setShareHashtag(shareHashtag)).setContentUrl(Uri.parse((String)FabricViewActivity.this.BANNER))).setImageUrl(Uri.parse((String)"https://play.google.com/store/apps/details?id=com.evlop.kostoom")).setQuote(FabricViewActivity.this.txtBrandName.getText().toString()).build();
                FabricViewActivity.this.shareDialog.show((Object)shareLinkContent);
            }
        });
        this.txtBuyFabric = (TextView)this.findViewById(2131690308);
        this.txtSewingNow = (TextView)this.findViewById(2131690310);
        if (this.IS_FROM_MODEL) {
            this.findViewById(2131690309).setVisibility(8);
        }
        this.txtSewingNow.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (FabricViewActivity.this.QTY == 0) {
                    FabricViewActivity.this.preToast(FabricViewActivity.this.getString(2131231179));
                    return;
                }
                new AlertDialog.Builder((Context)FabricViewActivity.this).setCancelable(false).setTitle(2131230952).setMessage((CharSequence)(FabricViewActivity.this.getString(2131231208) + " " + FabricViewActivity.this.QTY + " " + FabricViewActivity.this.getString(2131231147) + "?")).setPositiveButton(2131231134, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        dialogInterface.dismiss();
                        FabricViewActivity.this.preToast(FabricViewActivity.this.getString(2131231206));
                        FabricCartModel fabricCartModel = new FabricCartModel();
                        fabricCartModel.setFabric_id(FabricViewActivity.this.FABRIC_ID);
                        fabricCartModel.setName(FabricViewActivity.this.bundle.getString("NAME"));
                        fabricCartModel.setImage(FabricViewActivity.this.BANNER);
                        fabricCartModel.setPrice(FabricViewActivity.this.RATE);
                        fabricCartModel.setColor(FabricViewActivity.this.txtColor.getText().toString());
                        fabricCartModel.setColor_code(FabricViewActivity.this.COLOR_CODE);
                        fabricCartModel.setQuantity(FabricViewActivity.this.QTY);
                        fabricCartModel.setDate(new SimpleDateFormat("dd MMM yyyy", Locale.US).format(new Date()));
                        fabricCartModel.save();
                        FabricViewActivity.this.finish();
                        Intent intent = new Intent(FabricViewActivity.this.getApplicationContext(), ModelSewingActivity.class);
                        FabricViewActivity.this.sharedPreferences.edit().putString("FABRIC_ID", FabricViewActivity.this.FABRIC_ID).apply();
                        intent.putExtra("FROM_FABRIC", true);
                        FabricViewActivity.this.startActivity(intent);
                    }
                }).setNegativeButton(2131230921, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }

        });
        this.txtBuyFabric.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (FabricViewActivity.this.QTY == 0) {
                    FabricViewActivity.this.preToast(FabricViewActivity.this.getString(2131231179));
                    return;
                }
                if (FabricViewActivity.this.IS_FROM_MODEL) {
                    List list = ModelSewingCart.listAll(ModelSewingCart.class);
                    for (int i = 0; i < list.size(); ++i) {
                        if (!((ModelSewingCart)list.get(i)).getModel_id().equals((Object)FabricViewActivity.this.sharedPreferences.getString("MODEL_ID", ""))) continue;
                        ((ModelSewingCart)list.get(i)).setModel_fabric(FabricViewActivity.this.FABRIC_ID);
                        ((ModelSewingCart)list.get(i)).save();
                    }
                    FabricCartModel fabricCartModel = new FabricCartModel();
                    fabricCartModel.setFabric_id(FabricViewActivity.this.FABRIC_ID);
                    fabricCartModel.setName(FabricViewActivity.this.bundle.getString("NAME"));
                    fabricCartModel.setImage(FabricViewActivity.this.BANNER);
                    fabricCartModel.setPrice(FabricViewActivity.this.RATE);
                    fabricCartModel.setColor(FabricViewActivity.this.txtColor.getText().toString());
                    fabricCartModel.setColor_code(FabricViewActivity.this.COLOR_CODE);
                    fabricCartModel.setQuantity(FabricViewActivity.this.QTY);
                    fabricCartModel.setDate(new SimpleDateFormat("dd MMM yyyy", Locale.US).format(new Date()));
                    fabricCartModel.save();
                    Intent intent = new Intent(FabricViewActivity.this.getApplicationContext(), CustomerHomeActivity.class);
                    intent.putExtra("TO_CART", "");
                    intent.setFlags(268468224);
                    FabricViewActivity.this.startActivity(intent);
                    return;
                }
                new AlertDialog.Builder((Context)FabricViewActivity.this).setCancelable(false).setTitle(2131230952).setMessage((CharSequence)(FabricViewActivity.this.getString(2131231208) + " " + FabricViewActivity.this.QTY + " " + FabricViewActivity.this.getString(2131231147) + "?")).setPositiveButton(2131231134, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        dialogInterface.dismiss();
                        FabricCartModel fabricCartModel = new FabricCartModel();
                        fabricCartModel.setFabric_id(FabricViewActivity.this.FABRIC_ID);
                        fabricCartModel.setName(FabricViewActivity.this.bundle.getString("NAME"));
                        fabricCartModel.setImage(FabricViewActivity.this.BANNER);
                        fabricCartModel.setPrice(FabricViewActivity.this.RATE);
                        fabricCartModel.setColor(FabricViewActivity.this.txtColor.getText().toString());
                        fabricCartModel.setColor_code(FabricViewActivity.this.COLOR_CODE);
                        fabricCartModel.setQuantity(FabricViewActivity.this.QTY);
                        fabricCartModel.setDate(new SimpleDateFormat("dd MMM yyyy", Locale.US).format(new Date()));
                        fabricCartModel.save();
                        Intent intent = new Intent(FabricViewActivity.this.getApplicationContext(), FabricSummaryActivity.class);
                        intent.putExtra("NAME", FabricViewActivity.this.bundle.getString("NAME"));
                        intent.putExtra("IMAGE", FabricViewActivity.this.BANNER);
                        intent.putExtra("PRICE", FabricViewActivity.this.RATE);
                        intent.putExtra("COLOR", FabricViewActivity.this.txtColor.getText().toString());
                        intent.putExtra("COLOR_CODE", FabricViewActivity.this.COLOR_CODE);
                        intent.putExtra("QTY", FabricViewActivity.this.QTY);
                        FabricViewActivity.this.startActivityForResult(intent, 603);
                    }
                }).setNegativeButton(2131230921, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialogInterface, int n) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }

        });
        this.txtProductName = (TextView)this.findViewById(2131690210);
        this.txtBrandName = (TextView)this.findViewById(2131690300);
        this.txtBrandName.setPaintFlags(8 | this.txtBrandName.getPaintFlags());
        this.txtPrice = (TextView)this.findViewById(2131690161);
        this.viewColor = this.findViewById(2131689816);
        this.txtColor = (TextView)this.findViewById(2131690301);
        this.txtDesc = (TextView)this.findViewById(2131690019);
        this.txtTotal = (TextView)this.findViewById(2131690023);
        this.txtQty = (TextView)this.findViewById(2131690021);
        this.imgMinusQty = (ImageView)this.findViewById(2131690020);
        this.imgPlusQty = (ImageView)this.findViewById(2131690022);
        this.imgFabricProduct1 = (ImageView)this.findViewById(2131690303);
        this.imgFabricProduct2 = (ImageView)this.findViewById(2131690304);
        this.imgFabricProduct3 = (ImageView)this.findViewById(2131690305);
        this.imgFabricProduct4 = (ImageView)this.findViewById(2131690306);
        this.imgFabricProduct5 = (ImageView)this.findViewById(2131690307);
        this.imgFabricProduct1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FabricViewActivity.this.sendToImageView(0);
            }
        });
        this.imgFabricProduct2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FabricViewActivity.this.sendToImageView(1);
            }
        });
        this.imgFabricProduct3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FabricViewActivity.this.sendToImageView(2);
            }
        });
        this.imgFabricProduct4.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FabricViewActivity.this.sendToImageView(3);
            }
        });
        this.imgFabricProduct5.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FabricViewActivity.this.sendToImageView(4);
            }
        });
        this.imgMinusQty.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (FabricViewActivity.this.QTY != 0) {
                    FabricViewActivity.access$310(FabricViewActivity.this);
                    if (FabricViewActivity.this.QTY == 0) {
                        FabricViewActivity.this.txtTotal.setVisibility(8);
                    }
                }
                FabricViewActivity.this.txtQty.setText((CharSequence)(FabricViewActivity.this.QTY + " " + FabricViewActivity.this.getString(2131231147)));
                FabricViewActivity.this.txtTotal.setText((CharSequence)(FabricViewActivity.this.getString(2131231219) + " " + FabricViewActivity.this.getIndoRate(FabricViewActivity.this.QTY * FabricViewActivity.this.RATE)));
            }
        });
        this.imgPlusQty.setOnClickListener((View.OnClickListener)new 10(this));
        if (this.isNetworkAvailable(this.getApplicationContext())) {
            this.callProductDetailApi();
        } else {
            this.preToast(this.getString(2131231081));
            this.finish();
        }
        this.txtBrandName.setOnClickListener((View.OnClickListener)new 11(this));
    }

    private void sendToImageView(int n) {
        Intent intent = new Intent(this.getApplicationContext(), ImageFullScreenActivity.class);
        intent.putExtra("data", this.dataGalleryModelArrayList);
        intent.putExtra("pos", n);
        this.startActivity(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onActivityResult(int n, int n2, Intent intent) {
        if (n == 603 && n2 == 301 && intent != null) {
            this.finish();
        } else if (n == 603 && n2 == 302 && intent != null) {
            this.startActivity(new Intent(this.getApplicationContext(), FabricCartActivity.class));
            this.finish();
        }
        super.onActivityResult(n, n2, intent);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968821);
        this.bundle = this.getIntent().getExtras();
        this.shareDialog = new ShareDialog((Activity)this);
        this.IS_FROM_MODEL = this.bundle.getBoolean("IS_FROM_MODEL");
        this.initControl();
    }

}

