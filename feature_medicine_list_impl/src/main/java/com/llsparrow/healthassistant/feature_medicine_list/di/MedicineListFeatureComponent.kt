package com.llsparrow.healthassistant.feature_medicine_list.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_medicine_list.presentation.MedicineListFragment
import com.llsparrow.healthassistant.feature_medicine_list_api.di.MedicineListFeatureApi
import dagger.Component

@Component(
    dependencies = [MedicineListFeatureDependencies::class],
    modules = [
        MedicineListFeatureModule::class,
        MedicineListViewModelModule::class,
        MedicineListWizardModule::class
    ]
)
@FeatureScope
abstract class MedicineListFeatureComponent : MedicineListFeatureApi {
    abstract fun inject(fragment: MedicineListFragment)

    @Component(
        dependencies = [
            CoreNetworkApi::class,
            CoreBaseApi::class
        ]
    )
    @FeatureScope
    internal interface AuthenticationFeatureDependenciesComponent : MedicineListFeatureDependencies
}