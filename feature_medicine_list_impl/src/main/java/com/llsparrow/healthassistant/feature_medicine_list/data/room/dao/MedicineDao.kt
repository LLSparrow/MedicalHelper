package com.llsparrow.healthassistant.feature_medicine_list.data.room.dao

import androidx.room.*
import com.llsparrow.core_base_api.data.BaseEntityDao
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntity
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineWithImageEntity

@Dao
abstract class MedicineDao : BaseEntityDao<MedicineEntity>() {

    @Transaction
    @Query("SELECT * FROM medicine WHERE title = :title")
    abstract suspend fun getMedicineByTitle(title: String): MedicineWithImageEntity

    @Transaction
    @Query("SELECT * FROM medicine")
    abstract suspend fun getMedicineList(): List<MedicineWithImageEntity>
}