package com.llsparrow.core_net_impl.data

import com.llsparrow.core_network_api.data.NetworkApiCreator
import com.llsparrow.healthassistant.core_di.FeatureScope
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkApiCreatorImpl  constructor(private val retrofit: Retrofit) : NetworkApiCreator {

    override fun <T> create(service: Class<T>): T = retrofit.create(service)

}
