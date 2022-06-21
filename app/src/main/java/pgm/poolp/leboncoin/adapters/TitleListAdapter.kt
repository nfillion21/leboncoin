package pgm.poolp.leboncoin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pgm.poolp.leboncoin.data.Title
import pgm.poolp.leboncoin.databinding.ListItemTitleBinding

class TitleListAdapter : ListAdapter<Title, RecyclerView.ViewHolder>(TitleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChampionViewHolder(
            ListItemTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val champion = getItem(position)
        (holder as ChampionViewHolder).bind(champion)
    }
    class ChampionViewHolder(
        private val binding: ListItemTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.title?.let { title ->
                    //navigateToChampion(champion, it)
                }
            }
        }

        fun bind(item: Title) {
            binding.apply {
                title = item
                executePendingBindings()
            }
        }
    }
}

private class TitleDiffCallback : DiffUtil.ItemCallback<Title>() {

    override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean {
        return oldItem == newItem
    }
}
