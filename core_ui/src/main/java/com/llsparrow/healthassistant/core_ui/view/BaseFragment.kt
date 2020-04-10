package com.llsparrow.healthassistant.core_ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.llsparrow.core_network_api.data.exception.Alert


abstract class BaseFragment<V : ViewDataBinding> : Fragment, BaseFragmentCallbacks {

    private val fragmentDelegate: BaseFragmentDelegate by lazy {
        BaseFragmentDelegate(this)
    }

    constructor(contentLayoutId: Int) : super(contentLayoutId)

    constructor() : super()

    private var binding by AutoClearedValue<V>()

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createBinding(inflater, container, savedInstanceState)
            .also { binding = it }
            .root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(requireBinding(), savedInstanceState)
    }

    abstract fun onViewCreated(binding: V, savedInstanceState: Bundle?)

    protected fun requireBinding(): V = requireNotNull(binding)

    protected abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentDelegate.onAttach(context)
    }

    protected fun setTitle(@StringRes titleResId: Int) {
        fragmentDelegate.setTitle(titleResId)
    }

    protected fun setTitle(title: String) {
        fragmentDelegate.setTitle(title)
    }

    fun onError(alert: Alert) {
        fragmentDelegate.onError(alert)
    }

    fun showError(alert: Alert) {
        onError(alert)
    }

    protected fun showHomeUpEnabled(enabled: Boolean) {
        fragmentDelegate.showHomeUpEnabled(enabled)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return fragmentDelegate.onCreateAnimation(transit, enter, nextAnim)
    }
}