//package jp.co.soramitsu.feature_account_impl.user.data.room.converter
//
//import jp.co.soramitsu.core_di.PerFeature
//import jp.co.soramitsu.core_utils.mapper.Mapper
//import jp.co.soramitsu.feature_account_api.user.domain.model.Sex
//import jp.co.soramitsu.feature_account_api.user.domain.model.UserModel
//import com.llsparrow.healthassistant.feature_personal_info_api.data.room.model.UserEntity
//import java.util.*
//import javax.inject.Inject
//
//@PerFeature
//class UserModelToUserEntityMapper @Inject constructor() : Mapper<UserModel, UserEntity>() {
//
//    override fun map(item: UserModel) = UserEntity(
//        id = item.localId,
//        userId = item.id,
//        login = item.login,
//        firstName = item.firstName,
//        lastName = item.lastName,
//        sex = item.sex,
//        dateOfBirth = item.dateOfBirth,
//        phone = item.phone,
//        photoPath = item.selfPhotoPath,
//        documentPhotoPath = item.documentPhotoPath,
//        email = item.email,
//        address = item.address,
//        nationality = item.nationality,
//        documentId = item.documentId
//    )
//
//    override fun reverse(item: UserEntity) = UserModel(
//        localId = item.id,
//        id = item.userId,
//        login = item.login,
//        firstName = item.firstName,
//        lastName = item.lastName,
//        sex = item.sex ?: Sex.MALE,
//        dateOfBirth = item.dateOfBirth ?: Date(),
//        phone = item.phone,
//        selfPhotoPath = item.photoPath,
//        documentPhotoPath = item.documentPhotoPath,
//        email = item.email,
//        address = item.address,
//        nationality = item.nationality,
//        documentId = item.documentId
//    )
//}
