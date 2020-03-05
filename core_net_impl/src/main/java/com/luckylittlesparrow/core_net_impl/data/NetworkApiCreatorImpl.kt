package com.luckylittlesparrow.core_net_impl.data

import com.luckylittlesparrow.core_net_api.data.NetworkApiCreator
import retrofit2.Retrofit

class NetworkApiCreatorImpl(private val retrofit: Retrofit) : NetworkApiCreator {

    override fun <T> create(service: Class<T>): T = retrofit.create(service)

}
