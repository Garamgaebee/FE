package com.gachon.garamgaebi2.views.communityProfile

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.adapter.communityProfile.CommunityLeaderRVAdapter
import com.gachon.garamgaebi2.adapter.communityProfile.CommunityPostRVAdapter
import com.gachon.garamgaebi2.adapter.thread.ThreadRVAdapter
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