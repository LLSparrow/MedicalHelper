package com.llsparrow.core_net_impl.handler

import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandlerInterceptor @Inject constructor(
    private val httpCodeChecker: HttpCodeChecker,
    private val errorChecker: ErrorChecker
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        httpCodeChecker.throwExceptionIfFail(response.code)

        val jsonString = getJsonBody(response)
        errorChecker.throwExceptionIfError(jsonString)

        return response
    }

    private fun getJsonBody(response: Response): String? {
        val responseBody = response.body
        val source = responseBody?.source()
        source?.request(Long.MAX_VALUE)
        return source?.buffer?.clone()?.readString(Charset.forName("UTF-8"))
    }

}