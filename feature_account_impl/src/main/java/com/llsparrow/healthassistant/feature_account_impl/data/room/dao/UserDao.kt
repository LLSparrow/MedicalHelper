package com.llsparrow.healthassistant.feature_account_impl.data.room.dao

import androidx.room.*
import com.llsparrow.healthassistant.feature_account_impl.data.room.model.UserEntity

@Dao
abstract class UserDao {
//    @get:Query("SELECT * FROM user LIMIT 1")
//    abstract val user: Maybe<UserEntity>
//
//    @Update
//    abstract fun update(userEntity: UserEntity): Completable
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(userEntity: UserEntity): Completable
//
//    @Delete
//    abstract fun delete(userEntity: UserEntity): Completable
//
//    @Query("DELETE FROM user")
//    abstract fun clearInternal()
//
//    fun clear(): Completable {
//        return Completable.fromAction { clearInternal() }
//    }
}