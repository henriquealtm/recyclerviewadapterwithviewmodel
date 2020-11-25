package br.com.henriquealtmayer.recyclerviewdatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.Item
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.ItemStatus

class MainViewModel : ViewModel() {

    val list = MutableLiveData(
        listOf(
            Item("One MacBook Pro", ItemStatus.DENIED),
            Item("One desk", ItemStatus.DENIED),
            Item("Three HP notebooks", ItemStatus.REQUESTED),
            Item("Two DELL desktop PCs", ItemStatus.APPROVED),
            Item("Two Razer headsets", ItemStatus.REQUESTED),
            Item("Two DTC chairs", ItemStatus.APPROVED)
        )
    )

    private val mShowRequestedItemToast = MutableLiveData<Boolean>()
    val showRequestedItemToast: LiveData<Boolean> = mShowRequestedItemToast

    private val mShowDeniedItemDialog = MutableLiveData<Boolean>()
    val showDeniedItemDialog: LiveData<Boolean> = mShowDeniedItemDialog

    private val mNavigateToApprovedScreen = MutableLiveData<Boolean>()
    val navigateToApprovedScreen: LiveData<Boolean> = mNavigateToApprovedScreen

    fun onItemClick(item: Item) {
        when (item.status) {
            ItemStatus.REQUESTED -> mShowRequestedItemToast
            ItemStatus.DENIED -> mShowDeniedItemDialog
            ItemStatus.APPROVED -> mNavigateToApprovedScreen
        }.value = true
    }

}