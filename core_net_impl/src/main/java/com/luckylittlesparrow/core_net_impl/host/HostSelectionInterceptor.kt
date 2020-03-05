//package com.luckylittlesparrow.core_net_impl.host
//
//import com.luckylittlesparrow.core_net_api.data.HostnameProvider
//import okhttp3.HttpUrl
//import okhttp3.Interceptor
//import okhttp3.Response
//
///**
// * Interceptor for hot swap base url
// */
//class HostSelectionInterceptor constructor(
//    private val hostnameProvider: HostnameProvider
//) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        var request = chain.request()
//        val httpUrl = HttpUrl.parse(hostnameProvider.currentHost()) ?: throw RuntimeException("Host name not present")
//
//        val newUrl = request.url()
//            .newBuilder()
//            .scheme(httpUrl.scheme())
//            .host(httpUrl.host())
//            .port(httpUrl.port())
//            .build()
//
//        request = request.newBuilder()
//            .url(newUrl)
//            .build()
//
//        return chain.proceed(request)
//    }
//}