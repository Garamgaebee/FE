package com.gachon.garamgaebi2.view.register

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.di.GaramgaebiApplication
import com.gachon.garamgaebi2.util.ResendBottomDialogFragment
import com.gachon.garamgaebi2.databinding.FragmentRegister2AuthenticationBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Register2AuthenticationFragment : BaseBindingFragment<FragmentRegister2AuthenticationBinding>(R.layout.fragment_register2_authentication) {
    private val viewModel: RegisterViewModel by activityViewModels()
    private var timerJob: Job? = null
    private var timeOut = false

    override fun initView(){
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        GaramgaebiApplication.registerProcess = 2

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("D","BackPressd In Fragment")
                requireActivity().supportFragmentManager.popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )
        startTimer()

    }

    override fun initListener(){
        binding.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.border2.visibility = View.VISIBLE
                if (timeOut == false) {
                    if (s.toString().isNotEmpty()) {
                        viewModel.codeIsValid.value = true
                    } else {
                        viewModel.codeIsValid.value = false
                    }

                    if (viewModel.codeIsValid.value == true) {
                        binding.checkTv.apply {
                            text = resources.getString(R.string.register2_2)
                        }
                        binding.border.setBackgroundColor(
                            ContextCompat.getColor(
                                GaramgaebiApplication.getApplication(), R.color.main_blue
                            )
                        )
                        binding.border2.setBackgroundColor(
                            ContextCompat.getColor(
                                GaramgaebiApplication.getApplication(), R.color.main_blue
                            )
                        )
                    } else {
                        binding.checkTv.apply {
                            text = resources.getString(R.string.email_code_error)
                        }
                        binding.border.setBackgroundColor(
                            ContextCompat.getColor(
                                GaramgaebiApplication.getApplication(), R.color.red
                            )
                        )
                        binding.border2.setBackgroundColor(
                            ContextCompat.getColor(
                                GaramgaebiApplication.getApplication(), R.color.red
                            )
                        )
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                binding.border2.visibility = View.INVISIBLE

            }else{
                binding.border2.visibility = View.VISIBLE
            }
        }
    }

    override fun initViewModel(){
        observe()
    }

    private fun observe(){
        binding.resendTv.setOnClickListener {

            val resendBottomDialogFragment = ResendBottomDialogFragment()
            resendBottomDialogFragment.show(parentFragmentManager, "ResendBottomDialogFragment")
        }

        binding.checkTv.apply{
            text = resources.getString(R.string.register2_2)
            setTextColor(ContextCompat.getColorStateList(GaramgaebiApplication.getApplication(), R.color.black))
        }
    }
    private fun startTimer() {
        // 타이머를 시작할 총 시간 (초 단위)
        val totalTimeInSeconds: Long = 600 // 예: 2분 (2분 * 60초)

        // 코루틴을 사용하여 타이머를 실행합니다.
        timerJob = lifecycleScope.launch(Dispatchers.Main) {
            var remainingTimeInSeconds = totalTimeInSeconds

            while (remainingTimeInSeconds >= 0) {
                // 남은 시간을 분과 초로 변환하여 TextView에 업데이트합니다.
                val minutes = remainingTimeInSeconds / 60
                val seconds = remainingTimeInSeconds % 60
                val timeLeftFormatted = "%02d:%02d".format(minutes, seconds)
                binding.extra.text = timeLeftFormatted

                // 1초씩 대기합니다.
                delay(1000)

                // 남은 시간을 1초씩 감소시킵니다.
                remainingTimeInSeconds--
            }

            // 타이머 종료 시 필요한 작업을 수행합니다.
            binding.extra.text = "00:00"
            timeOut = true
            viewModel.codeIsValid.value = false


        }
    }

}


