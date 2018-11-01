package com.oldsenior.ella.medicinelist.data

import com.oldsenior.ella.medicinelist.di.MedicalListScope
import retrofit2.Retrofit
import javax.inject.Inject

@MedicalListScope
class Repository @Inject constructor(retrofit: Retrofit)