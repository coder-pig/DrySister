package cn.coderpig.cp_base

import android.content.Context

/**
 * Author: zpj
 * Date: 2023-09-25 16:19
 * Desc: 模块初始化类
 */

class CpKit {
    companion object {
        private var _appContext: Context? = null
        val appContext
            get() = _appContext ?: throw NullPointerException("需要在Application的onCreate()方法中调用cp-base模块的init()方法")

        fun init(context: Context) {
            this._appContext = context.applicationContext
        }
    }
}