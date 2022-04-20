package com.br.leandro.marvel_hero_app.ui.core

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class InfiniteRecyclerViewScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true

    private val startingPageIndex = 0
    private val visibleThreshold = 5

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        when {
            totalItemCount < previousTotalItemCount -> {
                this.currentPage = this.startingPageIndex
                this.previousTotalItemCount = totalItemCount
                if (totalItemCount == 0) {
                    this.loading = true
                }
            }
            loading && totalItemCount > previousTotalItemCount -> {
                loading = false
                previousTotalItemCount = totalItemCount
            }
            !loading && lastVisibleItemPosition + visibleThreshold > totalItemCount -> {
                currentPage++
                onLoadMore(currentPage, totalItemCount, view)
                loading = true
            }
            else -> {
                loading = false
            }
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)
}