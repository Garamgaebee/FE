package com.gachon.garamgaebi2.adapter.communityProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.adapter.thread.ThreadRVAdapter
import com.gachon.garamgaebi2.databinding.*

class CommunityPostRVAdapter(private val dataList : List<String> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MAIN_TEXT = 0
        private const val VIEW_TYPE_MAIN_ONE_IMG = 1
        private const val VIEW_TYPE_MAIN_IMGS = 2
    }

    inner class MainTextViewHolder(private val binding : ItemCommunityProfileTextBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }

    inner class MainOneImgViewHolder(private val binding: ItemCommunityProfileOneImageBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }

    inner class MainImgsViewHolder(private val binding : ItemCommunityProfileImagesBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: String){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MAIN_TEXT -> {
                val binding = ItemCommunityProfileTextBinding.inflate(inflater, parent, false)
                MainTextViewHolder(binding)
            }
            VIEW_TYPE_MAIN_ONE_IMG -> {
                val binding = ItemCommunityProfileOneImageBinding.inflate(inflater, parent, false)
                MainOneImgViewHolder(binding)
            }
            VIEW_TYPE_MAIN_IMGS -> {
                val binding = ItemCommunityProfileImagesBinding.inflate(inflater, parent, false)
                MainImgsViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataList[position]
        when (holder) {
            is MainTextViewHolder -> holder.bind(data)
            is MainOneImgViewHolder -> holder.bind(data)
            is MainImgsViewHolder -> holder.bind(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val data = dataList[position]
        //서버에서 데이터 받아와서 고치기
        return when (data) {
            "MainTextFeed" -> VIEW_TYPE_MAIN_TEXT
            "MainOneImageFeed" -> VIEW_TYPE_MAIN_ONE_IMG
            "MainImagesFeed" -> VIEW_TYPE_MAIN_IMGS
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }
}