//package com.llsparrow.healthassistant.appinitializers
//
//import android.app.Application
//import com.llsparrow.core_base_api.appinitializers.AppInitializer
//import com.llsparrow.core_util.di.coreUtilsModule
//import com.llsparrow.healthassistant.BuildConfig
//import com.llsparrow.healthassistant.authlib.di.authModule
//import com.llsparrow.healthassistant.core_base_impl.di.coreModule
//import com.llsparrow.healthassistant.core_ui.di.coreUIModule
//import com.llsparrow.healthassistant.di.appModule
//import com.llsparrow.healthassistant.feature_main_impl.di.mainModule
//import com.llsparrow.healthassistant.feature_medicine_list.di.medicineListModule
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger
//import org.koin.core.context.startKoin
//
///**
// * @author Gusev Andrei
// * @since  1.0
// */
//
//
//class KoinInitializer : AppInitializer {
//    override fun init(application: Application) {
//        startKoin {
//
//            if (BuildConfig.DEBUG) {
//                androidLogger()
//            }
//
//            androidContext(application)
//
//            modules(
//                listOf(
//                    appModule,
//                    coreUIModule,
//                    coreUtilsModule,
//                    coreModule,
//
//                    authModule,
//                    mainModule,
//                    medicineListModule
//                )
//            )
//        }
//    }
//}