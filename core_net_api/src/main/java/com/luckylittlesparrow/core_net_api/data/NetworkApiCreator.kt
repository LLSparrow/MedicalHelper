package com.luckylittlesparrow.core_net_api.data

interface NetworkApiCreator {
    fun <T> create(service: Class<T>): T
}