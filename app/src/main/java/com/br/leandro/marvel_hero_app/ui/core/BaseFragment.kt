package com.br.leandro.marvel_hero_app.ui.core

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerFragment
import javax.inject.Inject


abstract class BaseFragment<VM : ViewModel, VB : ViewBinding> : DaggerFragment() {

    @Inject
    protected lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    protected lateinit var requestManagerGlide: RequestManager

    protected lateinit var mViewBinding: VB

    protected abstract val mViewModel: VM

    protected var fragmentContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentContext = this.context
        setupView()
        setupViewModel()
    }

    abstract fun getViewBinding(): VB

    abstract fun setupViewModel()

    abstract fun setupView()
}