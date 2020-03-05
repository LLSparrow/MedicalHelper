package com.luckylittlesparrow.healthassistant.core_ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luckylittlesparrow.core_net_api.data.exception.Alert
import com.luckylittlesparrow.healthassistant.core_ui.error.ErrorConverter
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent {

    private val _error = MutableLiveData<Alert>()

    private val logExceptionHandler = CoroutineExceptionHandler(::showError)

    private val errorConvertor: ErrorConverter by inject()

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main.immediate + logExceptionHandler

    fun error(): LiveData<Alert> = _error

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    fun showError(context: CoroutineContext, throwable: Throwable) {
        val alert = errorConvertor.map(throwable)
        _error.postValue(alert)
        throwable.localizedMessage?.let { Logger.e(throwable, it) }
    }
}