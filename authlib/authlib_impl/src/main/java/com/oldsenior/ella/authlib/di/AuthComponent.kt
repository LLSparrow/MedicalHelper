package com.oldsenior.ella.medicinelist.di

import com.oldsenior.ella.authlib.LoginActivity
import com.oldsenior.ella.corelib.di.component.BaseComponent
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AuthScope

@AuthScope
@Component(dependencies = [BaseComponent::class],
        modules = [
            ViewModelModule::class]
)
interface AuthComponent {
    @Component.Builder
    interface Builder {

        fun plus(component: BaseComponent): Builder

        fun build(): AuthComponent
    }

    fun inject(activity: LoginActivity)

}
