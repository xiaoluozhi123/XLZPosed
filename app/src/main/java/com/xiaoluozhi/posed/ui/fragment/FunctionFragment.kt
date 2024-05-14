package com.xiaoluozhi.posed.ui.fragment

import android.util.Log
import com.tencent.mmkv.MMKV
import com.xiaoluozhi.posed.App
import com.xiaoluozhi.posed.databinding.FragmentFunctionBinding
import com.xiaoluozhi.posed.ui.base.BaseFragment

class FunctionFragment : BaseFragment<FragmentFunctionBinding>() {
    override fun initEvent() {
        super.initEvent()
        if (App.kv.containsKey("tomatoBookHook")) {
            val tomatoBookHook = App.kv.getString("tomatoBookHook", "0")
            Log.d("ceshi", tomatoBookHook.toString())

            if (tomatoBookHook == "0") {
                binding.tomatoBookHook.isChecked = false
            } else {
                binding.tomatoBookHook.isChecked = true
            }
        } else {
            App.kv.encode("tomatoBookHook", "0")
        }

        binding.tomatoBookHook.setOnClickListener {
            if (binding.tomatoBookHook.isChecked) {
                App.kv.encode("tomatoBookHook", "1")
            } else {
                App.kv.encode("tomatoBookHook", "0")
            }
        }
    }
}