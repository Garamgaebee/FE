package com.gachon.garamgaebi2.adapter.communityProfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.databinding.ItemActivityManageMemberAllMembersBinding
import com.gachon.garamgaebi2.databinding.ItemActivityManageMemberApplyMembersBinding
import com.gachon.garamgaebi2.viewModel.CommunityProfileViewModel

class ManageMemberApplyRVAdapter(
    private val items: List<String>,
    private val viewModel: CommunityProfileViewModel
) : RecyclerView.Adapter<ManageMemberApplyRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemActivityManageMemberApplyMembersBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.yes_btn, R.id.no_btn -> {
                        binding.radioDivider.visibility = View.GONE
                        viewModel.onRadioButtonSelected(adapterPosition, true)
                    }
                    else -> {
                        binding.radioDivider.visibility = View.VISIBLE
                        viewModel.onRadioButtonSelected(adapterPosition, false)
                    }
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActivityManageMemberApplyMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount(): Int = items.size
}