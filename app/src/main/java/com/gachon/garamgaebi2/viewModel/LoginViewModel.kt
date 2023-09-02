package com.gachon.garamgaebi2.viewModel

import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.di.GaramgaebiApplication.Companion.getApplication

class LoginViewModel : ViewModel(){

    /*
     @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _profileInfoLiveData = MutableLiveData<ProfileInfo>()
    private val _withdrawalResponseLiveData = MutableLiveData<String>()
    private val _nicknameValidationLiveData = MutableLiveData<String>()
    private val _editProfileResponseLiveData = MutableLiveData<String>()
    private val _countLiveData = MutableLiveData<Int>()
    private val _countOpenLiveData = MutableLiveData<Int>()
    private val _mySentenceListLiveData = MutableLiveData<List<MySentenceList>>()
    private val _mySentenceResponseLiveData = MutableLiveData<DefaultResponse>()
    private val _modifySentenceLiveData = MutableLiveData<DefaultResponse>()
    private val _sentenceInfoMeaningData = MutableLiveData<String>()
    private val _sentenceInfoKeywordData = MutableLiveData<String>()

    val profileInfoLiveData: LiveData<ProfileInfo> = _profileInfoLiveData
    val withdrawalResponseLiveData: LiveData<String> = _withdrawalResponseLiveData
    val nicknameValidationLiveData: LiveData<String> = _nicknameValidationLiveData
     */

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
    fun login(){

    }
}