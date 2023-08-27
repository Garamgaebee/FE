package com.gachon.garamgaebi2.util

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.databinding.FragmentTermsBottomDialogBinding
import com.gachon.garamgaebi2.views.WelcomeActivity
import com.gachon.garamgaebi2.viewModel.RegisterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TermsBottomDialogFragment () :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentTermsBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.termsAll.term.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) // 18sp로 텍스트 크기 설정

        val spannableStringBuilder_all = SpannableStringBuilder(binding.termsAll.term.text)
        // 스타일 적용
        val styleSpan = StyleSpan(Typeface.BOLD)
        spannableStringBuilder_all.setSpan(styleSpan, 0, binding.termsAll.term.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.termsAll.term.text = spannableStringBuilder_all

        viewModel.isTermsAllChecked.observe(viewLifecycleOwner) {
            if (it) {
                binding.nextBtn.nextBtn.apply {
                    isClickable = true
                }
                viewModel.btnOn.value = true

            } else {
                binding.nextBtn.nextBtn.apply {
                    isClickable = false
                }
                viewModel.btnOn.value = false
            }
        }

        binding.nextBtn.nextBtn.setOnClickListener {
            val intent = Intent(activity, WelcomeActivity::class.java)
            requireActivity().startActivity(intent)
            dismiss()
        }

    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}