package com.gachon.garamgaebi2.adapter.mainFeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gachon.garamgaebi2.databinding.ItemFragmentMainFeedMoreImageBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentMainFeedNoImageBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentMainFeedOneImageBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentMainFeedTwoImageBinding

class MainFeedRVAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Item types
    companion object {
        const val NO_IMAGE = 0
        const val ONE_IMAGE = 1
        const val SECOND_IMAGE = 2
        const val MORE_IMAGE = 3
    }

    inner class NoImageViewHolder(private val noImageBinding: ItemFragmentMainFeedNoImageBinding) : RecyclerView.ViewHolder(noImageBinding.root) {
        fun bind(item : String) {
        }
    }

    inner class OneImageViewHolder(private val oneImageBinding: ItemFragmentMainFeedOneImageBinding) : RecyclerView.ViewHolder(oneImageBinding.root) {
        fun bind(item : String) {
        }
    }

    inner class SecondImageViewHolder(private val twoImageBinding: ItemFragmentMainFeedTwoImageBinding) : RecyclerView.ViewHolder(twoImageBinding.root) {
        fun bind(item : String) {
        }
    }

    inner class MoreImageViewHolder(private val moreImageBinding: ItemFragmentMainFeedMoreImageBinding) : RecyclerView.ViewHolder(moreImageBinding.root) {
        fun bind(item : String) {
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            NO_IMAGE -> NoImageViewHolder(ItemFragmentMainFeedNoImageBinding.inflate(inflater, parent, false))
            ONE_IMAGE -> OneImageViewHolder(ItemFragmentMainFeedOneImageBinding.inflate(inflater, parent, false))
            SECOND_IMAGE -> SecondImageViewHolder(ItemFragmentMainFeedTwoImageBinding.inflate(inflater, parent, false))
            MORE_IMAGE -> MoreImageViewHolder(ItemFragmentMainFeedMoreImageBinding.inflate(inflater, parent, false))
            else -> NoImageViewHolder(ItemFragmentMainFeedNoImageBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is NoImageViewHolder -> holder.bind(item)
            is OneImageViewHolder -> holder.bind(item)
            is SecondImageViewHolder -> holder.bind(item)
            is MoreImageViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException("Unknown view holder")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            "no_image" -> NO_IMAGE
            "one_image" -> ONE_IMAGE
            "second_image" -> SECOND_IMAGE
            "more_image" -> MORE_IMAGE
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun getItemCount(): Int = items.size
}