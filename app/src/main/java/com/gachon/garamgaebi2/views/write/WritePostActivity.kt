package com.gachon.garamgaebi2.views.write

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AnimationUtils
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

    // 프로필 선택 여부
    private var isProfileSelected: Boolean = false
    // 애니메이션 초기 높이값을 설정하기 위한 변수
    var originalHeight: Int = 0
    // 갤러리에서 이미지 선택
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    // 이미지뷰 상태 관리 변수
    enum class ImageViewState { EMPTY, ADD, FILLED }
    private val imageViewsState = arrayOf(
        ImageViewState.ADD, ImageViewState.EMPTY, ImageViewState.EMPTY, ImageViewState.EMPTY
    )
    private var currentSelectedImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initObserve()
        initClickListener()
    }
    override fun initView() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        // 이미지 선택 후 동작
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri = result.data?.data
                currentSelectedImageView?.setImageURI(selectedImageUri)

                val index = when (currentSelectedImageView?.id) {
                    R.id.desc_first_iv -> 0
                    R.id.desc_second_iv -> 1
                    R.id.desc_third_iv -> 2
                    R.id.desc_fourth_iv -> 3
                    else -> -1
                }

                if (index != -1) {
                    imageViewsState[index] = ImageViewState.FILLED
                    if (index + 1 < imageViewsState.size) {
                        imageViewsState[index + 1] = ImageViewState.ADD
                    }
                    updateImageViewState()
                }
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
            profileListCl.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    profileListCl.viewTreeObserver.removeOnPreDrawListener(this)
                    originalHeight = profileListCl.measuredHeight
                    profileListCl.layoutParams.height = 0
                    profileListCl.visibility = View.GONE
                    return true
                }
            })

            // 프로필 리스트 열기
            openProfileListBtn.setOnClickListener {
                openProfileListCl.visibility = View.GONE
                profileListTopCl.visibility = View.VISIBLE
                profileListCl.visibility = View.VISIBLE

                val expandAnimator = ValueAnimator.ofInt(0, originalHeight)
                expandAnimator.addUpdateListener { valueAnimator ->
                    profileListCl.layoutParams.height = valueAnimator.animatedValue as Int
                    profileListCl.requestLayout()
                }
                expandAnimator.duration = 300
                expandAnimator.start()
            }

            // 프로필 리스트 닫기
            profileListCloseBtn.setOnClickListener {
                collapseProfileList {
                    if (isProfileSelected) {
                        openProfileListTitleTv.visibility = View.GONE
                        selectedProfileCl.visibility = View.VISIBLE
                    }
                }
            }
            // 개인 프로필로 작성하기
            personalProfileTitleTv.setOnClickListener {
                collapseProfileList {
                    isProfileSelected = true
                    openProfileListTitleTv.visibility = View.GONE
                    selectedProfileCl.visibility = View.VISIBLE
                }
            }
            // 이미지 업로드
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

            // 이미지 삭제
            deleteFirstIv.setOnClickListener { resetImage(descFirstIv) }
            deleteSecondIv.setOnClickListener { resetImage(descSecondIv) }
            deleteThirdIv.setOnClickListener { resetImage(descThirdIv) }
            deleteFourthIv.setOnClickListener { resetImage(descFourthIv) }
        }
    }

    private fun pickImageFromGallery(targetImageView: ImageView) {
        val index = when (targetImageView) {
            binding.descFirstIv -> 0
            binding.descSecondIv -> 1
            binding.descThirdIv -> 2
            binding.descFourthIv -> 3
            else -> return  // 올바르지 않은 ImageView가 주어진 경우 함수를 종료합니다.
        }

        // 해당 이미지뷰의 상태가 ADD가 아니면 함수 종료
        if (imageViewsState[index] != ImageViewState.ADD) return

        currentSelectedImageView = targetImageView
        val imageIntent = Intent(Intent.ACTION_PICK)
        imageIntent.type = "image/*"
        imagePickerLauncher.launch(imageIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        imagePickerLauncher.unregister()
    }
    private fun collapseProfileList(afterCollapse: () -> Unit) {
        val collapseAnimator = ValueAnimator.ofInt(originalHeight, 0)
        with(binding) {
            collapseAnimator.addUpdateListener { valueAnimator ->
                profileListCl.layoutParams.height = valueAnimator.animatedValue as Int
                profileListCl.requestLayout()
            }
            collapseAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    profileListTopCl.visibility = View.GONE
                    profileListCl.visibility = View.GONE
                    openProfileListCl.visibility = View.VISIBLE
                    afterCollapse()
                }
            })
            collapseAnimator.duration = 300
            collapseAnimator.start()
        }
    }

    private fun updateImageViewState() {
        val imageViews = listOf(binding.descFirstIv, binding.descSecondIv, binding.descThirdIv, binding.descFourthIv)
        val deleteImageViews = listOf(binding.deleteFirstIv, binding.deleteSecondIv, binding.deleteThirdIv, binding.deleteFourthIv)

        for (i in imageViews.indices) {
            when (imageViewsState[i]) {
                ImageViewState.EMPTY -> {
                    imageViews[i].setImageResource(R.color.thin_gray)
                    deleteImageViews[i].visibility = View.GONE
                }
                ImageViewState.ADD -> {
                    imageViews[i].setImageResource(R.drawable.activity_write_post_add_image_default)
                    deleteImageViews[i].visibility = View.GONE
                }
                ImageViewState.FILLED -> {
                    deleteImageViews[i].visibility = View.VISIBLE
                }
            }
        }
    }

    private fun resetImage(targetImageView: ImageView) {
        val imageViews = listOf(binding.descFirstIv, binding.descSecondIv, binding.descThirdIv, binding.descFourthIv)

        val index = imageViews.indexOf(targetImageView)
        if (index != -1) {
            for (i in index until imageViewsState.size - 1) {
                imageViews[i].setImageDrawable(imageViews[i + 1].drawable)
                imageViewsState[i] = imageViewsState[i + 1]
            }
            // 마지막 이미지 초기화
            imageViews[imageViewsState.size - 1].setImageResource(R.color.thin_gray)  // 또는 초기 이미지로 설정
            imageViewsState[imageViewsState.size - 1] = ImageViewState.EMPTY

            // 모든 이미지뷰의 상태를 확인하고 마지막 채워진 이미지 다음을 ADD 상태로 설정
            for (i in 0 until imageViewsState.size - 1) {
                if (imageViewsState[i] == ImageViewState.FILLED && imageViewsState[i + 1] == ImageViewState.EMPTY) {
                    imageViewsState[i + 1] = ImageViewState.ADD
                    break
                }
            }
            updateImageViewState()
        }
    }
}