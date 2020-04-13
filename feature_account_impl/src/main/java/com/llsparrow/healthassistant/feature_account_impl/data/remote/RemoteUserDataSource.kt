package com.llsparrow.healthassistant.feature_account_impl.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_api.mapper.FirebaseUserToAppUserMapper
import com.llsparrow.healthassistant.feature_account_impl.data.UserDataSource
import javax.inject.Inject


@FeatureScope
class RemoteUserDataSource @Inject constructor(
    private val firebaseUserToAppUserMapper: FirebaseUserToAppUserMapper
) : UserDataSource {

    override suspend fun getUser(): User? {
        val user = FirebaseAuth.getInstance().currentUser
        return if (user == null) null else firebaseUserToAppUserMapper.map(user)
    }

    override suspend fun updateUser(user: User) {
        val fUser = FirebaseAuth.getInstance().currentUser

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(user.getFullName())
            .setPhotoUri(user.selfPhotoPath)
            .build()

        fUser?.updateProfile(profileUpdates)
    }

    override suspend fun clear() {
        FirebaseAuth.getInstance().currentUser?.delete()
    }
}
