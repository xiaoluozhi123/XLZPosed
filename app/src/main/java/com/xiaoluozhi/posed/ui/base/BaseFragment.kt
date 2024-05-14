package com.xiaoluozhi.posed.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dylanc.viewbinding.base.FragmentBinding
import com.dylanc.viewbinding.base.FragmentBindingDelegate
abstract class BaseFragment<VB : ViewBinding> : Fragment(),
    FragmentBinding<VB> by FragmentBindingDelegate() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return createViewWithBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initEvent()
    }

    override fun onResume() {
        loadData()
        super.onResume()
    }

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化事件
     */
    open fun initEvent() {}


    /**
     * 每次页面可见时调用
     */
    open fun loadData() {}

}
