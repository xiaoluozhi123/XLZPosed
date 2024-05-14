package com.xiaoluozhi.posed.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.core.annotation.LegacyResourcesHook
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed(isUsingResourcesHook = true)
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "xlz_tag"
        }
        isDebug = true
    }

    @OptIn(LegacyResourcesHook::class)
    override fun onHook() = encase {
        if (prefs.getBoolean("tomatoBookHook")) {
            loadApp("com.dragon.read") {
                "com.dragon.read.user.model.VipInfoModel".toClass().constructor()
                    .hook {
                        before {
                            args[0] = "1893211199"
                            args[1] = "1"
                            args[2] = "1"
                            args[3] = true
                            args[4] = true
                            args[6] = true
                        }
                    }
                "com.dragon.read.widget.m".toClass().method {
                    name = "a"
                }.hookAll {
                    replaceUnit { }
                }
            }
        }
    }
}