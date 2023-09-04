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

class CommunityProfileViewModel : ViewModel(){

    private val _goToWithdrawalBtnClicked = MutableLiveData<Boolean>(false)
    val goToWithdrawalBtnClicked : LiveData<Boolean> = _goToWithdrawalBtnClicked


    private val _withdrawalBtnClicked = MutableLiveData<Boolean>(false)
    val withdrawalBtnClicked : LiveData<Boolean> = _withdrawalBtnClicked

    fun onClickGoToWithdrawal(){
        _goToWithdrawalBtnClicked.value = true
    }

    fun onClickWithdrawal(){
        _withdrawalBtnClicked.value = true
    }





}