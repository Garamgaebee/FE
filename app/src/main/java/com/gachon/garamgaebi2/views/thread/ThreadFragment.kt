package com.gachon.garamgaebi2.views.thread

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.adapter.thread.ThreadRVAdapter
import com.gachon.garamgaebi2.base.BaseBindingFragment
import com.gachon.garamgaebi2.databinding.FragmentThreadBinding
import com.gachon.garamgaebi2.views.communityProfile.CommunityProfileMemberMenuBottomDialogFragment

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
            adapter = ThreadRVAdapter(requireContext(), listOf("MainTextFeed" ,"AnswerTextFeed", "AnswerOneImageFeed", "AnswerImagesFeed" ),
            ThreadRVAdapter.PostClickListener {
                Log.d("ThreadKebab","onClick")
                val dialog = MyThreadBottomDialogFragment(){
                    when(it) {
                        0-> {
                            val dialog2 = RemoveThreadBottomDialogFragment(){
                                when(it) {
                                    0 -> {
                                        // 진짜 삭제
                                    }
                                }
                            }
                            dialog2.show(requireActivity().supportFragmentManager, dialog2.tag)

                        }
                        1->{
                            // 공유하기
                        }
                    }
                    }
                dialog.show(requireActivity().supportFragmentManager, dialog.tag)

            })
        }

    }
}