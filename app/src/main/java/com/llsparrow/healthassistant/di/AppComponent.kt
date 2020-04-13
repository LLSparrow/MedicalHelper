package com.llsparrow.healthassistant.di

import com.llsparrow.healthassistant.common_navigation.core.NavigationControllerImpl
import com.llsparrow.healthassistant.CoreApplication
import com.llsparrow.healthassistant.StartActivity
import com.llsparrow.healthassistant.config.remote.FirebaseConfig
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        AppModule::class,
        ComponentHolderModule::class,
        ErrorHandlerModule::class
    ]
)
@Singleton
abstract class AppComponent {
    abstract val application: CoreApplication

    abstract val config: FirebaseConfig

    abstract val navigationController: NavigationControllerImpl

    abstract fun inject(application: CoreApplication)

    abstract fun inject(activity: StartActivity)

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
