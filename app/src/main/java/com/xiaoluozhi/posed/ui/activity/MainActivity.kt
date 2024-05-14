@file:Suppress("SetTextI18n")

package com.xiaoluozhi.posed.ui.activity

import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.xiaoluozhi.posed.R
import com.xiaoluozhi.posed.databinding.ActivityMainBinding
import com.xiaoluozhi.posed.ui.base.BaseActivity
import com.xiaoluozhi.posed.ui.fragment.CustomHookFragment
import com.xiaoluozhi.posed.ui.fragment.FunctionFragment
import com.xiaoluozhi.posed.ui.fragment.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate() {
        val viewPager = binding.viewPager2
        val bottomNavigation = binding.bottomNavigation

        // 设置ViewPager
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 5
            override fun createFragment(position: Int) =
                when (position) {
                    0 -> HomeFragment()
                    1 -> FunctionFragment()
                    2 -> CustomHookFragment()
                    else -> HomeFragment()
                }
        }

        // 设置导航栏监听器
        bottomNavigation.setOnItemSelectedListener { item ->
            val position = when (item.itemId) {
                R.id.home_page -> 0
                R.id.function_page -> 1
                R.id.custom_hook -> 2
                else -> 0
            }
            viewPager.currentItem = position
            true
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigation.menu.getItem(position).isChecked = true
            }
        })
    }
}