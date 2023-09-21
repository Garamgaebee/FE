package com.gachon.garamgaebi2.views.communityProfile

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.communityProfile.CommunityLeaderRVAdapter
import com.gachon.garamgaebi2.adapter.communityProfile.CommunityPostRVAdapter
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityCommunityProfileBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel

class CommunityProfileActivity : BaseActivity<ActivityCommunityProfileBinding>(ActivityCommunityProfileBinding::inflate) {
    private val viewModel by viewModels<CommunityProfileViewModel>()

    override fun initView() {

        //서버에서 상태값 받아와서 UI visibility 표시하기
        //모임장 화면
        binding.settingBtn.visibility= View.VISIBLE
        binding.joinSettingBtn.visibility = View.INVISIBLE
        binding.joinBtn.visibility = View.INVISIBLE
        /*//회원 화면
        binding.settingBtn.visibility = View.INVISIBLE
        binding.joinSettingBtn.visibility = View.VISIBLE
        binding.joinBtn.visibility = View.INVISIBLE
        //비회원 화면
        binding.settingBtn.visibility = View.INVISIBLE
        binding.notice.visibility = View.GONE
        binding.link.visibility = View.GONE
        binding.joinSettingBtn.visibility = View.INVISIBLE
        binding.joinBtn.visibility = View.VISIBLE*/

        initRecyclerView()
        observe()
        initListener()
        initToolbar()
    }

    private fun initListener(){
        with(binding){
            settingBtn.setOnClickListener {
                val dialog = CommunityProfileManagerMenuBottomDialogFragment(){
                    when(it){
                        0 -> {
                            val intent = Intent(this@CommunityProfileActivity, CommunityProfileEditActivity::class.java)
                            startActivity(intent)
                            Log.d("goToCommunityProfileEditBtnClicked", "goToCommunityProfileEditBtnClicked")

                        }
                        1 ->{

                        }
                        2 -> {

                        }
                        3 ->{

                        }
                        else ->{

                        }
                    }
                }
                dialog.show(supportFragmentManager, dialog.tag)
            }
            joinSettingBtn.setOnClickListener {
                val dialog = CommunityProfileMemberMenuBottomDialogFragment(){
                    when(it) {
                        0 -> {

                            val dialog2 = CommunityProfileMemberWithdrawalBottomDialogFragment() {
                                when(it){
                                    0->{

                                    }
                                    1->{

                                    }
                                }
                            }
                            dialog2.show(supportFragmentManager, dialog2.tag)


//                            val intent = Intent(
//                                this@CommunityProfileActivity,
//                                WritePostActivity::class.java
//                            )
//                            startActivity(intent)
                        }

                        1 -> {

                        }

                        2 -> {

                        }
                    }
                }
                dialog.show(supportFragmentManager, dialog.tag)
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
    private fun observe(){
        with(viewModel){
            isUserType.observe(this@CommunityProfileActivity){
                when(it){
                    // 모임장
                    0 ->{
                        binding.settingBtn.visibility= View.VISIBLE
                        binding.joinSettingBtn.visibility = View.INVISIBLE
                        binding.joinBtn.visibility = View.INVISIBLE
                    }
                    // 회원
                    1 -> {
                        binding.settingBtn.visibility = View.INVISIBLE
                        binding.joinSettingBtn.visibility = View.VISIBLE
                        binding.joinBtn.visibility = View.INVISIBLE
                    }
                    // 비회원
                    2 ->{
                        binding.settingBtn.visibility = View.INVISIBLE
                        binding.notice.visibility = View.GONE
                        binding.link.visibility = View.GONE
                        binding.joinSettingBtn.visibility = View.INVISIBLE
                        binding.joinBtn.visibility = View.VISIBLE
                    }
                }
            }
            goToCommunityProfileEditBtnClicked.observe(this@CommunityProfileActivity){
                if(it){
                    // 프로필 편집 화면으로 이동
//                    val intent = Intent(this@CommunityProfileActivity, CommunityProfileEditActivity::class.java)
//                    startActivity(intent)
                    Log.d("goToCommunityProfileEditBtnClicked", "goToCommunityProfileEditBtnClicked")
                }
            }
        }
    }

    private fun initRecyclerView() {

        binding.communityLeaderRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CommunityLeaderRVAdapter(listOf("000", "000"))

        }

        binding.communityPostRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CommunityPostRVAdapter(listOf("MainTextFeed" ,"MainOneImageFeed", "MainImagesFeed" ))
        }
    }
}