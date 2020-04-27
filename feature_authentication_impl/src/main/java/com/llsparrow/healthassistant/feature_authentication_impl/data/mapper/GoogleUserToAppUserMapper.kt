package com.llsparrow.healthassistant.feature_authentication_impl.data.mapper

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.llsparrow.core_base_api.mapper.OneWayMapper
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import javax.inject.Inject


@FeatureScope
internal class GoogleUserToAppUserMapper @Inject constructor() : OneWayMapper<GoogleSignInAccount, User>() {

    override suspend fun map(item: GoogleSignInAccount): User {
        return User(
            id = item.id,
            firstName = item.givenName,
            lastName = item.familyName,
            selfPhotoPath = item.photoUrl,
            email = item.email,
            fullname = item.displayName
        )
    }
}