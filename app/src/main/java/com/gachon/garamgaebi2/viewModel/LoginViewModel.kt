package com.gachon.garamgaebi2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    val id = MutableLiveData<String>("")
    val pw = MutableLiveData<String>("")
    val idIsValid = MutableLiveData<Boolean>(false)
    val pwIsValid = MutableLiveData<Boolean>(false)
}