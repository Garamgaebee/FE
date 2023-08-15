package com.gachon.garamgaebi2.view

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityLoginBinding
import com.gachon.garamgaebi2.viewModel.LoginViewModel
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.CustomPasswordTransformation
import com.gachon.garamgaebi2.base.GaramgaebiApplication
import com.gachon.garamgaebi2.databinding.ActivityWelcomeBinding


class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>(ActivityWelcomeBinding::inflate){

    private lateinit var transitionDrawable: TransitionDrawable

    override fun initView() {
        animation()
    }

    private fun animation(){
        // XML 리소스에서 페이드 애니메이션을 로드합니다.
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        // 애니메이션을 ImageView에 적용합니다.
        binding.logoIVWhite.startAnimation(fadeInAnimation)
        binding.logoIVBlack.startAnimation(fadeOutAnimation)
    }


}