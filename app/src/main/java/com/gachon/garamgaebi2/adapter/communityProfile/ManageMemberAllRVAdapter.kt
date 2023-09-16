package com.gachon.garamgaebi2.adapter.communityProfile

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.databinding.ItemActivityManageMemberAllMembersBinding
import com.gachon.garamgaebi2.views.communityProfile.CommunityProfileEditActivity
import com.gachon.garamgaebi2.views.communityProfile.CommunityProfileManagerMenuBottomDialogFragment
import com.gachon.garamgaebi2.views.communityProfile.ManageMemberBottomDialogFragment

class ManageMemberAllRVAdapter(
    private val items: List<String>,
    private val onMenuClicked: (ManageMemberBottomDialogFragment) -> Unit
) : RecyclerView.Adapter<ManageMemberAllRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemActivityManageMemberAllMembersBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(binding) {
                hamburgerMenuIv.setOnClickListener {
                    val dialog = ManageMemberBottomDialogFragment(){
                        when(it){
                            0 -> {
                                hamburgerMenuIv.visibility = View.GONE
                                manageMemberExportTv.visibility = View.VISIBLE
                            }
                            1 -> {
                                hamburgerMenuIv.visibility = View.GONE
                                manageMemberAddLeaderTv.visibility = View.VISIBLE
                            }
                            else ->{
                            }
                        }
                    }
                    onMenuClicked(dialog)
                }
                onItemCancelClick(manageMemberExportTv, hamburgerMenuIv)
                onItemCancelClick(manageMemberAddLeaderTv, hamburgerMenuIv)
            }
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

    private fun onItemCancelClick(view : TextView, hamburger: ImageView) {
        view.setOnClickListener {
            view.visibility = View.GONE
            hamburger.visibility = View.VISIBLE
        }
    }
}