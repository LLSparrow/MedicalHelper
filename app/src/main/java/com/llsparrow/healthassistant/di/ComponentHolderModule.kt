//package com.llsparrow.healthassistant.di
//
//import com.llsparrow.core_base_api.di.CoreBaseApi
//import com.llsparrow.core_network_api.di.CoreNetworkApi
//import com.llsparrow.core_net_impl.di.CoreNetworkHolder
//import com.llsparrow.healthassistant.CoreApplication
//import com.llsparrow.healthassistant.core_base_impl.di.CoreBaseHolder
//import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder
//import com.llsparrow.healthassistant.core_di.holder.FeatureContainer
//import com.llsparrow.healthassistant.core_di.holder.FeatureHolderManager
//import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi
//import com.llsparrow.healthassistant.core_feature_toggle_impl.di.CoreFeatureToggleHolder
//import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi
//import com.llsparrow.healthassistant.feature_authentication_impl.di.AuthenticationFeatureHolder
//import com.llsparrow.healthassistant.feature_medicine_list.di.MedicineListFeatureHolder
//import com.llsparrow.healthassistant.feature_medicine_list_api.di.MedicineListFeatureApi
//import com.llsparrow.healthassistant.feature_secure_storage_api.di.SecureStorageFeatureApi
//import com.llsparrow.healthassistant.feature_secure_storage_impl.di.SecureStorageFeatureHolder
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import dagger.multibindings.ClassKey
//import dagger.multibindings.IntoMap
//import javax.inject.Singleton
//
//@Module
//abstract class ComponentHolderModule {
//
//    companion object {
//        @Singleton
//        @Provides
//        @JvmStatic
//        fun provideFeatureHolderManager(featureApiHolderMap: Map<Class<*>, FeatureApiHolder>): FeatureHolderManager {
//            return FeatureHolderManager(featureApiHolderMap)
//        }
//    }
//
//    @Singleton
//    @Binds
//    abstract fun provideFeatureContainer(application: CoreApplication): FeatureContainer
//
//
//    //region Core dependencies
//    @Singleton
//    @Binds
//    @ClassKey(CoreFeatureToggleApi::class)
//    @IntoMap
//    abstract fun provideCoreFeatureToggleApiHolder(coreFeatureToggleHolder: CoreFeatureToggleHolder): FeatureApiHolder
//
//    @Singleton
//    @Binds
//    @ClassKey(CoreNetworkApi::class)
//    @IntoMap
//    abstract fun provideCoreNetwork(coreNetworkHolder: CoreNetworkHolder): FeatureApiHolder
//
//    @Singleton
//    @Binds
//    @ClassKey(CoreBaseApi::class)
//    @IntoMap
//    abstract fun provideCoreBase(coreBaseHolder: CoreBaseHolder): FeatureApiHolder
//
//    //endregion
//
//    @Singleton
//    @Binds
//    @ClassKey(AuthenticationFeatureApi::class)
//    @IntoMap
//    abstract fun provideAuthenticationFeatureHolder(authenticationFeatureHolder: AuthenticationFeatureHolder): FeatureApiHolder
//
//    @Singleton
//    @Binds
//    @ClassKey(MedicineListFeatureApi::class)
//    @IntoMap
//    abstract fun provideMedicineListFeatureHolder(medicineListFeatureHolder: MedicineListFeatureHolder): FeatureApiHolder
//
//
//    @Singleton
//    @Binds
//    @ClassKey(SecureStorageFeatureApi::class)
//    @IntoMap
//    abstract fun provideSecureStorageFeature(secureStorageApiHolder: SecureStorageFeatureHolder): FeatureApiHolder?
//
//}