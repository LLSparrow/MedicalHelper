package com.llsparrow.healthassistant.di;

import androidx.annotation.NonNull;

import com.llsparrow.healthassistant.common_navigation.di.NavigationCommonHolder;
import com.llsparrow.healthassistant.common_navigation_api.navigation.di.NavigationCommonApi;
import com.llsparrow.core_base_api.di.CoreBaseApi;
import com.llsparrow.core_net_impl.di.CoreNetworkHolder;
import com.llsparrow.core_network_api.di.CoreNetworkApi;
import com.llsparrow.feature_main_api.di.MainMenuFeatureApi;
import com.llsparrow.healthassistant.CoreApplication;
import com.llsparrow.healthassistant.core_base_impl.di.CoreBaseHolder;
import com.llsparrow.healthassistant.core_di.holder.FeatureApiHolder;
import com.llsparrow.healthassistant.core_di.holder.FeatureContainer;
import com.llsparrow.healthassistant.core_di.holder.FeatureHolderManager;
import com.llsparrow.healthassistant.core_feature_toggle_api.di.CoreFeatureToggleApi;
import com.llsparrow.healthassistant.core_feature_toggle_impl.di.CoreFeatureToggleHolder;
import com.llsparrow.healthassistant.feature_account_api.di.AccountFeatureApi;
import com.llsparrow.healthassistant.feature_account_impl.di.AccountFeatureHolder;
import com.llsparrow.healthassistant.feature_authentication_api.di.AuthenticationFeatureApi;
import com.llsparrow.healthassistant.feature_authentication_impl.di.AuthenticationFeatureHolder;
import com.llsparrow.healthassistant.feature_main_impl.di.MainMenuFeatureHolder;
import com.llsparrow.healthassistant.feature_medicine_list.di.MedicineListFeatureHolder;
import com.llsparrow.healthassistant.feature_medicine_list_api.di.MedicineListFeatureApi;
import com.llsparrow.healthassistant.feature_secure_storage_api.di.SecureStorageFeatureApi;
import com.llsparrow.healthassistant.feature_secure_storage_impl.di.SecureStorageFeatureHolder;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class ComponentHolderModule {

    @Singleton
    @Binds
    abstract FeatureContainer provideFeatureContainer(@NonNull CoreApplication application);

    @Singleton
    @Provides
    static FeatureHolderManager provideFeatureHolderManager(Map<Class<?>, FeatureApiHolder> featureApiHolderMap) {
        return new FeatureHolderManager(featureApiHolderMap);
    }

    //region Core dependencies

    @Singleton
    @Binds
    @ClassKey(CoreFeatureToggleApi.class)
    @IntoMap
    abstract FeatureApiHolder provideCoreFeatureToggleApiHolder(@NonNull CoreFeatureToggleHolder coreFeatureToggleHolder);

    @Singleton
    @Binds
    @ClassKey(CoreNetworkApi.class)
    @IntoMap
    abstract FeatureApiHolder provideCoreNetwork(@NonNull CoreNetworkHolder coreNetworkHolder);

    @Singleton
    @Binds
    @ClassKey(CoreBaseApi.class)
    @IntoMap
    abstract FeatureApiHolder provideCoreBase(@NonNull CoreBaseHolder coreBaseHolder);

    //endregion

    @Singleton
    @Binds
    @ClassKey(NavigationCommonApi.class)
    @IntoMap
    abstract FeatureApiHolder provideCommonNavigation(@NonNull NavigationCommonHolder navigationCommonHolder);

    @Singleton
    @Binds
    @ClassKey(AuthenticationFeatureApi.class)
    @IntoMap
    abstract FeatureApiHolder provideAuthenticationFeatureHolder(@NonNull AuthenticationFeatureHolder holder);

    @Singleton
    @Binds
    @ClassKey(MedicineListFeatureApi.class)
    @IntoMap
    abstract FeatureApiHolder provideMedicineListFeatureHolder(@NonNull MedicineListFeatureHolder holder);

    @Singleton
    @Binds
    @ClassKey(MainMenuFeatureApi.class)
    @IntoMap
    abstract FeatureApiHolder provideMainMenuFeatureHolder(@NonNull MainMenuFeatureHolder holder);

    @Singleton
    @Binds
    @ClassKey(AccountFeatureApi.class)
    @IntoMap
    abstract FeatureApiHolder provideAccountFeatureHolder(@NonNull AccountFeatureHolder holder);

    @Singleton
    @Binds
    @ClassKey(SecureStorageFeatureApi.class)
    @IntoMap
    abstract FeatureApiHolder provideSecureStorageFeatureHolder(@NonNull SecureStorageFeatureHolder holder);
}
