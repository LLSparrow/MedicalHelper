package com.oldsenior.core_net_impl.handler

interface HttpCodeChecker {
    fun throwExceptionIfFail(httpCode: Int)
}