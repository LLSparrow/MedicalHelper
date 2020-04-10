package com.llsparrow.healthassistant.core_ui.view.viewmodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

inline fun <reified T : ViewModel> Fragment.withViewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
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

inline fun <reified T : ViewModel> FragmentActivity.withViewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = getViewModel<T>(factory)
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, onEmission: (T) -> Unit) {
    return observe(owner, Observer<T> {
        if (it != null) {
            onEmission(it)
        }
    })
}