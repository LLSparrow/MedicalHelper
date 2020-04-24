package com.llsparrow.healthassistant.feature_medicine_list.data.room.dao

import androidx.room.*
import com.llsparrow.core_base_api.data.BaseEntityDao
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntity

@Dao
abstract class MedicineDao : BaseEntityDao<MedicineEntity>() {

    @Query("SELECT * FROM medicine WHERE title = :title")
    abstract suspend fun getMedicineByTitle(title: String): MedicineEntity

    @Query("SELECT * FROM medicine")
    abstract suspend fun getMedicineList(): List<MedicineEntity>
}