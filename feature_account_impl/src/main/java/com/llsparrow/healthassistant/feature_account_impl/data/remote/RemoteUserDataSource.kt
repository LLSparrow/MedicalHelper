//package jp.co.soramitsu.feature_account_impl.user.data.remote
//
//import io.reactivex.Completable
//import io.reactivex.Maybe
//import jp.co.soramitsu.core_di.PerFeature
//import jp.co.soramitsu.core_utils.date.YYYY_DD_MM_HH_MM_SS
//import jp.co.soramitsu.core_utils.date.format
//import jp.co.soramitsu.feature_account_api.user.domain.model.UserModel
//import com.llsparrow.healthassistant.feature_personal_info_api.data.UserDataSource
//import com.llsparrow.healthassistant.feature_personal_info_api.data.remote.UserApi
//import jp.co.soramitsu.feature_account_impl.user.data.remote.mapper.ProfileResponseToUserModelMapper
//import jp.co.soramitsu.feature_account_impl.user.data.remote.request.UserUpdateRequest
//import javax.inject.Inject
//
//@PerFeature
//class RemoteUserDataSource @Inject constructor(
//    private val userApi: UserApi,
//    private val profileResponseToUserModelMapper: ProfileResponseToUserModelMapper
//) : UserDataSource {
//
//    override fun getUser(): Maybe<UserModel> {
//        return userApi.profile()
//            .map { profileResponseToUserModelMapper.mapResponse(it) }
//            .toMaybe()
//    }
//
//    override fun saveUser(userModel: UserModel): Completable {
//        val request = UserUpdateRequest(
//            firstName = userModel.firstName,
//            lastName = userModel.lastName,
//            dateOfBirth = userModel.dateOfBirth.format(YYYY_DD_MM_HH_MM_SS),
//            sex = userModel.sex.toString(),
//            email = userModel.email,
//            address = userModel.address
//        )
//        return userApi.update(request)
//            .ignoreElement()
//    }
//
//    override fun clear(): Completable {
//        throw UnsupportedOperationException()
//    }
//}
