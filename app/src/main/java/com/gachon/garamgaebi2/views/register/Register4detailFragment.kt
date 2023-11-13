package com.gachon.garamgaebi2.views.register

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.di.GaramgaebiApplication
import com.gachon.garamgaebi2.databinding.FragmentRegister4DetailBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel

class Register4detailFragment  : BaseBindingFragment<FragmentRegister4DetailBinding>(R.layout.fragment_register4_detail) {

    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onResume() {
        GaramgaebiApplication.registerProcess = 4
        viewModel.registerProcess.value = 4
        super.onResume()
    }
    override fun initView(){
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        GaramgaebiApplication.registerProcess = 4

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("D","BackPressd In Fragment")
                requireActivity().supportFragmentManager.popBackStack()
                val activity = requireActivity() as RegisterActivity
                activity.backProgressBar(3,3)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )
        observe()

    }

    private fun observe(){
        val majorLayout = binding.majorTextfield

        viewModel.majorIsValid.observe(viewLifecycleOwner){
            if(it == true){
                majorLayout.border.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.main_blue))
                majorLayout.border2.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.main_blue))
            }else{
                majorLayout.border.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.red))
                majorLayout.border2.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.red))
            }
        }
        val companyLayout = binding.companyTextfield
        viewModel.companyIsValid.observe(viewLifecycleOwner){
            if(it) {
                companyLayout.border.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.main_blue))
                companyLayout.border2.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.main_blue))
            }else{
                companyLayout.border.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.red))
                companyLayout.border2.setBackgroundColor(
                    ContextCompat.getColor(
                        GaramgaebiApplication(), R.color.red))
            }
        }
    }

    override fun initListener(){
        val majorLayout = binding.majorTextfield

        majorLayout.input.hint = this.getString(R.string.major_hint)
        majorLayout.sideIcon.visibility = View.VISIBLE
        majorLayout.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 18sp로 텍스트 크기 설정
        majorLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                majorLayout.sideIcon.visibility = View.GONE
                majorLayout.border2.visibility = View.INVISIBLE
            }else{
                majorLayout.sideIcon.visibility = View.VISIBLE
                majorLayout.border2.visibility = View.VISIBLE
            }
        }


        val companyLayout = binding.companyTextfield

        companyLayout.input.hint = this.getString(R.string.company_hint)
        companyLayout.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 18sp로 텍스트 크기 설정
        companyLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                companyLayout.sideIcon.visibility = View.GONE
                companyLayout.border2.visibility = View.INVISIBLE
            }else{
                companyLayout.sideIcon.visibility = View.VISIBLE
                companyLayout.border2.visibility = View.VISIBLE
            }
        }
        companyLayout.sideIcon.setOnClickListener {
            companyLayout.input.text = null
        }

    }


}

