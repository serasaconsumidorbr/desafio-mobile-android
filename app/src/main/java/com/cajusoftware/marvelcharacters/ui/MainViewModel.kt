package com.cajusoftware.marvelcharacters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cajusoftware.marvelcharacters.data.network.ConnectivityStatus
import com.cajusoftware.marvelcharacters.utils.NetworkUtils.connectionErrorHandle

open class MainViewModel : ViewModel() {

    private val _isNoConnection: MutableLiveData<ConnectivityStatus> = MutableLiveData()
    val isNoConnection: LiveData<ConnectivityStatus>
        get() = _isNoConnection


    init {
        connectionErrorHandle = { isConnectionError ->
            _isNoConnection.postValue(isConnectionError)
        }
    }
}