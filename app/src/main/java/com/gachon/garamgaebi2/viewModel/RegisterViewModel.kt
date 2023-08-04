package com.gachon.garamgaebi2.viewModel

import android.graphics.Color
import android.provider.Settings.System.getString
import android.util.Log
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

    val codeIsValid = MutableLiveData<Boolean>(false)

    val nickName = MutableLiveData<String>("")
    val nickNameIsValid = MutableLiveData<Boolean>(false)

    val pwCheck = MutableLiveData<String>("")
    val pwCheckIsValid = MutableLiveData<Boolean>(false)

    val isStudent = MutableLiveData<Boolean>(false)
    val isGraduate = MutableLiveData<Boolean>(false)

    val major = MutableLiveData<String>("")
    val majorIsValid = MutableLiveData<Boolean>(false)

    val company = MutableLiveData<String>("")
    val companyIsValid = MutableLiveData<Boolean>(false)

    val registerIsValid = MutableLiveData<Boolean>(false)

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

    fun isStudentClicked() {
        isStudent.value = !isStudent.value!!

        if(isStudent.value == true){
            isGraduate.value = false
        }
    }

    fun isGraduateClicked() {
        isGraduate.value = !isGraduate.value!!

        if(isGraduate.value == true){
            isStudent.value = false
        }

    }

    // 인증코드 시간

    var time = "00:00"
    var minute = 0
    var second = 0

    // 초기 상태를 설정합니다.
    fun setInitialBorderColor() {
        setEditTextBorderColor(R.color.black)
    }

    val isTermsAllChecked = MutableLiveData<Boolean>(false)
    val isTerms1Checked = MutableLiveData<Boolean>(false)
    val isTerms2Checked = MutableLiveData<Boolean>(false)
    val isTerms3Checked = MutableLiveData<Boolean>(false)

    fun onOptionClicked(num : Int){
        when(num){
            1-> onTerms1Clicked()
            2-> onTerms2Clicked()
            3-> onTerms3Clicked()
            0-> onTermsAllClicked()
        }
    }

    fun onTermsAllClicked() {
        isTermsAllChecked.value = !isTermsAllChecked.value!!
        isTerms1Checked.value = isTermsAllChecked.value
        isTerms2Checked.value = isTermsAllChecked.value
        isTerms3Checked.value = isTermsAllChecked.value
    }

    fun onTerms1Clicked() {
        Log.d("RegisterViewModel", "onTerms1Clicked: ")
        isTerms1Checked.value = !isTerms1Checked.value!!
        if(isTerms1Checked.value == false){
            isTermsAllChecked.value = false
        }
        isTermsAllChecked.value = isTerms1Checked.value == true && isTerms2Checked.value == true && isTerms3Checked.value == true
    }

    fun onTerms2Clicked() {
        isTerms2Checked.value = !isTerms2Checked.value!!
        if(isTerms2Checked.value == false){
            isTermsAllChecked.value = false
        }
        isTermsAllChecked.value = isTerms1Checked.value == true && isTerms2Checked.value == true && isTerms3Checked.value == true

    }

    fun onTerms3Clicked() {
        isTerms3Checked.value = !isTerms3Checked.value!!
        if(isTerms3Checked.value == false){
            isTermsAllChecked.value = false
        }
        isTermsAllChecked.value = isTerms1Checked.value == true && isTerms2Checked.value == true && isTerms3Checked.value == true
    }

}