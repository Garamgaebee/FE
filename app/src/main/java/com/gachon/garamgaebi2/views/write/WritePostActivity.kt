package com.gachon.garamgaebi2.views.write

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.mainFeed.MainFeedRVAdapter
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityWritePostBinding
import com.gachon.garamgaebi2.viewModel.WritePostViewModel

class WritePostActivity : BaseActivity<ActivityWritePostBinding>(ActivityWritePostBinding::inflate) {
    private val viewModel by viewModels<WritePostViewModel>()
    // 갤러리에서 이미지 선택
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private var currentSelectedImageView: ImageView? = null
    private var isProfileListOpen: Boolean = false
    private var isProfileSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initObserve()
        initClickListener()
    }
    override fun initView() {
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri = result.data?.data
                currentSelectedImageView?.setImageURI(selectedImageUri)
            }
        }
    }
    private fun initToolbar() {
        with(binding.toolbar) {
            logo = null
            navigationIcon = context.getDrawable(R.drawable.ic_back_arrow)
            setNavigationOnClickListener { finish() }
        }
    }
    private fun initObserve() {
        viewModel.etCnt.observe(this) { value ->
            if(value.isEmpty()) {
                binding.etCntTv.setTextColor(getColor(R.color.dark_gray))
            }
        }
    }
    private fun initRecycler() {
        binding.profileListRv.apply {
            layoutManager = LinearLayoutManager(this@WritePostActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                WritePostRVItemDecoration(
                TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 21f,
                resources.displayMetrics
            ).toInt())
            )
            adapter = MainFeedRVAdapter(listOf("ㅇㅇ"))
        }
    }

    private fun initClickListener() {
        with(binding) {
            completeBtn.setOnClickListener {
                finish()
            }
            // 프로필 리스트 열기
            openProfileListBtn.setOnClickListener {
                if(isProfileListOpen) {
                    profileListLayout.visibility = View.GONE
                    openProfileListCl.visibility = View.VISIBLE
                    if(isProfileSelected) {
                        openProfileListTitleTv.visibility = View.GONE
                        selectedProfileCl.visibility = View.VISIBLE
                    }
                    isProfileSelected = !isProfileSelected
                } else {
                    profileListLayout.visibility = View.VISIBLE
                    openProfileListCl.visibility = View.GONE

                    isProfileSelected = !isProfileSelected
                }
            }
            // 개인 프로필로 작성하기
            personalProfileTitleTv.setOnClickListener {
                profileListLayout.visibility = View.GONE
                openProfileListCl.visibility = View.VISIBLE
            }


            descFirstIv.setOnClickListener {
                pickImageFromGallery(descFirstIv)
            }
            descSecondIv.setOnClickListener {
                pickImageFromGallery(descSecondIv)
            }
            descThirdIv.setOnClickListener {
                pickImageFromGallery(descThirdIv)
            }
            descFourthIv.setOnClickListener {
                pickImageFromGallery(descFourthIv)
            }
        }
    }

    private fun pickImageFromGallery(targetImageView: ImageView) {
        currentSelectedImageView = targetImageView
        val imageIntent = Intent(Intent.ACTION_PICK)
        imageIntent.type = "image/*"
        imagePickerLauncher.launch(imageIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        imagePickerLauncher.unregister()
    }
}