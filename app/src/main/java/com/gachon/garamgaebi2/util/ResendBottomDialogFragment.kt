package com.gachon.garamgaebi2.util

import android.content.DialogInterface
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.databinding.FragmentResendBottomDialogBinding
import com.gachon.garamgaebi2.viewModel.RegisterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResendBottomDialogFragment () :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentResendBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResendBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val fullText = binding.resendDescription.text.toString()

        // 스타일 적용을 시작할 위치와 끝 위치 지정
        val start = 53 // 시작 위치
        val end = 57   // 끝 위치

        // Spannable 문자열 생성
        val spannableString = SpannableString(fullText)

        // 스타일 지정 (예: 글자색과 스타일)
        spannableString.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 폰트바꾸기
        spannableString.setSpan(TypefaceSpan("spoqa_han_sans_neo_bold"), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 밑줄 넣기
        spannableString.setSpan(UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        // TextView에 적용
        binding.resendDescription.text = spannableString


    }
    override fun dismiss() {
        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}