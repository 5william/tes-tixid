package com.virakumaro.testixid.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.virakumaro.testixid.adapter.UsersAdapter
import com.virakumaro.testixid.base.BaseVBActivity
import com.virakumaro.testixid.data.Resource
import com.virakumaro.testixid.data.repository.UserRepository
import com.virakumaro.testixid.databinding.ActivityMainBinding
import com.virakumaro.testixid.model.User
import com.virakumaro.testixid.util.PaginationListener
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseVBActivity<ActivityMainBinding>() {
    private val adapter by lazy { UsersAdapter() }
    private var isLastPage = false
    private var isLoading = false
    private var lastId = 0L
    private val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData(savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = adapter
        var lmanager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = lmanager
        binding.recyclerView.addOnScrollListener(object: PaginationListener(lmanager){
            override fun loadMoreItems() {
                viewModel.setLastId(lastId)
            }

            override val isLastPage: Boolean
                get() = this@MainActivity.isLastPage
            override val isLoading: Boolean
                get() = this@MainActivity.isLoading

        })
        binding.swipeRefresh.setOnRefreshListener {
            resetPage()
        }
        subscribeData()
    }

    private fun subscribeData() {
        viewModel.getUsers().observe(this) {
            when (it) {
                is Resource.Success -> {
                    setLoading(false)
                    it.data?.let {
                        lastId = it.last().id
                    }
                    adapter.addItems(it.data?: arrayListOf())
                }
                is Resource.Loading -> {
                    setLoading(true)
                    if (lastId == 0L) {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    setLoading(false)
                    if (lastId == 0L) {
                        binding.llError.visibility = View.VISIBLE
                        binding.textError.setText(it.message)

                    } else {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        if (!isLoading) {
            binding.swipeRefresh.isRefreshing = false
            binding.progressBar.visibility = View.GONE
            binding.swipeRefresh.visibility = View.VISIBLE
        }
    }

    private fun resetPage() {
        adapter.clearData()
        lastId = 0
        viewModel.setLastId(lastId)
        PaginationListener.PAGE = 0
    }
}