package com.example.marvel_characters.core.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EndlessScrollListener(
    private val requestItems: () -> Unit,
) : RecyclerView.OnScrollListener() {
    private var loading = true;

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            (recyclerView.layoutManager as? LinearLayoutManager)?.let {

                if (!loading) {
                    if (!recyclerView.canScrollVertically(1))
                        loading = true
                    requestItems()
                }
            }
        }
    }

    fun finishLoading() {
        loading = false
    }
}