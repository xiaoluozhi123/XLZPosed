package com.xiaoluozhi.posed

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.drake.net.utils.scope
import com.tencent.mmkv.MMKV

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var kv: MMKV
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        MMKV.initialize(this)
        kv = MMKV.mmkvWithID("kv", MMKV.MULTI_PROCESS_MODE)
    }
}