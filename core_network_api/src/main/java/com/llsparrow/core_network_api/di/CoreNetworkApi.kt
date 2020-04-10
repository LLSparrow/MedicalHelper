package com.llsparrow.core_network_api.di

import com.llsparrow.core_network_api.data.NetworkApiCreator
import com.llsparrow.core_network_api.session.UserActivityWatcher

interface CoreNetworkApi {
    //fun networkApiCreator(): NetworkApiCreator

    fun userActivityWatcher(): UserActivityWatcher
}