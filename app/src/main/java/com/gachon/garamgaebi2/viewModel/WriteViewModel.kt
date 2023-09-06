package com.gachon.garamgaebi2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteViewModel: ViewModel() {
    val postContent = MutableLiveData<String>("")
    val imageFirst = MutableLiveData<String>("")
    val imageSecond = MutableLiveData<String>("")
    val imageThird = MutableLiveData<String>("")
    val imageFourth = MutableLiveData<String>("")

    private val _etCnt = MutableLiveData<String>("0")
    val etCnt : LiveData<String> = _etCnt

    val completeIsValid = MutableLiveData<Boolean>(false)

    fun onEditTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        completeIsValid.value = !s.isNullOrEmpty() // || image 갯수 로직
        _etCnt.value = s.length.toString()
    }
}