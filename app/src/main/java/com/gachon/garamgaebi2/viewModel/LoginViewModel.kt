package com.gachon.garamgaebi2.viewModel

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.GaramgaebiApplication.Companion.getApplication

class LoginViewModel : ViewModel(){

    val id = MutableLiveData<String>("")
    val pw = MutableLiveData<String>("")
    val idIsValid = MutableLiveData<Boolean>(false)
    val pwIsValid = MutableLiveData<Boolean>(false)
    val loginIsValid = MutableLiveData<Boolean>(false)

    val editTextBorderColor = MutableLiveData<Int>(
        ContextCompat.getColor(getApplication(), R.color.black)
    )

    private fun setEditTextBorderColor(color: Int) {
        editTextBorderColor.value = ContextCompat.getColor(getApplication(), color)
    }

    // 초기 상태를 설정합니다.
    fun setInitialBorderColor() {
        setEditTextBorderColor(R.color.black)
    }
}