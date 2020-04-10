package com.llsparrow.healthassistant.feature_medicine_list.di

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_medicine_list.navigation.MedicineListLauncherImpl
import com.llsparrow.healthassistant.feature_medicine_list_api.navigation.MedicineListLauncher
import dagger.Binds
import dagger.Module

@Module
abstract class MedicineListFeatureModule {

    @FeatureScope
    @Binds
    abstract fun provideMedicineListLauncher(launcher: MedicineListLauncherImpl): MedicineListLauncher
}