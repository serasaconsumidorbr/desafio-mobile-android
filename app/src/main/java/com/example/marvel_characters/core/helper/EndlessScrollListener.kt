package com.example.marvel_characters.core.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EndlessScrollListener(
    private val requestItems: () -> Unit,
) : RecyclerView.OnScrollListener() {
    private var loading = true;
    private var pastVisiblesItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            (recyclerView.layoutManager as? LinearLayoutManager)?.let {
                visibleItemCount = it.childCount
                totalItemCount = it.getItemCount()
                pastVisiblesItems = it.findFirstVisibleItemPosition()

                if (!loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = true
                        requestItems()
                    }
                }
            }
        }
    }

    fun finishLoading() {
        loading = false
    }
}