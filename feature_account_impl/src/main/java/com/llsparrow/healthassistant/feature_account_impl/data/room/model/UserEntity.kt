package com.llsparrow.healthassistant.feature_account_impl.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.llsparrow.core_util.room.DateConverter
import com.llsparrow.healthassistant.feature_account_api.domain.model.Sex
import com.llsparrow.healthassistant.feature_account_impl.data.room.converter.SexConverter
import java.util.*

@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "user_id")
    var userId: String? = null,

    @ColumnInfo(name = "login")
    var login: String? = null,

    @ColumnInfo(name = "first_name")
    var firstName: String? = null,

    @ColumnInfo(name = "last_name")
    var lastName: String? = null,

    @ColumnInfo(name = "photo_path")
    var photoPath: String? = null,

    @ColumnInfo(name = "document_photo_path")
    var documentPhotoPath: String? = null,

    @param:TypeConverters(DateConverter::class)
    @ColumnInfo(name = "date_of_birth")
    var dateOfBirth: Date? = null,

    @param:TypeConverters(SexConverter::class)
    @ColumnInfo(name = "sex")
    var sex: Sex? = null,

    @ColumnInfo(name = "email")
    var email: String? = null
)
