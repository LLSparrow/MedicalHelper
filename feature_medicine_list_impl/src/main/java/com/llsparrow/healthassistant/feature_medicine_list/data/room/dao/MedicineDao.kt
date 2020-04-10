package com.llsparrow.healthassistant.feature_medicine_list.data.room.dao

import androidx.room.*
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntity

@Dao
interface MedicineDao {
// WHERE title MATCH 'pul*' AND rank MATCH 'bm25(10.0, 1.0)' ORDER BY rank

    @Query("SELECT * FROM medicine WHERE title = :title")
    suspend fun getMedicineByTitle(title: String): MedicineEntity

    @Query("SELECT * FROM medicine")
    suspend fun getMedicineList(): List<MedicineEntity>

    @Delete
    suspend fun deleteMedicine(entity: MedicineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMedicine(entity: MedicineEntity): Long
}