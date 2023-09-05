package com.gachon.garamgaebi2.adapter.communityProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.databinding.ItemCommunityProfileLeaderBinding

class CommunityLeaderRVAdapter(private val dataList : List<String>) : RecyclerView.Adapter<CommunityLeaderRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemCommunityProfileLeaderBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data : String){

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommunityProfileLeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}