package com.gachon.garamgaebi2.adapter.mainFeed

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gachon.domain.model.ThreadMainListResult
import com.gachon.garamgaebi2.R
import com.gachon.garamgaebi2.databinding.ItemFragmentMainFeedImagesBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentMainFeedNoImageBinding
import com.gachon.garamgaebi2.databinding.ItemFragmentMainFeedOneImageBinding
import com.gachon.garamgaebi2.views.thread.ThreadActivity

class MainFeedRVAdapter(
    private val activityContext : Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val threadList = mutableListOf<ThreadMainListResult>()
    // Item types
    companion object {
        const val NO_IMAGE = 0
        const val ONE_IMAGE = 1
        const val IMAGES = 2
    }

    inner class NoImageViewHolder(private val noImageBinding: ItemFragmentMainFeedNoImageBinding)
        : RecyclerView.ViewHolder(noImageBinding.root) {
        fun bind(item : ThreadMainListResult) {
            noImageBinding.result = item
            setAuthorProfileImage(noImageBinding.profileIv, item.authorImgUrl)
            setTeamProfileImage(noImageBinding.communityProfileIv, item.teamImgUrl)

        }
    }

    inner class OneImageViewHolder(private val oneImageBinding: ItemFragmentMainFeedOneImageBinding)
        : RecyclerView.ViewHolder(oneImageBinding.root) {
        fun bind(item : ThreadMainListResult) {
            oneImageBinding.result = item
            setAuthorProfileImage(oneImageBinding.profileIv, item.authorImgUrl)
            setTeamProfileImage(oneImageBinding.communityProfileIv, item.teamImgUrl)
        }
    }

    inner class MoreImageViewHolder(private val moreImageBinding: ItemFragmentMainFeedImagesBinding)
        : RecyclerView.ViewHolder(moreImageBinding.root) {
        fun bind(item : ThreadMainListResult) {
            moreImageBinding.result = item
            setAuthorProfileImage(moreImageBinding.profileIv, item.authorImgUrl)
            setTeamProfileImage(moreImageBinding.communityProfileIv, item.teamImgUrl)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            NO_IMAGE -> NoImageViewHolder(ItemFragmentMainFeedNoImageBinding.inflate(inflater, parent, false))
            ONE_IMAGE -> OneImageViewHolder(ItemFragmentMainFeedOneImageBinding.inflate(inflater, parent, false))
            IMAGES -> MoreImageViewHolder(ItemFragmentMainFeedImagesBinding.inflate(inflater, parent, false))
            else -> NoImageViewHolder(ItemFragmentMainFeedNoImageBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = threadList[position]
        when (holder) {
            is NoImageViewHolder -> holder.bind(item)
            is OneImageViewHolder -> holder.bind(item)
            is MoreImageViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException("Unknown view holder")
        }
        holder.itemView.setOnClickListener {
            val intent = Intent( activityContext,ThreadActivity::class.java)
            activityContext.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (threadList[position].imgs.size) {
            0 -> NO_IMAGE
            1 -> ONE_IMAGE
            2 -> IMAGES
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun getItemCount(): Int = threadList.size

    fun setData(data: List<ThreadMainListResult>) {
        threadList.clear()
        threadList.addAll(data)
        notifyDataSetChanged()
    }

    private fun setAuthorProfileImage(view: ImageView, url: String) {
        Glide.with(view)
            .load(url)
            .fallback(
                ColorDrawable(ContextCompat.getColor(activityContext, R.color.black)))
            .into(view)
    }
    private fun setTeamProfileImage(view: ImageView, url: String) {
        when (url) {
            "NONE" -> {
                Log.d("setTeamProfileImage", "NONE")
                Glide.with(view).load(
                    ColorDrawable(ContextCompat.getColor(activityContext, R.color.light_gray)))
                .into(view)
            }
            null -> view.visibility = View.GONE
            else -> {
                Log.d("setTeamProfileImage", "else")
                Glide.with(view).load(url).into(view)
            }
        }
    }
}