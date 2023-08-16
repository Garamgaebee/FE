package com.gachon.garamgaebi2.adapter.mainFeed

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gachon.garamgaebi2.view.mainFeed.MainFeedActivity

class MainFeedVPAdapter(fragment: MainFeedActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> Fragment()
            1 -> Fragment()
            else -> Fragment()
        }
    }
}