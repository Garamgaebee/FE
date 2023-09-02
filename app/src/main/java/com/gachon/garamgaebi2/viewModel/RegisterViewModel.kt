package com.gachon.garamgaebi2.viewModel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.di.GaramgaebiApplication.Companion.getApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel(){

    val pw = MutableLiveData<String>("")
    private val _pwIsValid = MutableLiveData<Boolean>()
    val pwSideText = MutableLiveData<String>("")

    val email = MutableLiveData<String>("")
    private val _emailIsValid = MutableLiveData<Boolean>()

    val code = MutableLiveData<String>()
    private val _codeIsValid = MutableLiveData<Boolean>()

    val nickName = MutableLiveData<String>("")
    private val _nickNameIsValid = MutableLiveData<Boolean>()
    val nickNameSideText = MutableLiveData<String>("")
    val nickNameError = MutableLiveData<String>(
        getApplication().resources.getString(R.string.nickname_description)
    )

    val pwCheck = MutableLiveData<String>("")
    private val _pwCheckIsValid = MutableLiveData<Boolean>()
    val pwCheckSideText = MutableLiveData<String>("")


    val isStudent = MutableLiveData<Boolean>(false)
    val isGraduate = MutableLiveData<Boolean>(false)

    val major = MutableLiveData<String>("")
    private val _majorIsValid = MutableLiveData<Boolean>()

    val company = MutableLiveData<String>("")
    private val _companyIsValid = MutableLiveData<Boolean>()

    val btnOn = MutableLiveData<Boolean>(false)
    val registerProcess = MutableLiveData<Int>(1)

    val emailIsValid : LiveData<Boolean> = _emailIsValid
    val pwIsValid : LiveData<Boolean> = _pwIsValid
    val codeIsValid : LiveData<Boolean> = _codeIsValid
    val pwCheckIsValid : LiveData<Boolean> = _pwCheckIsValid
    val nickNameIsValid : LiveData<Boolean> = _nickNameIsValid

    private val _infoIsValid = MutableLiveData<Boolean>(nickNameIsValid.value == true && pwIsValid.value == true && pwCheckIsValid.value == true)
    val infoIsValid : LiveData<Boolean> = _infoIsValid

    val majorIsValid : LiveData<Boolean> = _majorIsValid
    val companyIsValid : LiveData<Boolean> = _companyIsValid

    private val _detailIsValid = MutableLiveData<Boolean>(
        ((isStudent.value == true && majorIsValid.value == true)
                || (isGraduate.value == true && companyIsValid.value == true && majorIsValid.value == true)))
    val detailIsValid : LiveData<Boolean> = _detailIsValid


    val authTimerStarted = MutableLiveData<Boolean>(false)
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

    private var timerJob: Job? = null
    private var timeOut = false
    private val _timerText = MutableLiveData<String>("00:00")
    var timerText : LiveData<String> = _timerText




    // 가입 동의

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

    fun onEmailTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _emailIsValid.value = s.isNotEmpty()
    }

    fun onCodeTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _codeIsValid.value = s.isNotEmpty() && count <= 6
    }

    fun onNicknameTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        if(s.toString().length > 10 || s.toString().length < 4) {
            nickNameError.value = getApplication().resources.getString(R.string.nickname_error)
            _nickNameIsValid.value = false
        }else if(s.toString() == "중복"){
            nickNameError.value = getApplication().resources.getString(R.string.nickname_duplicate)
            _nickNameIsValid.value = false
        }else{
            nickNameError.value = getApplication().resources.getString(R.string.nickname_description)
            _nickNameIsValid.value = true
        }
        _infoIsValid.value = nickNameIsValid.value == true && pwIsValid.value == true && pwCheckIsValid.value == true

    }

    fun onPwTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _pwIsValid.value = s.isNotEmpty()
        _infoIsValid.value = nickNameIsValid.value == true && pwIsValid.value == true && pwCheckIsValid.value == true
    }

    fun onPwCheckTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _pwCheckIsValid.value = s.isNotEmpty()
        _infoIsValid.value = nickNameIsValid.value == true && pwIsValid.value == true && pwCheckIsValid.value == true

    }

    fun onMajorTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _majorIsValid.value = s.isNotEmpty()
        _detailIsValid.value = ((isStudent.value == true && majorIsValid.value == true)
                || (isGraduate.value == true && companyIsValid.value == true && majorIsValid.value == true))
    }

    fun onCompanyTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _companyIsValid.value = s.isNotEmpty()
        _detailIsValid.value = ((isStudent.value == true && majorIsValid.value == true)
                || (isGraduate.value == true && companyIsValid.value == true && majorIsValid.value == true))
    }


    fun startTimer(){
        // 타이머를 시작할 총 시간 (초 단위)
        val totalTimeInSeconds: Long = 600 // 예: 2분 (2분 * 60초)

        // 코루틴을 사용하여 타이머를 실행합니다.
        timerJob = viewModelScope.launch(Dispatchers.Main) {
            var remainingTimeInSeconds = totalTimeInSeconds

            while (remainingTimeInSeconds >= 0 && !authTimerStarted.value!!) {
                // 남은 시간을 분과 초로 변환하여 TextView에 업데이트합니다.
                val minutes = remainingTimeInSeconds / 60
                val seconds = remainingTimeInSeconds % 60
                val timeLeftFormatted = "%02d:%02d".format(minutes, seconds)
                _timerText.value = timeLeftFormatted

                // 1초씩 대기합니다.
                delay(1000)

                // 남은 시간을 1초씩 감소시킵니다.
                remainingTimeInSeconds--
            }

            // 타이머 종료 시 필요한 작업을 수행합니다.
            _timerText.value = "00:00"
            timeOut = true
            _codeIsValid.value = false
        }
    }



}