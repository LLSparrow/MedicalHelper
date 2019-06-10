//package com.oldsenior.ella.authlib.router
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import androidx.core.app.Fragment
//import com.oldsenior.ella.authlib.view.LoginActivity
//import com.oldsenior.ella.core_base_impl.di.injector.Injector
//import com.oldsenior.ella.core_base_impl.router.FlowResult
//import com.oldsenior.ella.core_base_impl.router.FlowResultCallback
//import com.oldsenior.ella.authlib.di.AuthComponent
//
//object AuthRouter : Injector<AuthComponent>() {
//    override var component: AuthComponent? = null
//    lateinit var flowResultCallback: FlowResultCallback
//
//    override fun initComponent() {
////        component = DaggerAuthComponent.builder()
////            .plus(AppInjector.baseComponent)
////            .build()
//    }
//
//    override fun injectActivity(activity: Activity): Boolean =
//        when (activity) {
//            is LoginActivity -> {
//                component?.inject(activity)
//                true
//            }
//            else -> false
//        }
//
//    override fun injectFragment(fragment: Fragment): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//    fun startLoginFlow(context: Context, callback: FlowResultCallback) {
//        context.startActivity(Intent(context, LoginActivity::class.java))
//        flowResultCallback = callback
//    }
//
//    fun sendResult(flowResult: FlowResult) {
//        flowResultCallback.sendResult(flowResult)
//    }
//}