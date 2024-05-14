@file:Suppress("SetTextI18n")

package com.xiaoluozhi.posed.ui.fragment

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.highcapable.yukihookapi.YukiHookAPI
import com.xiaoluozhi.posed.BuildConfig
import com.xiaoluozhi.posed.R
import com.xiaoluozhi.posed.databinding.FragmentHomeBinding
import com.xiaoluozhi.posed.ui.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun initData() {
        super.initData()
        refreshModuleStatus()
        binding.mainTextVersion.text = getString(R.string.module_version, BuildConfig.VERSION_NAME)
        binding.hideIconInLauncherSwitch.isChecked = isLauncherIconShowing.not()
        binding.hideIconInLauncherSwitch.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed) hideOrShowLauncherIcon(isChecked)
        }
    }

    override fun initEvent() {
        super.initEvent()
        binding.github.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle("小罗纸模块")
                .setIcon(R.mipmap.ic_launcher_round)
                .setMessage("小罗纸自用的模块\n仅供学习交流使用\n作者QQ2686020087")
                .setPositiveButton("Github") { _, _ ->
                    val uri = Uri.parse("https://github.com/xiaoluozhi123/XLZPosed")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                .show()
        }
    }

    /**
     * Hide or show launcher icons
     *
     * - You may need the latest version of LSPosed to enable the function of hiding launcher
     *   icons in higher version systems
     *
     * 隐藏或显示启动器图标
     *
     * - 你可能需要 LSPosed 的最新版本以开启高版本系统中隐藏 APP 桌面图标功能
     * @param isShow Whether to display / 是否显示
     */
    private fun hideOrShowLauncherIcon(isShow: Boolean) {
        requireContext().packageManager?.setComponentEnabledSetting(
            ComponentName(requireContext().packageName, "${BuildConfig.APPLICATION_ID}.Home"),
            if (isShow) PackageManager.COMPONENT_ENABLED_STATE_DISABLED else PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    /**
     * Get launcher icon state
     *
     * 获取启动器图标状态
     * @return [Boolean] Whether to display / 是否显示
     */
    private val isLauncherIconShowing
        get() = requireContext().packageManager?.getComponentEnabledSetting(
            ComponentName(requireContext().packageName, "${BuildConfig.APPLICATION_ID}.Home")
        ) != PackageManager.COMPONENT_ENABLED_STATE_DISABLED

    /**
     * Refresh module status
     *
     * 刷新模块状态
     */
    private fun refreshModuleStatus() {
        binding.mainLinStatus.setBackgroundResource(
            when {
                YukiHookAPI.Status.isXposedModuleActive -> R.drawable.bg_green_round
                else -> R.drawable.bg_dark_round
            }
        )
        binding.mainImgStatus.setImageResource(
            when {
                YukiHookAPI.Status.isXposedModuleActive -> R.mipmap.ic_success
                else -> R.mipmap.ic_warn
            }
        )
        binding.mainTextStatus.text = getString(
            when {
                YukiHookAPI.Status.isXposedModuleActive -> R.string.module_is_activated
                else -> R.string.module_not_activated
            }
        )
        binding.mainTextApiWay.isVisible = YukiHookAPI.Status.isXposedModuleActive
        binding.mainTextApiWay.text = if (YukiHookAPI.Status.Executor.apiLevel > 0)
            "${YukiHookAPI.Status.Executor.name} API ${YukiHookAPI.Status.Executor.apiLevel}"
        else YukiHookAPI.Status.Executor.name
    }
}