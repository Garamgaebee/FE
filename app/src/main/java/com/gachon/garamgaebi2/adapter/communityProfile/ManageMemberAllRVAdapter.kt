package com.gachon.garamgaebi2.adapter.communityProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.databinding.ItemActivityManageMemberAllMembersBinding

class ManageMemberAllRVAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<ManageMemberAllRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemActivityManageMemberAllMembersBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActivityManageMemberAllMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount(): Int = items.size
}