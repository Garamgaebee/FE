package com.gachon.garamgaebi2.view

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.base.GaramgaebiApplication
import com.gachon.garamgaebi2.databinding.ActivityLoginBinding
import com.gachon.garamgaebi2.databinding.ActivityRegisterBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){

    private lateinit var progressBar: ProgressBar
    private var currentStep = 1
    private val maxStep = 4
    private val animationDuration = 1000L // 애니메이션 지속 시간 (1.5초)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register1EmailFragment()).commit()


        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.registerProcessBtn.nextBtn.setOnClickListener {
            currentStep = GaramgaebiApplication.registerProcess
            changeFragment()
            nextStep()

        }

        progressBar = binding.progressBar

    }

    fun changeFragment(){
        when (currentStep) {
            1 ->{
                // Register 1로 이동
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register2AuthenticationFragment()).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.authenticate)

            }
            2->{
                // Register 2로 이동
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register3InfoFragment()).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.next)
            }
            3->{
                // Register 3로 이동
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, Register3InfoFragment()).commit()
                binding.registerProcessBtn.nextBtn.text= application.getString(R.string.start)
            }
            4->{

            }
        }
    }

    // 프로그레스바 애니메이션 메서드
    private fun updateProgressBar(currentStep: Int, maxStep: Int) {
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

    // 다음 단계로 진행하는 메서드
    private fun nextStep() {
        if (currentStep < maxStep) {
            currentStep++
            updateProgressBar(currentStep, maxStep)
        }
    }

}