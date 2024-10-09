package com.openle.our.aos;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppCommon {

    //  非Android Library项目建议直接使用 BuildConfig.VERSION_CODE
    public static long versionCode(Context context) {
        PackageInfo info = null;
        try {
            //  PackageManager.GET_CONFIGURATIONS 缩小了包信息的字段量，仅包含Version等基本信息
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        //return info.versionCode;  //  < 28
        return info != null ? info.getLongVersionCode() : 0;
    }

    //  Godot 项目则可换用 ProjectSettings.GetSetting("application/config/version");
    public static String versionName(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        return info != null ? info.versionName : null;
    }

    public static String appName(Context context) {
        String appName = null;
        try {
            appName = context.getPackageManager().getPackageInfo(
                            context.getPackageName(), PackageManager.GET_CONFIGURATIONS).applicationInfo
                    .loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        return appName;
    }

    //  非Library方式可直接用BuildConfig.DEBUG
    public static boolean isDebugForLibrary(Context context) {
        return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
}
