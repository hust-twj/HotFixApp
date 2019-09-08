package com.twj.hotfixapp;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-09-06.
 */
public class SampleApplication extends TinkerApplication {

    public SampleApplication() {
        super(
                //tinkerFlags, which types is supported
                //dex only, library only, all support
                ShareConstants.TINKER_ENABLE_ALL,
                // This is passed as a string so the shell application does not
                // have a binary dependency on your ApplicationLifeCycle class.
                "tinker.sample.android.app.SampleApplicationLike");
    }

}
