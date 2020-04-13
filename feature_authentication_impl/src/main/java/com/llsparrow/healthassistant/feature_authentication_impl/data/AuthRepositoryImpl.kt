package com.llsparrow.healthassistant.feature_authentication_impl.data

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_api.mapper.FirebaseUserToAppUserMapper
import com.llsparrow.healthassistant.feature_authentication_impl.data.mapper.GoogleUserToAppUserMapper
import com.llsparrow.healthassistant.feature_authentication_impl.data.service.AuthService
import com.llsparrow.healthassistant.feature_authentication_impl.domain.AuthRepository
import com.orhanobut.logger.Logger
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@FeatureScope
internal class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val dispatchers: AppDispatchers,
    private val firebaseMapper: FirebaseUserToAppUserMapper,
    private val googleMapper: GoogleUserToAppUserMapper
) : AuthRepository {

    override suspend fun signIn(username: String, password: String) = withContext(dispatchers.IO) {
        val result = authService.signIn(username, password).await()
        val user = result.user
        return@withContext if (user == null) null else firebaseMapper.map(user)
    }

    override suspend fun signUp(username: String, email: String, password: String) = withContext(dispatchers.IO) {
        var user = authService.currentUser()

        val result: AuthResult = if (user != null) {
            user.linkWithCredential(EmailAuthProvider.getCredential(email, password)).await()
        } else authService.signUp(email, password).await()


        authService.currentUser()?.updateProfile(
            UserProfileChangeRequest
                .Builder()
                .setDisplayName(username)
                .build()
        )

        user = result.user

        return@withContext if (user == null) null else firebaseMapper.map(user)
    }

    override suspend fun signInAnonymously() = withContext(dispatchers.IO) {
        val result = authService.signInAnonymously().await()
        Logger.d(result)
        return@withContext (result != null)
    }

    override suspend fun checkLogin() = withContext(dispatchers.IO) {
        val user = authService.currentUser()
        Logger.d(user)
        return@withContext if (user == null) null else firebaseMapper.map(user)
    }

    override suspend fun signOut() = withContext(dispatchers.IO) {
        authService.signOut()
        return@withContext true
    }

    override suspend fun signInWithGoogle(data: Intent?): User? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account = task.getResult(ApiException::class.java)
        return firebaseAuthWithGoogle(account)
    }

    private suspend fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?): User? {
        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        val result = authService.signInWithCredential(credential).await()
        val user = result.user
        return if (user == null) null else googleMapper.map(acct!!)
    }
}