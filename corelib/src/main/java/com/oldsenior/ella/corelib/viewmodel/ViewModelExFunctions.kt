package com.oldsenior.ella.corelib.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.Fragment


inline fun <reified T : ViewModel> Fragment.withViewModel(factory: ViewModelProvider.Factory,
                                                          body: T.() -> Unit): T {
    val vm = getViewModel<T>(factory)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}