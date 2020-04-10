//package jp.co.soramitsu.feature_account_impl.user.data.remote.mapper
//
//import jp.co.soramitsu.core_di.PerFeature
//import jp.co.soramitsu.core_network_api.data.mapper.BaseServerMapper
//import jp.co.soramitsu.feature_account_api.user.domain.model.UserModel
//import jp.co.soramitsu.feature_account_impl.user.data.remote.response.ProfileResponse
//import com.llsparrow.healthassistant.feature_personal_info_api.data.room.converter.SexConverter
//import java.util.*
//import javax.inject.Inject
//
//@PerFeature
//class ProfileResponseToUserModelMapper @Inject constructor(private val sexConverter: SexConverter) :
//    BaseServerMapper<ProfileResponse, UserModel>() {
//
//    companion object {
//        private const val PLUS = '+'
//        private const val PHOTO_PATH = "/paymentservice/api/v1/user/photo?uuid="
//        private const val IDENTITY_PATH = "/paymentservice/api/v1/user/identity?uuid="
//    }
//
//    override fun map(item: ProfileResponse): UserModel {
//        return UserModel(
//            id = item.id,
//            login = item.accountName,
//            selfPhotoPath = combinePhotoUrl(item.photo),
//            documentPhotoPath = combineIdentityUrl(item.identity),
//            firstName = item.firstName,
//            lastName = item.lastName,
//            sex = sexConverter.fromString(item.sex),
//            phone = PLUS + item.phone,
//            email = item.email,
//            address = item.address,
//            nationality = item.nationality,
//            documentId = item.documentId,
//            dateOfBirth = Date(item.birthDate)
//        )
//    }
//
//    private fun combinePhotoUrl(key: String?): String? {
//        return if (key.isNullOrBlank()) null else PHOTO_PATH + key
//    }
//
//    private fun combineIdentityUrl(key: String?): String? {
//        return if (key.isNullOrBlank()) null else IDENTITY_PATH + key
//    }
//}