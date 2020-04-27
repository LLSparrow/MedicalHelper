package com.llsparrow.healthassistant.feature_medicine_list.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.llsparrow.core_util.room.DateConverter
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntity
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntityFts
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineImageEntity
import com.llsparrow.healthassistant.feature_medicine_list.data.room.dao.MedicineDao


@Database(
    entities = [
        MedicineEntity::class,
        MedicineEntityFts::class,
        MedicineImageEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [DateConverter::class])
internal abstract class MedicineDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {

        const val DB_NAME = "medicine_db"
    }
}