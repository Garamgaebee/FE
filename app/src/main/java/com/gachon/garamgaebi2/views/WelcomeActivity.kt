package com.gachon.garamgaebi2.views

import android.content.Intent
import android.graphics.drawable.TransitionDrawable
import android.view.animation.AnimationUtils
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.databinding.ActivityWelcomeBinding
import com.gachon.garamgaebi2.views.mainFeed.MainFeedActivity


class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>(ActivityWelcomeBinding::inflate){

    private lateinit var transitionDrawable: TransitionDrawable

    override fun initView() {
        animation()
        initListener()
    }

    private fun animation(){
        // XML 리소스에서 페이드 애니메이션을 로드합니다.
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        // 애니메이션을 ImageView에 적용합니다.
        binding.logoIVWhite.startAnimation(fadeInAnimation)
        binding.logoIVBlack.startAnimation(fadeOutAnimation)

    }
    fun initListener() {
        binding.mainFeedBtn.setOnClickListener {
            val intent = Intent(this, MainFeedActivity::class.java)
            startActivity(intent)
        }
        }
    }
