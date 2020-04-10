package com.llsparrow.core_network_api.data

interface HostnameProvider {
    fun currentHost(): String?
}