package com.gachon.garamgaebi2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gachon.domain.model.ThreadMainListResult
import com.gachon.domain.usecase.GetThreadMainListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val getThreadMainListUseCase: GetThreadMainListUseCase
): ViewModel() {
    private val _threadMainListLiveData = MutableLiveData<List<ThreadMainListResult>>()
    val threadMainListLiveData: LiveData<List<ThreadMainListResult>>
        get() = _threadMainListLiveData

    fun getThreadMainList(orderType: Int) {
        viewModelScope.launch {
            val res = getThreadMainListUseCase(orderType)
            _threadMainListLiveData.value = res.result
        }
    }
}