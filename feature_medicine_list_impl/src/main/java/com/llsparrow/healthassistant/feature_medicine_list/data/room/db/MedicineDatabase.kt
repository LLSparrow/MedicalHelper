package com.llsparrow.healthassistant.feature_medicine_list.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntity
import com.llsparrow.healthassistant.feature_medicine_list.data.room.dao.MedicineDao


@Database(
    entities = [MedicineEntity::class],
    version = MedicineDatabase.VERSION,
    exportSchema = false
)
internal abstract class MedicineDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {

        const val DB_NAME = "medicine_db"

        const val VERSION = 1
    }
}