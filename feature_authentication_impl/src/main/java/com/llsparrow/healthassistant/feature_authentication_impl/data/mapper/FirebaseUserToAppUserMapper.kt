package com.llsparrow.healthassistant.feature_authentication_impl.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.llsparrow.core_base_api.mapper.OneWayMapper
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import javax.inject.Inject

/**
 * @author Gusev Andrei
 * @since  1.0
 */
@FeatureScope
internal class FirebaseUserToAppUserMapper @Inject constructor() : OneWayMapper<FirebaseUser, User>() {

    override fun map(item: FirebaseUser): User {
        return User(item.uid)
    }
}