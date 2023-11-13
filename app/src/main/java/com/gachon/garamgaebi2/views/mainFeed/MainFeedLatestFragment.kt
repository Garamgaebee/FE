package com.gachon.garamgaebi2.views.mainFeed

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.mainFeed.MainFeedRVAdapter
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.databinding.FragmentMainFeedLatestBinding
import com.gachon.garamgaebi2.viewModel.MainFeedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFeedLatestFragment : BaseBindingFragment<FragmentMainFeedLatestBinding>(R.layout.fragment_main_feed_latest) {
    private val viewModel by viewModels<MainFeedViewModel>()
    companion object {
        private const val ORDER_LATEST = 0
    }
    override fun initView() {
        super.initView()
        viewModel.getThreadMainList(ORDER_LATEST)
        initRecyclerView()
        observe()
    }

    override fun initListener() {
        super.initListener()
    }
    private fun initRecyclerView() {
        binding.fragmentMainFeedLatestRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = MainFeedRVAdapter(requireContext())
            addItemDecoration(MainFeedRVItemDecoration(
                0.5 * context.resources.displayMetrics.density,
                resources.getColor(R.color.light_gray, null)))
        }
    }
    private fun observe() {
        viewModel.threadMainListLiveData.observe(viewLifecycleOwner) {
            (binding.fragmentMainFeedLatestRv.adapter as MainFeedRVAdapter).setData(it)
        }
    }
}