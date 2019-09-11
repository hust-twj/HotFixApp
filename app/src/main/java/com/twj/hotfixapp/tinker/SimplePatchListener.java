package com.twj.hotfixapp.tinker;

import android.content.Context;

import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.twj.hotfixapp.util.MD5Utils;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-09-06.
 */
public class SimplePatchListener extends DefaultPatchListener {

    private String currentMD5;
    public void setCurrentMD5(String md5Value) {
        this.currentMD5 = md5Value;
    }

    public SimplePatchListener(Context context) {
        super(context);
    }

    @Override
    protected int patchCheck(String path, String patchMd5) {
        //增加patch文件的md5较验
        if (!MD5Utils.isFileMD5Matched(path, currentMD5)) {
            return ShareConstants.ERROR_PATCH_DISABLE;
        }
        return super.patchCheck(path, patchMd5);
    }

}
