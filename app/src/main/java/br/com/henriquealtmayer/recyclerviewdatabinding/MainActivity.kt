package br.com.henriquealtmayer.recyclerviewdatabinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import br.com.henriquealtmayer.recyclerviewdatabinding.approved.ApprovedActivity
import br.com.henriquealtmayer.recyclerviewdatabinding.databinding.ActivityMainBinding
import br.com.henriquealtmayer.recyclerviewdatabinding.itemlist.adapter.ItemAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeDataBinding()
        initializeAdapter()
        initializeRecyclerView()
        initializeListObserver()
        initializeShowRequestToastObserver()
        initializeShowDeniedDialogObserver()
        initializeNavigateToApprovedScreen()
    }

    private fun initializeDataBinding() {
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).run {
            vm = mainViewModel
            lifecycleOwner = this@MainActivity
        }
    }

    private fun initializeAdapter() {
        adapter = ItemAdapter(this, mainViewModel)
    }

    private fun initializeRecyclerView() {
        rv_main.adapter = this@MainActivity.adapter
    }

    private fun initializeListObserver() {
        mainViewModel.list.observe(this, {
            adapter.submitList(it)
        })
    }

    private fun initializeShowRequestToastObserver() {
        mainViewModel.showRequestedItemToast.observe(this, { mustShow ->
            if (mustShow) {
                Toast.makeText(this, R.string.item_requested_message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initializeShowDeniedDialogObserver() {
        mainViewModel.showDeniedItemDialog.observe(this, { mustShow ->
            if (mustShow) {
                AlertDialog
                    .Builder(this)
                    .setTitle(R.string.item_denied_title)
                    .setMessage(R.string.item_denied_message)
                    .show()
            }
        })
    }

    private fun initializeNavigateToApprovedScreen() {
        mainViewModel.navigateToApprovedScreen.observe(this, { mustNavigate ->
            if (mustNavigate) {
                startActivity(
                    Intent(this, ApprovedActivity::class.java)
                )
            }
        })
    }

}