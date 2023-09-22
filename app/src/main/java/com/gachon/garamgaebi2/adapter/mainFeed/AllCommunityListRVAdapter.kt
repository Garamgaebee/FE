package com.gachon.garamgaebi2.adapter.mainFeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.databinding.ItemActivityAllCommunityListBinding

class AllCommunityListRVAdapter(
    private val items: List<String>,
    private val itemClicked: (String) -> Unit
) : RecyclerView.Adapter<AllCommunityListRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemActivityAllCommunityListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, position: Int) {
            binding.root.setOnClickListener {
                itemClicked(item)
            }
            if(position == 0) {
                binding.root.background = binding.root.context.getDrawable(com.gachon.garamgaebi2.R.drawable.community_profile_input_container_bottom_border)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActivityAllCommunityListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
    override fun getItemCount(): Int = items.size

}