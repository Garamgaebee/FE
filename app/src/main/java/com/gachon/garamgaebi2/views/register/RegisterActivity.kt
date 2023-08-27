package com.gachon.garamgaebi2.views.register

import android.animation.ValueAnimator
import android.content.ContentValues.TAG
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.di.GaramgaebiApplication
import com.gachon.garamgaebi2.util.TermsBottomDialogFragment
import com.gachon.garamgaebi2.databinding.ActivityRegisterBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){

    private val viewModel by viewModels<RegisterViewModel>()

    private lateinit var progressBar: ProgressBar
    private var currentStep = 1
    private val maxStep = 3
    private val animationDuration = 1000L // 애니메이션 지속 시간 (1.5초)

    override fun initView() {
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register1EmailFragment()).commit()

        this.onBackPressedDispatcher.addCallback(this, callback) //위에서 생성한 콜백 인스턴스 붙여주기

        progressBar = binding.progressBar
        progressBar.max = 100

        updateProgressBar(currentStep, maxStep)

        observe()
        initListener()
        val marginBottom = binding.registerProcessBtn

        // 화면의 높이를 가져옴
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels

        // 비율에 따라 마진 값을 계산
        val marginInPixels = (screenHeight * 0.21).toInt() // 예시로 10%로 설정

        // 마진 값을 뷰에 적용
        val layoutParams = marginBottom.root.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(0, 0, 0, marginInPixels)
        marginBottom.root.layoutParams = layoutParams

    }

    private fun observe() {
        // 첫 단계
        viewModel.emailIsValid.observe(this) {
            Log.d(
                "RegisterActivity",
                "currentStep: $currentStep ${viewModel.emailIsValid.value} ${viewModel.codeIsValid.value} ${viewModel.infoIsValid.value} ${viewModel.detailIsValid.value}"
            )

            if (it && GaramgaebiApplication.registerProcess == 1) {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = true
                }
                viewModel.btnOn.value = true
            } else {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = false
                }
                viewModel.btnOn.value = false
            }
        }
        // 두 번째 단계
        viewModel.codeIsValid.observe(this) {
            if (it && GaramgaebiApplication.registerProcess == 2) {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = true
                }
                viewModel.btnOn.value = true
            } else {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = false
                }
                viewModel.btnOn.value = false

            }
        }
        // 세 번째 단계
        viewModel.infoIsValid.observe(this) {
            if (it && GaramgaebiApplication.registerProcess == 3) {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = true
                }
                viewModel.btnOn.value = true
            } else {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = false
                }
                viewModel.btnOn.value = false

            }
        }
        // 네 번째 단계
        viewModel.detailIsValid.observe(this) {
            if (it && GaramgaebiApplication.registerProcess == 4) {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = true
                }
                viewModel.btnOn.value = true
            } else {
                binding.registerProcessBtn.nextBtn.apply {
                    isClickable = false
                }
                viewModel.btnOn.value = false
            }
        }
    }
    private fun initListener(){
        binding.backBtn.setOnClickListener {
            //전 스택의 프래그먼트로 돌아가기
            if(GaramgaebiApplication.registerProcess == 1) {
                finish()
            }
            supportFragmentManager.popBackStack()
            if(GaramgaebiApplication.registerProcess != 2){
                backProgressBar(currentStep, maxStep)
            }


        }
        binding.registerProcessBtn.nextBtn.setOnClickListener {
            changeFragment()
            Log.d("RegisterActivity", "currentStep: $currentStep ${viewModel.emailIsValid.value} ${viewModel.codeIsValid.value} ${viewModel.infoIsValid.value} ${viewModel.detailIsValid}")
        }
    }

    fun changeFragment(){
        when (GaramgaebiApplication.registerProcess) {
            1 ->{
                // Register 2로 이동
                supportFragmentManager.beginTransaction().
                replace(binding.fragmentContainer.id, Register2AuthenticationFragment()).addToBackStack(null).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.authenticate)
            }
            2->{
                // Register 3로 이동
                supportFragmentManager.beginTransaction().
                replace(binding.fragmentContainer.id, Register3InfoFragment()).addToBackStack(null).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.next)
                nextStep()
            }
            3->{
                // Register 4로 이동
                supportFragmentManager.beginTransaction().
                replace(binding.fragmentContainer.id, Register4detailFragment()).addToBackStack(null).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.start)
                nextStep()
            }
            4->{
                val termsBottomDialogFragment = TermsBottomDialogFragment()
                termsBottomDialogFragment.show(supportFragmentManager, "termsBottomDialogFragment")

            }
        }
        viewModel.btnOn.value = false
        binding.registerProcessBtn.nextBtn.apply{
            isClickable = false
        }
    }

    // 프로그레스바 애니메이션 메서드
    fun updateProgressBar(currentStep: Int, maxStep: Int) {
        val startProgress = (currentStep - 1) * 100f / maxStep // 시작 값 (이전 단계)
        val endProgress = currentStep * 100f / maxStep // 끝 값 (현재 단계)

        // ValueAnimator 생성
        val valueAnimator = ValueAnimator.ofFloat(startProgress, endProgress)
        valueAnimator.duration = animationDuration

        // 값이 변할 때마다 호출되는 리스너 설정
        valueAnimator.addUpdateListener {
            val progress = it.animatedValue as Float
            progressBar.progress = progress.toInt()
        }

        // 애니메이션 시작
        valueAnimator.start()
    }

    fun backProgressBar(currentStep: Int, maxStep: Int) {
        val startProgress = (currentStep) * 100f / maxStep // 시작 값 (이전 단계)
        val endProgress = (currentStep - 1) * 100f / maxStep // 끝 값 (현재 단계)

        // ValueAnimator 생성
        val valueAnimator = ValueAnimator.ofFloat(startProgress, endProgress)
        valueAnimator.duration = animationDuration

        // 값이 변할 때마다 호출되는 리스너 설정
        valueAnimator.addUpdateListener {
            val progress = it.animatedValue as Float
            progressBar.progress = progress.toInt()
        }

        // 애니메이션 시작
        valueAnimator.start()
    }

    // 다음 단계로 진행하는 메서드
    private fun nextStep() {
        if (currentStep < maxStep) {
            currentStep++
            updateProgressBar(currentStep, maxStep)
        }
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 버튼 이벤트 처리
            Log.e(TAG, "뒤로가기 클릭")
        }
    }

}