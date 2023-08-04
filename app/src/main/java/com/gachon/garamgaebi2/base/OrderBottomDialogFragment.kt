package com.gachon.garamgaebi2.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginStart
import com.gachon.garamgaebi2.databinding.FragmentOrderBottomDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderBottomDialogFragment () :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentOrderBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun dismiss() {
        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}