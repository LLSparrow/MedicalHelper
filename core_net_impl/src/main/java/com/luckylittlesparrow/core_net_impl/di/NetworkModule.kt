//package com.luckylittlesparrow.core_net_impl.di
//
//import okhttp3.OkHttpClient
//import org.koin.dsl.module
//import retrofit2.Retrofit
//
///**
// * @author Gusev Andrei
// * @since  1.0
// */
//
//val networkModule = module {
//
//    single<OkHttpClient>(DEFAULT_NAMESPACE) { provideDefaultOkhttpClient() }
//    single<Retrofit>(DEFAULT_NAMESPACE) { provideRetrofit(get(DEFAULT_NAMESPACE)) }
//    single<TmdbApi> { provideTmdbService(get(DEFAULT_NAMESPACE)) }
//
//}
//
//fun provideDefaultOkhttpClient(): OkHttpClient {
//    return OkHttpClient.Builder()
//        .addInterceptor(ApiKeyInterceptor())
//        .build()
//}
//
//fun provideRetrofit(client: OkHttpClient): Retrofit {
//    return Retrofit.Builder()
//        .baseUrl(BuildConfig.SERVER_BASE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//}
//
//fun provideTmdbService(retrofit: Retrofit): TmdbApi = retrofit.create(TmdbApi::class.java)
//
//const val DEFAULT_NAMESPACE = "default"