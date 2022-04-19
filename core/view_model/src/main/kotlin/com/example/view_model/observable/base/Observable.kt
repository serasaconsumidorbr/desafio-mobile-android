package com.example.view_model.observable.base

interface Observable<T> {
    fun update(updateBlock: (current: T) -> T)
}