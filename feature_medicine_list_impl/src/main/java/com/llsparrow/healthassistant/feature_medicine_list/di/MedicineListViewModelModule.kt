package com.llsparrow.healthassistant.feature_medicine_list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_di.ViewModelKey
import com.llsparrow.healthassistant.core_ui.view.viewmodel.BaseViewModelFactory
import com.llsparrow.healthassistant.feature_medicine_list.presentation.MedicineListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MedicineListViewModelModule {

    @FeatureScope
    @Binds
    abstract fun provideViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(MedicineListViewModel::class)
    @Binds
    abstract fun provideSignInViewModel(viewModel: MedicineListViewModel): ViewModel

}