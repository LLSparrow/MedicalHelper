//package com.llsparrow.healthassistant.feature_personal_info_api.data.remote
//
//import io.reactivex.Single
//import jp.co.soramitsu.core_network_api.data.response.BaseResponse
//import jp.co.soramitsu.feature_account_impl.user.data.remote.request.UserUpdateRequest
//import jp.co.soramitsu.feature_account_impl.user.data.remote.response.ProfileResponse
//import retrofit2.http.Body
//import retrofit2.http.GET
//import retrofit2.http.PUT
//
//interface UserApi {
//
//    @GET("/paymentservice/api/v1/user")
//    fun profile(): Single<BaseResponse<ProfileResponse>>
//
//    @PUT("/paymentservice/api/v1/user/update-mobile")
//    fun update(@Body request: UserUpdateRequest): Single<BaseResponse<ProfileResponse>>
//}
