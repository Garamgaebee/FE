package com.gachon.garamgaebi2.views

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.databinding.FragmentCommunityProfileManagerMenuBottomDialogBinding
import com.gachon.garamgaebi2.databinding.FragmentCommunityProfileMemberMenuBottomDialogBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel
import com.gachon.garamgaebi2.viewModel.RegisterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommunityProfileMemberMenuBottomDialogFragment () :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentCommunityProfileMemberMenuBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_community_profile_member_menu_bottom_dialog, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[CommunityProfileViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.closeBtn.nextBtn.setOnClickListener {
           // val intent = Intent(activity, WelcomeActivity::class.java)
            //requireActivity().startActivity(intent)
            dismiss()
        }
        initListener()

    }

    private fun initListener(){
        with(binding){

            onClickedItem(withdrawalTv)
            onClickedItem(shareCommunityTv)
//            withdrawalTv.setOnClickListener {
//                // 멤버 관리 이동 로직
//
//                dismiss()
//            }
//            shareCommunityTv.setOnClickListener {
//                // 커뮤니티 공유 이동 로직
//
//                dismiss()
//            }
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun onClickedItem(view: TextView) {
        view.setOnTouchListener(View.OnTouchListener { v, event ->


            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.setBackgroundColor(resources.getColor(R.color.main_blue))
                    view.setTextColor(resources.getColor(R.color.white))
                }

                MotionEvent.ACTION_UP -> {
                    view.setBackgroundColor(resources.getColor(R.color.white))
                    view.setTextColor(resources.getColor(R.color.black))
                    dismiss()
                }
            }
            true
        })
    }

    override fun dismiss() {
        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}