package com.coderpig.drysisters;

import android.app.Application;

/**
 * 描述：Application类
 *
 * @author coder-pig： 2016/08/07 15:07
 */
public class DrySisterApp extends Application {

    private static DrySisterApp context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        DryInit.initTimber();
        DryInit.initOKHttp(this);
    }

    public static DrySisterApp getContext() {
        return context;
    }
}
