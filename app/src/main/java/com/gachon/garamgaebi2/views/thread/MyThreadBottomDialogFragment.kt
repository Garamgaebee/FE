package com.gachon.garamgaebi2.views.thread

import android.annotation.SuppressLint
import android.content.DialogInterface
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
import com.gachon.garamgaebi2.databinding.FragmentCommunityProfileMemberWithdrawalBottomDialogBinding
import com.gachon.garamgaebi2.databinding.FragmentMyThreadBottomDialogBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel
import com.gachon.garamgaebi2.viewModel.ThreadViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyThreadBottomDialogFragment(val itemClick : (Int) -> Unit) :
    BottomSheetDialogFragment() {
    private val viewModel: ThreadViewModel by activityViewModels()

    lateinit var binding: FragmentMyThreadBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_my_thread_bottom_dialog,
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

        with(binding) {
            onClickedItem(removeTv, 0)
            onClickedItem(shareCommunityTv, 1)
        }

        binding.closeBtn.nextBtn.setOnClickListener {
            // val intent = Intent(activity, WelcomeActivity::class.java)
            //requireActivity().startActivity(intent)
            dismiss()
        }
        initListener()

    }
    @SuppressLint("ClickableViewAccessibility")
    private fun onClickedItem(view: TextView, itemClick : Int) {
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
                    itemClick(itemClick)

                }
            }
            true
        })
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