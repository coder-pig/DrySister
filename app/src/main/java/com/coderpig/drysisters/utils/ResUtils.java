package com.coderpig.drysisters.utils;

import com.coderpig.drysisters.DrySisterApp;

/**
 * 描述：获取文件资源工具类
 *
 * @author CoderPig on 2018/02/14 11:07.
 */

public class ResUtils {
    /* 获取文件资源 */
    public static String getString(int strId) {
        return DrySisterApp.getContext().getResources().getString(strId);
    }
}
