package br.com.henriquealtmayer.recyclerviewdatabinding.bindingadapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.henriquealtmayer.recyclerviewdatabinding.R
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.ItemStatus

@BindingAdapter("app:item_background_color")
fun View.setItemBackgroundColor(
    status: ItemStatus?
) {
    if (status == null) return

    setBackgroundColor(
        ContextCompat.getColor(context, status.getColorId())
    )
}

fun ItemStatus.getColorId() = when (this) {
    ItemStatus.REQUESTED -> R.color.requested
    ItemStatus.DENIED -> R.color.denied
    else -> R.color.approved
}