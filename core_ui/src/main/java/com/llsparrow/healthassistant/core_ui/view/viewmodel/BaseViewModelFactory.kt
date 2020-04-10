package com.llsparrow.healthassistant.core_ui.view.viewmodel

import androidx.lifecycle.ViewModel
import com.llsparrow.healthassistant.core_di.FeatureScope
import javax.inject.Inject
import javax.inject.Provider

@FeatureScope
class BaseViewModelFactory @Inject constructor(
    viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelFactory(viewModels)
