package com.hysyyl.bloodapp.activity.main

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(val fm: FragmentManager, val tabs: List<Fragment>, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {


    override fun getItem(position: Int): Fragment {
        return tabs.get(position)
    }

    override fun getCount(): Int {
        return tabs.size
    }

//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
////        super.destroyItem(container, position, `object`)
//    }
}