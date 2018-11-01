package com.oldsenior.ella.corelib.view

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.oldsenior.ella.corelib.io.AppExecutors
import javax.inject.Inject

open class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors
}