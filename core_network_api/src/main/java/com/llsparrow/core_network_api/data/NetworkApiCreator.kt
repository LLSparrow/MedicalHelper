package com.llsparrow.core_network_api.data

interface NetworkApiCreator {
    fun <T> create(service: Class<T>): T
}