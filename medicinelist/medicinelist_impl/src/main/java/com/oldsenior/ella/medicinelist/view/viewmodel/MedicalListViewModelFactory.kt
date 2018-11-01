package com.oldsenior.ella.medicinelist.view.viewmodel

import android.arch.lifecycle.ViewModel
import com.oldsenior.ella.corelib.view.viewmodel.ViewModelFactory
import com.oldsenior.ella.medicinelist.di.MedicalListScope
import javax.inject.Inject
import javax.inject.Provider

class MedicalListViewModelFactory @Inject constructor(
        @MedicalListScope viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>)
    : ViewModelFactory(viewModels)