package com.gachon.garamgaebi2.views.communityProfile

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.databinding.FragmentCommunityProfileManagerMenuBottomDialogBinding
import com.gachon.garamgaebi2.databinding.FragmentManageMemberBottomDialogBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ManageMemberBottomDialogFragment(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {
    private val viewModel: CommunityProfileViewModel by activityViewModels()

    lateinit var binding: FragmentManageMemberBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_manage_member_bottom_dialog,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initListener()

    }

    private fun initListener() {
        with(binding) {
            // 내보내기
            onClickedItem(manageMemberExportTv, 0)
            // 모임장 추가하기
            onClickedItem(manageMemberAddLeaderTv, 1)
            // 닫기
            closeBtn.nextBtn.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun onClickedItem(view: TextView, itemClick: Int) {
        view.setOnClickListener {
            itemClick(itemClick)
            dismiss()
        }
    }
}