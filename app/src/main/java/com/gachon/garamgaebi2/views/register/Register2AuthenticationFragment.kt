package com.gachon.garamgaebi2.views.register

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
        binding.checkTv.apply{
            text = resources.getString(R.string.register2_2)
            setTextColor(ContextCompat.getColorStateList(GaramgaebiApplication.getApplication(), R.color.black))
        }
        binding.emailTextfield.input.isEnabled = false

    }

    override fun initListener(){

        binding.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                binding.border2.visibility = View.INVISIBLE

            }else{
                binding.border2.visibility = View.VISIBLE
            }
        }
    }

    override fun initViewModel(){
        viewModel.startTimer()
        observe()
    }

    private fun observe() {
        binding.resendTv.setOnClickListener {

            val resendBottomDialogFragment = ResendBottomDialogFragment()
            resendBottomDialogFragment.show(parentFragmentManager, "ResendBottomDialogFragment")
        }

        viewModel.codeIsValid.observe(viewLifecycleOwner) {
            if (it) {
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
}


