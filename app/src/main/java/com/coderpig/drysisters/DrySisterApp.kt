package com.coderpig.drysisters

import android.app.Application
import cn.coderpig.cp_base.CpKit

/**
 * Author: zpj
 * Date: 2023-09-25 16:06
 * Desc: 自定义Application类
 */
class DrySisterApp : Application() {
    companion object {
        var instance: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CpKit.init(this)
        DryInit.initTimber()
        DryInit.initOKHttp(this)
    }
}