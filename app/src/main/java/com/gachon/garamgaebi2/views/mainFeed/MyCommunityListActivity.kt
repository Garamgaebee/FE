package com.gachon.garamgaebi2.views.mainFeed

import android.content.Intent
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.communityProfile.ManageMemberAllRVAdapter
import com.gachon.garamgaebi2.adapter.communityProfile.ManageMemberApplyRVAdapter
import com.gachon.garamgaebi2.adapter.mainFeed.MyCommunityListRVAdapter
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityManageMemberBinding
import com.gachon.garamgaebi2.databinding.ActivityMyCommunityListBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel
import com.gachon.garamgaebi2.views.communityProfile.CommunityProfileActivity
import com.gachon.garamgaebi2.views.mainFeed.MainFeedRVItemDecoration

class MyCommunityListActivity : BaseActivity<ActivityMyCommunityListBinding>(ActivityMyCommunityListBinding::inflate) {
    private val viewModel by viewModels<CommunityProfileViewModel>()
    override fun initView() {
        initToolbar()
        binding.lifecycleOwner = this
        initRecyclerView()
        initListener()
    }
    private fun initToolbar() {
        with(binding.toolbar) {
            logo = null
            navigationIcon = context.getDrawable(R.drawable.ic_back_arrow)
            setNavigationOnClickListener { finish() }
        }
    }
    private fun initRecyclerView() {
        binding.activityMyCommunityListRv.apply {
            layoutManager = LinearLayoutManager(this@MyCommunityListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MyCommunityListRVAdapter(listOf("1", "2", "3", "4","5","6","7","8","9","10")) { string ->
                val intent = Intent(this@MyCommunityListActivity, CommunityProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun initListener() {
//        binding.backBtn.setOnClickListener {
//            finish()
//        }
    }
}