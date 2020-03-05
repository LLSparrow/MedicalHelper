package com.luckylittlesparrow.healthassistant.appinitializers

import android.app.Application
import com.luckylittlesparrow.core_base_api.appinitializers.AppInitializer
import com.luckylittlesparrow.core_util.di.coreUtilsModule
import com.luckylittlesparrow.healthassistant.BuildConfig
import com.luckylittlesparrow.healthassistant.authlib.di.authModule
import com.luckylittlesparrow.healthassistant.core_base_impl.di.coreModule
import com.luckylittlesparrow.healthassistant.core_ui.di.coreUIModule
import com.luckylittlesparrow.healthassistant.di.appModule
import com.luckylittlesparrow.healthassistant.feature_main_impl.di.mainModule
import com.luckylittlesparrow.healthassistant.feature_medicine_list.di.medicineListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author Gusev Andrei
 * @since  1.0
 */


class KoinInitializer : AppInitializer {
    override fun init(application: Application) {
        startKoin {

            if (BuildConfig.DEBUG) {
                androidLogger()
            }

            androidContext(application)

            modules(
                listOf(
                    appModule,
                    coreUIModule,
                    coreUtilsModule,
                    coreModule,

                    authModule,
                    mainModule,
                    medicineListModule
                )
            )
        }
    }
}