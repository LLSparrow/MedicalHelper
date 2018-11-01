package com.oldsenior.ella.medicinelist.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.oldsenior.ella.corelib.di.injector.AppInjector
import com.oldsenior.ella.corelib.di.injector.Injector
import com.oldsenior.ella.medicinelist.MedicalListMenuRouter
import com.oldsenior.ella.medicinelist.di.DaggerMedicalListComponent
import com.oldsenior.ella.medicinelist.di.MedicalListComponent
import com.oldsenior.ella.medicinelist.view.MedicalListMenuActivity
import com.oldsenior.ella.medicinelist.view.MedicalListMenuFragment

object MedicalListMenuRouterImpl : Injector<MedicalListComponent>(), MedicalListMenuRouter {

    override var component: MedicalListComponent? = null

    override fun injectFragment(fragment: Fragment): Boolean =
        when (fragment) {
            is MedicalListMenuFragment -> {
                component?.inject(fragment)
                true
            }
            else -> false
        }

    override fun injectActivity(activity: Activity): Boolean =
        when (activity) {
            is MedicalListMenuActivity -> {
                component?.inject(activity)
                true
            }
            else -> false
        }

    override fun initComponent() {
        component = DaggerMedicalListComponent.builder()
            .plus(AppInjector.baseComponent)
            .build()
    }

    override fun startMedicalListMenuActivity(context: Context, intent: Intent) {
        if (component == null) initObserver()

        context.startActivity(intent)
    }
}