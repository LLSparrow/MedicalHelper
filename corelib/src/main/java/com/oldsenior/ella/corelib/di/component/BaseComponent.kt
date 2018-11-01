package com.oldsenior.ella.corelib.di.component

import android.content.Context
import com.oldsenior.ella.corelib.io.AppExecutors
import dagger.Subcomponent
import retrofit2.Retrofit

@Subcomponent
interface BaseComponent {

    val retrofit: Retrofit

    val appExecutors: AppExecutors

    @Subcomponent.Builder
    interface Builder {
        fun build(): BaseComponent
    }
}