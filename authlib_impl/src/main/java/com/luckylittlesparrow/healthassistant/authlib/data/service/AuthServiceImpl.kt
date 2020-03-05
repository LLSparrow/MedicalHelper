package com.luckylittlesparrow.healthassistant.authlib.data.service

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class AuthServiceImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthService {
    override fun signIn(email: String, password: String): Task<AuthResult> = firebaseAuth.signInWithEmailAndPassword(email, password)

    override fun signInWithCredential(credential: AuthCredential) = firebaseAuth.signInWithCredential(credential)

    override fun currentUser(): FirebaseUser? = firebaseAuth.currentUser

    override fun signIn(): FirebaseUser? = firebaseAuth.currentUser

    override fun signInAnonymously(): Task<AuthResult?> = firebaseAuth.signInAnonymously()

    override fun signUp(email: String, password: String): Task<AuthResult> = firebaseAuth.createUserWithEmailAndPassword(email, password)

    override fun signOut() = firebaseAuth.signOut()

}