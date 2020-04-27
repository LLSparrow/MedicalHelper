package com.llsparrow.healthassistant.core_ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.core_network_api.data.exception.Alert
import com.llsparrow.healthassistant.core_ui.error.ErrorConverter
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val _error = MutableLiveData<Alert>()

    private val logExceptionHandler = CoroutineExceptionHandler(::showError)

    private val errorConverter = ErrorConverter()

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate + logExceptionHandler

    fun error(): LiveData<Alert> = _error

    override fun onCleared() {
        super.onCleared()
        cancel()

    }

    fun showError(context: CoroutineContext, throwable: Throwable) {
        launch {
            val alert = errorConverter.map(throwable)
            _error.postValue(alert)
            throwable.localizedMessage?.let { Logger.e(throwable, it) }
        }
    }
}