package com.gachon.garamgaebi2.views.mainFeed

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.mainFeed.MainFeedRVAdapter
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.databinding.FragmentMainFeedLatestBinding

class MainFeedLatestFragment : BaseBindingFragment<FragmentMainFeedLatestBinding>(R.layout.fragment_main_feed_latest) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initRecyclerView()
    }
    override fun initView() {
        super.initView()

    }

    override fun initListener() {
        super.initListener()
    }
    private fun initRecyclerView() {
        binding.fragmentMainFeedLatestRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = MainFeedRVAdapter(listOf("no_image", "one_image", "second_image", "more_image"))
            addItemDecoration(MainFeedRVItemDecoration(
                0.5 * context.resources.displayMetrics.density,
                resources.getColor(R.color.light_gray, null)))
        }
    }
}