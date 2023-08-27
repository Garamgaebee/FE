package com.gachon.garamgaebi2.views.register

import android.text.Editable
import android.text.InputType
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
import com.gachon.garamgaebi2.util.CustomPasswordTransformation
import com.gachon.garamgaebi2.di.GaramgaebiApplication
import com.gachon.garamgaebi2.databinding.FragmentRegister3InfoBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel

class Register3InfoFragment  : BaseBindingFragment<FragmentRegister3InfoBinding>(R.layout.fragment_register3_info) {
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun initView(){
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        GaramgaebiApplication.registerProcess = 3

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("D","BackPressd In Fragment")
                requireActivity().supportFragmentManager.popBackStack()
                val activity = requireActivity() as RegisterActivity
                activity.backProgressBar(2,3)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )

    }

    override fun initListener(){
        val nickNameLayout = binding.nickNameTextfield

        viewModel.nickNameError.value = resources.getString(R.string.nickname_description)

        nickNameLayout.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 18sp로 텍스트 크기 설정


        nickNameLayout.checkTv.apply {
            visibility = View.VISIBLE
            setTextColor(ContextCompat.getColorStateList(GaramgaebiApplication.getApplication(), R.color.black))
        }
        nickNameLayout.input.hint = resources.getString(R.string.nickname_hint)

        nickNameLayout.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nickNameLayout.border2.visibility = View.VISIBLE

                if(s.toString().length > 10 || s.toString().length < 4) {
                    viewModel.nickNameError.value = resources.getString(R.string.nickname_error)
                    viewModel.nickNameIsValid.value = false
                }else if(s.toString() == "중복"){
                    viewModel.nickNameError.value = resources.getString(R.string.nickname_duplicate)
                    viewModel.nickNameIsValid.value = false
                }else{
                    viewModel.nickNameError.value = resources.getString(R.string.nickname_description)
                    viewModel.nickNameIsValid.value = true
                }

                if(viewModel.nickNameIsValid.value == true) {
                    nickNameLayout.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                    nickNameLayout.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                }else{
                    nickNameLayout.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                    nickNameLayout.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                }
                with(viewModel){
                    infoIsValid.value = nickNameIsValid.value!! && pwIsValid.value!! && pwCheckIsValid.value!!
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        nickNameLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                nickNameLayout.sideIcon.visibility = View.GONE
                nickNameLayout.border2.visibility = View.INVISIBLE
            }else{
                nickNameLayout.sideIcon.visibility = View.VISIBLE
                nickNameLayout.border2.visibility = View.VISIBLE
            }
        }
        nickNameLayout.sideIcon.setOnClickListener {
            nickNameLayout.input.text = null
        }

        val pwLayout = binding.pwTextfield

        pwLayout.input.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwLayout.input.transformationMethod = CustomPasswordTransformation()
        pwLayout.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 18sp로 텍스트 크기 설정

        pwLayout.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(s.toString().isNotEmpty()){
                    Log.d("비밀번호", s.toString())
                    viewModel.pwIsValid.value = true
                }else{
                    viewModel.pwIsValid.value = false
                }

                if(viewModel.pwIsValid.value == true) {
                    Log.d("비밀번호2", s.toString())

                    pwLayout.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                    pwLayout.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                }else{
                    pwLayout.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                    pwLayout.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                }
                with(viewModel){
                    infoIsValid.value = nickNameIsValid.value!! && pwIsValid.value!! && pwCheckIsValid.value!!
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        pwLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                pwLayout.sideIcon.visibility = View.GONE
                pwLayout.border2.visibility = View.INVISIBLE
            }else{
                pwLayout.sideIcon.visibility = View.VISIBLE
                pwLayout.border2.visibility = View.VISIBLE
            }
        }
        pwLayout.sideIcon.setOnClickListener {
            pwLayout.input.text = null
        }

        val pwCheckLayout = binding.pwCheckTextfield
        pwCheckLayout.input.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwCheckLayout.input.transformationMethod = CustomPasswordTransformation()
        pwCheckLayout.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 18sp로 텍스트 크기 설정


        pwCheckLayout.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(s.toString().isNotEmpty()){
                    viewModel.pwCheckIsValid.value = true
                }else{
                    viewModel.pwCheckIsValid.value = false
                }

                if(viewModel.pwCheckIsValid.value == true) {
                    pwCheckLayout.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                    pwCheckLayout.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                }else{
                    pwCheckLayout.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                    pwCheckLayout.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                }
                with(viewModel){
                    infoIsValid.value = nickNameIsValid.value!! && pwIsValid.value!! && pwCheckIsValid.value!!
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        pwCheckLayout.sideIcon.setOnClickListener {
            pwCheckLayout.input.text = null
        }

        pwCheckLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                pwCheckLayout.sideIcon.visibility = View.GONE
                pwCheckLayout.border2.visibility = View.INVISIBLE
            }else{
                pwCheckLayout.sideIcon.visibility = View.VISIBLE
                pwCheckLayout.border2.visibility = View.VISIBLE
            }
        }

    }


}

