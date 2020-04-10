package com.llsparrow.core_net_impl.handler

interface ErrorChecker {
    fun throwExceptionIfError(jsonString: String?)
}