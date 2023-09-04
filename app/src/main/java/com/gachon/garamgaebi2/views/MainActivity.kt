package com.gachon.garamgaebi2.views

import android.content.Intent
import android.os.Build
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityMainBinding
import com.gachon.garamgaebi2.views.communityProfile.CommunityProfileEditActivity
import com.gachon.garamgaebi2.views.login.LoginActivity
import com.gachon.garamgaebi2.views.mainFeed.MainFeedActivity
import com.gachon.garamgaebi2.views.register.RegisterActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){


    @RequiresApi(Build.VERSION_CODES.S)
    override fun initView() {
        initListener()

        val marginBottom = binding.loginTv

        // 화면의 높이를 가져옴
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels

        // 비율에 따라 마진 값을 계산
        val marginInPixels = (screenHeight * 0.16).toInt() // 예시로 10%로 설정

        // 마진 값을 뷰에 적용
        val layoutParams = marginBottom.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(0, 0, 0, marginInPixels)
        marginBottom.layoutParams = layoutParams

        val marginTop = binding.logoIV
        // 비율에 따라 마진 값을 계산
        val marginInPixelsTop = (screenHeight * 0.146).toInt() // 예시로 10%로 설정

        // 마진 값을 뷰에 적용
        val layoutParamsTop = marginTop.layoutParams as ViewGroup.MarginLayoutParams
        layoutParamsTop.setMargins(0, marginInPixelsTop, 0, 0)
        marginTop.layoutParams = layoutParamsTop
    }

    private fun initListener(){
        binding.registerBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.loginTv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // 테스트용
        binding.logoIV.setOnClickListener{
            val intent = Intent(this, MainFeedActivity::class.java)
            startActivity(intent)
        }
        binding.welcome1Tv.setOnClickListener {
            val intent = Intent(this, CommunityProfileEditActivity::class.java)
            startActivity(intent)
        }
    }

}