package com.gachon.garamgaebi2.viewModel

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.di.GaramgaebiApplication.Companion.getApplication

class RegisterViewModel : ViewModel(){

    val pw = MutableLiveData<String>("")
    val pwIsValid = MutableLiveData<Boolean>(false)
    val pwSideText = MutableLiveData<String>("")

    val email = MutableLiveData<String>("")
    val emailIsValid = MutableLiveData<Boolean>(false)

    val codeIsValid = MutableLiveData<Boolean>(false)

    val nickName = MutableLiveData<String>("")
    val nickNameIsValid = MutableLiveData<Boolean>(false)
    val nickNameSideText = MutableLiveData<String>("")
    val nickNameError = MutableLiveData<String>("")

    val pwCheck = MutableLiveData<String>("")
    val pwCheckIsValid = MutableLiveData<Boolean>(false)
    val pwCheckSideText = MutableLiveData<String>("")

    val infoIsValid = MutableLiveData<Boolean>(nickNameIsValid.value == true && pwIsValid.value == true && pwCheckIsValid.value == true)


    val isStudent = MutableLiveData<Boolean>(false)
    val isGraduate = MutableLiveData<Boolean>(false)

    val major = MutableLiveData<String>("")
    val majorIsValid = MutableLiveData<Boolean>(true)

    val company = MutableLiveData<String>("")
    val companyIsValid = MutableLiveData<Boolean>(false)

    val detailIsValid = MutableLiveData<Boolean>(
        ((isStudent.value == true && majorIsValid.value == true)
                || (isGraduate.value == true && companyIsValid.value == true && majorIsValid.value == true)))

    val registerIsValid = MutableLiveData<Boolean>(false)

    val btnOn = MutableLiveData<Boolean>(false)

    val registerBtnColor = MutableLiveData<Int>(ContextCompat.getColor(getApplication(), R.color.light_gray))

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


    val isTermsAllChecked = MutableLiveData<Boolean>(false)
    val isTerms1Checked = MutableLiveData<Boolean>(false)
    val isTerms2Checked = MutableLiveData<Boolean>(false)
    val isTerms3Checked = MutableLiveData<Boolean>(false)


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