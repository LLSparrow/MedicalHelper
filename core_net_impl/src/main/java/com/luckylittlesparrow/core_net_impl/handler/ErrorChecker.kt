package com.luckylittlesparrow.core_net_impl.handler

interface ErrorChecker {
    fun throwExceptionIfError(jsonString: String?)
}