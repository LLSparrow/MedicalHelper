package com.llsparrow.core_net_impl.di

import com.google.gson.Gson
import com.llsparrow.core_network_api.data.NetworkApiCreator
import com.llsparrow.core_network_api.session.UserActivityWatcher
import com.llsparrow.core_net_impl.data.NetworkApiCreatorImpl
import com.llsparrow.core_net_impl.handler.ErrorChecker
import com.llsparrow.core_net_impl.handler.ErrorCheckerImpl
import com.llsparrow.core_net_impl.handler.HttpCodeChecker
import com.llsparrow.core_net_impl.handler.HttpCodeCheckerImpl
import com.llsparrow.core_net_impl.session.UserActivityWatcherImpl
import com.llsparrow.healthassistant.core_di.FeatureScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
internal abstract class NetworkModule {

    @FeatureScope
    @Binds
    abstract fun provideErrorChecker(errorChecker: ErrorCheckerImpl): ErrorChecker

    @FeatureScope
    @Binds
    abstract fun provideHttpCodeHandler(httpCodeHandler: HttpCodeCheckerImpl): HttpCodeChecker

    @FeatureScope
    @Binds
    abstract fun provideUserActivityWatcher(userActivityWatcher: UserActivityWatcherImpl): UserActivityWatcher

    companion object {


//        @Provides
//        @FeatureScope
//        fun provideOkHttpClient(
//            hostnameProvider: HostnameProvider,
//            appConfig: AppConfig,
//            errorHandlerInterceptor: ErrorHandlerInterceptor,
//
//        ): OkHttpClient {
//            val logging = HttpLoggingInterceptor()
//            if (appConfig.isDebug()) {
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                logging.setLevel(HttpLoggingInterceptor.Level.NONE)
//            }
//            val hostSelectionInterceptor: Interceptor = HostSelectionInterceptor(hostnameProvider)
//            return OkHttpClient.Builder()
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .writeTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
//                .addInterceptor(hostSelectionInterceptor)
//                .sslSocketFactory(trustKit.getSSLSocketFactory("stub.com"), trustKit.getTrustManager("stub.com"))
//                .addNetworkInterceptor(tokenInterceptor)
//                .authenticator(serverAuthenticator)
//                .addInterceptor(errorHandlerInterceptor)
//                .addInterceptor(logging)
//                .build()
//        }
//
//        @AuthorizationZone
//        @Provides
//        @PerFeature
//        fun providePicasso(
//            tokenInterceptor: TokenInterceptor,
//            serverAuthenticator: ServerAuthenticator,
//            hostnameProvider: HostnameProvider,
//            appConfig: AppConfig,
//            context: Context,
//            trustKit: TrustKit
//        ): Picasso {
//            val logging = HttpLoggingInterceptor()
//            if (appConfig.isDebug()) {
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//            } else {
//                logging.setLevel(HttpLoggingInterceptor.Level.NONE)
//            }
//            return Builder(context)
//                .downloader(
//                    OkHttp3Downloader(
//                        OkHttpClient.Builder()
//                            .connectTimeout(30, TimeUnit.SECONDS)
//                            .writeTimeout(30, TimeUnit.SECONDS)
//                            .readTimeout(10, TimeUnit.SECONDS)
//                            .retryOnConnectionFailure(true)
//                            .sslSocketFactory(
//                                trustKit.getSSLSocketFactory("stub.com"),
//                                trustKit.getTrustManager("stub.com")
//                            )
//                            .addNetworkInterceptor(tokenInterceptor)
//                            .addInterceptor(logging)
//                            .authenticator(serverAuthenticator).build()
//                    )
//                )
//                .requestTransformer({ request ->
//                    Builder(
//                        Uri.parse(
//                            hostnameProvider.currentHost()
//                                    + request.uri.toString()
//                        )
//                    ).build()
//                })
//                .build()
//        }

//        @JvmStatic
//        @Provides
//        @FeatureScope
//        fun provideApiCreator(
//            okHttpClient: OkHttpClient
//        ): NetworkApiCreator {
//            val retrofit = Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl("http://stub.com")
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//            return NetworkApiCreatorImpl(retrofit)
//        }

        @JvmStatic
        @Provides
        @FeatureScope
        fun provideGson(): Gson {
            return Gson()
        }

    }
}