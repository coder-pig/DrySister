package com.coderpig.drysisters.utils;

import android.content.Context;

/**
 * 描述：尺寸转换工具类
 *
 * @author coder-pig： 2016/08/17 11:47
 */
public class SizeUtils {

    /** dp转px */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /** px转dp */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
