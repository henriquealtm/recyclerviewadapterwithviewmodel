package br.com.henriquealtmayer.recyclerviewdatabinding

import br.com.henriquealtmayer.recyclerviewdatabinding.TestData.approvedItem
import br.com.henriquealtmayer.recyclerviewdatabinding.TestData.deniedItem
import br.com.henriquealtmayer.recyclerviewdatabinding.TestData.requestedItem
import br.com.henriquealtmayer.recyclerviewdatabinding.bindingadapter.getColorId
import junit.framework.Assert.assertEquals
import org.junit.Test

class ViewTest {

    @Test
    fun `Verify if the if the getColorId() for a item with status equals to REQUEST is equals to R_color_requested`() {
        assertEquals(R.color.requested, requestedItem.status.getColorId())
    }

    @Test
    fun `Verify if the if the getColorId() for a item with status equals to DENIED is equals to R_color_denied`() {
        assertEquals(R.color.denied, deniedItem.status.getColorId())
    }

    @Test
    fun `Verify if the if the getColorId() for a item with status equals to APPROVED is equals to R_color_approved`() {
        assertEquals(R.color.approved, approvedItem.status.getColorId())
    }

}