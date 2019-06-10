//package com.oldsenior.ella.core_base_impl.di.injector
//
//import android.app.Activity
//import android.arch.lifecycle.LifecycleObserver
//import android.os.Bundle
//import androidx.core.app.Fragment
//import androidx.core.app.FragmentActivity
//import androidx.core.app.FragmentManager
//
///**
// *  Abstraction of Injector
// */
//abstract class Injector<T> : LifecycleObserver {
//
//    private var injectionCount: Int = 0
//
//    protected abstract var component: T?
//
//    protected fun initObserver() {
//        AppInjector.addObserver(this)
//    }
//
//    /**
//     * Lifecycle callback from [AppInjector], which subscribed to android system
//     *
//     * @param activity created activity from application callback
//     */
//    fun onActivityCreated(activity: Activity) {
//        if (component == null) initComponent()
//
//        if (injectActivity(activity)) injectionCount++
//
//        if (activity is FragmentActivity)
//            activity.supportFragmentManager
//                .registerFragmentLifecycleCallbacks(
//                    object : FragmentManager.FragmentLifecycleCallbacks() {
//                        override fun onFragmentCreated(
//                            fm: FragmentManager,
//                            fragment: Fragment,
//                            savedInstanceState: Bundle?
//                        ) {
//                            injectFragment(fragment)
//                        }
//                    }, true
//                )
//    }
//
//    /**
//     * Lifecycle callback from [AppInjector],  which subscribed to android system
//     *
//     * @param activity created activity from application callback
//     */
//    fun onActivityDestroyed() {
//        injectionCount--
//        if (injectionCount == 0) releaseComponent()
//    }
//
//    /**
//     * Method for clearing reference to dagger component of specific scope
//     */
//    protected open fun releaseComponent() {
//        if (shouldReleaseComponent()) {
//            component = null
//            AppInjector.removeObserver(this)
//        }
//    }
//
//    /**
//     * Method for initialising dagger component of specific scope
//     */
//    protected abstract fun initComponent()
//
//    protected open fun shouldReleaseComponent(): Boolean = true
//
//    /**
//     * Method for activity injection, successor must provide realisation to
//     * determine the possibility of an injection into the activity
//
//     * @param activity activity for injection
//     */
//    protected abstract fun injectActivity(activity: Activity): Boolean
//
//    /**
//     * Method for fragment injection, successor must provide realisation to
//     * determine the possibility of an injection into the fragment
//
//     * @param fragment fragment for injection
//     */
//    protected abstract fun injectFragment(fragment: Fragment): Boolean
//
//}