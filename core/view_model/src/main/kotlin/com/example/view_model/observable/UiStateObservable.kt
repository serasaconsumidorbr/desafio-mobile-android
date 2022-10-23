package com.example.view_model.observable

import com.example.view_model.observable.base.ObservableFlow

class UiStateObservable<STATE>(firstState: STATE) : ObservableFlow<STATE>(firstState)