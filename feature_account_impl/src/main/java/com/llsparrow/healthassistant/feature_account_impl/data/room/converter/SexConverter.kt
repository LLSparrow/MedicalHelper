package com.llsparrow.healthassistant.feature_account_impl.data.room.converter

import androidx.room.TypeConverter
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.Sex
import javax.inject.Inject

@FeatureScope
class SexConverter @Inject constructor() {

    companion object {
        private const val MALE = "MALE"
    }

    @TypeConverter
    fun fromInteger(sexInt: Int?): Sex {
        return if (sexInt == 0) Sex.MALE else Sex.FEMALE
    }

    @TypeConverter
    fun fromSex(sex: Sex?): Int? {
        return if (sex == Sex.MALE) 0 else 1
    }

    @TypeConverter
    fun fromString(sex: String?): Sex {
        return if (sex == MALE) Sex.MALE else Sex.FEMALE
    }
}