package cn.coderpig.cp_base.utils

import android.widget.Toast
import cn.coderpig.cp_base.CpKit

/**
 * Author: zpj
 * Date: 2023-09-25 16:24
 * Desc: Toast相关工具类
 */

fun shortToast(msg: String) = Toast.makeText(CpKit.appContext, msg, Toast.LENGTH_SHORT).show()

fun longToast(msg: String) = Toast.makeText(CpKit.appContext, msg, Toast.LENGTH_LONG).show()