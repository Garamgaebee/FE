package com.gachon.garamgaebi2.views.register

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.di.GaramgaebiApplication
import com.gachon.garamgaebi2.databinding.FragmentRegister1EmailBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel

class Register1EmailFragment  : BaseBindingFragment<FragmentRegister1EmailBinding>(R.layout.fragment_register1_email) {
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onResume() {
        GaramgaebiApplication.registerProcess = 1
        viewModel.registerProcess.value = 1
        super.onResume()
    }
    override fun initView(){
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        GaramgaebiApplication.registerProcess = 1

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("D","BackPressd In Fragment")
                requireActivity().supportFragmentManager.popBackStack()
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )

        initObserver()
    }

    private fun initObserver(){
        viewModel.emailIsValid.observe(viewLifecycleOwner){
            if(it == true){
                binding.emailTextfield.border.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication.getApplication(), R.color.main_blue))
                binding.emailTextfield.border2.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication.getApplication(), R.color.main_blue))
                binding.emailTextfield.checkTv.visibility = View.INVISIBLE
            }else{
                binding.emailTextfield.border.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication.getApplication(), R.color.red))
                binding.emailTextfield.border2.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication.getApplication(), R.color.red))
            }
        }

    }

    override fun initListener(){
        val emailLayout = binding.emailTextfield
        emailLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                emailLayout.border2.visibility = View.INVISIBLE

            }else{
                emailLayout.border2.visibility = View.VISIBLE
            }
        }
    }

}

