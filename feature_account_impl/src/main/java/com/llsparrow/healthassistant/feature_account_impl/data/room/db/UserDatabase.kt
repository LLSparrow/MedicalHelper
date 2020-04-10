package com.llsparrow.healthassistant.feature_account_impl.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.llsparrow.core_util.room.DateConverter
import com.llsparrow.healthassistant.feature_account_impl.data.room.converter.SexConverter
import com.llsparrow.healthassistant.feature_account_impl.data.room.dao.UserDao
import com.llsparrow.healthassistant.feature_account_impl.data.room.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = UserDatabase.VERSION,
    exportSchema = false
)
@TypeConverters(value = [SexConverter::class, DateConverter::class])
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "user_db"

        const val VERSION = 1
    }
}
