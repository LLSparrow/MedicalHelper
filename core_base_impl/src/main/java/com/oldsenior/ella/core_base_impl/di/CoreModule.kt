//package com.oldsenior.ella.core_base_impl.di
//
//import com.oldsenior.ella.core_base_impl.util.BuildType
//import dagger.Module
//import dagger.Provides
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
//import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
//import retrofit2.Retrofit
//import javax.inject.Singleton
//
//@Module
//object CoreModule {
//
//    @Provides
//    @Singleton
//    @JvmStatic
//    internal fun provideOkHttp(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = if (BuildType.DEBUG.isCurrentBuild) {
//            BODY
//        } else {
//            NONE
//        }
//        return OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build()
//    }
//
//    @JvmStatic
//    @Provides
//    @Singleton
//    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
//            Retrofit.Builder()
//                    .client(okHttpClient)
//                    .baseUrl("http://mobile.asosservices.com/")
//                    .build()
//
//}