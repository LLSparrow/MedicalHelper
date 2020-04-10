//package com.oldsenior.core_net_api.data.mapper
//
//import com.oldsenior.core_base_api.mapper.Mapper
//import com.oldsenior.core_net_api.data.response.BaseResponse
//
//abstract class BaseServerMapper<T, R> : com.oldsenior.core_base_api.mapper.Mapper<T, R>() {
//
//    fun mapResponse(item: BaseResponse<T>): R = map(item.data)
//
//    override fun reverse(item: R): T = throw UnsupportedOperationException()
//}
