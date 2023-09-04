package com.gachon.garamgaebi2.views

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.databinding.FragmentCommunityProfileMemberWithdrawalBottomDialogBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommunityProfileMemberWithdrawalBottomDialogFragment() :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentCommunityProfileMemberWithdrawalBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_community_profile_member_withdrawal_bottom_dialog,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[CommunityProfileViewModel::class.java]
        binding.setVariable(BR.viewModel, viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.closeBtn.nextBtn.setOnClickListener {
            // val intent = Intent(activity, WelcomeActivity::class.java)
            //requireActivity().startActivity(intent)
            dismiss()
        }
        initListener()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}