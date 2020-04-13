package com.llsparrow.healthassistant.feature_account_api.mapper

import com.google.firebase.auth.FirebaseUser
import com.llsparrow.core_base_api.mapper.OneWayMapper
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import javax.inject.Inject


@FeatureScope
class FirebaseUserToAppUserMapper @Inject constructor() : OneWayMapper<FirebaseUser, User>() {

    override fun map(item: FirebaseUser): User {
        return User(
            id = item.uid,
            email = item.email,
            fullname = item.displayName
        )
    }
}