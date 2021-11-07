package ru.dillab.chucknorrisjokes.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.dillab.chucknorrisjokes.databinding.ListItemBinding
import ru.dillab.chucknorrisjokes.network.JokesProperty

class JokesRecycleViewAdapter :
    ListAdapter<JokesProperty, JokesRecycleViewAdapter.JokesPropertyViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JokesPropertyViewHolder {
        return JokesPropertyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: JokesPropertyViewHolder, position: Int) {
        val jokesProperty = getItem(position)
        holder.bind(jokesProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<JokesProperty>() {
        override fun areItemsTheSame(oldItem: JokesProperty, newItem: JokesProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: JokesProperty, newItem: JokesProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class JokesPropertyViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(jokesProperty: JokesProperty) {
            binding.jokesProperty = jokesProperty
            binding.executePendingBindings()
        }
    }
}