//package com.oldsenior.ella.core_base_impl.di.component
//
//import android.content.Context
//import com.oldsenior.ella.core_base_impl.di.CoreModule
//import dagger.*
//import javax.inject.Singleton
//
//@Component(
//    modules = [
//        CoreModule::class]
//)
//@Singleton
//interface AppComponent : BaseComponent {
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun applicationContext(context: Context): Builder
//
//        fun build(): AppComponent
//    }
//
//    fun baseSubcomponentBuilder(): BaseComponent.Builder
//
//}
//
////@Module
////class AppModule(val context: Context) {
////
////    @Provides
////    fun provideContext(): Context = context
////}