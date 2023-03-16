package com.cajusoftware.marvelcharacters.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cajusoftware.marvelcharacters.data.network.ConnectivityStatus
import com.cajusoftware.marvelcharacters.utils.NetworkUtils.connectionErrorHandle
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private val viewModel: MainViewModel = MainViewModel()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun mainViewModel_isNoConnectivityStatus_shouldReturnsOnLine() {
        connectionErrorHandle?.invoke(ConnectivityStatus.ONLINE)
        val currentConnectivityStatus = viewModel.isNoConnection.value
        Assert.assertEquals(currentConnectivityStatus, ConnectivityStatus.ONLINE)
    }

}