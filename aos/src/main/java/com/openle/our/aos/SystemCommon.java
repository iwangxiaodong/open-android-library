package com.openle.our.aos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class SystemCommon {

    //  Android OS Wipe后该值才会重置 - Android 8+ 每个App的均不相同。
    //  若引入了Google Play services库则首选广告ID方案 - AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
    @SuppressLint("HardwareIds")    //  仅仅抑制使用Google Play services库建议
    public static String androidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);    //  值与广告ID不同，可同时使用。
    }

    // 返回Home主屏; 仅切换界面至后台可用this.moveTaskToBack(true);
    public static void goToDesktopActivity(Context c) {
        Intent home = new Intent(Intent.ACTION_MAIN);   //  入口
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        home.addCategory(Intent.CATEGORY_HOME); //  桌面Activity
        c.startActivity(home);
    }
}
