package com.llsparrow.healthassistant.feature_medicine_list.di

import com.llsparrow.core_base_api.di.CoreBaseApi
import com.llsparrow.core_network_api.di.CoreNetworkApi
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicineListFeatureHolder @Inject constructor(featureContainer: FeatureContainer) :
    FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val medicineListFeatureDependencies =
            DaggerMedicineListFeatureComponent_AuthenticationFeatureDependenciesComponent.builder()
                .coreNetworkApi(getFeature(CoreNetworkApi::class.java))
                .coreBaseApi(getFeature(CoreBaseApi::class.java))
                .build()
        return DaggerMedicineListFeatureComponent.builder()
            .medicineListFeatureDependencies(medicineListFeatureDependencies)
            .build()
    }
}
