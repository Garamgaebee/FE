package com.gachon.garamgaebi2.adapter.mainFeed

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gachon.garamgaebi2.views.mainFeed.MainFeedActivity
import com.gachon.garamgaebi2.views.mainFeed.MainFeedLatestFragment
import com.gachon.garamgaebi2.views.mainFeed.MainFeedPopularityFragment

class MainFeedVPAdapter(fragment: MainFeedActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MainFeedLatestFragment()
            1 -> MainFeedPopularityFragment()
            else -> Fragment()
        }
    }
}