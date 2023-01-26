package com.openle.our.aos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AppStoreCommon {

    //  startActivity(genPlayStoreAppDetailIntent(v.getContext(), BuildConfig.APPLICATION_ID));
    /*  若引入play-services-base库则可先用isGooglePlayServicesAvailable判定环境:
            var intent = genPlayStoreAppDetailIntent(BuildConfig.APPLICATION_ID);
            var cr = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(v.getContext());
            if (cr != ConnectionResult.SUCCESS) {
            intent.setPackage(null);    //  清除PlayStore应用包名则降级至浏览器打开
            }
            startActivity(intent);
    */
    public static Intent genPlayStoreAppDetailIntent(Context context, String appId) {
        var intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + appId));
        intent.setPackage("com.android.vending");
        if (intent.resolveActivity(context.getPackageManager()) == null) {
            Toast.makeText(context, "未安装Google Play则通过浏览器打开！", Toast.LENGTH_LONG).show();
            intent.setPackage(null);    //  清除PlayStore应用包名则降级至浏览器打开
        }
        return intent;  //  未登录则跳转至登录页
    }
}