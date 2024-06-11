package com.xiaoluozhi.posed.ui.fragment

import com.xiaoluozhi.posed.databinding.FragmentFunctionBinding
import com.xiaoluozhi.posed.ui.base.BaseFragment
import com.xiaoluozhi.posed.utils.setSwitch

class FunctionFragment : BaseFragment<FragmentFunctionBinding>() {
    override fun initEvent() {
        super.initEvent()

        binding.tomatoBookHook.setSwitch("tomatoBookHook")
        binding.wangcBillHook.setSwitch("wangcBillHook")
    }
}