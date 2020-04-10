package com.llsparrow.healthassistant.di

import com.llsparrow.core_base_api.navigation.NavigationController
import com.llsparrow.healthassistant.CoreApplication
import com.llsparrow.healthassistant.LauncherActivity
import com.llsparrow.healthassistant.config.remote.FirebaseConfig
import com.llsparrow.healthassistant.core_base_impl.navigation.NavigationControllerImpl
import dagger.BindsInstance
import dagger.Component
import org.xml.sax.ErrorHandler
import javax.inject.Singleton


@Component(
    modules = [AppModule::class,
        // AppNavigationModule::class,
        ComponentHolderModule::class,
        ErrorHandlerModule::class]
)
@Singleton
abstract class AppComponent {
    abstract val application: CoreApplication

    abstract val config: FirebaseConfig

    abstract val navigationController: NavigationControllerImpl

    abstract fun inject(application: CoreApplication)

    abstract fun inject(activity: LauncherActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: CoreApplication): Builder
    }

    companion object {
        @Volatile
        @JvmStatic
        var sInstance: AppComponent? = null
            private set

        fun init(component: AppComponent) {
            require(sInstance == null) { "AppComponent is already initialized." }
            sInstance = component
        }
    }
}
