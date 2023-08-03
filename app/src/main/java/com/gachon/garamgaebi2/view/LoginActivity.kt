package com.gachon.garamgaebi2.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityLoginBinding
import com.gachon.garamgaebi2.databinding.ActivityMainBinding
import com.gachon.garamgaebi2.viewModel.LoginViewModel
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.BR


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        with(viewModel){
            id.observe(this@LoginActivity) {
                idIsValid.value = it.isNotEmpty()
                loginIsValid.value = idIsValid.value!! && pwIsValid.value!!
                editTextBorderColor.value = if (it.isEmpty()) {
                    ContextCompat.getColor(getApplication(), R.color.red)
                } else {
                    ContextCompat.getColor(getApplication(), R.color.main_blue)
                }
            }
            pw.observe(this@LoginActivity) {
                pwIsValid.value = it.isNotEmpty()
                loginIsValid.value = idIsValid.value!! && pwIsValid.value!!
            }


            // EditText의 초기 상태를 설정합니다.
            setInitialBorderColor()
        }

//
//        val includedLayout = binding.includedLayout // included 레이아웃의 바인딩을 가져옵니다
//        includedLayout.editText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                // EditText의 값이 변경될 때 ViewModel의 setEditTextValue를 호출합니다.
//                viewModel.setEditTextValue(s.toString())
//            }
//        })
    }

}