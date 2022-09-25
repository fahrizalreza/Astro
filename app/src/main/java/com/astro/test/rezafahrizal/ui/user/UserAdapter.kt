package com.astro.test.rezafahrizal.ui.user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.astro.test.rezafahrizal.R
import com.astro.test.rezafahrizal.base.BaseState
import com.astro.test.rezafahrizal.databinding.ItemUserBind
import com.astro.test.rezafahrizal.model.UserLocal

class UserAdapter(val state: BaseState, val context: Context) :
    ListAdapter<UserLocal, UserAdapter.SourceViewHolder>(Companion) {

    private var filteredList: List<UserLocal?>? = null

    class SourceViewHolder(val binding: ItemUserBind) : RecyclerView.ViewHolder(binding.root)

    // attach layout to item view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBind.inflate(layoutInflater)
        binding.adapter = this
        binding.state = state

        return SourceViewHolder(binding)
    }

    // attach item to adapter
    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.userData = item
        if (item.favourite == 1) {
            holder.binding.ivUserListLayoutFave.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.binding.ivUserListLayoutFave.setImageResource(R.drawable.ic_favorite_border)
        }
        holder.binding.executePendingBindings()
    }

    // set list so can be filtered
    fun setFilterList(list: List<UserLocal?>?) {
        filteredList = list
    }

    // constant value
    companion object : DiffUtil.ItemCallback<UserLocal>() {

        override fun areContentsTheSame(oldItem: UserLocal, newItem: UserLocal): Boolean =
            oldItem.id == newItem.id

        override fun areItemsTheSame(oldItem: UserLocal, newItem: UserLocal): Boolean =
            oldItem === newItem

        const val LOG_PROCESS_ADAPTER: String = "UserListAdapter"
    }
}