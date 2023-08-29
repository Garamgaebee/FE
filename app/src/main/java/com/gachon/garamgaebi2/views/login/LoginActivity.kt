package com.gachon.garamgaebi2.views.login

import android.content.ContentValues
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityLoginBinding
import com.gachon.garamgaebi2.viewModel.LoginViewModel
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.util.CustomPasswordTransformation
import com.gachon.garamgaebi2.di.GaramgaebiApplication


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate){

    private val viewModel by viewModels<LoginViewModel>()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun initView() {
        observe()
        initListener()
        binding.root.isFocusableInTouchMode = true
        binding.root.isClickable = true
        binding.root.setOnTouchListener { view, motionEvent ->
            hideKeyboard(view)
            false
        }
        this.onBackPressedDispatcher.addCallback(this, callback) //위에서 생성한 콜백 인스턴스 붙여주기

    }

    private fun observe() {
//        binding.setVariable(BR.viewModel, viewModel)
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this

        with(viewModel) {
            id.observe(this@LoginActivity) {
                idIsValid.value = it.isNotEmpty()
                loginIsValid.value = idIsValid.value!! && pwIsValid.value!!
            }
            pw.observe(this@LoginActivity) {
                pwIsValid.value = it.isNotEmpty()
                loginIsValid.value = idIsValid.value!! && pwIsValid.value!!
            }
            // EditText의 초기 상태를 설정합니다.
            setInitialBorderColor()
        }
    }

    private fun initListener(){
        binding.backBtn.setOnClickListener {
                finish()
        }

        val idInput = binding.idTextfield // included 레이아웃의 바인딩을 가져옵니다

        idInput.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                idInput.border2.visibility = View.VISIBLE

                if(viewModel.idIsValid.value == true) {
                    idInput.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                    idInput.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                }else{
                    idInput.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                    idInput.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        idInput.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                idInput.sideIcon.visibility = View.GONE
                idInput.border2.visibility = View.INVISIBLE
            }else{
                idInput.sideIcon.visibility = View.GONE
                idInput.border2.visibility = View.VISIBLE
            }
        }

        val pwInput = binding.pwTextfield // included 레이아웃의 바인딩을 가져옵니다

        pwInput.input.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        pwInput.input.transformationMethod = CustomPasswordTransformation()
        pwInput.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) // 18sp로 텍스트 크기 설정

        pwInput.input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                pwInput.border2.visibility = View.VISIBLE

                if(viewModel.pwIsValid.value == true) {
                    pwInput.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                    pwInput.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.main_blue))
                }else{
                    pwInput.border.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))
                    pwInput.border2.setBackgroundColor(
                        ContextCompat.getColor(
                            GaramgaebiApplication.getApplication(), R.color.red))                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        pwInput.sideIcon.setOnClickListener {
            pwInput.input.text = null
        }

        pwInput.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                pwInput.sideIcon.visibility = View.GONE
                pwInput.border2.visibility = View.INVISIBLE
            }else{
                pwInput.sideIcon.visibility = View.VISIBLE
                pwInput.border2.visibility = View.VISIBLE
            }
        }
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 버튼 이벤트 처리
            Log.e(ContentValues.TAG, "뒤로가기 클릭")
        }
    }
    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            this.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}