package br.com.henriquealtmayer.recyclerviewdatabinding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import br.com.henriquealtmayer.recyclerviewdatabinding.TestData.approvedItem
import br.com.henriquealtmayer.recyclerviewdatabinding.TestData.deniedItem
import br.com.henriquealtmayer.recyclerviewdatabinding.TestData.requestedItem
import br.com.henriquealtmayer.recyclerviewdatabinding.extensions.handleOpt
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.Item
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.model.ItemStatus
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel


    @Before
    @Throws(Exception::class)
    fun prepare() {
        mainViewModel = MainViewModel()
    }

    // Show Requested Toast - Section
    @Test
    fun `verify if showRequestedItemToast is null when creating viewmodel`() {
        assertNull(mainViewModel.showRequestedItemToast.value)
    }

    @Test
    fun `verify if showRequestedItemToast is false when calling onItemClick passing a item with its status different from REQUESTED`() {
        val nonRequestedRandomItem = listOf(deniedItem, approvedItem).random()

        onItemClickAssert(
            nonRequestedRandomItem,
            mainViewModel.showRequestedItemToast
        ) {
            assertFalse(mainViewModel.showRequestedItemToast.value.handleOpt())
        }
    }

    @Test
    fun `verify if showRequestedItemToast is true when calling onItemClick passing a item with its status equals to REQUESTED`() {
        onItemClickAssert(
            requestedItem,
            mainViewModel.showRequestedItemToast
        ) {
            assertTrue(mainViewModel.showRequestedItemToast.value.handleOpt())
        }
    }

    // Show Denied Dialog - Section
    @Test
    fun `verify if showDeniedItemDialog is null when creating viewmodel`() {
        assertNull(mainViewModel.showDeniedItemDialog.value)
    }

    @Test
    fun `verify if showDeniedItemDialog is false when calling onItemClick passing a item with its status different from DENIED`() {
        val nonDeniedRandomItem = listOf(requestedItem, approvedItem).random()

        onItemClickAssert(
            nonDeniedRandomItem,
            mainViewModel.showDeniedItemDialog
        ) {
            assertFalse(mainViewModel.showDeniedItemDialog.value.handleOpt())
        }
    }

    @Test
    fun `verify if showDeniedItemDialog is true when calling onItemClick passing a item with its status equals to DENIED`() {
        onItemClickAssert(
            deniedItem,
            mainViewModel.showDeniedItemDialog
        ) {
            assertTrue(mainViewModel.showDeniedItemDialog.value.handleOpt())
        }
    }

    // Show Approved Navigation - Section
    @Test
    fun `verify if navigateToApprovedScreen is null when creating viewmodel`() {
        assertNull(mainViewModel.navigateToApprovedScreen.value)
    }

    @Test
    fun `verify if navigateToApprovedScreen is false when calling onItemClick passing a item with its status different from APPROVED`() {
        val nonDeniedRandomItem = listOf(requestedItem, deniedItem).random()

        onItemClickAssert(
            nonDeniedRandomItem,
            mainViewModel.navigateToApprovedScreen
        ) {
            assertFalse(mainViewModel.navigateToApprovedScreen.value.handleOpt())
        }
    }

    @Test
    fun `verify if navigateToApprovedScreen is false when calling onItemClick passing a item with its status is equals to APPROVED`() {
        onItemClickAssert(
            approvedItem,
            mainViewModel.navigateToApprovedScreen
        ) {
            assertTrue(mainViewModel.navigateToApprovedScreen.value.handleOpt())
        }
    }

    private fun <T> onItemClickAssert(
        item: Item,
        state: LiveData<T>,
        assertBlock: () -> Unit
    ) {
        val observer = Observer<T> {}

        state.observeForever(observer)

        mainViewModel.onItemClick(item)

        assertBlock.invoke()

        state.removeObserver(observer)
    }

}