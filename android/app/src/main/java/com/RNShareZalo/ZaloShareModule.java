package com.RNShareZalo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.zing.zalo.zalosdk.oauth.FeedData;
import com.zing.zalo.zalosdk.oauth.OpenAPIService;
import com.zing.zalo.zalosdk.oauth.ZaloPluginCallback;

public class ZaloShareModule extends ReactContextBaseJavaModule  {

    private static final String TAG = "ZaloShare";

    private static final String zaloPackageName = "com.zing.zalo";

    public ZaloShareModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return TAG;
    }


    private boolean checkZaloAppInstall() {
        PackageManager pm = this.getReactApplicationContext().getPackageManager();
        try {
            pm.getPackageInfo(zaloPackageName, PackageManager.GET_ACTIVITIES);
            Log.d( TAG, String.valueOf(pm.getPackageInfo(zaloPackageName, PackageManager.GET_ACTIVITIES)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    private void openStore() {
//        try {
//            this.getCurrentActivity().startActivity(
//                    new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("market://details?id=" + zaloPackageName))
//            );
//        } catch (android.content.ActivityNotFoundException anfe) {
//            this.getCurrentActivity().startActivity(
//                    new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("https://play.google.com/store/apps/details?id=" + zaloPackageName))
//            );
//        }
//    }

    private FeedData getFeedData(ReadableMap config) {
        String msg = config.getString("msg");
        String link = config.getString("link");
        String linkSource = config.getString("linkSource");
        String linkTitle = config.getString("linkTitle");
        String linkThumb = config.getString("linkThumb");

        FeedData feed = new FeedData();
        feed.setMsg(msg);
        feed.setLink(link);
        feed.setLinkTitle(linkTitle);
        feed.setLinkSource(linkSource);
        feed.setLinkThumb(new String[] { linkThumb });

        return feed;
    }

    @ReactMethod
    public void shareMessage(ReadableMap config, final Promise promise) {
        Boolean isAppInstall = this.checkZaloAppInstall();
        if(isAppInstall){
            try {

                FeedData feed = this.getFeedData(config);
                ZaloPluginCallback mcalCallback = new ZaloPluginCallback() {
                    @Override
                    public void onResult(boolean success, int i, String str, String str2) {
                        if(success) {
                            promise.resolve("success");
                        } else {
                            promise.reject("app_not_share", "app_not_share");
                        }
                    }
                };

                OpenAPIService.getInstance().shareMessage(
                        this.getCurrentActivity(),
                        feed,
                        mcalCallback,
                        true
                );

                promise.resolve(null);
            } catch (Exception e) {
                promise.reject(e.getMessage());
            }
        } else {
            promise.reject("app_not_install", "app_not_install");
        }
    }

    @ReactMethod
    public void shareFeed(ReadableMap config, final Promise promise) {
        Boolean isAppInstall = this.checkZaloAppInstall();
        if(isAppInstall){
            try {

                FeedData feed = this.getFeedData(config);

                ZaloPluginCallback mcalCallback = new ZaloPluginCallback() {
                    @Override
                    public void onResult(boolean success, int i, String str, String str2) {
                        if(success) {
                            promise.resolve("success");
                        } else {
                            promise.reject("app_not_share", "app_not_share");
                        }
                    }
                };

                OpenAPIService.getInstance().shareFeed(
                        this.getCurrentActivity(),
                        feed,
                        mcalCallback,
                        true
                );

                promise.resolve(null);
            } catch (Exception e) {
                promise.reject(e.getMessage());
            }
        } else {
            promise.reject("app_not_install", "app_not_install");
        }
    }

}
