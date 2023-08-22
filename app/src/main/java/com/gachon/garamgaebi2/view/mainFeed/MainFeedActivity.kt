package com.gachon.garamgaebi2.view.mainFeed

import android.os.Bundle
import android.view.View
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.mainFeed.MainFeedVPAdapter
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.databinding.ActivityMainFeedBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFeedActivity : BaseActivity<ActivityMainFeedBinding>(ActivityMainFeedBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initToolbar()
        initListener()
        initViewPager()
    }
    override fun initView() {
    }
    private fun initToolbar() {
        with(binding.toolbar) {
            logo = null
        }
    }
    fun initListener() {
        with(binding) {
            icPostingCv.setOnClickListener {
                //TODO 게시글 작성 페이지로 이동
            }
            searchIv.setOnClickListener {

            }
            hamburgerMenuIv.setOnClickListener {

            }
        }
    }
    private fun initViewPager() {
        val mainFeedVPAdapter = MainFeedVPAdapter(this)
        binding.activityMainFeedVp.adapter = mainFeedVPAdapter
        binding.activityMainFeedVp.isUserInputEnabled = false
        val tabNameArray = arrayListOf("최신순","인기순")
        TabLayoutMediator(binding.activityMainFeedTl, binding.activityMainFeedVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }
}