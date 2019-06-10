//package com.oldsenior.ella.medicinelist.di
//
//import android.arch.lifecycle.ViewModel
//import android.arch.lifecycle.ViewModelProvider
//import com.oldsenior.ella.core_ui.view.viewmodel.ViewModelKey
//import com.oldsenior.ella.medicinelist.view.MedicalListViewModel
//import com.oldsenior.ella.medicinelist.view.viewmodel.MedicalListViewModelFactory
//import dagger.Binds
//import dagger.Module
//import dagger.multibindings.IntoMap
//
//@Module
//abstract class ViewModelModule {
//
//    @Binds
//    @IntoMap
//    @MedicalListScope
//    @ViewModelKey(MedicalListViewModel::class)
//    abstract fun bindMedicalListViewModel(medicalListViewModel: MedicalListViewModel): ViewModel
//
//    @Binds
//    @MedicalListScope
//    abstract fun bindViewModelFactory(factory: MedicalListViewModelFactory): ViewModelProvider.Factory
//}