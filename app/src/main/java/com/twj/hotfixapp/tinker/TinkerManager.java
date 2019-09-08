package com.twj.hotfixapp.tinker;

import android.content.Context;

import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-09-06.
 */
public class TinkerManager {

    private static boolean isInstalled = false;
    //ApplicationLike可以理解为Application的载体，可以当成Application去使用
    private static ApplicationLike mAppLike;
    private static SimplePatchListener mPatchListener;

    /**
     * 初始化Tinker
     * @param applicationLike
     */
    public static void installTinker(ApplicationLike applicationLike) {
        mAppLike = applicationLike;
        if (isInstalled) {
            return;
        }
        TinkerInstaller.install(mAppLike);
        isInstalled = true;
    }

    /**
     * 初始化Tinker，带有拓展模块
     * @param applicationLike
     * @param md5Value        服务器下发的md5
     */
    public static void installTinker(ApplicationLike applicationLike, String md5Value) {
        mAppLike = applicationLike;
        if (isInstalled) {
            return;
        }
        mPatchListener = new SimplePatchListener(getApplicationContext());
        mPatchListener.setCurrentMD5(md5Value);
        // Load补丁包时候的监听
        LoadReporter loadReporter = new DefaultLoadReporter(getApplicationContext());
        // 补丁包加载时候的监听
        PatchReporter patchReporter = new DefaultPatchReporter(getApplicationContext());
        AbstractPatch upgradePatchProcessor = new UpgradePatch();
        TinkerInstaller.install(applicationLike,
                loadReporter,
                patchReporter,
                mPatchListener,
                CustomResultService.class,
                upgradePatchProcessor);
        isInstalled = true;
    }

    /**
     * 添加补丁包路径
     * @param path
     */
    public static void addPatch(String path) {
        if (Tinker.isTinkerInstalled()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    private static Context getApplicationContext() {
        if (mAppLike != null) {
            return mAppLike.getApplication().getApplicationContext();
        }
        return null;
    }

}
