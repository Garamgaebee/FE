package com.gachon.garamgaebi2.views.mainFeed

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.mainFeed.MainFeedVPAdapter
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.MainMenuDrawerBinding
import com.gachon.garamgaebi2.views.thread.RemoveThreadBottomDialogFragment
import com.gachon.garamgaebi2.views.userProfile.UserProfileEditActivity
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
            myProfileBtn.btnLayout.setBackgroundResource(R.drawable.side_menu_item_top_bottom_border)
            myProfileBtn.btnIv.setImageResource(R.drawable.ic_my_profile)
            myCommunityBtn.btnIv.setImageResource(R.drawable.ic_my_community)
            allCommunityBtn.btnIv.setImageResource(R.drawable.ic_all_community)
            makeCommunityBtn.btnIv.setImageResource(R.drawable.ic_plus_circle)
            settingsBtn.btnIv.setImageResource(R.drawable.ic_settings)
            binding.name = "김가람"
            binding.major = "소프트웨어학과"

        }

    }

    private fun initToolbar() {
        setSupportActionBar(binding.mainContentLayout.toolbar)
        with(binding.mainContentLayout.toolbar) {
            logo = null
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(com.gachon.garamgaebi2.R.drawable.ic_hamburger_menu)
//        binding.navigationView.itemIconTintList = null
//

        // 햄버거 모양 아이콘을 설정하고 상단 마진 추가
        val hamburgerIcon = com.gachon.garamgaebi2.R.drawable.ic_hamburger_menu
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawerLayout,
            binding.mainContentLayout.toolbar.apply {
                setPadding(0, 20, 0, 0) // 여기서 marginTop은 상단 마진의 크기

            },
            R.string.my_community,
            R.string.all_community
        )

        // 햄버거 모양 아이콘 설정
        actionBarDrawerToggle.drawerArrowDrawable.color = Color.WHITE
        supportActionBar?.setHomeAsUpIndicator(hamburgerIcon)
        binding.navigationView.itemIconTintList = null



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
        binding.logoutBtn.setOnClickListener {
            val dialog2 = LogoutBottomDialogFragment(){
                when(it) {
                    0 -> {
                        // 진짜 삭제
                        binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    }
                }
            }
            dialog2.show(this.supportFragmentManager, dialog2.tag)

        }

        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)


        binding.myProfileBtn.btnLayout.setOnClickListener {
            it.startAnimation(bounceAnimation)
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
          //  val intent = Intent(this, UserProfileEditActivity::class.java)
            startActivity(intent)
        }
        binding.myCommunityBtn.btnLayout.setOnClickListener {
            it.startAnimation(bounceAnimation)
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            val intent = Intent(this, MyCommunityListActivity::class.java)
            startActivity(intent)
        }
        binding.allCommunityBtn.btnLayout.setOnClickListener {
            it.startAnimation(bounceAnimation)
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            val intent = Intent(this, AllCommunityListActivity::class.java)
            startActivity(intent)
        }
        binding.makeCommunityBtn.btnLayout.setOnClickListener {
            it.startAnimation(bounceAnimation)
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            val intent = Intent(this, CommunityProfileMakeActivity::class.java)
            startActivity(intent)
        }
        binding.settingsBtn.btnLayout.setOnClickListener {
            it.startAnimation(bounceAnimation)
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
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