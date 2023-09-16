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
import androidx.lifecycle.ViewModelProvider
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.databinding.FragmentCommunityProfileManagerMenuBottomDialogBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel
import com.gachon.garamgaebi2.viewModel.RegisterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommunityProfileManagerMenuBottomDialogFragment(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {
    private val viewModel: CommunityProfileViewModel by activityViewModels()

    lateinit var binding: FragmentCommunityProfileManagerMenuBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_community_profile_manager_menu_bottom_dialog,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        with(binding) {
            // 프로필 편집 이동 로직
            onClickedItem(profileEditTv, 0)

            // 공지사항 작성 이동 로직
            onClickedItem(postNoticeTv, 1)

            // 멤버 관리 이동 로직
            onClickedItem(manageMemberTv, 2)

            // 커뮤니티 공유 이동 로직
            onClickedItem(shareCommunityTv, 3)
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun onClickedItem(view: TextView, itemClick: Int) {
        view.setOnTouchListener(View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.setBackgroundColor(resources.getColor(R.color.main_blue, null))
                    view.setTextColor(resources.getColor(R.color.white, null))
                }

                MotionEvent.ACTION_UP -> {
                    view.setBackgroundColor(resources.getColor(R.color.white))
                    view.setTextColor(resources.getColor(R.color.black))
                    dismiss()
                    if(itemClick == 2) {
                        startActivity(Intent(requireContext(), ManageMemberActivity::class.java))
                    }
                    itemClick(itemClick)
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