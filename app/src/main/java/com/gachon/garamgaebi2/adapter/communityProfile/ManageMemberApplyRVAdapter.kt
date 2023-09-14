package com.gachon.garamgaebi2.adapter.communityProfile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.databinding.ItemActivityManageMemberApplyMembersBinding

class ManageMemberApplyRVAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemActivityManageMemberApplyMembersBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemActivityManageMemberApplyMembersBinding.inflate(inflater, parent, false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
    override fun getItemCount(): Int = items.size
}