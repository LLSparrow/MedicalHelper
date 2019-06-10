package com.oldsenior.ella.authlib.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun onLoginSuccess() {
        //AuthRouter.sendResult(FlowResult.OK)
    }
}
