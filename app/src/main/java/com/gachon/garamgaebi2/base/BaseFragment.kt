package com.gachon.garamgaebi2.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

// Fragment의 기본을 작성, 뷰 바인딩 활용
@Suppress("UNREACHABLE_CODE")
abstract class BaseFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes val layoutResId: Int
) : Fragment(layoutResId) {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    var disposables = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showCustomToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    override fun onStop() {
        super.onStop()
        if (disposables.size() > 0) {
            disposables.clear()
        }
    }
}