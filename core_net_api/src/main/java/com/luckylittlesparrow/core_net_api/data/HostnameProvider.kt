package com.luckylittlesparrow.core_net_api.data

interface HostnameProvider {
    fun currentHost(): String?
}