package com.coderpig.drysisters;

import android.app.Application;
import android.content.Context;

/**
 * 描述：Application类
 *
 * @author coder-pig： 2016/08/07 15:07
 */
public class DrySisterApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static DrySisterApp getContext() {
        return (DrySisterApp) context;
    }
}
