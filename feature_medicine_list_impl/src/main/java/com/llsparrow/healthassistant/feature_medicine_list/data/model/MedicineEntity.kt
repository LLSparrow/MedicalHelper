package com.llsparrow.healthassistant.feature_medicine_list.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.llsparrow.core_base_api.data.BaseEntity
import java.util.*

@Entity(tableName = "medicine")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "amount") val amount: Int = 0,
    @ColumnInfo(name = "expirationDate") val expirationDate: Date? = null,
    @Relation(parentColumn = "id", entityColumn = "medicine_id") val image: MedicineImageEntity? = null
) : BaseEntity