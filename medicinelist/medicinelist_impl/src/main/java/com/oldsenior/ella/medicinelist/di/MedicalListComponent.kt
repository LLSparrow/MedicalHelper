package com.oldsenior.ella.medicinelist.di

import com.oldsenior.ella.corelib.di.component.BaseComponent
import com.oldsenior.ella.medicinelist.view.MedicalListMenuActivity
import com.oldsenior.ella.medicinelist.view.MedicalListMenuFragment
import dagger.Component
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MedicalListScope

@MedicalListScope
@Component(dependencies = [BaseComponent::class],
        modules = [
            MedicalListModule::class,
            ViewModelModule::class]
)
interface  MedicalListComponent {
    @Component.Builder
    interface Builder {

        fun plus(component: BaseComponent): Builder

        fun build(): MedicalListComponent
    }

    fun inject(activity: MedicalListMenuActivity)

    fun inject(fragment: MedicalListMenuFragment)

}
