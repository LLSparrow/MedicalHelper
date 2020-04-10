//package com.llsparrow.healthassistant.feature_medicine_list.di
//
//import com.llsparrow.healthassistant.feature_medicine_list.navigation.MedicineListLauncherImpl
//import com.llsparrow.healthassistant.feature_medicine_list.presentation.list.MedicineListAdapter
//import com.llsparrow.healthassistant.feature_medicine_list.presentation.MedicineListViewModel
//import com.llsparrow.healthassistant.feature_medicine_list_api.navigation.MedicineListLauncher
//import org.koin.androidx.experimental.dsl.viewModel
//import org.koin.dsl.module
//
///**
// * @author Gusev Andrei
// * @since  1.0
// */
//
//val medicineListModule = module {
//    single { MedicineListAdapter() }
//    single<MedicineListLauncher> { MedicineListLauncherImpl(get()) }
//    viewModel<MedicineListViewModel>()
//}