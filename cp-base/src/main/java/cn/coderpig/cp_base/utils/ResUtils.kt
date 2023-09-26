package cn.coderpig.cp_base.utils

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import cn.coderpig.cp_base.CpKit

/**
 * Author: zpj
 * Date: 2023-09-25 17:30
 * Desc: 资源工具类
 */

@SuppressLint("UseCompatLoadingForDrawables")
fun getDrawableRes(resId: Int): Drawable = CpKit.appContext.resources.getDrawable(resId, null)

fun getDimensionPixelSizeRes(resId: Int) = CpKit.appContext.resources.getDimensionPixelSize(resId)

fun getDimensionRes(resId: Int) = CpKit.appContext.resources.getDimension(resId)

fun getBooleanRes(resId: Int) = CpKit.appContext.resources.getBoolean(resId)

fun getColorRes(resId: Int) = CpKit.appContext.resources.getColor(resId)

fun getTextRes(resId: Int): String = CpKit.appContext.resources.getString(resId)

fun getAssets(): AssetManager = CpKit.appContext.resources.assets
