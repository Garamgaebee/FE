package com.gachon.garamgaebi2.adapter.thread

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.adapter.mainFeed.MainFeedRVAdapter
import com.gachon.garamgaebi2.databinding.ItemFragmentThreadAnswerImagesBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentThreadAnswerOneImageBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentThreadAnswerTextBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentThreadMainImagesBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentThreadMainOneImageBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentThreadMainTextBinding

class ThreadRVAdapter (private val dataList : List<String> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MAIN_TEXT = 0
        private const val VIEW_TYPE_MAIN_ONE_IMG = 1
        private const val VIEW_TYPE_MAIN_IMGS = 2
        private const val VIEW_TYPE_ANSWER_TEXT = 3
        private const val VIEW_TYPE_ANSWER_ONE_IMG = 4
        private const val VIEW_TYPE_ANSWER_IMGS = 5
    }

    inner class MainTextViewHolder(private val binding : ItemFragmentThreadMainTextBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }
    inner class MainOneImgViewHolder(private val binding : ItemFragmentThreadMainOneImageBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }
    inner class MainImgsViewHolder(private val binding : ItemFragmentThreadMainImagesBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }
    inner class AnswerTextViewHolder(private val binding : ItemFragmentThreadAnswerTextBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }
    inner class AnswerOneImgViewHolder(private val binding : ItemFragmentThreadAnswerOneImageBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }
    inner class AnswerImgsViewHolder(private val binding : ItemFragmentThreadAnswerImagesBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_MAIN_TEXT -> {
                val binding = ItemFragmentThreadMainTextBinding.inflate(inflater, parent, false)
                MainTextViewHolder(binding)
            }
            VIEW_TYPE_MAIN_ONE_IMG -> {
                val binding = ItemFragmentThreadMainOneImageBinding.inflate(inflater, parent, false)
                MainOneImgViewHolder(binding)
            }
            VIEW_TYPE_MAIN_IMGS -> {
                val binding = ItemFragmentThreadMainImagesBinding.inflate(inflater, parent, false)
                MainImgsViewHolder(binding)
            }
            VIEW_TYPE_ANSWER_TEXT -> {
                val binding = ItemFragmentThreadAnswerTextBinding.inflate(inflater, parent, false)
                AnswerTextViewHolder(binding)
            }
            VIEW_TYPE_ANSWER_ONE_IMG -> {
                val binding = ItemFragmentThreadAnswerOneImageBinding.inflate(inflater, parent, false)
                AnswerOneImgViewHolder(binding)
            }
            VIEW_TYPE_ANSWER_IMGS -> {
                val binding = ItemFragmentThreadAnswerImagesBinding.inflate(inflater, parent, false)
                AnswerImgsViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        when (holder) {
            is MainTextViewHolder -> holder.bind(data)
            is MainOneImgViewHolder -> holder.bind(data)
            is MainImgsViewHolder -> holder.bind(data)
            is AnswerTextViewHolder -> holder.bind(data)
            is AnswerOneImgViewHolder -> holder.bind(data)
            is AnswerImgsViewHolder -> holder.bind(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList[position]
        //서버에서 데이터 받아와서 고치기
        return when (data) {
            "MainTextFeed" -> VIEW_TYPE_MAIN_TEXT
            "MainOneImageFeed" -> VIEW_TYPE_MAIN_ONE_IMG
            "MainImagesFeed" -> VIEW_TYPE_MAIN_IMGS
            "AnswerTextFeed" -> VIEW_TYPE_ANSWER_TEXT
            "AnswerOneImageFeed" -> VIEW_TYPE_ANSWER_ONE_IMG
            "AnswerImagesFeed" -> VIEW_TYPE_ANSWER_IMGS
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }


    override fun getItemCount(): Int = dataList.size


}