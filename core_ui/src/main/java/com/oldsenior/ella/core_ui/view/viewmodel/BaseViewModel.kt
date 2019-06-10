//package com.oldsenior.ella.core_ui.view.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.oldsenior.ella.core_ui.alert.Alert
//import com.oldsenior.ella.core_ui.error.ErrorConverter
//import com.orhanobut.logger.Logger
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.disposables.Disposable
//import javax.inject.Inject
//
//open class BaseViewModel : ViewModel() {
//    private val _error = MutableLiveData<Alert>()
//    val error: LiveData<Alert> = _error
//    protected val compositeDisposable = CompositeDisposable()
//
//    @Inject
//    lateinit var errorConvertor: ErrorConverter
//
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.clear()
//    }
//
//    fun Disposable.unsubscribeOnDestroy() {
//        compositeDisposable.add(this)
//    }
//
//    protected fun showError(throwable: Throwable) {
//        val alert = errorConvertor.map(throwable)
//        _error.postValue(alert)
//        Logger.e(throwable, throwable.localizedMessage)
//    }
//
//}