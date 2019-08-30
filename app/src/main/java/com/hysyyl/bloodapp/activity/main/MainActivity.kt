package com.hysyyl.bloodapp.activity.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hysyyl.bloodapp.BloodApplication
import com.hysyyl.bloodapp.R
import com.hysyyl.bloodapp.activity.main.home.HomeFragment
import com.hysyyl.bloodapp.activity.main.mine.MineFragment
import com.hysyyl.bloodapp.activity.main.order.OrderFragment
import com.hysyyl.bloodapp.activity.main.report.ReportFragment
import com.hysyyl.bloodapp.base.activity.BloodBaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @desc:主页
 * @date:2019/8/23 16:39
 * @auther:lp
 * @version:1.1.6
 */
class MainActivity : BloodBaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {
    override fun addToolBar(): Boolean {
        return false
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        mApplication.finishOthersActivity(MainActivity::class.java)

        val tabs: MutableList<Fragment> = mutableListOf()
        tabs.add(HomeFragment())
        tabs.add(OrderFragment())
        tabs.add(ReportFragment())
        tabs.add(MineFragment())
        viewPager.offscreenPageLimit = tabs.size
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, tabs, 0)
        viewPager.addOnPageChangeListener(this)
        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.currentItem = 0
                return true
            }
            R.id.navigation_order -> {
                viewPager.currentItem = 1
                return true
            }
            R.id.navigation_report -> {
                viewPager.currentItem = 2
                return true
            }
            else -> {
                viewPager.currentItem = 3
                return true
            }
        }
        return false
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> navigation.selectedItemId = R.id.navigation_home
            1 -> navigation.selectedItemId = R.id.navigation_order
            2 -> navigation.selectedItemId = R.id.navigation_report
            else -> navigation.selectedItemId = R.id.navigation_mine
        }
    }
}
