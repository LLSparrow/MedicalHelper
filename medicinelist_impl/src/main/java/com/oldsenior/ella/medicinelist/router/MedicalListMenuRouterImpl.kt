//package com.oldsenior.ella.medicinelist.router
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import androidx.core.app.Fragment
//import com.oldsenior.ella.core_base_impl.di.injector.AppInjector
//import com.oldsenior.ella.core_base_impl.di.injector.Injector
//import com.oldsenior.ella.medicinelist.di.DaggerMedicalListComponent
//import com.oldsenior.ella.medicinelist.di.MedicalListComponent
//import com.oldsenior.ella.medicinelist.view.MedicalListMenuActivity
//import com.oldsenior.ella.medicinelist.view.MedicalListMenuFragment
//
//object MedicalListMenuRouterImpl : Injector<MedicalListComponent>() {
//
//    override var component: MedicalListComponent? = null
//
//    override fun injectFragment(fragment: Fragment): Boolean =
//        when (fragment) {
//            is MedicalListMenuFragment -> {
//                component?.inject(fragment)
//                true
//            }
//            else -> false
//        }
//
//    override fun injectActivity(activity: Activity): Boolean =
//        when (activity) {
//            is MedicalListMenuActivity -> {
//                component?.inject(activity)
//                true
//            }
//            else -> false
//        }
//
//    override fun initComponent() {
//        component = DaggerMedicalListComponent
//            .builder()
//            .plus(AppInjector.baseComponent)
//            .build()
//    }
//
//     fun startMedicalListMenuActivity(context: Context, intent: Intent) {
//        if (component == null) initObserver()
//
//         context.startActivity(Intent(context, MedicalListMenuActivity::class.java))
//    }
//}