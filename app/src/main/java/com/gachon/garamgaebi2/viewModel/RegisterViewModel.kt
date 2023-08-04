package com.gachon.garamgaebi2.viewModel

import android.graphics.Color
import android.provider.Settings.System.getString
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.GaramgaebiApplication.Companion.getApplication

class RegisterViewModel : ViewModel(){

    val id = MutableLiveData<String>("")
    val pw = MutableLiveData<String>("")
    val idIsValid = MutableLiveData<Boolean>(false)
    val pwIsValid = MutableLiveData<Boolean>(false)

    val email = MutableLiveData<String>("")
    val emailIsValid = MutableLiveData<Boolean>(false)

    val nickName = MutableLiveData<String>("")
    val nickNameIsValid = MutableLiveData<Boolean>(false)

    val pwCheck = MutableLiveData<String>("")
    val pwCheckIsValid = MutableLiveData<Boolean>(false)


    val registerIsValid = MutableLiveData<Boolean>(false)

    val registerProcess = MutableLiveData<String>()

    init {
        // 리소스에서 문자열 가져오기
        val initialText = getApplication().getString(R.string.next)
        registerProcess.value = initialText
    }

    val btnColor = MutableLiveData<Int>(
        ContextCompat.getColor(getApplication(), R.color.light_gray)
    )

    var registerBtnColor = ContextCompat.getColor(getApplication(), R.color.light_gray)


    val editTextBorderColor = MutableLiveData<Int>(
        ContextCompat.getColor(getApplication(), R.color.black)
    )

    private fun setEditTextBorderColor(color: Int) {
        editTextBorderColor.value = ContextCompat.getColor(getApplication(), color)
    }
    fun onInputChanged(){
        editTextBorderColor.value = if (id.value?.isEmpty() == true) {
            ContextCompat.getColor(getApplication(), R.color.red)
        } else {
            ContextCompat.getColor(getApplication(), R.color.main_blue)
        }
    }

    // 초기 상태를 설정합니다.
    fun setInitialBorderColor() {
        setEditTextBorderColor(R.color.black)
    }
}