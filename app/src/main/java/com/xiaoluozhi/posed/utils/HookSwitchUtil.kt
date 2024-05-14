package com.xiaoluozhi.posed.utils

import androidx.appcompat.widget.SwitchCompat
import com.highcapable.yukihookapi.hook.factory.prefs
import com.xiaoluozhi.posed.App

fun SwitchCompat.setSwitch(hookName: String, defaultSwitch: Boolean = true) {
    if (App.kv.containsKey(hookName)) {
        val tomatoBookHook = App.kv.getString(hookName, if (defaultSwitch) "1" else "0")

        if (tomatoBookHook == "0") {
            isChecked = false
            context.prefs().edit {
                putBoolean(hookName, false)
            }
        } else {
            isChecked = true
            context.prefs().edit {
                putBoolean(hookName, true)
            }
        }
    } else {
        App.kv.encode(hookName, if (defaultSwitch) "1" else "0")
    }

    setOnClickListener {
        if (isChecked) {
            App.kv.encode(hookName, "1")
            context.prefs().edit {
                putBoolean(hookName, true)
            }
        } else {
            App.kv.encode(hookName, "0")
            context.prefs().edit {
                putBoolean(hookName, false)
            }
        }
    }
}