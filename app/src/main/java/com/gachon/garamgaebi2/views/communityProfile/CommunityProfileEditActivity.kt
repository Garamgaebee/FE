package com.gachon.garamgaebi2.views.communityProfile

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import coil.load
import com.gachon.garamgaebi2.BR
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityCommunityProfileEditBinding
import com.gachon.garamgaebi2.util.KeyboardVisibilityUtils
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel
import com.gachon.garamgaebi2.viewModel.RegisterViewModel

class CommunityProfileEditActivity : BaseActivity<ActivityCommunityProfileEditBinding>(ActivityCommunityProfileEditBinding::inflate){
    private val viewModel by viewModels<CommunityProfileViewModel>()

    private val permissionList = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        result.forEach {
            if(!it.value) {
                Toast.makeText(applicationContext, "권한 동의 필요!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        Log.d("uri_check",uri.toString())
        viewModel.imageUri.value = uri
        viewModel.isLoadImage.value = true
        binding.profileIv.load(uri)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        keyboardVisibilityUtils = KeyboardVisibilityUtils(this.window,
            onShowKeyboard = { keyboardHeight ->
            },
            onHideKeyboard = {
                binding.nameInput.root.visibility = View.VISIBLE
                binding.descriptionInput.root.visibility = View.VISIBLE
                binding.referenceLinkInput.root.visibility = View.VISIBLE
            }
        )
    }

    override fun initView() {
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.root.isFocusableInTouchMode = true
        binding.root.isClickable = true
        binding.root.setOnTouchListener { view, motionEvent ->
            hideKeyboard(view)
            false
        }

        binding.nameInput.root.setBackgroundResource(R.drawable.community_profile_input_container_bottom_border)
        binding.descriptionInput.root.setBackgroundResource(R.drawable.community_profile_input_container_bottom_border)


        observe()
        initListener()

    }

    private fun observe(){
        with(viewModel){
            nameInputClicked.observe(this@CommunityProfileEditActivity) {
                if (it) {
                    binding.nameInput.input.requestFocus()
                    binding.descriptionInput.root.visibility = View.GONE
                    binding.referenceLinkInput.root.visibility = View.GONE
                    showKeyboard(binding.root)
                }
            }

            descriptionInputClicked.observe(this@CommunityProfileEditActivity) {
                if (it) {
                    binding.descriptionInput.input.requestFocus()
                    binding.nameInput.root.visibility = View.GONE
                    binding.referenceLinkInput.root.visibility = View.GONE
                    showKeyboard(binding.root)
                }
            }

            linkInputClicked.observe(this@CommunityProfileEditActivity) {
                if (it) {
                    binding.referenceLinkInput.input.requestFocus()
                    binding.descriptionInput.root.visibility = View.GONE
                    binding.nameInput.root.visibility = View.GONE
                    showKeyboard(binding.root)
                }
            }

            imageUri.observe(this@CommunityProfileEditActivity){
                Log.d("uri_check",it.toString())
                if(it != null && isLoadImage.value == true){
                    binding.profileIv.load(it)
                }
            }
        }
    }

    private fun initListener(){
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.checkIv.setOnClickListener {
            // 편집 완료
            finish()
        }
        binding.profileIv.setOnClickListener {
            readImage.launch("image/*")
        }
        val nameLayout = binding.nameInput
        nameLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                nameLayout.border2.visibility = View.INVISIBLE

            }else{
                nameLayout.border2.visibility = View.VISIBLE
                viewModel.onClickNameInput()
            }
        }

        val descriptionLayout = binding.descriptionInput
        descriptionLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                descriptionLayout.border2.visibility = View.INVISIBLE

            }else{
                descriptionLayout.border2.visibility = View.VISIBLE
                viewModel.onClickDescriptionInput()
            }
        }

        val linkLayout = binding.referenceLinkInput
        linkLayout.input.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(!hasFocus){
                linkLayout.border2.visibility = View.INVISIBLE
            }else{
                linkLayout.border2.visibility = View.VISIBLE
                viewModel.onClickLinkInput()
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view, 0)
    }

}