package com.gachon.garamgaebi2.views.communityProfile

import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.communityProfile.ManageMemberAllRVAdapter
import com.gachon.garamgaebi2.adapter.communityProfile.ManageMemberApplyRVAdapter
import com.gachon.garamgaebi2.base.BaseActivity
import com.gachon.garamgaebi2.databinding.ActivityManageMemberBinding
import com.gachon.garamgaebi2.views.mainFeed.MainFeedRVItemDecoration

class ManageMemberActivity : BaseActivity<ActivityManageMemberBinding>(ActivityManageMemberBinding::inflate) {
    override fun initView() {
        initToolbar()
        initRecyclerView()
    }
    private fun initToolbar() {
        with(binding.toolbar) {
            logo = null
            navigationIcon = context.getDrawable(R.drawable.ic_back_arrow)
            setNavigationOnClickListener { finish() }
        }
    }
    private fun initRecyclerView() {
        binding.activityManageMemberRegisterApplyRv.apply {
            layoutManager = LinearLayoutManager(this@ManageMemberActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ManageMemberApplyRVAdapter(listOf("1", "2", "3", "4"))
        }
        binding.activityManageMemberAllRv.apply {
            layoutManager = LinearLayoutManager(this@ManageMemberActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ManageMemberAllRVAdapter(listOf("1", "2", "3", "4","5","6","7","8","9","10"))
        }
    }
}