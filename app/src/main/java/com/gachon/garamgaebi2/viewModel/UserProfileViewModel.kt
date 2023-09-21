package com.gachon.garamgaebi2.viewModel

import android.net.Uri
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

class UserProfileViewModel : ViewModel(){


    private val _allInputIsValid = MutableLiveData<Boolean>(false)
    val allInputIsValid : LiveData<Boolean> = _allInputIsValid

    private val _nameIsValid = MutableLiveData<Boolean>(false)
    val nameIsValid : LiveData<Boolean> = _nameIsValid

    private val _communityInfoIsValid = MutableLiveData<Boolean>(false)
    val communityInfoIsValid : LiveData<Boolean> = _communityInfoIsValid

    private val _descriptionIsValid = MutableLiveData<Boolean>(false)
    val descriptionIsValid : LiveData<Boolean> = _descriptionIsValid

    private val _majorIsValid = MutableLiveData<Boolean>(false)
    val majorIsValid : LiveData<Boolean> = _majorIsValid

    val name = MutableLiveData<String>("")
    val description = MutableLiveData<String>("")
    val major = MutableLiveData<String>("")

    private val _nameInputClicked = MutableLiveData<Boolean>(false)
    val nameInputClicked : LiveData<Boolean> = _nameInputClicked

    private val _communityInfoInputClicked = MutableLiveData<Boolean>(false)
    val communityInfoInputClicked : LiveData<Boolean> = _communityInfoInputClicked

    private val _descriptionInputClicked = MutableLiveData<Boolean>(false)
    val descriptionInputClicked : LiveData<Boolean> = _descriptionInputClicked

    private val _majorInputClicked = MutableLiveData<Boolean>(false)
    val majorInputClicked : LiveData<Boolean> = _majorInputClicked

    private val _isUserType = MutableLiveData<Int>(0)
    val isUserType : LiveData<Int> = _isUserType

    private val _goToCommunityProfileEditBtnClicked = MutableLiveData<Boolean>(false)
    val goToCommunityProfileEditBtnClicked : LiveData<Boolean> = _goToCommunityProfileEditBtnClicked

    // 사진 업로드
    val imageUri = MutableLiveData<Uri>()
    val isLoadImage = MutableLiveData<Boolean>(false)

    fun onClickNameInput(){
        _nameInputClicked.value = true
        _descriptionInputClicked.value = false
        _majorInputClicked.value = false
        _communityInfoInputClicked.value = false
    }
    fun onClickDescriptionInput(){
        _descriptionInputClicked.value = false
        _descriptionInputClicked.value = true
        _majorInputClicked.value = false
        _communityInfoInputClicked.value = false
    }

    fun onClickCommunityInfoInput(){
        _descriptionInputClicked.value = false
        _descriptionInputClicked.value = false
        _majorInputClicked.value = false
        _communityInfoInputClicked.value = true
    }

    fun onClickMajorInput(){
        _majorInputClicked.value = false
        _descriptionInputClicked.value = false
        _majorInputClicked.value = true
        _communityInfoInputClicked.value = false
    }

    fun onNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _nameIsValid.value = s.isNotEmpty() && count <= 28
        _allInputIsValid.value = _nameIsValid.value == true || _descriptionIsValid.value == true || _majorIsValid.value == true

    }

    fun onDescriptionTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _descriptionIsValid.value = s.isNotEmpty() && count <= 80
        _allInputIsValid.value = _nameIsValid.value == true || _descriptionIsValid.value == true || _majorIsValid.value == true

    }


    fun onMajorTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _majorIsValid.value = s.isNotEmpty()
        _allInputIsValid.value = _nameIsValid.value == true || _descriptionIsValid.value == true || _majorIsValid.value == true

    }






}