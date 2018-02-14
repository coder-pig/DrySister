package com.coderpig.drysisters.utils;

import android.widget.Toast;

import com.coderpig.drysisters.DrySisterApp;

/**
 * 描述：Toast 工具类
 *
 * @author CoderPig on 2018/02/14 11:06.
 */

public class ToastUtils {
    public static void shortToast(String msg) {
        Toast.makeText(DrySisterApp.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
