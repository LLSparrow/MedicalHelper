package com.oldsenior.ella.medicinelist.view

import android.content.Context
import android.os.Bundle
import com.oldsenior.ella.core_ui.view.BaseActivity
import javax.inject.Inject

class MedicalListMenuActivity : BaseActivity() {

    @Inject
    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
