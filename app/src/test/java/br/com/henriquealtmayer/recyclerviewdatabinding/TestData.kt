package br.com.henriquealtmayer.recyclerviewdatabinding

import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.Item
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.ItemStatus

object TestData {

    val requestedItem = Item("Requested", ItemStatus.REQUESTED)
    val deniedItem = Item("Denied", ItemStatus.DENIED)
    val approvedItem = Item("Approved", ItemStatus.APPROVED)

}