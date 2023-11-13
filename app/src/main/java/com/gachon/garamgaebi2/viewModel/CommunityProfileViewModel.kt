package com.gachon.garamgaebi2.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityProfileViewModel : ViewModel(){

    private val _goToWithdrawalBtnClicked = MutableLiveData<Boolean>(false)
    val goToWithdrawalBtnClicked : LiveData<Boolean> = _goToWithdrawalBtnClicked


    private val _withdrawalBtnClicked = MutableLiveData<Boolean>(false)
    val withdrawalBtnClicked : LiveData<Boolean> = _withdrawalBtnClicked


    private val _allInputIsValid = MutableLiveData<Boolean>(false)
    val allInputIsValid : LiveData<Boolean> = _allInputIsValid

    private val _nameIsValid = MutableLiveData<Boolean>(false)
    val nameIsValid : LiveData<Boolean> = _nameIsValid

    private val _descriptionIsValid = MutableLiveData<Boolean>(false)
    val descriptionIsValid : LiveData<Boolean> = _descriptionIsValid

    private val _linkIsValid = MutableLiveData<Boolean>(false)
    val linkIsValid : LiveData<Boolean> = _linkIsValid

    val name = MutableLiveData<String>("")
    val description = MutableLiveData<String>("")
    val link = MutableLiveData<String>("")

    private val _nameInputClicked = MutableLiveData<Boolean>(false)
    val nameInputClicked : LiveData<Boolean> = _nameInputClicked

    private val _descriptionInputClicked = MutableLiveData<Boolean>(false)
    val descriptionInputClicked : LiveData<Boolean> = _descriptionInputClicked

    private val _linkInputClicked = MutableLiveData<Boolean>(false)
    val linkInputClicked : LiveData<Boolean> = _linkInputClicked

    private val _isUserType = MutableLiveData<Int>(0)
    val isUserType : LiveData<Int> = _isUserType

    private val _goToCommunityProfileEditBtnClicked = MutableLiveData<Boolean>(false)
    val goToCommunityProfileEditBtnClicked : LiveData<Boolean> = _goToCommunityProfileEditBtnClicked

    // 회원 관리
    private val _isManageComplete = MutableLiveData<Boolean>(false)
    val isManageComplete : LiveData<Boolean> = _isManageComplete

    private val selectionStateMap: MutableMap<Int, Boolean> = mutableMapOf()
    init {
        //item.size
        for(i in 0 until 4 ) {
            selectionStateMap[i] = false
        }
    }

    // 사진 업로드
    val imageUri = MutableLiveData<Uri>()
    val isLoadImage = MutableLiveData<Boolean>(false)


    fun onClickTypeChange(){
        _isUserType.value = isUserType.value!! + 1

        _isUserType.value = isUserType.value!! % 3
    }

    fun onClickGoToCommunityProfileEdit(){
        _goToCommunityProfileEditBtnClicked.value = true
        Log.d("goToCommunityProfileEditBtnClicked",goToCommunityProfileEditBtnClicked.value.toString())
    }


    fun onClickGoToWithdrawal(){
        _goToWithdrawalBtnClicked.value = true
    }

    fun onClickWithdrawal(){
        _withdrawalBtnClicked.value = true
    }

    fun onClickNameInput(){
        _nameInputClicked.value = true
        _descriptionInputClicked.value = false
        _linkInputClicked.value = false
    }
    fun onClickDescriptionInput(){
        _descriptionInputClicked.value = false
        _descriptionInputClicked.value = true
        _linkInputClicked.value = false
    }

    fun onClickLinkInput(){
        _linkInputClicked.value = false
        _descriptionInputClicked.value = false
        _linkInputClicked.value = true
    }

    fun onNameTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _nameIsValid.value = s.isNotEmpty() && count <= 28
        _allInputIsValid.value = _nameIsValid.value == true && _descriptionIsValid.value == true && _linkIsValid.value == true

    }

    fun onDescriptionTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _descriptionIsValid.value = s.isNotEmpty() && count <= 80
        _allInputIsValid.value = _nameIsValid.value == true && _descriptionIsValid.value == true && _linkIsValid.value == true

    }


    fun onLinkTextChanged(s: CharSequence, start: Int, before: Int, count: Int){
        _linkIsValid.value = s.isNotEmpty()
        _allInputIsValid.value = _nameIsValid.value == true && _descriptionIsValid.value == true && _linkIsValid.value == true

    }

    fun onRadioButtonSelected(position: Int, isRadioSelected: Boolean) {
        selectionStateMap[position] = isRadioSelected
        checkAllSelected()
    }
    private fun checkAllSelected() {
        _isManageComplete.value = selectionStateMap.values.all { it }
    }
}