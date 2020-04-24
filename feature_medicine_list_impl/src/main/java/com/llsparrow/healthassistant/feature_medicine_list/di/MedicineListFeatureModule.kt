package com.llsparrow.healthassistant.feature_medicine_list.di

import android.content.Context
import androidx.room.Room
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig
import com.llsparrow.healthassistant.feature_medicine_list.data.room.db.MedicineDatabase
import dagger.Module
import dagger.Provides

@Module
internal abstract class MedicineListFeatureModule {

    @Module
    companion object {

        @JvmStatic
        @FeatureScope
        @Provides
        fun provideDatabase(context: Context, appConfig: AppConfig): MedicineDatabase {
            val builder = Room.databaseBuilder(context, MedicineDatabase::class.java, MedicineDatabase.DB_NAME)
                .fallbackToDestructiveMigrationOnDowngrade()
            if (appConfig.isDebug()) {
                builder.allowMainThreadQueries()
            }
            return builder.build()
        }

        @JvmStatic
        @FeatureScope
        @Provides
        fun provideMedicineDao(db: MedicineDatabase) = db.medicineDao()
    }
}