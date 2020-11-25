package br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.henriquealtmayer.recyclerviewdatabinding.MainViewModel
import br.com.henriquealtmayer.recyclerviewdatabinding.R
import br.com.henriquealtmayer.recyclerviewdatabinding.databinding.ItemBinding
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.Item

class ItemAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val mainViewModel: MainViewModel
) : ListAdapter<Item, ItemAdapter.ViewHolder>(
    ItemListDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        currentList[pos].let { dataItem ->
            holder.binding.run {
                vm = mainViewModel
                item = dataItem
                lifecycleOwner = this@ItemAdapter.lifecycleOwner
            }
        }
    }

    inner class ViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

class ItemListDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(
        oldItem: Item,
        newItem: Item
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: Item,
        newItem: Item
    ) = oldItem == newItem

}