package com.gachon.garamgaebi2.base

import android.widget.EditText
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("onFocusing")
    fun EditText.onFocusing(callback: GaramgaebiFunction.OnFocusingListener) {
        setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                callback.onFocusing(false)
            } else {
                callback.onFocusing(true)
            }
        }
    }
}