package br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model

data class Item(
    val name: String,
    val status: ItemStatus
)

enum class ItemStatus {
    REQUESTED,
    APPROVED,
    DENIED
}
