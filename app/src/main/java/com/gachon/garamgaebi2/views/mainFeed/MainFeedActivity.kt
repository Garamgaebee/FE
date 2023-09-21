package com.gachon.garamgaebi2.views.mainFeed

import android.content.Intent
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.mainFeed.MainFeedVPAdapter
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.MainMenuDrawerBinding
import com.gachon.garamgaebi2.views.write.WritePostActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFeedActivity : BaseActivity<MainMenuDrawerBinding>(MainMenuDrawerBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        initListener()
        initViewPager()

    }
    override fun initView() {
        with(binding) {
            myProfileBtn.btnLayout.setBackgroundResource(0)
            myProfileBtn.btnIv.setImageResource(R.drawable.ic_my_profile)
            myCommunityBtn.btnIv.setImageResource(R.drawable.ic_my_community)
            allCommunityBtn.btnIv.setImageResource(R.drawable.ic_all_community)
            makeCommunityBtn.btnIv.setImageResource(R.drawable.ic_plus_circle)
            settingsBtn.btnIv.setImageResource(R.drawable.ic_settings)

        }

    }
    private fun initToolbar() {
        setSupportActionBar(binding.mainContentLayout.toolbar)
        with(binding.mainContentLayout.toolbar) {
            logo = null
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(com.gachon.garamgaebi2.R.drawable.ic_hamburger_menu)
        binding.navigationView.itemIconTintList = null


        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawerLayout,
            binding.mainContentLayout.toolbar,
            R.string.my_community,
            R.string.all_community
        )
        binding.mainDrawerLayout.addDrawerListener(actionBarDrawerToggle)
    }

    fun initListener() {


        with(binding.mainContentLayout) {
            icPostingCv.setOnClickListener {
                Log.d("MainFeedActivity", "icPostingCv clicked")

                startActivity(Intent(this@MainFeedActivity, WritePostActivity::class.java))
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)

            }
            searchIv.setOnClickListener {

            }
            activityMainFeedTl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val transitionDrawable = binding.mainContentLayout.tabLayoutBottomBorder.background as TransitionDrawable
                    when (tab.position) {
                        0 -> transitionDrawable.startTransition(300)
                        1 -> transitionDrawable.reverseTransition(300)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {}

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })
        }
    }
    private fun initViewPager() {
        val mainFeedVPAdapter = MainFeedVPAdapter(this)
        binding.mainContentLayout.activityMainFeedVp.adapter = mainFeedVPAdapter
        binding.mainContentLayout.activityMainFeedVp.isUserInputEnabled = false
        val tabNameArray = arrayListOf("최신순","인기순")
        TabLayoutMediator(binding.mainContentLayout.activityMainFeedTl, binding.mainContentLayout.activityMainFeedVp) {
                tab, position -> tab.text = tabNameArray[position]
        }.attach()
    }
}