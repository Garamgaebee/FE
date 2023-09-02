package com.gachon.garamgaebi2.views.thread

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.thread.ThreadRVAdapter
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.databinding.FragmentThreadBinding

class ThreadFragment : BaseBindingFragment<FragmentThreadBinding>(R.layout.fragment_thread) {

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
        binding.listRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = ThreadRVAdapter(listOf("MainTextFeed" ,"AnswerTextFeed", "AnswerOneImageFeed", "AnswerImagesFeed" ))
        }
    }
}