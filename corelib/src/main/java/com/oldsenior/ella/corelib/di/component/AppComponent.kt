package com.oldsenior.ella.corelib.di.component

import android.app.Application
import android.content.Context
import com.oldsenior.ella.corelib.di.CoreModule
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    CoreModule::class])
@Singleton
interface AppComponent  {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(application: Application): Builder

        fun build(): AppComponent
    }

    fun baseSubcomponentBuilder(): BaseComponent.Builder

}

@Module
internal interface AppModule {

    @Binds
    fun provideContext(context: Context): Context
}