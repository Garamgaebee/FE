package com.gachon.garamgaebi2.adapter.write

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.databinding.ItemProfileSelectBinding

class ProfileSelectRVAdapter(private val dataList : List<String>) : RecyclerView.Adapter<ProfileSelectRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemProfileSelectBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data : String){
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProfileSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}